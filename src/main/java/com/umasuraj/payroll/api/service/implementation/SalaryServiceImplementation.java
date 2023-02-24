package com.umasuraj.payroll.api.service.implementation;

import com.umasuraj.payroll.api.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umasuraj.payroll.api.entity.Salary;
import com.umasuraj.payroll.api.payload.SalaryDto;
import com.umasuraj.payroll.api.repository.EmployeeRepository;
import com.umasuraj.payroll.api.repository.SalaryRepository;
import com.umasuraj.payroll.api.service.SalaryService;

/**
 *
 * @author umasuraj
 */

@Service
public class SalaryServiceImplementation implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SalaryDto saveSalary(SalaryDto salaryDto, Long employeeId) {

        Employee employee = this.employeeRepository.findById(employeeId).get();
        
        Salary salary = this.modelMapper
                .map(salaryDto, Salary.class);        
        
        salary.setEmployee(employee);
        employee.setSalary(salary);
        
        Long ctc = salary.getCtc();        
        
        Long basicBeforePF = Double.valueOf(ctc * 0.40).longValue();
        Long basicAfterPF = Double.valueOf(basicBeforePF * 0.76).longValue();
        
        salary.setBasic(basicAfterPF);
        
        Long hra = Double.valueOf(ctc * 0.10).longValue();
        salary.setHra(hra);
        
        Long lta = Double.valueOf(ctc * 0.10).longValue();
        salary.setLta(lta);
        
        Long itAllowance = Double.valueOf(ctc * 0.10).longValue();
        salary.setItAllowance(itAllowance);
        
        Long medicalAllowance = Double.valueOf(ctc * 0.10).longValue();
        salary.setMedicalAllowance(medicalAllowance);
        
        Long specialAllowance = Double.valueOf(ctc * 0.10).longValue();
        salary.setSpecialAllowance(specialAllowance);
        
        Long transportAllowance = Double.valueOf(ctc * 0.10).longValue();
        salary.setTransportAllowance(transportAllowance);
        
        Long pf = Double.valueOf(basicBeforePF * 0.24).longValue();
        
        Long employerPF = Double.valueOf(pf / 2).longValue();
        salary.setEmployerPF(employerPF);
        
        Long employeePF = Double.valueOf(pf / 2).longValue();
        salary.setEmployeePF(employeePF); 
        
        LocalDateTime salaryAddTimestamp = LocalDateTime.now();
        salary.setAddTimestamp(salaryAddTimestamp);
              
        Salary savedSalary = this.salaryRepository.save(salary);

        SalaryDto savedSalaryDto = this.modelMapper
                .map(savedSalary, SalaryDto.class);

        return savedSalaryDto;
    }
    // end saveUser() method

    @Override
    public SalaryDto updateSalary(SalaryDto salaryDto, Long salaryId) {

        Salary salary = this.salaryRepository.findById(salaryId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        salary.setCtc(salaryDto.getCtc());
        salary.setBasic(salaryDto.getBasic());
        salary.setHra(salaryDto.getHra());
        salary.setLta(salaryDto.getLta());
        salary.setItAllowance(salaryDto.getItAllowance());
        salary.setMedicalAllowance(salaryDto.getMedicalAllowance());
        salary.setSpecialAllowance(salaryDto.getSpecialAllowance());
        salary.setTransportAllowance(salaryDto.getTransportAllowance());
        salary.setEmployerPF(salaryDto.getEmployerPF());
        salary.setEmployeePF(salaryDto.getEmployeePF());
        
        LocalDateTime salaryUpdateTimestamp = LocalDateTime.now();
        salary.setUpdateTimestamp(salaryUpdateTimestamp);
        
        Salary updatedSalary = this.salaryRepository.save(salary);

        SalaryDto updatedSalaryDTO = this.modelMapper
                .map(updatedSalary, SalaryDto.class);

        return updatedSalaryDTO;
    }
    // end updateSalary() method

    @Override
    public SalaryDto getSalaryById(Long salaryId) {

        Salary foundSalary = this.salaryRepository.findById(salaryId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        SalaryDto foundSalaryDto = this.modelMapper
                .map(foundSalary, SalaryDto.class);

        return foundSalaryDto;
    }
    // end findSalaryById() method

    @Override
    public List<SalaryDto> getAllSalary() {

        List<Salary> salaryList = new ArrayList<>();

        salaryList = this.salaryRepository.findAll();

        List<SalaryDto> salaryDtoList = salaryList.stream()
                .map(salary -> this.modelMapper.map(salary, SalaryDto.class))
                .collect(Collectors.toList());

        return salaryDtoList;
    }
    // end getAllSalary() method

    @Override
    public void deleteSalaryById(Long salaryId) {

        Salary foundSalary = this.salaryRepository.findById(salaryId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));
        this.salaryRepository.delete(foundSalary);
    }
    // end deleteSalaryById() method

    @Override
    public SalaryDto getSalaryByEmployeeId(Long employeeId) {
        
        return null;
    }

}
//end class SalaryServiceImplementation{}
