package com.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.model.EmployeeDetails;
import com.model.EmployeeDetailsVo;
import com.service.TaxService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


/**
 * @author KrishnaVamsi
 *
 */
@RestController
@RequestMapping("api/taxcal/")
public class EmployeeController {

	@Autowired
	TaxService taxServ;

	@PostMapping(value="taxpayers")
	public ResponseEntity<String> perssiteEmp(@Valid @RequestBody EmployeeDetails emp) {
		int empId = taxServ.addEmployee(emp);
		return ResponseEntity.ok("Employee Created with ID :" + empId);
	}
	
	
	@GetMapping("taxpayers/{financialYear}")
	public ResponseEntity<List<EmployeeDetailsVo>> empData(@PathVariable("financialYear") @Pattern(regexp="\\d{4}") String financialYear) {
		List<EmployeeDetailsVo> employeeVoList = taxServ.getEmpData(financialYear);
		return ResponseEntity.ok(employeeVoList);
	}


}
