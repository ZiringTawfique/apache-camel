package net.gcicom.cdr.processor.common;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import net.gcicom.cdr.processor.CdrProcessorApplication;
import net.gcicom.cdr.processor.ServletInitializer;


@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"net.gcicom.cdr.processor.common"})
@Profile(value = "local")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class SupplierMapTest {
	
	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSupplierName() {
		
		//String s = SupplierMap.getSupplierName("CDRM_EDGE_BTO_CDR_20170218051504_1.dat");
		
		//assertEquals(SupplierMap.PSTN_BTOPENREACH, s);
	}

}
