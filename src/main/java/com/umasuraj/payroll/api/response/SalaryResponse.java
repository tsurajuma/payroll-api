package com.umasuraj.payroll.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.umasuraj.payroll.api.payload.SalaryDto;

/**
 *
 * @author umasuraj
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaryResponse {

    private SalaryDto salaryDto;

    private String status;
    
    private String message;
}
