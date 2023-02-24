package com.umasuraj.payroll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umasuraj.payroll.api.payload.SalaryDto;
import com.umasuraj.payroll.api.response.ApiResponse;
import com.umasuraj.payroll.api.response.SalaryResponse;
import com.umasuraj.payroll.api.service.SalaryService;

/**
 *
 * @author umasuraj
 */


@RestController
@RequestMapping("/payroll/api/v1")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;
        
        // POST save salary
	@PostMapping("/employee/{employeeId}/salary/add")
	public ResponseEntity<SalaryResponse> saveSalary(@RequestBody SalaryDto salaryDto, @PathVariable("employeeId") Long employeeId) {
                          
		SalaryDto savedSalaryDto = this.salaryService.saveSalary(salaryDto, employeeId);
                
                SalaryResponse salaryResponse = new SalaryResponse(savedSalaryDto, "SUCCESS", "SALARY_ADDED");

		return new ResponseEntity(salaryResponse, HttpStatus.CREATED);
	}

	// PUT update salary By salary id
	@PutMapping("/salary/update/{salaryId}")
	public ResponseEntity<SalaryDto> updateSalary(@RequestBody SalaryDto salaryDto, @PathVariable("salaryId") Long salaryId) {

		SalaryDto updatedSalaryDto = this.salaryService.updateSalary(salaryDto, salaryId);
		
		return new ResponseEntity(updatedSalaryDto, HttpStatus.ACCEPTED);
	}

	// DELETE delete salary By salary id
	@DeleteMapping("/salary/delete/{salaryId}")
	public ResponseEntity<ApiResponse> deleteSalary(@PathVariable("salaryId") Long salaryId) {

		this.salaryService.deleteSalaryById(salaryId);

		ApiResponse apiResponse = new ApiResponse("SUCCESS", "SALARY_DELETED");
		
		return new ResponseEntity(apiResponse, HttpStatus.OK);

	}

	// GET salary By salary id
	@GetMapping("/salary/get/{salaryId}")
	public ResponseEntity<SalaryDto> getSalary(@PathVariable("salaryId") Long salaryId) {

		SalaryDto foundSalaryDto = this.salaryService.getSalaryById(salaryId);

		return new ResponseEntity<>(foundSalaryDto, HttpStatus.FOUND);
	}

	// GET find all salary list
	@GetMapping("/salary/get/all")
	public ResponseEntity<List<SalaryDto>> getAllSalary() {

		List<SalaryDto> foundSalaryDtoList = this.salaryService.getAllSalary();

		return new ResponseEntity<>(foundSalaryDtoList, HttpStatus.FOUND);
	}
        
        // GET salary By salary id
	@GetMapping("/employee/{employeeId}/salary/get")
	public ResponseEntity<SalaryDto> getSalaryByEmployee(@PathVariable("employeeId") Long employeeId) {

		SalaryDto foundSalaryDto = this.salaryService.getSalaryByEmployeeId(employeeId);

		return new ResponseEntity<>(foundSalaryDto, HttpStatus.FOUND);
	}

}
// end class EmployeeController{}
