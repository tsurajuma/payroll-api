package com.umasuraj.payroll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umasuraj.payroll.api.entity.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author umasuraj
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
