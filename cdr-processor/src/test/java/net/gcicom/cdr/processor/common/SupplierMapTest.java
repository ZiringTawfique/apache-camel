package net.gcicom.cdr.processor.common;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigurationPackage
@ComponentScan(basePackages = {"net.gcicom.cdr.processor.common"})
public class SupplierMapTest {


	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSupplierName() {
		
		String s = SupplierMap.getSupplierName("CDRM_EDGE_BTO_CDR_20170218051504_1.dat");
		
		assertEquals(SupplierMap.PSTN_BTO, s);
	}

}
