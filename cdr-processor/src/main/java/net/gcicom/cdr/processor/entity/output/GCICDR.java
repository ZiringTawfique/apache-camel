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
 * Class to represent CDR (Call Details Class) entity
 *
 */
@Entity(name = "gci_cdr")
@Table(name = "gci_cdr")
public class GCICDR extends BaseEntity implements Serializable {

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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCallBand() {
		return callBand;
	}

	public void setCallBand(String callBand) {
		this.callBand = callBand;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierAccountCode() {
		return supplierAccountCode;
	}

	public void setSupplierAccountCode(String supplierAccountCode) {
		this.supplierAccountCode = supplierAccountCode;
	}

	public BigDecimal getSupplierCost() {
		return supplierCost;
	}

	public void setSupplierCost(BigDecimal supplierCost) {
		this.supplierCost = supplierCost;
	}
	
    @Override
    public boolean equals(Object o) {
    	
        if (this == o) return true;
        if (!(o instanceof GCICDR)) return false;
        GCICDR output = (GCICDR) o;
        return Objects.equals(getId(), output.getId());
       
    }
 
    @Override
    public int hashCode() {
    	
        return Objects.hash(getId());
    }
    
	@Override
	public String toString() {

		return "[GCR CDR :id- " + this.id + " supplierId - " + this.supplierId + " supplierCost " 
					+ this.supplierCost + " supplierAccountCode " + this.supplierAccountCode 
					+ " timePeriod " + this.timePeriod + " callType " + this.callType 
					+ " callBand " + this.callBand + " duration " + this.duration + " ]";
	}

	@EmbeddedId
    private CDRKey id;
    
	@Column(name = "duration")
	String duration;
	
	@Column(name = "call_band")
	String callBand;

	@Column(name = "call_type")
	String callType;
	
	@Column(name = "time_period")
	String timePeriod;
	
	@Column(name = "supplier_id")
	String supplierId;

	@Column(name = "supplier_account_code")
	String supplierAccountCode;
	
	@Column(name = "supplier_cost")
	BigDecimal supplierCost;
	
	@Column(name="event_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    Timestamp eventTime;


}
