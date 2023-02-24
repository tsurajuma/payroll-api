package com.umasuraj.payroll.api.service.implementation;

import com.umasuraj.payroll.api.entity.Employee;
import com.umasuraj.payroll.api.entity.MonthlyPay;
import com.umasuraj.payroll.api.entity.Salary;
import com.umasuraj.payroll.api.payload.MonthlyPayDto;
import com.umasuraj.payroll.api.repository.EmployeeRepository;
import com.umasuraj.payroll.api.repository.MonthlyPayRepository;
import com.umasuraj.payroll.api.repository.SalaryRepository;
import com.umasuraj.payroll.api.service.MonthlyPayService;
import com.umasuraj.payroll.api.utility.MonthlyPayReportAsPDF;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author umasuraj
 */
@Service
public class MonthlyPayServiceImplementation implements MonthlyPayService {

    @Autowired
    private MonthlyPayRepository monthlyPayRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private MonthlyPayReportAsPDF monthlyPayRportAsPDF;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MonthlyPayDto saveMonthlyPay(MonthlyPayDto monthlyPayDto, Long salaryId) {

        Salary salary = this.salaryRepository.findById(salaryId).get();

        Employee employee = salary.getEmployee();

        String uniqueMonthlyPayId = employee.getEmployeeCode() + "-"
                + monthlyPayDto.getYear() + "-"
                + monthlyPayDto.getMonth();

        MonthlyPay monthlyPay = this.modelMapper
                .map(monthlyPayDto, MonthlyPay.class);
        
        monthlyPay.setSalary(salary);

        monthlyPay.setUniqueMonthlyPayId(uniqueMonthlyPayId);

        Long annualBasic = salary.getBasic();
        Long basic = Double.valueOf(annualBasic / 12).longValue();
        monthlyPay.setBasic(basic);

        Long annualHra = salary.getHra();
        Long hra = Double.valueOf(annualHra / 12).longValue();
        monthlyPay.setHra(hra);

        Long annualLta = salary.getLta();
        Long lta = Double.valueOf(annualLta / 12).longValue();
        monthlyPay.setLta(lta);

        Long annualItAllowance = salary.getItAllowance();
        Long itAllowance = Double.valueOf(annualItAllowance / 12).longValue();
        monthlyPay.setItAllowance(itAllowance);

        Long annualMedicalAllowance = salary.getMedicalAllowance();
        Long medicalAllowance = Double.valueOf(annualMedicalAllowance / 12).longValue();
        monthlyPay.setMedicalAllowance(medicalAllowance);

        Long annualSpecialAllowance = salary.getSpecialAllowance();
        Long specialAllowance = Double.valueOf(annualSpecialAllowance / 12).longValue();
        monthlyPay.setSpecialAllowance(specialAllowance);

        Long annualTransportAllowance = salary.getTransportAllowance();
        Long transportAllowance = Double.valueOf(annualTransportAllowance / 12).longValue();
        monthlyPay.setTransportAllowance(transportAllowance);

        Long annualEmployerPF = salary.getEmployerPF();
        Long employerPF = Double.valueOf(annualEmployerPF / 12).longValue();
        monthlyPay.setEmployerPF(employerPF);

        Long aanualEmployeePF = salary.getEmployeePF();
        Long employeePF = Double.valueOf(aanualEmployeePF / 12).longValue();
        monthlyPay.setEmployeePF(employeePF);

        Long professionalTax = monthlyPay.getProfessionalTax();

        Long grossEarning = basic + hra + lta + itAllowance + medicalAllowance + specialAllowance + transportAllowance;
        monthlyPay.setGrossEarning(grossEarning);

        Long grossDeduction = employerPF + employeePF + professionalTax;
        monthlyPay.setGrossDeduction(grossDeduction);

        Long netEarning = grossEarning - grossDeduction;
        monthlyPay.setNetEarning(netEarning);

        LocalDateTime monthlyPayAddTimestamp = LocalDateTime.now();
        monthlyPay.setAddTimestamp(monthlyPayAddTimestamp);

        monthlyPay.setEmployee(employee);

        MonthlyPay savedMonthlyPay = this.monthlyPayRepository.save(monthlyPay);

        MonthlyPayDto savedMonthlyPayDto = this.modelMapper
                .map(savedMonthlyPay, MonthlyPayDto.class);

        return savedMonthlyPayDto;
    }
    // end saveMonthlyPay() method

