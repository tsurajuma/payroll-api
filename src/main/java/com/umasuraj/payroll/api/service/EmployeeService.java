package com.umasuraj.payroll.api.service;

import com.umasuraj.payroll.api.payload.EmployeeDto;
import java.util.List;

/**
 *
 * @author umasuraj
 */

public interface EmployeeService {

	public EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId);
	
	public EmployeeDto getEmployeeById(Long employeeId);
	
	public List<EmployeeDto> getAllEmployee();
	
	public void deleteEmployeeById(Long employeeId);
	
}
// end interface UserService