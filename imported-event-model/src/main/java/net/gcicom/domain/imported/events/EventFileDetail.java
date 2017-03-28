package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EventFileDetails database table.
 * 
 */
@Entity
@Table(name="EventFileDetails")
public class EventFileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer duplicateRecordCount;

	private Integer errorRecordCount;

	private Integer errorRunsCount;

	private Integer eventRecordCount;

	private Integer headerAndTrailerRecordCount;

	private Integer killedRecordCount;

	private Integer numberOfRecords;

	private Integer successRecordCount;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	//bi-directional many-to-one association to EventFile
	@ManyToOne
	@JoinColumn(name="EventFileID")
	@MapsId
	private EventFile eventFile;

	public EventFileDetail() {
	}

	public Integer getDuplicateRecordCount() {
		return this.duplicateRecordCount;
	}

	public void setDuplicateRecordCount(Integer duplicateRecordCount) {
		this.duplicateRecordCount = duplicateRecordCount;
	}

	public Integer getErrorRecordCount() {
		return this.errorRecordCount;
	}

	public void setErrorRecordCount(Integer errorRecordCount) {
		this.errorRecordCount = errorRecordCount;
	}

	public Integer getErrorRunsCount() {
		return this.errorRunsCount;
	}

	public void setErrorRunsCount(Integer errorRunsCount) {
		this.errorRunsCount = errorRunsCount;
	}

	public Integer getEventRecordCount() {
		return this.eventRecordCount;
	}

	public void setEventRecordCount(Integer eventRecordCount) {
		this.eventRecordCount = eventRecordCount;
	}

	public Integer getHeaderAndTrailerRecordCount() {
		return this.headerAndTrailerRecordCount;
	}

	public void setHeaderAndTrailerRecordCount(Integer headerAndTrailerRecordCount) {
		this.headerAndTrailerRecordCount = headerAndTrailerRecordCount;
	}

	public Integer getKilledRecordCount() {
		return this.killedRecordCount;
	}

	public void setKilledRecordCount(Integer killedRecordCount) {
		this.killedRecordCount = killedRecordCount;
	}

	public Integer getNumberOfRecords() {
		return this.numberOfRecords;
	}

	public void setNumberOfRecords(Integer numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public Integer getSuccessRecordCount() {
		return this.successRecordCount;
	}

	public void setSuccessRecordCount(Integer successRecordCount) {
		this.successRecordCount = successRecordCount;
	}

	public EventFile getEventFile() {
		return this.eventFile;
	}

	public void setEventFile(EventFile eventFile) {
		this.eventFile = eventFile;
	}

}