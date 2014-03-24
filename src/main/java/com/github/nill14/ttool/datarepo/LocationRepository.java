package com.github.nill14.ttool.datarepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.Location;

public interface LocationRepository extends JpaRepository<Location, String> {

	List<Location> findByPostalCode(String postalCode);
}
