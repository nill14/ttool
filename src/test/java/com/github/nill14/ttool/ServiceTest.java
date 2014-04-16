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

import com.github.nill14.ttool.service.ICountryService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:/spring/service-context.xml",
		"classpath:/spring/hbm-context.xml",
		"classpath:/spring/jdbc-context.xml"
})
public class ServiceTest {

	@Rule
	public TestWatcher testWatcher = new LoggingTestWatcher();
	
	@Inject
	private ICountryService service;
	
	
	@Test
	public void testService() {
		
		assertNotNull("ICountryService", service);
		assertTrue("service.getAllCountries().size() > 0", service.getAllCountries().size() > 0);
	}
	
	
}
