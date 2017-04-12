package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ImportedEvents database table.
 * 
 */
@Embeddable
public class ImportedEventPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long importedEventID;

	private Integer accountingPeriod;

	public ImportedEventPK() {
	}
	public Long getImportedEventID() {
		return this.importedEventID;
	}
	public void setImportedEventID(Long importedEventID) {
		this.importedEventID = importedEventID;
	}
	public Integer getAccountingPeriod() {
		return this.accountingPeriod;
	}
	public void setAccountingPeriod(Integer accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ImportedEventPK)) {
			return false;
		}
		ImportedEventPK castOther = (ImportedEventPK)other;
		return 
			this.importedEventID.equals(castOther.importedEventID)
			&& (this.accountingPeriod == castOther.accountingPeriod);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.importedEventID.hashCode();
		hash = hash * prime + this.accountingPeriod;
		
		return hash;
	}
}