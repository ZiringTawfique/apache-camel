package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EventFileDetails database table.
 * 
 */
@Entity
@Table(name="EventFileDetails")
@NamedQuery(name="EventFileDetail.findAll", query="SELECT e FROM EventFileDetail e")
public class EventFileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int duplicateRecordCount;

	private int errorRecordCount;

	private int errorRunsCount;

	private int eventRecordCount;

	private int headerAndTrailerRecordCount;

	private int killedRecordCount;

	private int numberOfRecords;

	private int successRecordCount;

	//bi-directional many-to-one association to EventFile
	@ManyToOne
	@JoinColumn(name="EventFileID")
	private EventFile eventFile;

	public EventFileDetail() {
	}

	public int getDuplicateRecordCount() {
		return this.duplicateRecordCount;
	}

	public void setDuplicateRecordCount(int duplicateRecordCount) {
		this.duplicateRecordCount = duplicateRecordCount;
	}

	public int getErrorRecordCount() {
		return this.errorRecordCount;
	}

	public void setErrorRecordCount(int errorRecordCount) {
		this.errorRecordCount = errorRecordCount;
	}

	public int getErrorRunsCount() {
		return this.errorRunsCount;
	}

	public void setErrorRunsCount(int errorRunsCount) {
		this.errorRunsCount = errorRunsCount;
	}

	public int getEventRecordCount() {
		return this.eventRecordCount;
	}

	public void setEventRecordCount(int eventRecordCount) {
		this.eventRecordCount = eventRecordCount;
	}

	public int getHeaderAndTrailerRecordCount() {
		return this.headerAndTrailerRecordCount;
	}

	public void setHeaderAndTrailerRecordCount(int headerAndTrailerRecordCount) {
		this.headerAndTrailerRecordCount = headerAndTrailerRecordCount;
	}

	public int getKilledRecordCount() {
		return this.killedRecordCount;
	}

	public void setKilledRecordCount(int killedRecordCount) {
		this.killedRecordCount = killedRecordCount;
	}

	public int getNumberOfRecords() {
		return this.numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public int getSuccessRecordCount() {
		return this.successRecordCount;
	}

	public void setSuccessRecordCount(int successRecordCount) {
		this.successRecordCount = successRecordCount;
	}

	public EventFile getEventFile() {
		return this.eventFile;
	}

	public void setEventFile(EventFile eventFile) {
		this.eventFile = eventFile;
	}

}