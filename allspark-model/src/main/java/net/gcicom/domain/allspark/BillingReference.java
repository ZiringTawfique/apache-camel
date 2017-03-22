package net.gcicom.domain.allspark;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BillingReference database table.
 * 
 */
@Entity
@Table(name = "BillingReference")
public class BillingReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long billingReferenceID;

	private String accountNumber;

	private Long assetID;

	private String billingReference;

	@Temporal(TemporalType.TIMESTAMP)
	private Date billingReferenceCreateDate;

	private String billingReferenceCreateUser;

	private String billingReferenceDescription;

	@Temporal(TemporalType.DATE)
	private Date billingReferenceEndDate;

	@Temporal(TemporalType.DATE)
	private Date billingReferenceStartDate;

	@Temporal(TemporalType.DATE)
	private Date customerContractEndDate;

	@Temporal(TemporalType.DATE)
	private Date customerContractStartDate;

	private String customerCostCentre;

	private String customerCustomReference;

	private Long customerID;

	private String customerPONumber;

	private String customerSiteName;

	private String GCICustomField_1;

	private String GCICustomField_2;

	private String GCICustomField_3;

	private String GCISalesManager;

	private String installationPostCode;

	private Long nodeID;

	private String orderNumber;

	@Temporal(TemporalType.DATE)
	private Date supplierContractEndDate;

	@Temporal(TemporalType.DATE)
	private Date supplierContractStartDate;

	private String supplierReference_1;

	private String supplierReference_2;

	private String supplierReference_3;

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

	public Date getBillingReferenceCreateDate() {
		return this.billingReferenceCreateDate;
	}

	public void setBillingReferenceCreateDate(Date billingReferenceCreateDate) {
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

	public Date getBillingReferenceEndDate() {
		return this.billingReferenceEndDate;
	}

	public void setBillingReferenceEndDate(Date billingReferenceEndDate) {
		this.billingReferenceEndDate = billingReferenceEndDate;
	}

	public Date getBillingReferenceStartDate() {
		return this.billingReferenceStartDate;
	}

	public void setBillingReferenceStartDate(Date billingReferenceStartDate) {
		this.billingReferenceStartDate = billingReferenceStartDate;
	}

	public Date getCustomerContractEndDate() {
		return this.customerContractEndDate;
	}

	public void setCustomerContractEndDate(Date customerContractEndDate) {
		this.customerContractEndDate = customerContractEndDate;
	}

	public Date getCustomerContractStartDate() {
		return this.customerContractStartDate;
	}

	public void setCustomerContractStartDate(Date customerContractStartDate) {
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

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getSupplierContractEndDate() {
		return this.supplierContractEndDate;
	}

	public void setSupplierContractEndDate(Date supplierContractEndDate) {
		this.supplierContractEndDate = supplierContractEndDate;
	}

	public Date getSupplierContractStartDate() {
		return this.supplierContractStartDate;
	}

	public void setSupplierContractStartDate(Date supplierContractStartDate) {
		this.supplierContractStartDate = supplierContractStartDate;
	}

	public String getSupplierReference_1() {
		return this.supplierReference_1;
	}

	public void setSupplierReference_1(String supplierReference_1) {
		this.supplierReference_1 = supplierReference_1;
	}

	public String getSupplierReference_2() {
		return this.supplierReference_2;
	}

	public void setSupplierReference_2(String supplierReference_2) {
		this.supplierReference_2 = supplierReference_2;
	}

	public String getSupplierReference_3() {
		return this.supplierReference_3;
	}

	public void setSupplierReference_3(String supplierReference_3) {
		this.supplierReference_3 = supplierReference_3;
	}

}