package com.umasuraj.payroll.api.payload;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author umasuraj
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryDto {

    private Long id;

    private Long ctc;

    private Long basic;

    private Long hra;

    private Long lta;

    private Long itAllowance;

    private Long medicalAllowance;

    private Long specialAllowance;

    private Long transportAllowance;

    private Long employerPF;

    private Long employeePF;

    private LocalDateTime addTimestamp;

    private LocalDateTime updateTimestamp;

    private EmployeeDto employeeDto;

    // private List<MonthlyPayDto> monthlyPayDtoList = new ArrayList<>();
}
// end class UserDto{}
