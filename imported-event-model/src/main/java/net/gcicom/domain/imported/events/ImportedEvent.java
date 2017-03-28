package net.gcicom.domain.imported.events;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.persistence.*;

import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the ImportedEvents database table.
 * 
 */
@Entity
@Table(name="ImportedEvents")
public class ImportedEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eventRecordKey;

	private String accountingPeriod;

	private String accountNumber;

	private String country;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));

	private Long customerID;

	private String dialledCLI;

	private Integer eventDurationSecs;

	private Long eventFileID;

	private String eventReference;

	private Long eventReferenceID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eventTime;

	private Long eventTypeID;

	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	private Long numberRange;

	private String numberRangeClassification;

	private String numberRangeType;

	private String originatingCLI;

	private Integer preRatedEventFlag;

	private String presentationCLI;

	private String supplierAccountNumber;

	private String supplierCost;

	private Long supplierID;

	private String supplierNumberRange;

	private String supplierNumberRangeMap;

	private String supplierRatingPattern;

	private String supplierRecordReference;

	private String supplierServiceType;

	private Long supplierTariffPlanID;

	private String terminatingCLI;

	
	private Integer timePeriodID;

	private Integer weekDayFlag;
	
	//flag to mark record if rating processing completed or not
	private Integer rated;

	public ImportedEvent() {
	}
	
	public Integer getRated() {
		return rated;
	}

	public void setRated(Integer rated) {
		this.rated = rated;
	}

	public String getEventRecordKey() {
		return this.eventRecordKey;
	}

	public void setEventRecordKey(String eventRecordKey) {
		this.eventRecordKey = eventRecordKey;
	}

	public String getAccountingPeriod() {
		return this.accountingPeriod;
	}

	public void setAccountingPeriod(String accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
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

	public Long getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getDialledCLI() {
		return this.dialledCLI;
	}

	public void setDialledCLI(String dialledCLI) {
		this.dialledCLI = dialledCLI;
	}

	public Integer getEventDurationSecs() {
		return this.eventDurationSecs;
	}

	public void setEventDurationSecs(Integer eventDurationSecs) {
		this.eventDurationSecs = eventDurationSecs;
	}

	public Long getEventFileID() {
		return this.eventFileID;
	}

	public void setEventFileID(Long eventFileID) {
		this.eventFileID = eventFileID;
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

	public Long getEventTypeID() {
		return this.eventTypeID;
	}

	public void setEventTypeID(Long eventTypeID) {
		this.eventTypeID = eventTypeID;
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

	public Long getNumberRange() {
		return this.numberRange;
	}

	public void setNumberRange(Long numberRange) {
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

	public Integer getPreRatedEventFlag() {
		return this.preRatedEventFlag;
	}

	public void setPreRatedEventFlag(Integer preRatedEventFlag) {
		this.preRatedEventFlag = preRatedEventFlag;
	}

	public String getPresentationCLI() {
		return this.presentationCLI;
	}

	public void setPresentationCLI(String presentationCLI) {
		this.presentationCLI = presentationCLI;
	}

	public String getSupplierAccountNumber() {
		return this.supplierAccountNumber;
	}

	public void setSupplierAccountNumber(String supplierAccountNumber) {
		this.supplierAccountNumber = supplierAccountNumber;
	}

	public String getSupplierCost() {
		return this.supplierCost;
	}

	public void setSupplierCost(String supplierCost) {
		this.supplierCost = supplierCost;
	}

	public Long getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(Long supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierNumberRange() {
		return this.supplierNumberRange;
	}

	public void setSupplierNumberRange(String supplierNumberRange) {
		this.supplierNumberRange = supplierNumberRange;
	}

	public String getSupplierNumberRangeMap() {
		return this.supplierNumberRangeMap;
	}

	public void setSupplierNumberRangeMap(String supplierNumberRangeMap) {
		this.supplierNumberRangeMap = supplierNumberRangeMap;
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

	public Long getSupplierTariffPlanID() {
		return this.supplierTariffPlanID;
	}

	public void setSupplierTariffPlanID(Long supplierTariffPlanID) {
		this.supplierTariffPlanID = supplierTariffPlanID;
	}

	public String getTerminatingCLI() {
		return this.terminatingCLI;
	}

	public void setTerminatingCLI(String terminatingCLI) {
		this.terminatingCLI = terminatingCLI;
	}

	public Integer getTimePeriodID() {
		return this.timePeriodID;
	}

	public void setTimePeriodID(Integer timePeriodID) {
		this.timePeriodID = timePeriodID;
	}

	public Integer getWeekDayFlag() {
		return this.weekDayFlag;
	}

	public void setWeekDayFlag(Integer weekDayFlag) {
		this.weekDayFlag = weekDayFlag;
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
					+ this.supplierCost + " accountingPeriod " + this.accountingPeriod 
					+ " accountNumber " + this.accountNumber + " country " + this.country 
					+ " dialledCLI " + this.dialledCLI + " eventDurationSecs " + this.eventDurationSecs 
					+ " eventReference " + this.eventReference + " numberRange " + this.numberRange 
					+ " numberRangeClassification " + this.numberRangeClassification + " numberRangeType " + this.numberRangeType 
					+ " originatingCLI " + this.originatingCLI + " preRatedEventFlag " + this.preRatedEventFlag
					+ " presentationCLI " + this.presentationCLI + " supplierAccountNumber " + this.supplierAccountNumber
					+ " supplierNumberRange " + this.supplierNumberRange + " supplierNumberRangeMap " + this.supplierNumberRangeMap
					+ " supplierRatingPattern " + this.supplierRatingPattern + " supplierRecordReference " + this.supplierRecordReference
					+ " supplierServiceType " + this.supplierServiceType + " terminatingCLI " + this.terminatingCLI
					+ " timePeriodID " + this.timePeriodID + " weekDayFlag " + this.weekDayFlag
					+ " ]";
	}

}