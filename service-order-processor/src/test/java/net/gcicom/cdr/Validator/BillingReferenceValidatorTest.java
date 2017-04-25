package net.gcicom.cdr.Validator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.validator.BillingReferenceValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration

public class BillingReferenceValidatorTest {
	
	@Test
    public void billingReferenceValidationTestAllDates() {
		BillingReferenceValidator tester = new BillingReferenceValidator(); // MyClass is tested
		
		List<BillingReference> billingReferenceList =new ArrayList();
		LocalDateTime billingReferenceStartDate = LocalDateTime.of(2012, 07, 20, 14, 30);                            // 2013-12-18T14:30                 
		LocalDateTime billingReferenceEndDate = LocalDateTime.of(2016, 07, 19, 14, 30); 
		
		BillingReference billingReference1= new BillingReference();
		billingReference1.setBillingReferenceStartDate(billingReferenceStartDate);
		billingReference1.setBillingReferenceEndDate(billingReferenceEndDate);
		
		//BillingReference billingReference2= new BillingReference();
		//billingReference1.setBillingReferenceStartDate(billingReferenceStartDate);
		//billingReference1.setBillingReferenceEndDate(billingReferenceEndDate);
		
		billingReferenceList.add(billingReference1);
		
		LocalDateTime customerServiceStartDate = LocalDateTime.of(2017, 01, 10, 14, 30);                            // 2013-12-18T14:30                 
		LocalDateTime customerServiceEndDate = LocalDateTime.of(2017, 03, 31, 14, 30); 
		
		ChargeImportDto source= new ChargeImportDto();
		  source.setCustomerServiceStartDate(customerServiceStartDate);
		  source.setCustomerServiceEndDate(customerServiceEndDate);
		
		
		
		
		boolean isBillingReferenceValid= tester.billingReferenceValidation(billingReferenceList, source);
		
            // assert statements
            assertEquals("10 x 0 must be 0", Boolean.TRUE, isBillingReferenceValid);
	}
	
	
	@Test
    public void billingReferenceValidationTestNOBillingReferenceEndDate() {
		BillingReferenceValidator tester = new BillingReferenceValidator(); // MyClass is tested
		
		List<BillingReference> billingReferenceList =new ArrayList();
		LocalDateTime billingReferenceStartDate = LocalDateTime.of(2017, 04, 01, 14, 30);                            // 2013-12-18T14:30                 
	//	LocalDateTime billingReferenceEndDate = LocalDateTime.of(2016, 07, 19, 14, 30); 
		
		BillingReference billingReference1= new BillingReference();
		billingReference1.setBillingReferenceStartDate(billingReferenceStartDate);
		//billingReference1.setBillingReferenceEndDate(billingReferenceEndDate);
		
		//BillingReference billingReference2= new BillingReference();
		//billingReference1.setBillingReferenceStartDate(billingReferenceStartDate);
		//billingReference1.setBillingReferenceEndDate(billingReferenceEndDate);
		
		billingReferenceList.add(billingReference1);
		
		LocalDateTime customerServiceStartDate = LocalDateTime.of(2017, 01, 10, 14, 30);                            // 2013-12-18T14:30                 
		LocalDateTime customerServiceEndDate = LocalDateTime.of(2017, 03, 31, 14, 30); 
		
		ChargeImportDto source= new ChargeImportDto();
		  source.setCustomerServiceStartDate(customerServiceStartDate);
		  source.setCustomerServiceEndDate(customerServiceEndDate);
		
		
		boolean isBillingReferenceValid= tester.billingReferenceValidation(billingReferenceList, source);
		
		
            // assert statements
            assertEquals("10 x 0 must be 0", Boolean.TRUE, isBillingReferenceValid);
	}
	
	
	@Test
    public void billingReferenceValidationTestNOBillingReferenceEndDateNOCustomerServiceEndDate() {
		BillingReferenceValidator tester = new BillingReferenceValidator(); // MyClass is tested
		
		List<BillingReference> billingReferenceList =new ArrayList();
		LocalDateTime billingReferenceStartDate = LocalDateTime.of(2017, 01, 15, 14, 30);                            // 2013-12-18T14:30                 
	//	LocalDateTime billingReferenceEndDate = LocalDateTime.of(2016, 07, 19, 14, 30); 
		
		BillingReference billingReference1= new BillingReference();
		billingReference1.setBillingReferenceStartDate(billingReferenceStartDate);
		//billingReference1.setBillingReferenceEndDate(billingReferenceEndDate);
		
		//BillingReference billingReference2= new BillingReference();
		//billingReference1.setBillingReferenceStartDate(billingReferenceStartDate);
		//billingReference1.setBillingReferenceEndDate(billingReferenceEndDate);
		
		billingReferenceList.add(billingReference1);
		
		LocalDateTime customerServiceStartDate = LocalDateTime.of(2017, 01, 10, 14, 30);                            // 2013-12-18T14:30                 
		//LocalDateTime customerServiceEndDate = LocalDateTime.of(2017, 03, 31, 14, 30); 
		
		ChargeImportDto source= new ChargeImportDto();
		  source.setCustomerServiceStartDate(customerServiceStartDate);
	//	  source.setCustomerServiceEndDate(customerServiceEndDate);
		
		
		boolean isBillingReferenceValid= tester.billingReferenceValidation(billingReferenceList, source);
		
		
            // assert statements
            assertEquals("10 x 0 must be 0", Boolean.FALSE, isBillingReferenceValid);
	}
	

}
