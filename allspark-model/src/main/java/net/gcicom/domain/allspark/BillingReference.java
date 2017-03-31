package net.gcicom.domain.allspark;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



/**
 * The persistent class for the BillingReference database table.
 * 
 */
@Entity
@NamedQuery(name="BillingReference.findAll", query="SELECT b FROM BillingReference b")
@Table(name = "BillingReference")
public class BillingReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billingReferenceID;
	
	//bi-directional many-to-one association to EventFileDetail
	@OneToMany(mappedBy="billingReference", cascade = CascadeType.ALL)
	private List<CustomerProductCharge> customerProductCharge = new ArrayList<>();
		
	
	private Long customerID;

	private String accountNumber;
	
	private String orderNumber;
	

	private Long nodeID;

	private Long assetID;
	
	private String serviceCode;	

	private String billingReference;
	
	private String billingReferenceDescription;
	
	
	private String billingReferenceCreateUser;

	private LocalDateTime billingReferenceCreateDate;
	
	private LocalDateTime billingReferenceStartDate;

	
	private LocalDateTime billingReferenceEndDate;

	
	private LocalDateTime supplierContractEndDate;

	
	private LocalDateTime supplierContractStartDate;

	
	private LocalDateTime customerContractEndDate;

	private LocalDateTime  customerContractStartDate;
	

	private String customerCostCentre;

	private String installationPostCode;
	
	private String customerCustomReference;

	private String customerPONumber;

	private String customerSiteName;
	
	private String GCISalesManager;

	private String GCICustomField_1;

	private String GCICustomField_2;

	private String GCICustomField_3;

	

	private String supplierOrderNumber;
	private String supplierServiceReference;



	
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

	public String getSupplierOrderNumber() {
		return supplierOrderNumber;
	}

	public void setSupplierOrderNumber(String supplierOrderNumber) {
		this.supplierOrderNumber = supplierOrderNumber;
	}



	public BillingReference() {
	}

	public Long getBillingReferenceID() {
		return this.billingReferenceID;
	}

	public void setBillingReferenceID(Long billingReferenceID) {
		this.billingReferenceID = billingReferenceID;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getAssetID() {
		return this.assetID;
	}

	public void setAssetID(Long assetID) {
		this.assetID = assetID;
	}

	public String getBillingReference() {
		return this.billingReference;
	}

	public void setBillingReference(String billingReference) {
		this.billingReference = billingReference;
	}

	public LocalDateTime getBillingReferenceCreateDate() {
		return this.billingReferenceCreateDate;
	}

	public void setBillingReferenceCreateDate(LocalDateTime billingReferenceCreateDate) {
		this.billingReferenceCreateDate = billingReferenceCreateDate;
	}

	public String getBillingReferenceCreateUser() {
		return this.billingReferenceCreateUser;
	}

	public void setBillingReferenceCreateUser(String billingReferenceCreateUser) {
		this.billingReferenceCreateUser = billingReferenceCreateUser;
	}

	public String getBillingReferenceDescription() {
		return this.billingReferenceDescription;
	}

	public void setBillingReferenceDescription(String billingReferenceDescription) {
		this.billingReferenceDescription = billingReferenceDescription;
	}

	public LocalDateTime getBillingReferenceEndDate() {
		return this.billingReferenceEndDate;
	}

	public void setBillingReferenceEndDate(LocalDateTime billingReferenceEndDate) {
		this.billingReferenceEndDate = billingReferenceEndDate;
	}

	public LocalDateTime getBillingReferenceStartDate() {
		return this.billingReferenceStartDate;
	}

	public void setBillingReferenceStartDate(LocalDateTime billingReferenceStartDate) {
		this.billingReferenceStartDate = billingReferenceStartDate;
	}

	public LocalDateTime getCustomerContractEndDate() {
		return this.customerContractEndDate;
	}

	public void setCustomerContractEndDate(LocalDateTime customerContractEndDate) {
		this.customerContractEndDate = customerContractEndDate;
	}

	public LocalDateTime getCustomerContractStartDate() {
		return this.customerContractStartDate;
	}

	public void setCustomerContractStartDate(LocalDateTime customerContractStartDate) {
		this.customerContractStartDate = customerContractStartDate;
	}

	public String getCustomerCostCentre() {
		return this.customerCostCentre;
	}

	public void setCustomerCostCentre(String customerCostCentre) {
		this.customerCostCentre = customerCostCentre;
	}

	public String getCustomerCustomReference() {
		return this.customerCustomReference;
	}

	public void setCustomerCustomReference(String customerCustomReference) {
		this.customerCustomReference = customerCustomReference;
	}

	public Long getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerPONumber() {
		return this.customerPONumber;
	}

	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}

	public String getCustomerSiteName() {
		return this.customerSiteName;
	}

	public void setCustomerSiteName(String customerSiteName) {
		this.customerSiteName = customerSiteName;
	}

	public String getGCICustomField_1() {
		return this.GCICustomField_1;
	}

	public void setGCICustomField_1(String GCICustomField_1) {
		this.GCICustomField_1 = GCICustomField_1;
	}

	public String getGCICustomField_2() {
		return this.GCICustomField_2;
	}

	public void setGCICustomField_2(String GCICustomField_2) {
		this.GCICustomField_2 = GCICustomField_2;
	}

	public String getGCICustomField_3() {
		return this.GCICustomField_3;
	}

	public void setGCICustomField_3(String GCICustomField_3) {
		this.GCICustomField_3 = GCICustomField_3;
	}

	public String getGCISalesManager() {
		return this.GCISalesManager;
	}

	public void setGCISalesManager(String GCISalesManager) {
		this.GCISalesManager = GCISalesManager;
	}

	public String getInstallationPostCode() {
		return this.installationPostCode;
	}

	public void setInstallationPostCode(String installationPostCode) {
		this.installationPostCode = installationPostCode;
	}

	public Long getNodeID() {
		return this.nodeID;
	}

	public void setNodeID(Long nodeID) {
		this.nodeID = nodeID;
	}


	public LocalDateTime getSupplierContractEndDate() {
		return this.supplierContractEndDate;
	}

	public void setSupplierContractEndDate(LocalDateTime supplierContractEndDate) {
		this.supplierContractEndDate = supplierContractEndDate;
	}

	public LocalDateTime getSupplierContractStartDate() {
		return this.supplierContractStartDate;
	}

	public void setSupplierContractStartDate(LocalDateTime supplierContractStartDate) {
		this.supplierContractStartDate = supplierContractStartDate;
	}

	public List<CustomerProductCharge> getCustomerProductCharge() {
		return customerProductCharge;
	}

	public void setCustomerProductCharge(List<CustomerProductCharge> customerProductCharge) {
		this.customerProductCharge = customerProductCharge;
	}
	
	public String getSupplierServiceReference() {
		return supplierServiceReference;
	}

	public void setSupplierServiceReference(String supplierServiceReference) {
		this.supplierServiceReference = supplierServiceReference;
	}



}