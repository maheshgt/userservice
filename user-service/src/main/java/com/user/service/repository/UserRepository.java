package com.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.service.entity.Employee;

@Repository
public interface UserRepository extends JpaRepository<Employee, Long>{

	Employee findByEmployeeId(Long id);

}
