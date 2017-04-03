package net.gcicom.cdr.processor.common;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import net.gcicom.cdr.processor.ImportEventsLoaderInitializer;
import net.gcicom.cdr.processor.config.WebConfig;

@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = WebConfig.class)
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
