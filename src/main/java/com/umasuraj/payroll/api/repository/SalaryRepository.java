package com.umasuraj.payroll.api.repository;

import com.umasuraj.payroll.api.entity.Employee;
import com.umasuraj.payroll.api.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author umasuraj
 */

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    Salary findByEmployee(Employee employee);
    
    
}
// end interface SalaryRepository{}
