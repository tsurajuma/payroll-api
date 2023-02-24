package com.umasuraj.payroll.api.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umasuraj.payroll.api.entity.Employee;
import com.umasuraj.payroll.api.payload.EmployeeDto;
import com.umasuraj.payroll.api.repository.EmployeeRepository;
import com.umasuraj.payroll.api.service.EmployeeService;

/**
 *
 * @author umasuraj
 */

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = this.modelMapper
                .map(employeeDto, Employee.class);
        
        LocalDateTime employeeAddTimestamp = LocalDateTime.now();
        employee.setAddTimestamp(employeeAddTimestamp);
        
        Employee savedEmployee = this.employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = this.modelMapper
                .map(savedEmployee, EmployeeDto.class);

        return savedEmployeeDto;
    }
    // end saveUser() method

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {

        Employee employee = this.employeeRepository.findById(employeeId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getFirstName());
        employee.setAddress(employeeDto.getAddress());
        employee.setMobileNo(employeeDto.getMobileNo());
        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setAadhaar(employeeDto.getAadhaar());
        employee.setPan(employeeDto.getPan());
        employee.setEmail(employeeDto.getEmail());
        employee.setSsn(employeeDto.getSsn());
        employee.setEmployeeCode(employeeDto.getEmployeeCode());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setJoinDate(employeeDto.getJoinDate());
        employee.setResignDate(employeeDto.getResignDate());
        employee.setStatus(employeeDto.getStatus());
        
        

        LocalDateTime employeeUpdateTimestamp = LocalDateTime.now();
        employee.setUpdateTimestamp(employeeUpdateTimestamp);

        Employee updatedEmployee = this.employeeRepository.save(employee);

        EmployeeDto updatedEmployeeDTO = this.modelMapper
                .map(updatedEmployee, EmployeeDto.class);

        return updatedEmployeeDTO;
    }
    // end updateEmployee() method

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee foundEmployee = this.employeeRepository.findById(employeeId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        EmployeeDto foundEmployeeDto = this.modelMapper
                .map(foundEmployee, EmployeeDto.class);

        return foundEmployeeDto;
    }
    // end findEmployeeById() method

    @Override
    public List<EmployeeDto> getAllEmployee() {

        List<Employee> employeeList = new ArrayList<>();

        employeeList = this.employeeRepository.findAll();

        List<EmployeeDto> employeeDtoList = employeeList.stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());

        return employeeDtoList;
    }
    // end getAllEmployee() method

    @Override
    public void deleteEmployeeById(Long employeeId) {

        Employee foundEmployee = this.employeeRepository.findById(employeeId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));
        this.employeeRepository.delete(foundEmployee);
    }
    // end deleteEmployeeById() method

}
//end class EmployeeServiceImplementation{}
