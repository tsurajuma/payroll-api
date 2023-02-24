package com.umasuraj.payroll.api.service;

import java.util.List;

import com.umasuraj.payroll.api.payload.MonthlyPayDto;

/**
 *
 * @author umasuraj
 */

public interface MonthlyPayService {

    public MonthlyPayDto saveMonthlyPay(MonthlyPayDto monthlyPayDto, Long salaryId);

    public MonthlyPayDto updateMonthlyPay(MonthlyPayDto monthlyPayDto, Long monthlyPayId);

    public MonthlyPayDto getMonthlyPayById(Long userId);

    public List<MonthlyPayDto> getAllMonthlyPay();

    public void deleteMonthlyPayById(Long monthlyPayId);
    
    public List<MonthlyPayDto> getAllMonthlyPayBySalary(Long salaryId);
    
    public List<MonthlyPayDto> getAllMonthlyPayByEmployee(Long employeeId);
    
    public List<MonthlyPayDto> getAllMonthlyPayByEmployeeByYear(Long employeeId, Integer year);
    
    public MonthlyPayDto getAllMonthlyPayByEmployeeByYearByMonth(Long employeeId, Integer year, String month);
    
    public void getSalarySlip(Long employeeId, Long monthlyPayId);

}
// end interface MonthlyPayService{}
