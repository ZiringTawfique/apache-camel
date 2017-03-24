package net.gcicom.order.processor.entity.audit;


import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import net.gcicom.order.processor.entity.output.BaseEntity;

import java.util.HashMap;
import java.util.Map;
/**
 * modeled after similar jhipster audit entity
*/
@Entity
@Table(name = "audit_event")
public class AuditEvent extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2207213257930690690L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String principal = "cdr-processor";

    @Column(name = "event_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Timestamp auditEventDate;
    
    @Column(name = "event_type")
    private String auditEventType;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Lob
    @Column(name = "value")
    @CollectionTable(name = "audit_event_data", joinColumns=@JoinColumn(name="event_id"))
    private Map<String, String> data = new HashMap<>();

    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    private String createdBy = "cdr-processor";

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Timestamp getAuditEventDate() {
        return auditEventDate;
    }

    public void setAuditEventDate(Timestamp auditEventDate) {
        this.auditEventDate = auditEventDate;
    }

    public String getAuditEventType() {
        return auditEventType;
    }

    public void setAuditEventType(String auditEventType) {
        this.auditEventType = auditEventType;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Audit Event :id- " + this.id + " principal - " + this.principal + " auditEventDate " 
					+ this.auditEventDate + " auditEventType " + this.auditEventType 
					+ " createdBy " + this.createdBy + " ]";
	}
}

