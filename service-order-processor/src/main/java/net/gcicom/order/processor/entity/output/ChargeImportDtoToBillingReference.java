package net.gcicom.order.processor.entity.output;



import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_COUNT;
import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_PROCESSED_COUNT;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.allspark.CustomerProductCharge;
import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.repository.BillingReferenceRepository;
import net.gcicom.order.processor.service.RecordAlreadyExistsException;


@Component
public class ChargeImportDtoToBillingReference extends BaseEntity {
			
	@Autowired
	BillingReferenceRepository billingReferenceRepo;
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		Logger logger = LoggerFactory.getLogger(ChargeImportDtoToBillingReference.class);
	     DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	
				
			public List<BillingReference> convertToBillingReference(final List<ChargeImportDto> input, Exchange ex) throws ParseException, RecordAlreadyExistsException {
				
				List<BillingReference> cdrs = new ArrayList<>();
                  int loopCount=0; 
				for (ChargeImportDto source : input) {
					loopCount++;
					ex.getIn().setHeader(TOTAL_RECORD_PROCESSED_COUNT, loopCount++);
					
					
					logger.debug("Converting ChargeImportDto to Billing reference" + source.toString());

					BillingReference result = billingReferenceRepo.findByBillingReference(source.getBillingReference());
					if(result == null){
					
					BillingReference billingReference = new BillingReference();
					//TODO get correct ID
					billingReference.setBillingReferenceID(111111L);		
					billingReference.setAccountNumber(source.getAccountNumber());
					billingReference.setOrderNumber(source.getOrderNumber());
					billingReference.setServiceCode(source.getServiceCode());
					billingReference.setBillingReference(source.getBillingReference());
					billingReference.setBillingReferenceDescription(source.getBillingReferenceDesc());
					billingReference.setGCISalesManager(source.getGciSalesManager());
					
					billingReference.setBillingReferenceCreateUser(source.getItemType());
					billingReference.setCustomerCostCentre(source.getActionCode());
					LocalDateTime time = LocalDateTime.from(LocalDate.parse("22/04/2017", formatter).atStartOfDay());
					billingReference.setBillingReferenceStartDate(time);
					billingReference.setBillingReferenceEndDate(time);
					//billingReference.setBillingReferenceEndDate(LocalDateTime.from(LocalDate.parse(source.getCustomerServiceEndDate(),formatter),atStartOfDay()));
					
					billingReference.setSupplierContractEndDate(time);
					billingReference.setSupplierContractStartDate(time);
				  
				 	billingReference.setCustomerContractStartDate(time);
				 	billingReference.setCustomerContractEndDate(time);
				 	
				 	billingReference.setCustomerSiteName(source.getCustomerSiteName());
				 	billingReference.setCustomerCustomReference(source.getCustomerCustomReference());
				 	billingReference.setCustomerCostCentre(source.getCustomerCostCentre());
				 	billingReference.setCustomerPONumber(source.getCustomerPONumber());
				 	
				 	billingReference.setInstallationPostCode(source.getInstallationPostCode());
				 	billingReference.setSupplierOrderNumber(source.getSupplierOrderNumber());
				 	billingReference.setSupplierServiceReference(source.getSupplierServiceReference());
				 	billingReference.setCustomerID(2222L);
				 	
				 	//Default data
				 	billingReference.setAccountNumber("2222");
				 	billingReference.setBillingReference("22222");
				 	billingReference.setBillingReferenceDescription("22222");
				 
				 	List<CustomerProductCharge> CustomerProductChargeList= new ArrayList<>();
				 	
				 	CustomerProductCharge customerproductCharge= new CustomerProductCharge();
				 	customerproductCharge.setCustomerID(12345L);
				 	customerproductCharge.setBillingReference(billingReference);
				 	//TODO
				  //customerproductCharge.setCustomerProductChargeID(Long.valueOf(source.getProductCode()));
				 	
				 	//Default data
				 	//customerproductCharge.setChargeInstanceDescription(source.getDescription());				 	
				 //	customerproductCharge.setCustomerCustomReference(source.getCustomerReference());			

				 //	customerproductCharge.setOrderNumber(source.getOrderNumber());
				 //TODO	
				   //customerproductCharge.setChargeQuantity(Long.parseLong(source.getQuantity()));
				 	//TODO
				 	//customerproductCharge.setProductChargeFrequencyID(Short.valueOf(source.getChargeFrequency()));
				 	//customerproductCharge.setUnitCostToGCI(new BigDecimal(source.getUnitCostToGCI()));
				 	//	customerproductCharge.setUnitChargeToCustomer(new BigDecimal(source.getUnitChargeToCustomer()));
				 	
				 //	customerproductCharge.setChargeTaxTypeFlag(Short.valueOf(source.getTaxTypeFlag()));
				 	customerproductCharge.setChargeStartDate(time);
				 	customerproductCharge.setChargeEndDate(time);
				 	customerproductCharge.setChargeBilledUntil(time);
				 	
				 	customerproductCharge.setSupplierContractStartDate(time);
				 	customerproductCharge.setSupplierContractEndDate(time);
				 	
				 	customerproductCharge.setCustomerContractStartDate(time);
				 	customerproductCharge.setCustomerContractEndDate(time);
				 	
				 	CustomerProductChargeList.add(customerproductCharge);
				 	billingReference.setCustomerProductCharge(CustomerProductChargeList);
				 	
				 	
					//	billingReference.setSupplierReference_1(source.getSupplier());
					logger.debug("Converted cdr " + customerproductCharge.toString());
					cdrs.add(billingReference);
					}
					else 
						throw new RecordAlreadyExistsException("Record Already exists with this BIlling Reference");
				}
				
				
			return cdrs;
			
			}
	
	
}

