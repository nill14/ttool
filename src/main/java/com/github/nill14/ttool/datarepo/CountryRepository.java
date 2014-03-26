package com.github.nill14.ttool.datarepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.Country;

public interface CountryRepository extends CountryRepositoryCustom, JpaRepository<Country, String> {

	Country findByName(String name);
}
