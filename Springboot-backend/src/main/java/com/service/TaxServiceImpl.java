package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.EmployeeDetails;
import com.model.EmployeeDetailsVo;
import com.repo.EmployeeRepository;
import com.util.TaxCalUtility;



@Service
public class TaxServiceImpl implements TaxService {
	
	

	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	TaxCalUtility taxCalUtil;
	@Override
	public int addEmployee(EmployeeDetails emp) {

		return repo.save(emp).getId();
	}
	

	@Override
	public List<EmployeeDetailsVo> getEmpData(String fromYear) {

		List<EmployeeDetailsVo> employVoList = new ArrayList<EmployeeDetailsVo>();

		String toYear = String.valueOf(Integer.parseInt(fromYear) + 1);
		Optional<List<EmployeeDetails>> employee = repo.getEmployeeData(fromYear + "-04-01", toYear + "-03-31");
		if (employee.isPresent()) {
			List<EmployeeDetails> empList = employee.get();
			for (EmployeeDetails emp : empList) {
				EmployeeDetailsVo employVo = new EmployeeDetailsVo();
				employVo.setFirstName(emp.getFirstName());
				employVo.setEmployeeCode(emp.getId());
				employVo.setLastName(emp.getLastName());
				employVo.setYearlSalary(emp.getSal() * 12);
				employVo.setTax(taxCalUtil.taxApplicable(emp.getSal(), emp.getDoj(),fromYear + "-04-01"));
				employVo.setCess(taxCalUtil.cessApplied(emp.getSal() * 12));
				employVoList.add(employVo);
			}

		}

		return employVoList;
	}

	



}

