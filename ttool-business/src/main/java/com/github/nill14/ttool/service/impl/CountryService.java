package com.github.nill14.ttool.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.nill14.ttool.datarepo.CountryRepository;
import com.github.nill14.ttool.entity.Country;
import com.github.nill14.ttool.service.ICountryService;
import com.google.common.collect.ImmutableList;

@Service
public class CountryService implements ICountryService {

	@Inject
	private CountryRepository repo; 
	
	@Override
	@Transactional
	public List<Country> getAllCountries() {
		return ImmutableList.copyOf(repo.findAllWithRegions());
	}

}
