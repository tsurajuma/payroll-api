package com.umasuraj.payroll.api.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "TABLE_EMPLOYEE")
public class Employee implements Serializable {

    // private static final long serialVersionUID = 0;   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMPLOYEE_CODE", unique = true, nullable = false)
    private Long employeeCode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MOBILE_NO")
    private Long mobileNo;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "AADHAAR")
    private Long aadhaar;

    @Column(name = "PAN")
    private String pan;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "JOIN_DATE")
    private LocalDate joinDate;

    @Column(name = "RESIGN_DATE")
    private LocalDate resignDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ADD_TIMESTAMP")
    private LocalDateTime addTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private LocalDateTime updateTimestamp;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Salary salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<MonthlyPay> monthlyPayList = new ArrayList<>();

}
// end class Employee()
