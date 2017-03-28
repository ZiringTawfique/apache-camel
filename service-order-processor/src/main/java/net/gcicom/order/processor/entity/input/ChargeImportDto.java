/**
 * 
 */
package net.gcicom.order.processor.entity.input;

import java.time.LocalDate;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * @author Sathish Natarajan
 *
 */
@CsvRecord(separator = ",", skipFirstLine = false)
public class ChargeImportDto {

	@DataField(pos = 1)
	String actionCode;
	
	@DataField(pos = 2, required = true)
	String itemType;
	
	@DataField(pos = 3, required = true)
	String customerName;
	
	@DataField(pos = 4, required = true)
	String accountNumber;
	
	@DataField(pos = 5, required = true)
	String nodeName;
		
	@DataField(pos = 6, required = true)
	String orderNumber;
	
	

	@DataField(pos = 7, required = true)
	String serviceCode;
	
	@DataField(pos = 8, required = true)
	String billingReference;

	@DataField(pos = 9, required = true)
	String billingReferenceDesc;
	
	@DataField(pos = 10, required = true)
	String eventTariffName;
	
	@DataField(pos = 11, required = true)
	String gciSalesManager;
	
	

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
		
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getBillingReference() {
		return billingReference;
	}

	public void setBillingReference(String billingReference) {
		this.billingReference = billingReference;
	}

	public String getBillingReferenceDesc() {
		return billingReferenceDesc;
	}

	public void setBillingReferenceDesc(String billingReferenceDesc) {
		this.billingReferenceDesc = billingReferenceDesc;
	}

	public String getEventTariffName() {
		return eventTariffName;
	}

	public void setEventTariffName(String eventTariffName) {
		this.eventTariffName = eventTariffName;
	}

	public String getGciSalesManager() {
		return gciSalesManager;
	}

	public void setGciSalesManager(String gciSalesManager) {
		this.gciSalesManager = gciSalesManager;
	}
	
	String supplier = "GCI";
	
}
