/**
 * 
 */
package net.gcicom.cdr.processor.entity.input;

import java.time.LocalDateTime;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Maps to various fields as per specification 
file://ws-sma-bd01/AllSpark/CDR%20Spec/Vodaphone/THUS/Vodafone%20Thus/New_Inbound_CDR_Format.pdf * 
 */
@CsvRecord(separator = ",", skipFirstLine = false)//because we are streaming rows from the file we need this. I know batch processing is good but here unit of work is csv row
public class VodafoneThusNTS {
	
	/**
	 * DialledNumber 
	Dialed NTS number  
	The NTS-Number is presented as the Dialed NTS number  e.g. 08000270000
	 * 
	 */
	@DataField(pos = 1, required = true)
	String dialedNumber;
	
	/**
	 * 
		DateTime 
		YYYY-MM-DD 24:MM:SS.  
		Format: YYYY-MM-DD 24:MM:SS 
		2010-04-18 16:28:29 
		2010-06-05 12:03:01 
	 */
	@DataField(pos = 2, pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime dateTime;
	
	/**
		Contains the Service Destination description code. 
		Informational code indicating where the type of service.  Typical values are FEAT, PRC=Premium, FRC=Freefone, LOC=Low Call 
 
		Not to be used for billing purposes
	 */
	@DataField(pos = 3, required = true)
	String destinationCode;
	
	/**
	 * 
	Service/Destination Description 
	Information narrative describing the service. Not to be used for billing 
	 */
	@DataField(pos = 4, required = true)
	String destOrServiceDescription;
	
	/**
	 *Actual NGN Termination Number if present 
	For inbound this is Termination number prefixed with the type of service. 
	 */
	@DataField(pos = 5, required = true)
	String terminationNumber;
	
	/**
	 * 6 Duration in Seconds.   
	 * Rounded up to the next second 
	 * - a minimum of 1 second.   

	 */
	@DataField(pos = 6)
	int duration;
	
	/**
	 * Unique-ID Reference code for the call.   
		Will be blank on daily CDRs.  
	 */
	@DataField(pos = 7)
	String uuid;
	
	/**
	 * 	8 WS-Charge Wholesale Charge Cost or rebate to Business Partner 
	 */
	@DataField(pos = 8)
	String wholesalePrice;
	
	/**
	 * 9 Band Band description PEAK, OFFPEAK,WEEKEND 
	 */
	@DataField(pos = 9)
	String band;
	
	
	/**
	 * 10 Filler Spare Reserved 
	 */
	@DataField(pos = 10)
	String filler;
	
	/**
	 * Tariff code used to rate the event. 
		Information narrative describing the service. Not to be used for billing
	 */
	@DataField(pos = 11)
	String chargePoint;
	/**
	 * 	12 
		Service Origination 
		Calling Number - Anonymous 
		Contains the Origination Number. 
		The last 4 digits of the subscriber numbers are not supplied.
		This ensures the calling party remains anonymous and conforms to data-protection legislation.  
	 */
	@DataField(pos = 12)
	String serviceOrigination;
	
	/**
	 * 		13 Call Type Call type indicator “N” = Inbound. 
	 */
	@DataField(pos = 13)
	String callType;

	String supplier = "VodafoneThusNTS";

	public String getDialedNumber() {
		return dialedNumber;
	}

	public void setDialedNumber(String dialedNumber) {
		this.dialedNumber = dialedNumber;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public String getDestOrServiceDescription() {
		return destOrServiceDescription;
	}

	public void setDestOrServiceDescription(String destOrServiceDescription) {
		this.destOrServiceDescription = destOrServiceDescription;
	}

	public String getTerminationNumber() {
		return terminationNumber;
	}

	public void setTerminationNumber(String terminationNumber) {
		this.terminationNumber = terminationNumber;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(String wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getChargePoint() {
		return chargePoint;
	}

	public void setChargePoint(String chargePoint) {
		this.chargePoint = chargePoint;
	}

	public String getServiceOrigination() {
		return serviceOrigination;
	}

	public void setServiceOrigination(String serviceOrigination) {
		this.serviceOrigination = serviceOrigination;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	
}
