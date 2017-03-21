package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the AuditEvent database table.
 * 
 */
@Entity
@NamedQuery(name="AuditEvent.findAll", query="SELECT a FROM AuditEvent a")
public class AuditEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String eventID;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eventDate;

	private String eventType;

	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	//bi-directional many-to-one association to AuditEventData
	@OneToMany(mappedBy="auditEvent")
	private List<AuditEventData> auditEventData;

	public AuditEvent() {
	}

	public String getEventID() {
		return this.eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public List<AuditEventData> getAuditEventData() {
		return this.auditEventData;
	}

	public void setAuditEventData(List<AuditEventData> auditEventData) {
		this.auditEventData = auditEventData;
	}

	public AuditEventData addAuditEventData(AuditEventData auditEventData) {
		getAuditEventData().add(auditEventData);
		auditEventData.setAuditEvent(this);

		return auditEventData;
	}

	public AuditEventData removeAuditEventData(AuditEventData auditEventData) {
		getAuditEventData().remove(auditEventData);
		auditEventData.setAuditEvent(null);

		return auditEventData;
	}

}