package net.gcicom.cdr.processor.repository.allspark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import net.gcicom.cdr.processor.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"net.gcicom.cdr.processor", 
		"net.gcicom.common",
		"net.gcicom.domain"})
@ContextConfiguration(classes = WebConfig.class)
public class BillingReferenceRepositoryTest {


	//@Autowired
	//private BillingReferenceRepository repo;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByBillingReferenceAndByBillingReferenceStartDateLessThanEqualAndByBillingReferenceEndDateGreaterThanEqual() {
		//fail("Not yet implemented");
	}
	/*
	@Test
	public void testExample() throws Exception {
	    
		BillingReference bf = new BillingReference();
		bf.setBillingReference(JUNIT);
		LocalDateTime sdt = LocalDateTime.of(2008, 4, 3, 12, 23);
		bf.setBillingReferenceStartDate(sdt);
		
		LocalDateTime edt = LocalDateTime.of(2017, 4, 3, 12, 23);
		bf.setBillingReferenceEndDate(edt);
		
		LocalDateTime test = LocalDateTime.of(2016, 4, 3, 12, 23);

		bf.setAccountNumber("someNumber");
		bf.setCustomerID(new Long(1000));
		
		//entityManager.persist(bf);
	    
	    
	    List<BillingReference> bfs = this.repo.findBillingReferenceDetails(JUNIT, test);
	    
	    
	    assertEquals(JUNIT, bfs.get(0).getBillingReference());
	    assertEquals(new Long(1000), bfs.get(0).getCustomerID());
	}

*/}


