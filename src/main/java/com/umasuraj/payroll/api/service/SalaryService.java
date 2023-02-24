package com.umasuraj.payroll.api.service;

import com.umasuraj.payroll.api.payload.SalaryDto;
import java.util.List;

/**
 *
 * @author umasuraj
 */

public interface SalaryService {

    public SalaryDto saveSalary(SalaryDto salaryDto, Long employeeId);

    public SalaryDto updateSalary(SalaryDto salaryDto, Long salaryId);

    public SalaryDto getSalaryById(Long salaryId);

    public List<SalaryDto> getAllSalary();

    public void deleteSalaryById(Long salaryId);
    
    public SalaryDto getSalaryByEmployeeId(Long employeeId);

}
// end interface SalaryService{}
