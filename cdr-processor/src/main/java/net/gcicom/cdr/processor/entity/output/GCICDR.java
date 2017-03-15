package net.gcicom.cdr.processor.entity.output;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class to represent CDR (Call Details Class) entity
 *
 */
@Entity(name = "gci_cdr")
@Table(name = "gci_call_details_record")
public class GCICDR extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 8 am - 6 pm Peak
	 */
	@Column(name = "time_period", nullable = false)
	String timePeriod;
	
	@Column(name = "supplier_id", nullable = false)
	Long supplierId;
	
	@Column(name = "supplier_cost", nullable = false)
	String supplierCost;

	
	/**UniqueRecordIDInGCI
	 * 
	 */
	@Id
	@Column(name = "event_record_key")
	private String eventRecordKey;
	
	@Column(name = "customer_id", nullable = false )
	private Long customerId;
	
	@Column(name = "account_number", nullable = false)
	private String accountNumber;
	
	@Column(name = "accounting_period", nullable = false)
	private String accountingPeriod;
	
	@Column(name = "event_reference", nullable = false)
	private String eventReference;
	
	@Column(name = "event_reference_id", nullable = false)
	private Long eventReferenceId;
	
	@Column(name = "originating_cli", nullable = false)
	private String originatingCLI;
	
	@Column(name = "dialled_cli", nullable = false )
	private String dialledCLI;
	
	@Column(name = "terminating_cli", nullable = false)
	private String terminatingCLI;
	
	@Column(name = "presentation_cli")
	private String presentationCLI;
	
	@Column(name = "event_type_id", nullable = false )
	private Long eventTypeId;
	
	@Column(name = "event_time", nullable = false)
	private Timestamp eventTime;
	
	@Column(name = "event_duration_secs", nullable = false)
	private String eventDurationSecs;
	
	@Column(name = "week_day_flag", nullable = false)
	private Integer weekDayFlag;
	
	@Column(name = "number_range", nullable = false)
	private String numberRange;
	
	@Column(name = "number_range_classification", nullable = false)
	private String numberRangeClassification;
	
	@Column(name = "number_range_type", nullable = false)
	private String numberRangeType;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "pre_rated_event_flag", nullable = false)
	private String preRatedEventFlag;
	
	@Column(name = "event_file_id", nullable = false)
	private Long eventFileId;
	
	@Column(name = "supplier_account_number", nullable = false)
	private String supplierAccountNumber;
	
	@Column(name = "supplier_record_reference")
	private String supplierRecordReference;
	
	@Column(name = "supplier_number_range")
	private String supplierNumberRange;
	
	@Column(name = "supplier_number_range_map")
	private String supplierNumberRangeMap;
	
	@Column(name = "supplier_tariff_plan_id")
	private Long supplierTariffPlanId;

	@Column(name = "supplier_rating_pattern")
	private String supplierRatingPattern;
	
	@Column(name = "supplier_service_type")
	private String supplierServiceType;

	
	public String getEventRecordKey() {
		return eventRecordKey;
	}

	public void setEventRecordKey(String eventRecordKey) {
		this.eventRecordKey = eventRecordKey;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(String accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}
	
	public String getEventDurationSecs() {
		return eventDurationSecs;
	}

	public void setEventDurationSecs(String eventDurationSecs) {
		this.eventDurationSecs = eventDurationSecs;
	}

	public String getEventReference() {
		return eventReference;
	}

	public void setEventReference(String eventReference) {
		this.eventReference = eventReference;
	}

	public Long getEventReferenceId() {
		return eventReferenceId;
	}

	public void setEventReferenceId(Long eventReferenceId) {
		this.eventReferenceId = eventReferenceId;
	}

	public String getOriginatingCLI() {
		return originatingCLI;
	}

	public void setOriginatingCLI(String originatingCLI) {
		this.originatingCLI = originatingCLI;
	}

	public String getDialledCLI() {
		return dialledCLI;
	}

	public void setDialledCLI(String dialledCLI) {
		this.dialledCLI = dialledCLI;
	}

	public String getTerminatingCLI() {
		return terminatingCLI;
	}

	public void setTerminatingCLI(String terminatingCLI) {
		this.terminatingCLI = terminatingCLI;
	}

	public String getPresentationCLI() {
		return presentationCLI;
	}

	public void setPresentationCLI(String presentationCLI) {
		this.presentationCLI = presentationCLI;
	}

	public Long getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public Timestamp getEventTime() {
		return eventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}

	public Integer getWeekDayFlag() {
		return weekDayFlag;
	}

	public void setWeekDayFlag(Integer weekDayFlag) {
		this.weekDayFlag = weekDayFlag;
	}

	public String getNumberRange() {
		return numberRange;
	}

	public void setNumberRange(String numberRange) {
		this.numberRange = numberRange;
	}

	public String getNumberRangeClassification() {
		return numberRangeClassification;
	}

	public void setNumberRangeClassification(String numberRangeClassification) {
		this.numberRangeClassification = numberRangeClassification;
	}

	public String getNumberRangeType() {
		return numberRangeType;
	}

	public void setNumberRangeType(String numberRangeType) {
		this.numberRangeType = numberRangeType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPreRatedEventFlag() {
		return preRatedEventFlag;
	}

	public void setPreRatedEventFlag(String preRatedEventFlag) {
		this.preRatedEventFlag = preRatedEventFlag;
	}

	public Long getEventFileId() {
		return eventFileId;
	}

	public void setEventFileId(Long eventFileId) {
		this.eventFileId = eventFileId;
	}

	public String getSupplierAccountNumber() {
		return supplierAccountNumber;
	}

	public void setSupplierAccountNumber(String supplierAccountNumber) {
		this.supplierAccountNumber = supplierAccountNumber;
	}

	public String getSupplierRecordReference() {
		return supplierRecordReference;
	}

	public void setSupplierRecordReference(String supplierRecordReference) {
		this.supplierRecordReference = supplierRecordReference;
	}

	public String getSupplierNumberRange() {
		return supplierNumberRange;
	}

	public void setSupplierNumberRange(String supplierNumberRange) {
		this.supplierNumberRange = supplierNumberRange;
	}

	public String getSupplierNumberRangeMap() {
		return supplierNumberRangeMap;
	}

	public void setSupplierNumberRangeMap(String supplierNumberRangeMap) {
		this.supplierNumberRangeMap = supplierNumberRangeMap;
	}

	public Long getSupplierTariffPlanId() {
		return supplierTariffPlanId;
	}

	public void setSupplierTariffPlanId(Long supplierTariffPlanId) {
		this.supplierTariffPlanId = supplierTariffPlanId;
	}

	public String getSupplierRatingPattern() {
		return supplierRatingPattern;
	}

	public void setSupplierRatingPattern(String supplierRatingPattern) {
		this.supplierRatingPattern = supplierRatingPattern;
	}

	public String getSupplierServiceType() {
		return supplierServiceType;
	}

	public void setSupplierServiceType(String supplierServiceType) {
		this.supplierServiceType = supplierServiceType;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupplierCost() {
		return supplierCost;
	}

	public void setSupplierCost(String supplierCost) {
		this.supplierCost = supplierCost;
	}
	
	
    @Override
    public boolean equals(Object o) {
    	
        if (this == o) return true;
        if (!(o instanceof GCICDR)) return false;
        GCICDR output = (GCICDR) o;
        return Objects.equals(getEventRecordKey(), output.getEventRecordKey());
       
    }
 
    @Override
    public int hashCode() {
    	
        return Objects.hash(getEventRecordKey());
    }
    
	@Override
	public String toString() {

		return "[GCR CDR :eventRecordKey- " + this.eventRecordKey + " supplierId - " + this.supplierId + " supplierCost " 
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
