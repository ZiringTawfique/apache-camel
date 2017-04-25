package net.gcicom.order.processor.entity.output;



import static net.gcicom.order.processor.common.AppConstants.TOTAL_RECORD_PROCESSED_COUNT;

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
import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.exception.InvalidRecordException;
import net.gcicom.order.processor.repository.BillingReferenceRepository;
import net.gcicom.order.processor.service.GCIChargeImportService;
import net.gcicom.order.processor.validator.BillingReferenceValidator;


@Component
public class ChargeImportDtoToBillingReference extends BaseEntity {
			
	@Autowired
	BillingReferenceRepository billingReferenceRepo;
	
	@Autowired
	GCIChargeImportService gciImportService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		Logger logger = LoggerFactory.getLogger(ChargeImportDtoToBillingReference.class);
	     DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	
				
			public List<BillingReference> convertToBillingReference(final List<ChargeImportDto> input, Exchange ex) throws ParseException, InvalidRecordException {
				
				List<BillingReference> cdrs = new ArrayList<>();
                  int loopCount=0; 
				for (ChargeImportDto source : input) {
					loopCount++;
					ex.getIn().setHeader(TOTAL_RECORD_PROCESSED_COUNT, loopCount);
					
					
					logger.debug("Converting ChargeImportDto to Billing reference" + source.toString());

					List<BillingReference> result = billingReferenceRepo.findByBillingReference(String.valueOf(source.getBillingReference()));
					if(result.size() == 0 ){
					
					BillingReference billingReference = new BillingReference();
					//TODO get correct ID
					
					//ALl the field level  validation -NULL checks primarily 
					BillingReferenceValidator.billingReferenceNullValidations(source);
					
					
					//ALl the field level  validation -Allowed characters checks primarily 
					BillingReferenceValidator.billingReferenceAllowedCharactersCheck(source);
					
					//ALl the field level  validation -Date validations checks primarily 
					BillingReferenceValidator.billingReferenceDateValidations(source);
									
					billingReference.setBillingReferenceID(111111L);					
					billingReference.setOrderNumber(String.valueOf(source.getOrderNumber()));
					billingReference.setServiceCode(source.getServiceCode());
					//Default data
				 	billingReference.setAccountNumber(source.getAccountNumber());
				 	billingReference.setBillingReference(source.getBillingReference());
				 	billingReference.setBillingReferenceDescription(source.getBillingReferenceDesc());	
					billingReference.setGCISalesManager(source.getGciSalesManager());
					
					billingReference.setBillingReferenceCreateUser(source.getItemType());
					billingReference.setCustomerCostCentre(source.getActionCode()); 
					
					billingReference.setBillingReferenceStartDate(source.getCustomerServiceStartDate());
					billingReference.setBillingReferenceEndDate(source.getCustomerServiceEndDate());
					//billingReference.setBillingReferenceEndDate(LocalDateTime.from(LocalDate.parse(source.getCustomerServiceEndDate(),formatter),atStartOfDay()));
					
					billingReference.setSupplierContractEndDate(source.getSupplierContractEndDate());
					billingReference.setSupplierContractStartDate(source.getSupplierContractStartDate());
				  
				 	billingReference.setCustomerContractStartDate(source.getCustomerContractStartDate());
				 	billingReference.setCustomerContractEndDate(source.getCustomerContractEndDate());
				 	
				 	billingReference.setCustomerSiteName(source.getCustomerSiteName());
				 	billingReference.setCustomerCustomReference(source.getCustomerCustomReference());
				 	billingReference.setCustomerCostCentre(source.getCustomerCostCentre());
				 	billingReference.setCustomerPONumber(source.getCustomerPONumber());
				 	
				 	billingReference.setInstallationPostCode(source.getInstallationPostCode());
				 	billingReference.setSupplierOrderNumber(source.getSupplierOrderNumber());
				 	billingReference.setSupplierServiceReference(source.getSupplierServiceReference());
				 	billingReference.setCustomerID(2222L);
				 	
				 	
				 
				 	//Code to add the customer product charge
				 	
				 	/*List<CustomerProductCharge> CustomerProductChargeList= new ArrayList<>();
				 	
				 	CustomerProductCharge customerproductCharge= new CustomerProductCharge();
				 	customerproductCharge.setCustomerID(12345L);
				 	customerproductCharge.setBillingReference(billingReference);
				 	
				 	customerproductCharge.setChargeStartDate(time);
				 	customerproductCharge.setChargeEndDate(time);
				 	customerproductCharge.setChargeBilledUntil(time);
				 	
				 	customerproductCharge.setSupplierContractStartDate(time);
				 	customerproductCharge.setSupplierContractEndDate(time);
				 	
				 	customerproductCharge.setCustomerContractStartDate(time);
				 	customerproductCharge.setCustomerContractEndDate(time);
				 	
				 	CustomerProductChargeList.add(customerproductCharge);
				 	billingReference.setCustomerProductCharge(CustomerProductChargeList);
				 	
				 	logger.debug("Converted cdr " + customerproductCharge.toString());
				 	*/
				 	
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

				 	
				 	
					//	billingReference.setSupplierReference_1(source.getSupplier());
					
					//cdrs.add(billingReference);
					//BillingReference billingReferencePersist = billingReferenceRepo.save(billingReference);
				 	
				 	gciImportService.addBillingReference(billingReference);
					//cdrs.add(billingReference);
					}
					else {
					  //=result.getBillingReferenceStartDate();
					  //  =result.getBillingReferenceEndDate();
						
					boolean isValidBillingReference=	BillingReferenceValidator.billingReferenceValidation(result,source);
					if(isValidBillingReference == false)
						ex.setException(new InvalidRecordException("Record Already exists with this BIlling Reference",loopCount,ex)); 
						throw new InvalidRecordException("Record Already exists with this BIlling Reference",loopCount,ex );
					}
				}
				
				
			return cdrs;
			
			}
			

}
