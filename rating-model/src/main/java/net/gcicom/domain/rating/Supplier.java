package net.gcicom.domain.rating;

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
	private Long supplierID;

	private String supplierName;

	public Supplier() {
	}

	public Long getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(Long supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}