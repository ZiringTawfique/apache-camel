package net.gcicom.cdr.processor.entity.output;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "invalid_cdr")
public class InvalidCDR extends AbstractCDR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String cdr;
	
	String reason;
	
    @Column(name = "cdr_file")
	String cdrFile;
    
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[CDR :id- " + this.id + " cdr - " + this.cdr + " reason " 
					+ this.reason  + " created " + super.getCreatedDate() + " ]";
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCdr() {
		return cdr;
	}

	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCdrFile() {
		return cdrFile;
	}

	public void setCdrFile(String cdrFile) {
		this.cdrFile = cdrFile;
	}

}
