package net.gcicom.cdr.processor.entity.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Sathish Natarajan
 *
 *
 * 
 *
 */
@Entity(name = "gci_charge_import")
@Table(name = "gci_charge_import")
public class GCIChargeImport extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public CDRKey getId() {
		return id;
	}
	

	public void setId(CDRKey id) {
		this.id = id;
	}

	
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
    @Override
    public boolean equals(Object o) {
    	
        if (this == o) return true;
        if (!(o instanceof GCIChargeImport)) return false;
        GCIChargeImport output = (GCIChargeImport) o;
        return Objects.equals(getId(), output.getId());
       
    }
 
    @Override
    public int hashCode() {
    	
        return Objects.hash(getId());
    }
    
	@Override
	public String toString() {

	/*	return "[GCR CDR :id- " + this.id + " supplierId - " + this.supplierId + " supplierCost " 
					+ this.supplierCost + " supplierAccountCode " + this.supplierAccountCode 
					+ " timePeriod " + this.timePeriod + " callType " + this.callType 
					+ " callBand " + this.callBand + " duration " + this.duration +
					" accountCode " + this.accountCode + " ]";*/
		return "[GCR CDR :id- " + this.id + " accountCode " + this.accountCode + " ]";
	}

	@EmbeddedId
    private CDRKey id;
    
	
	@Column(name = "account_code")
	String accountCode;
	
	public String getAccountCode() {
		return accountCode;
	}


	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}


	@Column(name = "supplier_id")
	String supplierId;

}
