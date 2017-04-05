package net.gcicom.domain.reference;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the Supplier database table.
 * 
 */
@Entity
@Table(name = "Supplier")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer supplierID;

	@Column(name = "SupplierCode")
	private String supplierName;
	
	private String supplierDescription;
	
	private String supplierNominalCode;
	
	@Column(name = "SupplierHasEventFlag")
	private int hasEventFlag ;


	public Supplier() {
	}

	public Integer getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(Integer supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getSupplierDescription() {
		return supplierDescription;
	}

	public void setSupplierDescription(String supplierDescription) {
		this.supplierDescription = supplierDescription;
	}

	public String getSupplierNominalCode() {
		return supplierNominalCode;
	}

	public void setSupplierNominalCode(String supplierNominalCode) {
		this.supplierNominalCode = supplierNominalCode;
	}

	public int isHasEventFlag() {
		return hasEventFlag;
	}

	public void setHasEventFlag(int hasEventFlag) {
		this.hasEventFlag = hasEventFlag;
	}

}