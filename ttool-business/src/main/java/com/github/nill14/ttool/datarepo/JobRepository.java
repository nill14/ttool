package com.github.nill14.ttool.datarepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.Job;

public interface JobRepository extends JpaRepository<Job, String> {

}
