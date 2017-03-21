package net.gcicom.domain.imported.events;

import java.io.Serializable;
import javax.persistence.*;

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

	@Id
	private String eventRecordKey;

	private String accountingPeriod;

	private String accountNumber;

	private String country;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private BigInteger customerID;

	private String dialledCLI;

	private BigInteger eventDurationSecs;

	private BigInteger eventFileID;

	private String eventReference;

	private BigInteger eventReferenceID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eventTime;

	private BigInteger eventTypeID;

	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	private String numberRange;

	private String numberRangeClassification;

	private String numberRangeType;

	private String originatingCLI;

	private String preRatedEventFlag;

	private String presentationCLI;

	private String supplierAccountNumber;

	private double supplierCost;

	private BigInteger supplierID;

	private String supplierNumberRange;

	private String supplierNumberRangeMap;

	private String supplierRatingPattern;

	private String supplierRecordReference;

	private String supplierServiceType;

	private BigInteger supplierTariffPlanID;

	private String terminatingCLI;

	private String timePeriod;

	private Integer weekDayFlag;

	public ImportedEvent() {
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

	public BigInteger getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(BigInteger customerID) {
		this.customerID = customerID;
	}

	public String getDialledCLI() {
		return this.dialledCLI;
	}

	public void setDialledCLI(String dialledCLI) {
		this.dialledCLI = dialledCLI;
	}

	public BigInteger getEventDurationSecs() {
		return this.eventDurationSecs;
	}

	public void setEventDurationSecs(BigInteger eventDurationSecs) {
		this.eventDurationSecs = eventDurationSecs;
	}

	public BigInteger getEventFileID() {
		return this.eventFileID;
	}

	public void setEventFileID(BigInteger eventFileID) {
		this.eventFileID = eventFileID;
	}

	public String getEventReference() {
		return this.eventReference;
	}

	public void setEventReference(String eventReference) {
		this.eventReference = eventReference;
	}

	public BigInteger getEventReferenceID() {
		return this.eventReferenceID;
	}

	public void setEventReferenceID(BigInteger eventReferenceID) {
		this.eventReferenceID = eventReferenceID;
	}

	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public BigInteger getEventTypeID() {
		return this.eventTypeID;
	}

	public void setEventTypeID(BigInteger eventTypeID) {
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

	public String getNumberRange() {
		return this.numberRange;
	}

	public void setNumberRange(String numberRange) {
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

	public String getPreRatedEventFlag() {
		return this.preRatedEventFlag;
	}

	public void setPreRatedEventFlag(String preRatedEventFlag) {
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

	public double getSupplierCost() {
		return this.supplierCost;
	}

	public void setSupplierCost(double supplierCost) {
		this.supplierCost = supplierCost;
	}

	public BigInteger getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(BigInteger supplierID) {
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

	public BigInteger getSupplierTariffPlanID() {
		return this.supplierTariffPlanID;
	}

	public void setSupplierTariffPlanID(BigInteger supplierTariffPlanID) {
		this.supplierTariffPlanID = supplierTariffPlanID;
	}

	public String getTerminatingCLI() {
		return this.terminatingCLI;
	}

	public void setTerminatingCLI(String terminatingCLI) {
		this.terminatingCLI = terminatingCLI;
	}

	public String getTimePeriod() {
		return this.timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
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
					+ " timePeriod " + this.timePeriod + " weekDayFlag " + this.weekDayFlag
					+ " ]";
	}

}