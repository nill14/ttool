package com.github.nill14.ttool;
/**
 * 
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.nill14.ttool.entity.Country;
import com.github.nill14.ttool.entity.Region;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class EntityTest {

	@PersistenceContext
	private EntityManager em;
	
	@Rule
	public TestWatcher testWatcher = new LoggingTestWatcher();
	
	@Test
	public void testRegionName() {
		TypedQuery<Region> query = em.createQuery("select r from Region r where r.regionId = ?1", Region.class);
		query.setParameter(1, 1L);
		Region region = query.getSingleResult();
		
		assertEquals("Europe", region.getName());
	}
	
	@Test
	public void testCountry() {
		TypedQuery<Country> query = em.createQuery("select c from Country c where c.isoCode = :isoCode", Country.class);
		query.setParameter("isoCode", "DE");
		Country country = query.getSingleResult();
		
		assertEquals("Germany", country.getName());
		assertEquals("Europe", country.getRegion().getName());
	}
	
	@Test
	public void testCountries() {
		TypedQuery<Region> query = em.createQuery("select r from Region r join fetch r.countries where r.regionId = :regionId", Region.class);
		query.setParameter("regionId", 1L);
		Region region = query.getSingleResult();
		
		Country countryUK = Iterables.find(region.getCountries(), new Predicate<Country>() {

			@Override
			public boolean apply(Country input) {
				return "UK".equals(input.getIsoCode());
			}
		}, null);
		
		assertTrue(countryUK != null);
	}	
	
}
