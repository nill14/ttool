package com.github.nill14.ttool;
/**
 * 
 */


import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.nill14.ttool.entity.*;
import com.github.nill14.ttool.entity.Department;
import com.github.nill14.ttool.entity.Employee;
import com.github.nill14.ttool.entity.Region;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="classpath:/spring/applicationContext-test.xml")
public class EntityTest {

	@PersistenceContext
	private EntityManager em;
	
	@Rule
	public TestWatcher testWatcher = new LoggingTestWatcher();
	
	@Test
	public void testRegionName() {
		TypedQuery<Region> query = em.createQuery("select r from Region r where r.regionId = ?1", Region.class);
		query.setParameter(1, 1);
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
		query.setParameter("regionId", 1);
		Region region = query.getSingleResult();
		
		Country countryUK = region.getCountries().get("UK");
		
		assertTrue(countryUK != null);
	}	
	
	@Test
	public void testEmployees() {
		TypedQuery<Employee> query = em.createQuery("select m from Employee m where exists (" +
				"select e from Employee e where e.manager = m)", Employee.class);
		List<Employee> managers = query.getResultList();
		
		
		assertTrue(managers.size() > 0);
	}
	
	@Test
	public void testDepartments() {
		TypedQuery<Department> query = em.createQuery(
				"select d from Department d join fetch d.employees", Department.class);
		List<Department> departments = query.getResultList();
		
		
		assertTrue(departments.size() > 0);
	}	
	
}
