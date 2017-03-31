package net.gcicom.domain.allspark;

import java.io.Serializable;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import javax.persistence.*;



/**
 * The persistent class for the BillingReference database table.
 * 
 */
@Entity
@Table(name = "CustomerProductCharge")
public class CustomerProductCharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerProductChargeID;
	
	public Long getCustomerProductChargeID() {
		return customerProductChargeID;
	}


	public void setCustomerProductChargeID(Long customerProductChargeID) {
		this.customerProductChargeID = customerProductChargeID;
	}



	
	@ManyToOne
	@JoinColumn(name="BillingReferenceID")
	@MapsId
	private BillingReference billingReference;
	




	private Long customerID;

	private String accountNumber;
	
	private String orderNumber;
	

	private Long productID;
	
	private String defaultProductDescription;	

	private String chargeInstanceDescription;
	
	private Long chargeQuantity;
	
	private Short productChargeFrequencyID;
	
	
	private BigDecimal  unitCostToGCI;
	
	private BigDecimal unitChargeToCustomer;
	
	private Short chargeTaxTypeFlag;	
	
	private LocalDateTime chargeStartDate;
	
	private LocalDateTime chargeEndDate;
	
	private LocalDateTime chargeBilledUntil;

	
	private LocalDateTime supplierContractEndDate;

	
	private LocalDateTime supplierContractStartDate;
	
	private LocalDateTime customerContractEndDate;

	private LocalDateTime  customerContractStartDate;
	//TODO
	//private Integer allowCreditBackFlag;

	private String customerCustomReference;

	private LocalDateTime chargeInstanceCreateDate;
	
	
	private String chargeInstanceCreateUser;







	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}





	public Long getProductID() {
		return productID;
	}


	public void setProductID(Long productID) {
		this.productID = productID;
	}


	public String getDefaultProductDescription() {
		return defaultProductDescription;
	}


	


	public void setChargeInstanceDescription(String chargeInstanceDescription) {
		this.chargeInstanceDescription = chargeInstanceDescription;
	}


	public Long getChargeQuantity() {
		return chargeQuantity;
	}


	public void setChargeQuantity(Long chargeQuantity) {
		this.chargeQuantity = chargeQuantity;
	}


	public Short getProductChargeFrequencyID() {
		return productChargeFrequencyID;
	}


	public void setProductChargeFrequencyID(Short productChargeFrequencyID) {
		this.productChargeFrequencyID = productChargeFrequencyID;
	}


	public BigDecimal getUnitCostToGCI() {
		return unitCostToGCI;
	}


	public void setUnitCostToGCI(BigDecimal unitCostToGCI) {
		this.unitCostToGCI = unitCostToGCI;
	}


	public BigDecimal getUnitChargeToCustomer() {
		return unitChargeToCustomer;
	}


	public void setUnitChargeToCustomer(BigDecimal unitChargeToCustomer) {
		this.unitChargeToCustomer = unitChargeToCustomer;
	}


	public Short getChargeTaxTypeFlag() {
		return chargeTaxTypeFlag;
	}


	public void setChargeTaxTypeFlag(Short chargeTaxTypeFlag) {
		this.chargeTaxTypeFlag = chargeTaxTypeFlag;
	}


	public LocalDateTime getChargeStartDate() {
		return chargeStartDate;
	}


	public void setChargeStartDate(LocalDateTime chargeStartDate) {
		this.chargeStartDate = chargeStartDate;
	}


	public LocalDateTime getChargeEndDate() {
		return chargeEndDate;
	}


	public void setChargeEndDate(LocalDateTime chargeEndDate) {
		this.chargeEndDate = chargeEndDate;
	}


	public LocalDateTime getChargeBilledUntil() {
		return chargeBilledUntil;
	}


	public void setChargeBilledUntil(LocalDateTime chargeBilledUntil) {
		this.chargeBilledUntil = chargeBilledUntil;
	}


	public LocalDateTime getSupplierContractEndDate() {
		return supplierContractEndDate;
	}


	public void setSupplierContractEndDate(LocalDateTime supplierContractEndDate) {
		this.supplierContractEndDate = supplierContractEndDate;
	}


	public LocalDateTime getSupplierContractStartDate() {
		return supplierContractStartDate;
	}


	public void setSupplierContractStartDate(LocalDateTime supplierContractStartDate) {
		this.supplierContractStartDate = supplierContractStartDate;
	}


	public LocalDateTime getCustomerContractEndDate() {
		return customerContractEndDate;
	}


	public void setCustomerContractEndDate(LocalDateTime customerContractEndDate) {
		this.customerContractEndDate = customerContractEndDate;
	}


	public LocalDateTime getCustomerContractStartDate() {
		return customerContractStartDate;
	}


	public void setCustomerContractStartDate(LocalDateTime customerContractStartDate) {
		this.customerContractStartDate = customerContractStartDate;
	}


	/*public Integer getAllowCreditBackFlag() {
		return allowCreditBackFlag;
	}


	public void setAllowCreditBackFlag(Integer allowCreditBackFlag) {
		this.allowCreditBackFlag = allowCreditBackFlag;
	}*/


	public String getCustomerCustomReference() {
		return customerCustomReference;
	}


	public void setCustomerCustomReference(String customerCustomReference) {
		this.customerCustomReference = customerCustomReference;
	}


	public LocalDateTime getChargeInstanceCreateDate() {
		return chargeInstanceCreateDate;
	}


	public void setChargeInstanceCreateDate(LocalDateTime chargeInstanceCreateDate) {
		this.chargeInstanceCreateDate = chargeInstanceCreateDate;
	}


	public String getChargeInstanceCreateUser() {
		return chargeInstanceCreateUser;
	}


	public void setChargeInstanceCreateUser(String chargeInstanceCreateUser) {
		this.chargeInstanceCreateUser = chargeInstanceCreateUser;
	}


	public Long getCustomerID() {
		return customerID;
	}


	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public String getChargeInstanceDescription() {
		return chargeInstanceDescription;
	}


	public void setDefaultProductDescription(String defaultProductDescription) {
		this.defaultProductDescription = defaultProductDescription;
	}


	public BillingReference getBillingReference() {
		return billingReference;
	}


	public void setBillingReference(BillingReference billingReference) {
		this.billingReference = billingReference;
	}



	
}