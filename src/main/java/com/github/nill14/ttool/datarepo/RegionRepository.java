package com.github.nill14.ttool.datarepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

	Region findByName(String name);
}
