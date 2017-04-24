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
import java.util.regex.Pattern;

import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.domain.allspark.CustomerProductCharge;
import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.repository.BillingReferenceRepository;
import net.gcicom.order.processor.service.GCIChargeImportService;
import net.gcicom.order.processor.service.RecordAlreadyExistsException;
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

	
				
			public List<BillingReference> convertToBillingReference(final List<ChargeImportDto> input, Exchange ex) throws ParseException, RecordAlreadyExistsException {
				
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
					BillingReferenceNullValidations(source);
					BillingReferenceAllowedCharactersCheck(source);
									
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
					LocalDateTime time = LocalDateTime.from(LocalDate.parse("22/04/2017", formatter).atStartOfDay());
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
						ex.setException(new RecordAlreadyExistsException("Record Already exists with this BIlling Reference",loopCount,ex)); 
						throw new RecordAlreadyExistsException("Record Already exists with this BIlling Reference",loopCount,ex );
					}
				}
				
				
			return cdrs;
			
			}
			
			
		/*	public boolean ValidateBillingReference(BillingReference billingReference)
			{
				boolean isValid = false;
				
			//Validation pseudocode
		//	LocalDateTime today= LocalDateTime.now(); 
			//			today.isBefore(XXXX));
						
						
				returnisValid;
			}
			*/
			
			
			public static void BillingReferenceNullValidations(ChargeImportDto source) throws RecordAlreadyExistsException{
				
				if(source.getActionCode() == null)
					throw new RecordAlreadyExistsException("Fail - No Action Code" );
				
				if(source.getItemType() == null)
					throw new RecordAlreadyExistsException("Fail - No Itemtype" );
				
				if(source.getCustomerName() == null)
					throw new RecordAlreadyExistsException("Fail - No Customer Name" );
				
				if(source.getAccountNumber() == null)
					throw new RecordAlreadyExistsException("Fail - No Account Number" );
				
				if(source.getNodeName() == null)
					throw new RecordAlreadyExistsException("Fail - No Node Name" );
				
				if(source.getServiceCode() == null)
				throw new RecordAlreadyExistsException("Fail - No Service Code" );
				
				if(source.getBillingReference() == null)
					throw new RecordAlreadyExistsException("Fail - No Billing Reference " );
				
				if(source.getBillingReferenceDesc() == null)
					throw new RecordAlreadyExistsException("Fail - No Billing Reference Description" );
				
				if(source.getCustomerServiceStartDate() == null)
					throw new RecordAlreadyExistsException("Fail - No  Customer Service Start Date" );
				
			}
	
			
			
			public static void BillingReferenceAllowedCharactersCheck (ChargeImportDto source) throws RecordAlreadyExistsException{
				
				if(!((StringUtils.isBlank(source.getAccountNumber()) && StringUtils.isEmpty(source.getAccountNumber())))  && ! Pattern.matches("^[A-Za-z0-9]+$", source.getAccountNumber()))
					throw new RecordAlreadyExistsException("Fail - Invalid Account Number" );
				
				if(!((StringUtils.isBlank(source.getNodeName()) && StringUtils.isEmpty(source.getNodeName())))  && ! Pattern.matches("^[A-Za-z0-9]+$", source.getNodeName()))
					throw new RecordAlreadyExistsException("Fail - Invalid Node Name" );				
			       
				if(!((StringUtils.isBlank(source.getServiceCode()) && StringUtils.isEmpty(source.getServiceCode())))  && ! Pattern.matches("^[A-Za-z0-9.._-]+$", source.getServiceCode()))
					throw new RecordAlreadyExistsException("Fail - Invalid Service Code" );
			
				
				if(!((StringUtils.isBlank(source.getBillingReference()) && StringUtils.isEmpty(source.getBillingReference())))  && !  Pattern.matches("^[A-Za-z0-9..@_-]+$", source.getBillingReference()))
					throw new RecordAlreadyExistsException("Fail - Invalid Billing Reference" );
				
				if(!((StringUtils.isBlank(source.getBillingReferenceDesc()) && StringUtils.isEmpty(source.getBillingReferenceDesc())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getBillingReferenceDesc()))
					throw new RecordAlreadyExistsException("Fail - Invalid Billing Reference Description" );
				
				
				if(!((StringUtils.isBlank(source.getGciSalesManager()) && StringUtils.isEmpty(source.getGciSalesManager())))  && !  Pattern.matches("^[A-Za-z.\\s_-]+$", source.getGciSalesManager()))
					throw new RecordAlreadyExistsException("Fail - Invalid GCI Sales Manager" );
								
				if(!((StringUtils.isBlank(source.getCustomerSiteName()) && StringUtils.isEmpty(source.getCustomerSiteName())))  && ! Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerSiteName()))
					throw new RecordAlreadyExistsException("Fail - Invalid Billing Customer SiteName" );
				
				if(!((StringUtils.isBlank(source.getCustomerCustomReference()) && StringUtils.isEmpty(source.getCustomerCustomReference())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerCustomReference()))
					throw new RecordAlreadyExistsException("Fail - Invalid Customer Custom Reference" );
				
				if(!((StringUtils.isBlank(source.getCustomerCostCentre()) && StringUtils.isEmpty(source.getCustomerCostCentre())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerCostCentre()))
					throw new RecordAlreadyExistsException("Fail - Invalid Customer Customer Cost Centre" );
				

				if(!((StringUtils.isBlank(source.getCustomerPONumber()) && StringUtils.isEmpty(source.getCustomerPONumber())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerPONumber()))
					throw new RecordAlreadyExistsException("Fail - Invalid Customer PO Number" );
			/*
			 * Will be uncommented when implementing for charge import are added
			 *  
				if(!((StringUtils.isBlank(source.getInstallationPostCode()) && StringUtils.isEmpty(source.getInstallationPostCode())))  && !  Pattern.matches("^[A-Za-z.\\s]+$", source.getInstallationPostCode()))
					throw new RecordAlreadyExistsException("Fail - Invalid Installation PostCode" );
									
				if(!((StringUtils.isBlank(source.getSupplierOrderNumber()) && StringUtils.isEmpty(source.getSupplierOrderNumber())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getSupplierOrderNumber()))
					throw new RecordAlreadyExistsException("Fail - Invalid Supplier Order Number" );
				
				if(!((StringUtils.isBlank(source.getSupplierServiceReference()) && StringUtils.isEmpty(source.getSupplierServiceReference())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getSupplierServiceReference()))
					throw new RecordAlreadyExistsException("Fail - Invalid Supplier Service Reference" );
					
					*/
			}
			
			
			
			
			
			public static void BillingReferenceDateValidations(ChargeImportDto source) throws RecordAlreadyExistsException{
				
				if (source.getCustomerServiceStartDate().isBefore(source.getSupplierContractStartDate())	)	        		  
						throw new RecordAlreadyExistsException("Fail - BillingReferenceStartDate cannot be before SupplierContractStartDate" );
	
				if (source.getCustomerContractStartDate().isBefore(source.getSupplierContractStartDate())	)	        		  
					throw new RecordAlreadyExistsException("Fail - CustomerContractStartDate cannot be before SupplierContractStartDate" );
	           
			
		      if (source.getSupplierContractEndDate().isBefore(source.getSupplierContractStartDate())	)	        		  
				throw new RecordAlreadyExistsException("Fail - SupplierContractEndDate cannot be before SupplierContractStartDate" );
		      
		      if (source.getCustomerContractEndDate().isBefore(source.getCustomerContractStartDate())	)	        		  
					throw new RecordAlreadyExistsException("Fail - CustomerContractEndDate cannot be before CustomerContractStartDate" );   
		      
              
		      if (source.getCustomerContractStartDate().isBefore(source.getSupplierContractStartDate())	)	        		  
					throw new RecordAlreadyExistsException("Fail - CustomerContractStartDate cannot be before SupplierContractStartDate" );
		      
            }
			
		

}
