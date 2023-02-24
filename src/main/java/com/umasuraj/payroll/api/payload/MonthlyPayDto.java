package com.umasuraj.payroll.api.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author umasuraj
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthlyPayDto {

    private Long id;

    private String uniqueMonthlyPayId;

    private LocalDate date;

    private Integer year;

    private String month;

    private Long daysWorked;

    private Long basic;

    private Long hra;

    private Long lta;

    private Long itAllowance;

    private Long medicalAllowance;

    private Long specialAllowance;

    private Long transportAllowance;

    private Long bonus;

    private Long professionalTax;

    private Long employerPF;

    private Long employeePF;

    private Long grossEarning;

    private Long grossDeduction;

    private Long netEarning;

    private LocalDateTime addTimestamp;

    private LocalDateTime updateTimestamp;

    // @Autowired
    // private EmployeeDto employeeDto;
    
    private SalaryDto salaryDto;
}
// end class UserDto{}
