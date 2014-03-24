package com.github.nill14.ttool.datarepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
