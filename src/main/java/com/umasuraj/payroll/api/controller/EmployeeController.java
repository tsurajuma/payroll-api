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

import com.umasuraj.payroll.api.payload.EmployeeDto;
import com.umasuraj.payroll.api.response.ApiResponse;
import com.umasuraj.payroll.api.service.EmployeeService;

/**
 *
 * @author umasuraj
 */

@RestController
@RequestMapping("/payroll/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// POST save employee
	@PostMapping("/employee/add")
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {

		EmployeeDto savedEmployeeDto = this.employeeService.saveEmployee(employeeDto);

		return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
	}

	// PUT update employee
	@PutMapping("/employee/update/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("employeeId") Long employeeId) {

		EmployeeDto updatedEmployeeDto = this.employeeService.updateEmployee(employeeDto, employeeId);
		
		return new ResponseEntity(updatedEmployeeDto, HttpStatus.ACCEPTED);
	}

	// DELETE delete employee
	@DeleteMapping("/employee/delete/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("employeeId") Long employeeId) {

		this.employeeService.deleteEmployeeById(employeeId);

		ApiResponse apiResponse = new ApiResponse("SUCCESS", "EMPLOYEE_DELETED");
		
		return new ResponseEntity(apiResponse, HttpStatus.OK);

	}

	// GET  employee
	@GetMapping("/employee/get/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long employeeId) {

		EmployeeDto foundEmployeeDto = this.employeeService.getEmployeeById(employeeId);

		return new ResponseEntity<>(foundEmployeeDto, HttpStatus.FOUND);
	}

	// GET find employee list
	@GetMapping("/employee/get/all")
	public ResponseEntity<List<EmployeeDto>> getAllEmployee() {

		List<EmployeeDto> foundEmployeeDtoList = this.employeeService.getAllEmployee();

		return new ResponseEntity<>(foundEmployeeDtoList, HttpStatus.FOUND);
	}

}
// end class EmployeeController{}
