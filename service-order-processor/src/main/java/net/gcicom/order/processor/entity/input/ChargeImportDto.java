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
	String customerServiceStartDate;
	
	@DataField(pos=13, required=false)
	String customerServiceEndDate;
	
	
	@DataField(pos = 14, required = false)
	String supplierContractStartDate;
	
	@DataField(pos=15, required=false)
	String supplierContractEndDate;
	
	@DataField(pos=16, required=false)
	String customerContractStartDate;
	
	@DataField(pos=17, required=false)
	String customerContractEndDate;
	
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
	String ProductCode;
	
	@DataField(pos=26, required=false)
	String Description;
	
	@DataField(pos=27, required=false)
	String CustomerReference;
	
	@DataField(pos=28, required=false)
	String OrderNumber;
	
	@DataField(pos=29, required=false)
	String Quantity;
	
	@DataField(pos=30, required=false)
	String ChargeFrequency;
	
	@DataField(pos=31, required=false)
	String UnitCostToGCI;
	
	@DataField(pos=32, required=false)
	String UnitChargeToCustomer;
	
	@DataField(pos=33, required=false)
	String TaxTypeFlag;
	
	@DataField(pos=34, required=false)
	String ChargeStartDate;
	
	@DataField(pos=35, required=false)
	String ChargeCeaseDate;
	
	@DataField(pos=36, required=false)
	String ChargeBilledUntilDate;
	
	@DataField(pos=37, required=false)
	String SupplierContractStartDate;
	
	@DataField(pos=38, required=false)
	String SupplierContractEndDate;
	
	@DataField(pos=39, required=false)
	String CustomerContractStartDate;
	
	@DataField(pos=40, required=false)
	String CustomerContractEndDate;
	
	@DataField(pos=41, required=false)
	String ChargeID;
	




	public String getCustomerServiceStartDate() {
		return customerServiceStartDate;
	}

	public void setCustomerServiceStartDate(String customerServiceStartDate) {
		this.customerServiceStartDate = customerServiceStartDate;
	}
	
	
	public String getCustomerServiceEndDate() {
		return customerServiceEndDate;
	}

	public void setCustomerServiceEndDate(String customerServiceEndDate) {
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
	
	
	public String getSupplierContractStartDate() {
		return supplierContractStartDate;
	}

	public void setSupplierContractStartDate(String supplierContractStartDate) {
		this.supplierContractStartDate = supplierContractStartDate;
	}

	public String getSupplierContractEndDate() {
		return supplierContractEndDate;
	}

	public void setSupplierContractEndDate(String supplierContractEndDate) {
		this.supplierContractEndDate = supplierContractEndDate;
	}

	public String getCustomerContractStartDate() {
		return customerContractStartDate;
	}

	public void setCustomerContractStartDate(String customerContractStartDate) {
		this.customerContractStartDate = customerContractStartDate;
	}

	public String getCustomerContractEndDate() {
		return customerContractEndDate;
	}

	public void setCustomerContractEndDate(String customerContractEndDate) {
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
		return ProductCode;
	}

	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCustomerReference() {
		return CustomerReference;
	}

	public void setCustomerReference(String customerReference) {
		CustomerReference = customerReference;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getChargeFrequency() {
		return ChargeFrequency;
	}

	public void setChargeFrequency(String chargeFrequency) {
		ChargeFrequency = chargeFrequency;
	}

	public String getUnitCostToGCI() {
		return UnitCostToGCI;
	}

	public void setUnitCostToGCI(String unitCostToGCI) {
		UnitCostToGCI = unitCostToGCI;
	}

	public String getUnitChargeToCustomer() {
		return UnitChargeToCustomer;
	}

	public void setUnitChargeToCustomer(String unitChargeToCustomer) {
		UnitChargeToCustomer = unitChargeToCustomer;
	}

	public String getTaxTypeFlag() {
		return TaxTypeFlag;
	}

	public void setTaxTypeFlag(String taxTypeFlag) {
		TaxTypeFlag = taxTypeFlag;
	}

	public String getChargeStartDate() {
		return ChargeStartDate;
	}

	public void setChargeStartDate(String chargeStartDate) {
		ChargeStartDate = chargeStartDate;
	}

	public String getChargeCeaseDate() {
		return ChargeCeaseDate;
	}

	public void setChargeCeaseDate(String chargeCeaseDate) {
		ChargeCeaseDate = chargeCeaseDate;
	}

	public String getChargeBilledUntilDate() {
		return ChargeBilledUntilDate;
	}

	public void setChargeBilledUntilDate(String chargeBilledUntilDate) {
		ChargeBilledUntilDate = chargeBilledUntilDate;
	}

	public String getChargeID() {
		return ChargeID;
	}

	public void setChargeID(String chargeID) {
		ChargeID = chargeID;
	}




	String supplier = "GCI";
	
}
