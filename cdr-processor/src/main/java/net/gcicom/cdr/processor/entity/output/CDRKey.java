package net.gcicom.cdr.processor.entity.output;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CDRKey implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 
	@Column(name = "date_time")
	private Timestamp dateTime;
	
	@Column(name = "customer_id")
	private String customerId;
	
	@Column(name = "originating_number")
	private String originatingNumber;

	@Column(name = "dialed_number")
	String dialedNumber;
	
    public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOriginatingNumber() {
		return originatingNumber;
	}

	public void setOriginatingNumber(String originatingNumber) {
		this.originatingNumber = originatingNumber;
	}

	public String getDialedNumber() {
		return dialedNumber;
	}

	public void setDialedNumber(String dialedNumber) {
		this.dialedNumber = dialedNumber;
	}

	public CDRKey() {
    	
    }
 
    public CDRKey(String customerId, String originatingNumber, String dialedNumber, Timestamp dateTime) {
        
    	this.customerId = customerId;
        this.originatingNumber = originatingNumber;
        this.dialedNumber = dialedNumber;
        this.dateTime = dateTime;

        
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CDRKey)) return false;
        CDRKey output = (CDRKey) o;
        return Objects.equals(getDialedNumber(), output.getDialedNumber()) &&
                Objects.equals(getOriginatingNumber(), output.getOriginatingNumber()) 
                && Objects.equals(getCustomerId(), output.getCustomerId())
                && Objects.equals(getDateTime(), output.getDateTime());
    }
 
    @Override
    public int hashCode() {
    	
        return Objects.hash(getDialedNumber(), getOriginatingNumber(), getDateTime());
    }
    
	@Override
	public String toString() {

		return "[CDRKey :" + " dateTime " + this.dateTime + " dialedNumber " + this.dialedNumber 
					+ " originatingNumber " + this.originatingNumber + " customerId " + this.customerId + " ]";
	}
}