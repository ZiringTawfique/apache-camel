package net.gcicom.order.processor.entity.output;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.order.processor.entity.input.ChargeImportDto;


@Component
public class ChargeImportDtoToBillingReference {
			
	   SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Logger logger = LoggerFactory.getLogger(ChargeImportDtoToBillingReference.class);

	
		public List<BillingReference> convertToBillingReference(final List<ChargeImportDto> input) throws ParseException {
			
			List<BillingReference> cdrs = new ArrayList<>();

			for (ChargeImportDto source : input) {
				
				logger.debug("Converting ChargeImportDto to Billing reference" + source.toString());

				BillingReference billingReference = new BillingReference();
				//TODO get correct ID
				//billingReference.setBillingReferenceID(111111L);		
				billingReference.setAccountNumber(source.getAccountNumber());
				billingReference.setOrderNumber(source.getOrderNumber());
				billingReference.setServiceCode(source.getServiceCode());
				billingReference.setBillingReference(source.getBillingReference());
				billingReference.setBillingReferenceDescription(source.getBillingReferenceDesc());
				billingReference.setGCISalesManager(source.getGciSalesManager());
				
				
				billingReference.setCustomerContractStartDate(formatter.parse(source.getCustomerServiceStartDate()));
				billingReference.setCustomerContractEndDate(formatter.parse(source.getCustomerServiceEndDate()));
				
				billingReference.setBillingReferenceCreateUser(source.getItemType());
				billingReference.setCustomerCostCentre(source.getActionCode());
			//	billingReference.setBillingReference("TEST BILLING REFERENCE");
			//	billingReference.setBillingReferenceDescription("TEST BILLING REF");
				billingReference.setCustomerID(2222L);
				
				billingReference.setBillingReferenceStartDate(new java.util.Date(System.currentTimeMillis()));
				billingReference.setSupplierContractEndDate(new java.util.Date(System.currentTimeMillis()));
				billingReference.setSupplierContractStartDate(new java.util.Date(System.currentTimeMillis()));
				billingReference.setCustomerContractEndDate(new java.util.Date(System.currentTimeMillis()));
				billingReference.setCustomerContractStartDate(new java.util.Date(System.currentTimeMillis()));
				
			//	billingReference.setSupplierReference_1(source.getSupplier());
				logger.debug("Converted cdr " + billingReference.toString());
				cdrs.add(billingReference);
			}
			
			

			return cdrs;
			
		}
	
	
}
