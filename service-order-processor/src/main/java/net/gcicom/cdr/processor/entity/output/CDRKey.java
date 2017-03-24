package net.gcicom.cdr.processor.entity.output;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Key class for CDR (Call Details Class)
 * Any addition/deletion to this class must ensure appropriate equals and hashCode implementation
 *
 */
@Embeddable
public class CDRKey implements Serializable {
 
	private static final long serialVersionUID = 1L;

 

	
	public String getActionCode() {
		return actionCode;
	}


	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}


	public String getItemType() {
		return itemType;
	}


	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "action_code")
	private String actionCode;
	
	@Column(name = "item_type")
	private String itemType;

	@Column(name = "account_number")
	String accountNumber;
	
	


	public CDRKey() {
    	
    }
 
  
    	 public CDRKey(String actionCode, String itemType, String accountNumber) {    
    	this.actionCode = actionCode;
        this.itemType = itemType;
        this.accountNumber = accountNumber;
    

        
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CDRKey)) return false;
        CDRKey output = (CDRKey) o;
        return Objects.equals(getActionCode(), output.getActionCode()) &&
                Objects.equals(getItemType(), output.getItemType()) 
                && Objects.equals(getAccountNumber(), output.getAccountNumber());
            
    }
 
    @Override
    public int hashCode() {
    	 return Objects.hash(getActionCode(), getAccountNumber());
        
    }
    
	@Override
	public String toString() {

		
		return "[CDRKey :" +" actionCode " + this.actionCode
					+ " accountNumber " + this.accountNumber + " itemType " + this.itemType + " ]";
	}
}