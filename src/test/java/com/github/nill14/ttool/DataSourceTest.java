package com.github.nill14.ttool;
/**
 * 
 */


import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="classpath:/spring/applicationContext-test.xml")
public class DataSourceTest {

	@Rule
	public TestWatcher testWatcher = new LoggingTestWatcher();
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	@Test
	public void testCount() {
		
		Integer regionsCount = jdbcTemplate.queryForObject("SELECT count(*) FROM regions", Integer.class);
		assertTrue("regionsCount != null", regionsCount != null);
		assertTrue("regionsCount > 0", regionsCount > 0);
	}
	
	@Test
	public void testRowSet() {
	
		Object[] params = new Object[] { 1 };
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM regions WHERE RegionID > ?", params);
			
		assertTrue(rowSet.next());
		
		String regionId = rowSet.getString("RegionID");
		assertTrue(regionId != null);
		
		String regionName = rowSet.getString("RegionName");
		assertTrue(regionName != null);
	}
	
	@Test
	public void testRowSet2() {
	
		Object[] params = new Object[] { "CA" };
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM countries WHERE CountryISOCode = ?", params);
			
		assertTrue("Has next", rowSet.next());
		
		String isoCode = rowSet.getString("CountryISOCode");
		assertTrue(isoCode != null);
		
		String name = rowSet.getString("CountryName");
		assertTrue(name != null);
	}	
	
}
