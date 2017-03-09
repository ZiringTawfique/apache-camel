package net.gcicom.cdr.processor.entity.output;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class to represent invalid CDR (Call Details Class) feed. 
 * Basically represents a row from cdr file + other details
 *
 */
@Entity(name = "invalid_cdr")
@Table(name = "invalid_cdr")
public class InvalidCDR extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String cdr;
	
	private String reason;
	
    @Column(name = "cdr_file")
    private String cdrFile;
    
	
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
