package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AuditEventData database table.
 * 
 */
@Embeddable
public class AuditEventDataPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String eventID;

	private String dataName;

	public AuditEventDataPK() {
	}
	public String getEventID() {
		return this.eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getDataName() {
		return this.dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AuditEventDataPK)) {
			return false;
		}
		AuditEventDataPK castOther = (AuditEventDataPK)other;
		return 
			this.eventID.equals(castOther.eventID)
			&& this.dataName.equals(castOther.dataName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eventID.hashCode();
		hash = hash * prime + this.dataName.hashCode();
		
		return hash;
	}
}