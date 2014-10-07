package com.github.nill14.ttool.datarepo;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.github.nill14.ttool.entity.Country;

@NoRepositoryBean
public interface CountryRepositoryCustom {

	List<Country> findAllWithRegions();
	
}
