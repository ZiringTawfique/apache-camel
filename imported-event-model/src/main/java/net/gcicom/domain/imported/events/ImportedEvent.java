package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.math.BigInteger;


/**
 * The persistent class for the ImportedEvents database table.
 * 
 */
@Entity
@Table(name="ImportedEvents")
@NamedQuery(name="ImportedEvent.findAll", query="SELECT i FROM ImportedEvent i")
public class ImportedEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ImportedEventPK id;

	private String accountNumber;

	private String country;

	private BigDecimal customerID;

	private String dialledCLI;

	private String eventBandCode;

	private BigDecimal eventDuration;

	private BigDecimal eventFileID;

	private String eventRecordKey;

	private String eventReference;

	private Long eventReferenceID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eventTime;

	private Short eventTypeID;

	private String extension;

	private BigDecimal importBatchNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date importedEventCreateDate;

	private BigDecimal numberRange;

	private String numberRangeClassification;

	private String numberRangeType;

	private String originatingCLI;

	private BigDecimal preRatedEventFlag;

	private String presentationCLI;

	private BigDecimal ratedFlag;

	private BigDecimal ratingPlanID;

	private BigDecimal ratingRunNumber;

	private String supplierAccountNumber;

	private BigDecimal supplierCost;

	private String supplierEventBand;

	private Integer supplierID;

	private String supplierNumberRange;

	private String supplierRatingPattern;

	private String supplierRecordReference;

	private String supplierServiceType;

	private String supplierTariffPlanID;

	private String terminatingCLI;

	private BigDecimal timePeriodID;

	private BigDecimal weekDayFlag;

	public ImportedEvent() {
	}

	public ImportedEventPK getId() {
		return this.id;
	}

	public void setId(ImportedEventPK id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(BigDecimal customerID) {
		this.customerID = customerID;
	}

	public String getDialledCLI() {
		return this.dialledCLI;
	}

	public void setDialledCLI(String dialledCLI) {
		this.dialledCLI = dialledCLI;
	}

	public String getEventBandCode() {
		return this.eventBandCode;
	}

	public void setEventBandCode(String eventBandCode) {
		this.eventBandCode = eventBandCode;
	}

	public BigDecimal getEventDuration() {
		return this.eventDuration;
	}

	public void setEventDuration(BigDecimal eventDuration) {
		this.eventDuration = eventDuration;
	}

	public BigDecimal getEventFileID() {
		return this.eventFileID;
	}

	public void setEventFileID(BigDecimal eventFileID) {
		this.eventFileID = eventFileID;
	}

	public String getEventRecordKey() {
		return this.eventRecordKey;
	}

	public void setEventRecordKey(String eventRecordKey) {
		this.eventRecordKey = eventRecordKey;
	}

	public String getEventReference() {
		return this.eventReference;
	}

	public void setEventReference(String eventReference) {
		this.eventReference = eventReference;
	}

	public Long getEventReferenceID() {
		return this.eventReferenceID;
	}

	public void setEventReferenceID(Long eventReferenceID) {
		this.eventReferenceID = eventReferenceID;
	}

	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public Short getEventTypeID() {
		return this.eventTypeID;
	}

	public void setEventTypeID(Short eventTypeID) {
		this.eventTypeID = eventTypeID;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public BigDecimal getImportBatchNumber() {
		return this.importBatchNumber;
	}

	public void setImportBatchNumber(BigDecimal importBatchNumber) {
		this.importBatchNumber = importBatchNumber;
	}

	public Date getImportedEventCreateDate() {
		return this.importedEventCreateDate;
	}

	public void setImportedEventCreateDate(Date importedEventCreateDate) {
		this.importedEventCreateDate = importedEventCreateDate;
	}

	public BigDecimal getNumberRange() {
		return this.numberRange;
	}

	public void setNumberRange(BigDecimal numberRange) {
		this.numberRange = numberRange;
	}

	public String getNumberRangeClassification() {
		return this.numberRangeClassification;
	}

	public void setNumberRangeClassification(String numberRangeClassification) {
		this.numberRangeClassification = numberRangeClassification;
	}

	public String getNumberRangeType() {
		return this.numberRangeType;
	}

	public void setNumberRangeType(String numberRangeType) {
		this.numberRangeType = numberRangeType;
	}

	public String getOriginatingCLI() {
		return this.originatingCLI;
	}

	public void setOriginatingCLI(String originatingCLI) {
		this.originatingCLI = originatingCLI;
	}

	public BigDecimal getPreRatedEventFlag() {
		return this.preRatedEventFlag;
	}

	public void setPreRatedEventFlag(BigDecimal preRatedEventFlag) {
		this.preRatedEventFlag = preRatedEventFlag;
	}

	public String getPresentationCLI() {
		return this.presentationCLI;
	}

	public void setPresentationCLI(String presentationCLI) {
		this.presentationCLI = presentationCLI;
	}

	public BigDecimal getRatedFlag() {
		return this.ratedFlag;
	}

	public void setRatedFlag(BigDecimal ratedFlag) {
		this.ratedFlag = ratedFlag;
	}

	public BigDecimal getRatingPlanID() {
		return this.ratingPlanID;
	}

	public void setRatingPlanID(BigDecimal ratingPlanID) {
		this.ratingPlanID = ratingPlanID;
	}

	public BigDecimal getRatingRunNumber() {
		return this.ratingRunNumber;
	}

	public void setRatingRunNumber(BigDecimal ratingRunNumber) {
		this.ratingRunNumber = ratingRunNumber;
	}

	public String getSupplierAccountNumber() {
		return this.supplierAccountNumber;
	}

	public void setSupplierAccountNumber(String supplierAccountNumber) {
		this.supplierAccountNumber = supplierAccountNumber;
	}

	public BigDecimal getSupplierCost() {
		return this.supplierCost;
	}

	public void setSupplierCost(BigDecimal supplierCost) {
		this.supplierCost = supplierCost;
	}

	public String getSupplierEventBand() {
		return this.supplierEventBand;
	}

	public void setSupplierEventBand(String supplierEventBand) {
		this.supplierEventBand = supplierEventBand;
	}

	public int getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierNumberRange() {
		return this.supplierNumberRange;
	}

	public void setSupplierNumberRange(String supplierNumberRange) {
		this.supplierNumberRange = supplierNumberRange;
	}

	public String getSupplierRatingPattern() {
		return this.supplierRatingPattern;
	}

	public void setSupplierRatingPattern(String supplierRatingPattern) {
		this.supplierRatingPattern = supplierRatingPattern;
	}

	public String getSupplierRecordReference() {
		return this.supplierRecordReference;
	}

	public void setSupplierRecordReference(String supplierRecordReference) {
		this.supplierRecordReference = supplierRecordReference;
	}

	public String getSupplierServiceType() {
		return this.supplierServiceType;
	}

	public void setSupplierServiceType(String supplierServiceType) {
		this.supplierServiceType = supplierServiceType;
	}

	public String getSupplierTariffPlanID() {
		return this.supplierTariffPlanID;
	}

	public void setSupplierTariffPlanID(String supplierTariffPlanID) {
		this.supplierTariffPlanID = supplierTariffPlanID;
	}

	public String getTerminatingCLI() {
		return this.terminatingCLI;
	}

	public void setTerminatingCLI(String terminatingCLI) {
		this.terminatingCLI = terminatingCLI;
	}

	public BigDecimal getTimePeriodID() {
		return this.timePeriodID;
	}

	public void setTimePeriodID(BigDecimal timePeriodID) {
		this.timePeriodID = timePeriodID;
	}

	public BigDecimal getWeekDayFlag() {
		return this.weekDayFlag;
	}

	public void setWeekDayFlag(BigDecimal weekDayFlag) {
		this.weekDayFlag = weekDayFlag;
	}
	@Transient
	private String fileChecksum;
	
	public String getFileChecksum() {
		return fileChecksum;
	}

	public void setFileChecksum(String fileChecksum) {
		this.fileChecksum = fileChecksum;
	}
	@Override
    public boolean equals(Object o) {
    	
        if (this == o) return true;
        if (!(o instanceof ImportedEvent)) return false;
        ImportedEvent output = (ImportedEvent) o;
        return Objects.equals(getEventRecordKey(), output.getEventRecordKey());
       
    }
 
    @Override
    public int hashCode() {
    	
        return Objects.hash(getEventRecordKey());
    }
    
	@Override
	public String toString() {

		return "[ImportedEvent :eventRecordKey- " + this.eventRecordKey + " supplierId - " + this.supplierID + " supplierCost " 
					+ this.supplierCost + " accountingPeriod " + this.id.getAccountingPeriod() 
					+ " accountNumber " + this.accountNumber + " country " + this.country 
					+ " dialledCLI " + this.dialledCLI + " eventDurationSecs " + this.eventDuration 
					+ " eventReference " + this.eventReference + " numberRange " + this.numberRange 
					+ " numberRangeClassification " + this.numberRangeClassification + " numberRangeType " + this.numberRangeType 
					+ " originatingCLI " + this.originatingCLI + " preRatedEventFlag " + this.preRatedEventFlag
					+ " presentationCLI " + this.presentationCLI + " supplierAccountNumber " + this.supplierAccountNumber
					+ " supplierNumberRange " + this.supplierNumberRange + " supplierNumberRangeMap " + this.supplierNumberRange
					+ " supplierRatingPattern " + this.supplierRatingPattern + " supplierRecordReference " + this.supplierRecordReference
					+ " supplierServiceType " + this.supplierServiceType + " terminatingCLI " + this.terminatingCLI
					+ " timePeriodID " + this.timePeriodID + " weekDayFlag " + this.weekDayFlag
					+"eventBandCode"+this.eventBandCode
					+ " ]";
	}

}