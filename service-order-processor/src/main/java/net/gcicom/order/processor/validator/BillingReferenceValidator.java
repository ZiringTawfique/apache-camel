package net.gcicom.order.processor.validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.order.processor.entity.input.ChargeImportDto;
import net.gcicom.order.processor.service.InvalidRecordException;
import net.gcicom.order.processor.service.RecordAlreadyExistsException;

public class BillingReferenceValidator {

	
		
	public static void billingReferenceNullValidations(ChargeImportDto source) throws InvalidRecordException{
		
		if(source.getActionCode() == null)
			throw new InvalidRecordException("Fail - No Action Code" );
		
		if(source.getItemType() == null)
			throw new InvalidRecordException("Fail - No Itemtype" );
		
		if(source.getCustomerName() == null)
			throw new InvalidRecordException("Fail - No Customer Name" );
		
		if(source.getAccountNumber() == null)
			throw new InvalidRecordException("Fail - No Account Number" );
		
		if(source.getNodeName() == null)
			throw new InvalidRecordException("Fail - No Node Name" );
		
		if(source.getServiceCode() == null)
		throw new InvalidRecordException("Fail - No Service Code" );
		
		if(source.getBillingReference() == null)
			throw new InvalidRecordException("Fail - No Billing Reference " );
		
		if(source.getBillingReferenceDesc() == null)
			throw new InvalidRecordException("Fail - No Billing Reference Description" );
		
		if(source.getCustomerServiceStartDate() == null)
			throw new InvalidRecordException("Fail - No  Customer Service Start Date" );
		
	}
	
	
	public static boolean billingReferenceValidation(List<BillingReference> billingReferenceList,
			ChargeImportDto source) {

		boolean isValidBillingReference = Boolean.FALSE;

		LocalDateTime inputBillingReferenceStartDate = source.getCustomerServiceStartDate();
		LocalDateTime inputBillingReferenceEndDate = source.getCustomerServiceEndDate();

		int loopCount = 0;
		for (BillingReference billingReferenceEntity : billingReferenceList) {

			LocalDateTime billingReferenceStartDate = billingReferenceEntity.getBillingReferenceStartDate();
			LocalDateTime billingReferenceEndDate = billingReferenceEntity.getBillingReferenceEndDate();

			
			//  inputBillingReferenceStartDate NOT NULL
			//  inputBillingReferenceEndDate   NOT NULL
			//  billingReferenceStartDate     NOT NULL
			//  billingReferenceEndDate       NOT NULL
			
			if(billingReferenceStartDate != null && billingReferenceEndDate != null){  //When both billingReferenceStartDate and billingReferenceEndDate exist
				
				if(inputBillingReferenceStartDate != null && inputBillingReferenceEndDate != null) {  //When both inputBillingReferenceStartDate and inputBillingReferenceEndDate exist
		                    	
					       
					           if ((inputBillingReferenceStartDate.isAfter(billingReferenceStartDate) 	&& inputBillingReferenceStartDate.isAfter(billingReferenceEndDate))
					        		  ||
					        		(inputBillingReferenceStartDate.isBefore(billingReferenceStartDate) && inputBillingReferenceStartDate.isBefore(billingReferenceEndDate))  )	                     
					           {
		                   				                   			                   
			                	   
			                   if ((inputBillingReferenceEndDate.isAfter(billingReferenceStartDate) && inputBillingReferenceEndDate.isAfter(billingReferenceEndDate)) 
			                		   ||
			                		   (inputBillingReferenceEndDate.isBefore(billingReferenceStartDate)&& inputBillingReferenceEndDate.isBefore(billingReferenceEndDate)))
			                   {      

					                  isValidBillingReference=Boolean.TRUE;
					           }
				           } //ENd of Inner If condition
				      }
			      }
			
			
	         if(billingReferenceStartDate != null && billingReferenceEndDate == null){  //When both billingReferenceStartDate and billingReferenceEndDate exist
				
				if(inputBillingReferenceStartDate != null && inputBillingReferenceEndDate != null) {  //When both inputBillingReferenceStartDate and inputBillingReferenceEndDate exist
		                    	
					       
					           if ((inputBillingReferenceStartDate.isAfter(billingReferenceStartDate) 	&& inputBillingReferenceEndDate.isAfter(billingReferenceStartDate))
					        		   ||
					        		(inputBillingReferenceStartDate.isBefore(billingReferenceStartDate) && inputBillingReferenceEndDate.isBefore(billingReferenceStartDate))  )	                     
					           {
		                   				
					                  isValidBillingReference=Boolean.TRUE;
					          
				           } //ENd of Inner If condition
				      }
				else if(inputBillingReferenceStartDate != null && inputBillingReferenceEndDate == null){
					
					
					  if ((inputBillingReferenceStartDate.isAfter(billingReferenceStartDate) 	|| inputBillingReferenceStartDate.isBefore(billingReferenceStartDate)))
			        		
                  				                   			                   
						  isValidBillingReference=Boolean.FALSE;   
	                  
				        
				
				
			      } //End of else condition
			}

	                //End of for loop	
             }
		return isValidBillingReference;
	}
	
	
	
	
	public static void billingReferenceDateValidations(ChargeImportDto source) throws InvalidRecordException{
		
		if (source.getCustomerServiceStartDate().isBefore(source.getSupplierContractStartDate())	)	        		  
				throw new InvalidRecordException("Fail - BillingReferenceStartDate cannot be before SupplierContractStartDate" );

		if (source.getCustomerContractStartDate().isBefore(source.getSupplierContractStartDate())	)	        		  
			throw new InvalidRecordException("Fail - CustomerContractStartDate cannot be before SupplierContractStartDate" );
       
	
      if (source.getSupplierContractEndDate().isBefore(source.getSupplierContractStartDate())	)	        		  
		throw new InvalidRecordException("Fail - SupplierContractEndDate cannot be before SupplierContractStartDate" );
      
      if (source.getCustomerContractEndDate().isBefore(source.getCustomerContractStartDate())	)	        		  
			throw new InvalidRecordException("Fail - CustomerContractEndDate cannot be before CustomerContractStartDate" );   
      
      
      if (source.getCustomerContractStartDate().isBefore(source.getSupplierContractStartDate())	)	        		  
			throw new InvalidRecordException("Fail - CustomerContractStartDate cannot be before SupplierContractStartDate" );
      
    }
	
	
	
