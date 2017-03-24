package net.gcicom.cdr.processor.entity.mapper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"net.gcicom.cdr.processor.entity.mapper"})
public class BTOpenReachCDRMapperTest {

	@Autowired
	private BTOpenReachCDRMapper mapper;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertToGCICDR() throws Exception {
		
		mapper.convertToGCICDR(null, null, null);
		fail("Not yet implemented");
	}

}
