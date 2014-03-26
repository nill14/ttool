package com.github.nill14.ttool;
/**
 * 
 */


import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.nill14.ttool.datarepo.CountryRepository;
import com.github.nill14.ttool.entity.Country;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class DataRepoTest {

	@Inject
	private CountryRepository countryRepository;
	
	
	@Rule
	public TestWatcher testWatcher = new LoggingTestWatcher();
	
	@Test
	public void testCountryName() {
		Country country = countryRepository.findByName("Canada");
		
		assertEquals("CA", country.getIsoCode());
	}
	
	@Test
	public void testCountry() {
		Country country = countryRepository.findOne("DE");
		
		assertEquals("Germany", country.getName());
		assertEquals("Europe", country.getRegion().getName());
	}
	
	
}