	public static void billingReferenceAllowedCharactersCheck (ChargeImportDto source) throws InvalidRecordException{
		
		if(!((StringUtils.isBlank(source.getAccountNumber()) && StringUtils.isEmpty(source.getAccountNumber())))  && ! Pattern.matches("^[A-Za-z0-9]+$", source.getAccountNumber()))
			throw new InvalidRecordException("Fail - Invalid Account Number" );
		
		if(!((StringUtils.isBlank(source.getNodeName()) && StringUtils.isEmpty(source.getNodeName())))  && ! Pattern.matches("^[A-Za-z0-9]+$", source.getNodeName()))
			throw new InvalidRecordException("Fail - Invalid Node Name" );				
	       
		if(!((StringUtils.isBlank(source.getServiceCode()) && StringUtils.isEmpty(source.getServiceCode())))  && ! Pattern.matches("^[A-Za-z0-9.._-]+$", source.getServiceCode()))
			throw new InvalidRecordException("Fail - Invalid Service Code" );
	
		
		if(!((StringUtils.isBlank(source.getBillingReference()) && StringUtils.isEmpty(source.getBillingReference())))  && !  Pattern.matches("^[A-Za-z0-9..@_-]+$", source.getBillingReference()))
			throw new InvalidRecordException("Fail - Invalid Billing Reference" );
		
		if(!((StringUtils.isBlank(source.getBillingReferenceDesc()) && StringUtils.isEmpty(source.getBillingReferenceDesc())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getBillingReferenceDesc()))
			throw new InvalidRecordException("Fail - Invalid Billing Reference Description" );
		
		
		if(!((StringUtils.isBlank(source.getGciSalesManager()) && StringUtils.isEmpty(source.getGciSalesManager())))  && !  Pattern.matches("^[A-Za-z.\\s_-]+$", source.getGciSalesManager()))
			throw new InvalidRecordException("Fail - Invalid GCI Sales Manager" );
						
		if(!((StringUtils.isBlank(source.getCustomerSiteName()) && StringUtils.isEmpty(source.getCustomerSiteName())))  && ! Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerSiteName()))
			throw new InvalidRecordException("Fail - Invalid Billing Customer SiteName" );
		
		if(!((StringUtils.isBlank(source.getCustomerCustomReference()) && StringUtils.isEmpty(source.getCustomerCustomReference())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerCustomReference()))
			throw new InvalidRecordException("Fail - Invalid Customer Custom Reference" );
		
		if(!((StringUtils.isBlank(source.getCustomerCostCentre()) && StringUtils.isEmpty(source.getCustomerCostCentre())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerCostCentre()))
			throw new InvalidRecordException("Fail - Invalid Customer Customer Cost Centre" );
		

		if(!((StringUtils.isBlank(source.getCustomerPONumber()) && StringUtils.isEmpty(source.getCustomerPONumber())))  && !  Pattern.matches("^[A-Za-z0-9.\\s).//@\\\\_(-£$]+$", source.getCustomerPONumber()))
			throw new InvalidRecordException("Fail - Invalid Customer PO Number" );
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
	
}

