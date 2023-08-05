package com.service;


import java.util.List;

import com.model.EmployeeDetails;
import com.model.EmployeeDetailsVo;




/**
 * @author Krishna Vamsi
 *
 */
public interface TaxService {
	
	
	
 public int addEmployee(EmployeeDetails emp);
	 
	 public List<EmployeeDetailsVo> getEmpData(String financialYear);

}