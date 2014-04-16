package com.github.nill14.ttool;
/**
 * 
 */


import static org.junit.Assert.*;

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
@ContextConfiguration(locations="classpath:/spring/jdbc-context.xml")
public class ScriptTest {

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
	public void testScript() {
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SCRIPT NODATA TO 'HR-script.sql'");
		while(rowSet.next()) {
			/*String str = */rowSet.getString(1);
//			System.out.println(str);
		}
		assertTrue(true);
	}
	
	
}
