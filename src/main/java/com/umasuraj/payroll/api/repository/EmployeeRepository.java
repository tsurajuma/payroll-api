package com.umasuraj.payroll.api.repository;

import com.umasuraj.payroll.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author umasuraj
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
