package com.github.nill14.ttool.datarepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByName(String name);
}
