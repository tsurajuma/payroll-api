package com.umasuraj.payroll.api.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author umasuraj
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TABLE_MONTHLY_PAY")
public class MonthlyPay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "UNIQUE_MONTHLY_PAY_ID", unique = true)
    private String uniqueMonthlyPayId;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Column(name = "MONTH", nullable = false)
    private String month;

    @Column(name = "DAYS_WORKED")
    private Long daysWorked;

    @Column(name = "BASIC")
    private Long basic;

    @Column(name = "HRA")
    private Long hra;

    @Column(name = "LTA")
    private Long lta;

    @Column(name = "IT_ALLOWANCE")
    private Long itAllowance;

    @Column(name = "MEDICAL_ALLOWANCE")
    private Long medicalAllowance;

    @Column(name = "SPECIAL_ALLOWANCE")
    private Long specialAllowance;

    @Column(name = "TRANSPORT_ALLOWANCE")
    private Long transportAllowance;

    @Column(name = "BONUS", nullable = false)
    private Long bonus;

    @Column(name = "PROFESSIONAL_TAX", nullable = false)
    private Long professionalTax;

    @Column(name = "EMPLOYER_PF")
    private Long employerPF;

    @Column(name = "EMPLOYEE_PF")
    private Long employeePF;

    @Column(name = "GROSS_EARNING")
    private Long grossEarning;

    @Column(name = "GROSS_DEDUCTION")
    private Long grossDeduction;

    @Column(name = "NET_EARNING")
    private Long netEarning;

    @Column(name = "ADD_TIMESTAMP")
    private LocalDateTime addTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private LocalDateTime updateTimestamp;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "SALARY_ID")
    private Salary salary;

}
// end class MonthlyPay{}
