package com.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.EmployeeDetails;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Integer>{

	@Query(value = "select * from employee where to_char(doj,'yyyy-mm')  between :fromYear and :toYear", nativeQuery = true)
	public Optional<List<EmployeeDetails>> getEmployeeData(@Param("fromYear") String fromYear, @Param("toYear") String toYear);

}
