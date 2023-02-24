package com.umasuraj.payroll.api.utility;

import com.umasuraj.payroll.api.entity.Employee;
import com.umasuraj.payroll.api.entity.MonthlyPay;
import com.umasuraj.payroll.api.repository.EmployeeRepository;
import com.umasuraj.payroll.api.repository.MonthlyPayRepository;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author umasuraj
 */
@Service
public class GeneratePdf {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MonthlyPayRepository monthlyPayRepository;

    public void generateMonthlyPayPDF(Long employeeId, Long monthlyPayId) {

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
        
        MonthlyPayReportAsPDF objMonthlyPayReportAsPDF = new MonthlyPayReportAsPDF();
                flag = objMonthlyPayReportAsPDF.generatePDF(monthlyPayList,
                        parameters, reportTemplateFile, outputReport);

    }
}
