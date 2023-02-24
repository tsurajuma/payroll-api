package com.umasuraj.payroll.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TABLE_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "ADD_TIMESTAMP")
    private LocalDateTime addTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private LocalDateTime updateTimestamp;

}
// end class User{}
