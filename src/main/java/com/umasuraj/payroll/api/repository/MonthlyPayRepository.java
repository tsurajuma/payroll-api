package com.umasuraj.payroll.api.repository;

import com.umasuraj.payroll.api.entity.Employee;
import com.umasuraj.payroll.api.entity.MonthlyPay;
import com.umasuraj.payroll.api.entity.Salary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author umasuraj
 */

@Repository
public interface MonthlyPayRepository extends JpaRepository<MonthlyPay, Long> {

    List<MonthlyPay> findByEmployee(Employee employee);
    
    List<MonthlyPay> findBySalary(Salary salary);
    
}
// end interface MonthlyPayRepository{}