    @Override
    public MonthlyPayDto updateMonthlyPay(MonthlyPayDto monthlyPayDto, Long monthlyPayId) {

        MonthlyPay monthlyPay = this.monthlyPayRepository.findById(monthlyPayId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        monthlyPay.setYear(monthlyPayDto.getYear());
        monthlyPay.setMonth(monthlyPayDto.getMonth());
        monthlyPay.setBasic(monthlyPayDto.getBasic());
        monthlyPay.setHra(monthlyPayDto.getHra());
        monthlyPay.setLta(monthlyPayDto.getLta());
        monthlyPay.setItAllowance(monthlyPayDto.getItAllowance());
        monthlyPay.setMedicalAllowance(monthlyPayDto.getMedicalAllowance());
        monthlyPay.setSpecialAllowance(monthlyPayDto.getSpecialAllowance());
        monthlyPay.setTransportAllowance(monthlyPayDto.getTransportAllowance());
        monthlyPay.setEmployerPF(monthlyPayDto.getEmployerPF());
        monthlyPay.setEmployeePF(monthlyPayDto.getEmployeePF());

        LocalDateTime monthlyPayUpdateTimestamp = LocalDateTime.now();
        monthlyPay.setUpdateTimestamp(monthlyPayUpdateTimestamp);

        MonthlyPay updatedMonthlyPay = this.monthlyPayRepository.save(monthlyPay);

        MonthlyPayDto updatedMonthlyPayDTO = this.modelMapper
                .map(updatedMonthlyPay, MonthlyPayDto.class);

        return updatedMonthlyPayDTO;
    }
    // end updateMonthlyPay() method

    @Override
    public MonthlyPayDto getMonthlyPayById(Long monthlyPayId) {

        MonthlyPay foundMonthlyPay = this.monthlyPayRepository.findById(monthlyPayId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        MonthlyPayDto foundMonthlyPayDto = this.modelMapper
                .map(foundMonthlyPay, MonthlyPayDto.class);

        return foundMonthlyPayDto;
    }
    // end findMonthlyPayById() method

    @Override
    public List<MonthlyPayDto> getAllMonthlyPay() {

        List<MonthlyPay> monthlyPayList = new ArrayList<>();

        monthlyPayList = this.monthlyPayRepository.findAll();

        List<MonthlyPayDto> monthlyPayDtoList = monthlyPayList.stream()
                .map(monthlyPay -> this.modelMapper
                .map(monthlyPay, MonthlyPayDto.class))
                .collect(Collectors.toList());

        return monthlyPayDtoList;
    }
    // end getAllMonthlyPay() method

    @Override
    public void deleteMonthlyPayById(Long monthlyPayId) {

        MonthlyPay foundMonthlyPay = this.monthlyPayRepository.findById(monthlyPayId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));
        this.monthlyPayRepository.delete(foundMonthlyPay);
    }
    // end deleteMonthlyPayById() method

    @Override
    public List<MonthlyPayDto> getAllMonthlyPayBySalary(Long salaryId) {

        Salary salary = this.salaryRepository.findById(salaryId).get();

        List<MonthlyPay> monthlyPayList = this.monthlyPayRepository.findBySalary(salary);

        List<MonthlyPayDto> monthlyPayDtoList = monthlyPayList.stream()
                .map((monthlyPay) -> this.modelMapper
                .map(monthlyPay, MonthlyPayDto.class))
                .collect(Collectors.toList());

        return monthlyPayDtoList;
    }

    @Override
    public List<MonthlyPayDto> getAllMonthlyPayByEmployee(Long employeeId) {

        Employee employee = this.employeeRepository.findById(employeeId).get();

        List<MonthlyPay> monthlyPayList = this.monthlyPayRepository.findByEmployee(employee);

        List<MonthlyPayDto> monthlyPayDtoList = monthlyPayList.stream()
                .map((monthlyPay) -> this.modelMapper
                .map(monthlyPay, MonthlyPayDto.class))
                .collect(Collectors.toList());

        return monthlyPayDtoList;
    }

    @Override
    public List<MonthlyPayDto> getAllMonthlyPayByEmployeeByYear(Long employeeId, Integer year) {

        Employee employee = this.employeeRepository.findById(employeeId).get();

        List<MonthlyPay> monthlyPayList = this.monthlyPayRepository.findByEmployee(employee);

        List<MonthlyPayDto> monthlyPayDtoList = monthlyPayList.stream()
                .map((monthlyPay) -> this.modelMapper
                .map(monthlyPay, MonthlyPayDto.class))
                .collect(Collectors.toList());

        return monthlyPayDtoList;
    }

    @Override
    public MonthlyPayDto getAllMonthlyPayByEmployeeByYearByMonth(Long employeeId, Integer year, String month) {

        Employee employee = this.employeeRepository.findById(employeeId).get();

        List<MonthlyPay> monthlyPayList = this.monthlyPayRepository.findByEmployee(employee);
        
        MonthlyPay monthlyPay = null;
        // monthlyPayList.stream().filter(monthlyPay -> month.equals(monthlyPay.getMonth())).findAny().orElse(null);

        MonthlyPayDto monthPayDto = this.modelMapper.map(monthlyPay, MonthlyPayDto.class);
        
        return null;
    }

    @Override
    public void getSalarySlip(Long employeeId, Long monthlyPayId) {
        
        MonthlyPay monthlyPay = this.monthlyPayRepository.findById(monthlyPayId).get();

        List<MonthlyPay> monthlyPayList = new ArrayList<>();

        monthlyPayList.add(monthlyPay);

        String projectDirectory = System.getProperty("user.dir");

        System.out.println("projectDirectory: " + projectDirectory);

        String reportTemplateName = "MonthlyPayReportTemplate.jrxml";

        String separator = File.separator;

        String templateLocation = projectDirectory + "src" + separator + "main" + separator + "resources" + separator + "templates" + separator + "reports" + separator;

        String reportTemplateFile = templateLocation + reportTemplateName;

        String reportName = monthlyPay.getUniqueMonthlyPayId() + "-" + "SALARY-SLIP.pdf";
        System.out.println("reportName = " + reportName);

        String reportPath;
        reportPath = templateLocation + reportName;

        System.out.println(reportPath);

        String outputReport = reportPath;

        Employee employee = this.employeeRepository.findById(employeeId).get();

        String employeeName = employee.getFirstName();
        Long employeeCode = employee.getEmployeeCode();
        Long employeeAadhaar = employee.getAadhaar();
        String employeePan = employee.getPan();
        String employeeSsn = employee.getSsn();

        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("employeeName", employeeName);
        parameters.put("employeeCode", employeeCode);
        parameters.put("employeeAadhaar", employeeAadhaar);
        parameters.put("employeePan", employeePan);
        parameters.put("employeeSsn", employeeSsn);
        
        boolean flag;
        
        flag = this.monthlyPayRportAsPDF.generatePDF(monthlyPayList, 
                parameters, reportTemplateFile, outputReport);
        
    }

}
//end class MonthlyPayServiceImplementation{}
