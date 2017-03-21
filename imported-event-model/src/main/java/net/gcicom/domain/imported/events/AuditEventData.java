package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AuditEventData database table.
 * 
 */
@Entity
@NamedQuery(name="AuditEventData.findAll", query="SELECT a FROM AuditEventData a")
public class AuditEventData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuditEventDataPK id;

	@Lob
	private String value;

	//bi-directional many-to-one association to AuditEvent
	@ManyToOne
	@JoinColumn(name="EventID")
	private AuditEvent auditEvent;

	public AuditEventData() {
	}

	public AuditEventDataPK getId() {
		return this.id;
	}

	public void setId(AuditEventDataPK id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AuditEvent getAuditEvent() {
		return this.auditEvent;
	}

	public void setAuditEvent(AuditEvent auditEvent) {
		this.auditEvent = auditEvent;
	}

}