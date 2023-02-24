package com.umasuraj.payroll.api.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class EmployeeDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Long employeeCode;

    private String address;

    private Long mobileNo;

    private LocalDate birthDate;

    private Long aadhaar;

    private String pan;

    private String email;

    private String ssn;

    private String designation;

    private LocalDate joinDate;

    private LocalDate resignDate;

    private String status;

    private LocalDateTime addTimestamp;

    private LocalDateTime updateTimestamp;

    private SalaryDto salaryDto;

    private List<MonthlyPayDto> monthlyPayDtoList = new ArrayList<>();
}
// end class UserDto{}
