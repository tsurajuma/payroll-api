package com.umasuraj.payroll.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "TABLE_SALARY")
public class Salary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "CTC", nullable = false)
    private Long ctc;

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

    @Column(name = "EMPLOYER_PF")
    private Long employerPF;

    @Column(name = "EMPLOYEE_PF")
    private Long employeePF;

    @Column(name = "ADD_TIMESTAMP")
    private LocalDateTime addTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private LocalDateTime updateTimestamp;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @OneToMany(mappedBy = "salary", cascade = CascadeType.ALL)
    private List<MonthlyPay> monthlyPayList = new ArrayList<>();

}
// end class Salary{}
