/**
 * 
 */
package net.gcicom.order.processor.entity.input;

import java.time.LocalDateTime;

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
	
	@DataField(pos = 2, required = false)
	String itemType;
	
	@DataField(pos = 3, required = false)
	String customerName;
	
	@DataField(pos = 4, required = false)
	String accountNumber;
	
	@DataField(pos = 5, required = false)
	String nodeName;
		
	@DataField(pos = 6, required = false)
	String orderNumber;
	

	@DataField(pos = 7, required = false)
	String serviceCode;
	
	@DataField(pos = 8, required = false)
	String billingReference;

	@DataField(pos = 9, required = false)
	String billingReferenceDesc;
	
	@DataField(pos = 10, required = false)
	String eventTariffName;
	
	@DataField(pos = 11, required = false)
	String gciSalesManager;
	
	@DataField(pos = 12, required = false)
	LocalDateTime customerServiceStartDate;
	
	@DataField(pos=13, required=false)
	LocalDateTime customerServiceEndDate;
	
	
	@DataField(pos = 14, required = false)
	LocalDateTime supplierContractStartDate;
	
	@DataField(pos=15, required=false)
	LocalDateTime supplierContractEndDate;
	
	@DataField(pos=16, required=false)
	LocalDateTime customerContractStartDate;
	
	@DataField(pos=17, required=false)
	LocalDateTime customerContractEndDate;
	
	@DataField(pos=18, required=false)
	String customerSiteName;

	@DataField(pos=19, required=false)
	String customerCustomReference;
	
	
	@DataField(pos=20, required=false)
	String customerCostCentre;
	
	@DataField(pos=21, required=false)
	String customerPONumber;
	
	@DataField(pos=22, required=false)
	String installationPostCode;
	
	@DataField(pos=23, required=false)
	String supplierOrderNumber;
	
	@DataField(pos=24, required=false)
	String supplierServiceReference;
	
	@DataField(pos=25, required=false)
	String productCode;
	
	@DataField(pos=26, required=false)
	String description;
	
	@DataField(pos=27, required=false)
	String customerReference;
	
	@DataField(pos=28, required=false)
	String chargeOrderNumber;
	
	@DataField(pos=29, required=false)
	String quantity;
	
	@DataField(pos=30, required=false)
	String chargeFrequency;
	
	@DataField(pos=31, required=false)
	String unitCostToGCI;
	
	@DataField(pos=32, required=false)
	String unitChargeToCustomer;
	
	@DataField(pos=33, required=false)
	String taxTypeFlag;
	
	@DataField(pos=34, required=false)
	LocalDateTime chargeStartDate;
	
	@DataField(pos=35, required=false)
	LocalDateTime chargeCeaseDate;
	
	@DataField(pos=36, required=false)
	LocalDateTime chargeBilledUntilDate;
	
	@DataField(pos=37, required=false)
	LocalDateTime chargeSupplierContractStartDate;
	
	@DataField(pos=38, required=false)
	LocalDateTime chargeSupplierContractEndDate;
	
	@DataField(pos=39, required=false)
	LocalDateTime chargeCustomerContractStartDate;
	
	@DataField(pos=40, required=false)
	LocalDateTime chargeCustomerContractEndDate;
	
	@DataField(pos=41, required=false)
	String chargeID;
		
	String exceptionMessage;



	public LocalDateTime getCustomerServiceStartDate() {
		return customerServiceStartDate;
	}

	public void setCustomerServiceStartDate(LocalDateTime customerServiceStartDate) {
		this.customerServiceStartDate = customerServiceStartDate;
	}
	
	
	public LocalDateTime getCustomerServiceEndDate() {
		return customerServiceEndDate;
	}

	public void setCustomerServiceEndDate(LocalDateTime customerServiceEndDate) {
		this.customerServiceEndDate = customerServiceEndDate;
	}

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
	
	
	public LocalDateTime getChargeSupplierContractStartDate() {
		return supplierContractStartDate;
	}

	public void setChargeSupplierContractStartDate(LocalDateTime chargeSupplierContractStartDate) {
		this.chargeSupplierContractStartDate = chargeSupplierContractStartDate;
	}

	public LocalDateTime getSupplierContractEndDate() {
		return supplierContractEndDate;
	}

	public void setSupplierContractEndDate(LocalDateTime supplierContractEndDate) {
		this.supplierContractEndDate = supplierContractEndDate;
	}

	public LocalDateTime getCustomerContractStartDate() {
		return customerContractStartDate;
	}

	public void setCustomerContractStartDate(LocalDateTime customerContractStartDate) {
		this.customerContractStartDate = customerContractStartDate;
	}

	public LocalDateTime getCustomerContractEndDate() {
		return customerContractEndDate;
	}

	public void setCustomerContractEndDate(LocalDateTime customerContractEndDate) {
		this.customerContractEndDate = customerContractEndDate;
	}

	public String getCustomerSiteName() {
		return customerSiteName;
	}

	public void setCustomerSiteName(String customerSiteName) {
		this.customerSiteName = customerSiteName;
	}

	public String getCustomerCustomReference() {
		return customerCustomReference;
	}

	public void setCustomerCustomReference(String customerCustomReference) {
		this.customerCustomReference = customerCustomReference;
	}

	public String getCustomerCostCentre() {
		return customerCostCentre;
	}

	public void setCustomerCostCentre(String customerCostCentre) {
		this.customerCostCentre = customerCostCentre;
	}

	public String getCustomerPONumber() {
		return customerPONumber;
	}

	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}

	public String getInstallationPostCode() {
		return installationPostCode;
	}

	public void setInstallationPostCode(String installationPostCode) {
		this.installationPostCode = installationPostCode;
	}

	public String getSupplierOrderNumber() {
		return supplierOrderNumber;
	}

	public void setSupplierOrderNumber(String supplierOrderNumber) {
		this.supplierOrderNumber = supplierOrderNumber;
	}

	public String getSupplierServiceReference() {
		return supplierServiceReference;
	}

	public void setSupplierServiceReference(String supplierServiceReference) {
		this.supplierServiceReference = supplierServiceReference;
	}
	
	

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public String getChargeOrderNumber() {
		return chargeOrderNumber;
	}

	public void setChargeOrderNumber(String chargeOrderNumber) {
		this.chargeOrderNumber = chargeOrderNumber;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getChargeFrequency() {
		return chargeFrequency;
	}

	public void setChargeFrequency(String chargeFrequency) {
		this.chargeFrequency = chargeFrequency;
	}

	public String getUnitCostToGCI() {
		return unitCostToGCI;
	}

	public void setUnitCostToGCI(String unitCostToGCI) {
		this.unitCostToGCI = unitCostToGCI;
	}

	public String getUnitChargeToCustomer() {
		return unitChargeToCustomer;
	}

	public void setUnitChargeToCustomer(String unitChargeToCustomer) {
		this.unitChargeToCustomer = unitChargeToCustomer;
	}

	public String getTaxTypeFlag() {
		return taxTypeFlag;
	}

	public void setTaxTypeFlag(String taxTypeFlag) {
		this.taxTypeFlag = taxTypeFlag;
	}

	public LocalDateTime getChargeStartDate() {
		return chargeStartDate;
	}

	public void setChargeStartDate(LocalDateTime chargeStartDate) {
		this.chargeStartDate = chargeStartDate;
	}

	public LocalDateTime getChargeCeaseDate() {
		return chargeCeaseDate;
	}

	public void setChargeCeaseDate(LocalDateTime chargeCeaseDate) {
		this.chargeCeaseDate = chargeCeaseDate;
	}

	public LocalDateTime getChargeBilledUntilDate() {
		return chargeBilledUntilDate;
	}

	public void setChargeBilledUntilDate(LocalDateTime chargeBilledUntilDate) {
		this.chargeBilledUntilDate = chargeBilledUntilDate;
	}

	public LocalDateTime getSupplierContractStartDate() {
		return supplierContractStartDate;
	}

	public void setSupplierContractStartDate(LocalDateTime supplierContractStartDate) {
		this.supplierContractStartDate = supplierContractStartDate;
	}

	public LocalDateTime getChargeSupplierContractEndDate() {
		return chargeSupplierContractEndDate;
	}

	public void setChargeSupplierContractEndDate(LocalDateTime chargeSupplierContractEndDate) {
		this.chargeSupplierContractEndDate = chargeSupplierContractEndDate;
	}

	public LocalDateTime getChargeCustomerContractStartDate() {
		return chargeCustomerContractStartDate;
	}

	public void setChargeCustomerContractStartDate(LocalDateTime chargeCustomerContractStartDate) {
		this.chargeCustomerContractStartDate = chargeCustomerContractStartDate;
	}

	public LocalDateTime getChargeCustomerContractEndDate() {
		return chargeCustomerContractEndDate;
	}

	public void setChargeCustomerContractEndDate(LocalDateTime chargeCustomerContractEndDate) {
		this.chargeCustomerContractEndDate = chargeCustomerContractEndDate;
	}

	public String getChargeID() {
		return chargeID;
	}

	public void setChargeID(String chargeID) {
		this.chargeID = chargeID;
	}



	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}



	String supplier = "GCI";
	
}
