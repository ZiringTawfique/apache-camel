package net.gcicom.order.processor.validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import net.gcicom.domain.allspark.BillingReference;
import net.gcicom.order.processor.entity.input.ChargeImportDto;

public class BillingReferenceValidator {

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
}

