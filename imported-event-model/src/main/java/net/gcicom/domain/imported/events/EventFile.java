package net.gcicom.domain.imported.events;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EventFile database table.
 * 
 */
@Entity
@Table(name = "EventFile")
@NamedQuery(name="EventFile.findAll", query="SELECT e FROM EventFile e")
public class EventFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long eventFileID;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateProcessed;

	private String eventFileChecksum;

	private String eventFileName;

	private int importRunNumber;

	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	private Long supplierID;

	//bi-directional many-to-one association to EventFileDetail
	@OneToMany(mappedBy="eventFile")
	private List<EventFileDetail> eventFileDetails;

	public EventFile() {
	}

	public Long getEventFileID() {
		return this.eventFileID;
	}

	public void setEventFileID(Long eventFileID) {
		this.eventFileID = eventFileID;
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

	public Date getDateProcessed() {
		return this.dateProcessed;
	}

	public void setDateProcessed(Date dateProcessed) {
		this.dateProcessed = dateProcessed;
	}

	public String getEventFileChecksum() {
		return this.eventFileChecksum;
	}

	public void setEventFileChecksum(String eventFileChecksum) {
		this.eventFileChecksum = eventFileChecksum;
	}

	public String getEventFileName() {
		return this.eventFileName;
	}

	public void setEventFileName(String eventFileName) {
		this.eventFileName = eventFileName;
	}

	public int getImportRunNumber() {
		return this.importRunNumber;
	}

	public void setImportRunNumber(int importRunNumber) {
		this.importRunNumber = importRunNumber;
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

	public Long getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(Long supplierID) {
		this.supplierID = supplierID;
	}

	public List<EventFileDetail> getEventFileDetails() {
		return this.eventFileDetails;
	}

	public void setEventFileDetails(List<EventFileDetail> eventFileDetails) {
		this.eventFileDetails = eventFileDetails;
	}

	public EventFileDetail addEventFileDetail(EventFileDetail eventFileDetail) {
		getEventFileDetails().add(eventFileDetail);
		eventFileDetail.setEventFile(this);

		return eventFileDetail;
	}

	public EventFileDetail removeEventFileDetail(EventFileDetail eventFileDetail) {
		getEventFileDetails().remove(eventFileDetail);
		eventFileDetail.setEventFile(null);

		return eventFileDetail;
	}

}