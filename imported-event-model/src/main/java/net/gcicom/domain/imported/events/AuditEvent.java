package net.gcicom.domain.imported.events;


import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * modeled after similar jhipster audit entity
*/
@Entity
@Table(name = "audit_event")
public class AuditEvent implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2207213257930690690L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EventID")
    private Long id;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EventDate")
    private Date auditEventDate;
    
    @Column(name = "EventType")
    private String auditEventType;

    @ElementCollection
    @MapKeyColumn(name = "DataName")
    @Lob
    @Column(name = "Value")
    @CollectionTable(name = "AuditEventData", joinColumns=@JoinColumn(name="EventID"))
    private Map<String, String> data = new HashMap<>();

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String lastModifiedBy;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAuditEventDate() {
        return auditEventDate;
    }

    public void setAuditEventDate(Date auditEventDate) {
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
		return "[Audit Event :id- " + this.id + " auditEventDate " 
					+ this.auditEventDate + " auditEventType " + this.auditEventType 
					+ " createdBy " + this.createdBy + " ]";
	}
}

