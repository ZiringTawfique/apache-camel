/**
 * 
 */
package net.gcicom.cdr.processor.entity.input;

import java.time.LocalDateTime;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Maps to various fields as per specification 
 * file://ws-sma-bd01/AllSpark/CDR%20Spec/Vodaphone/THUS/Vodafone%20Thus/New_Outbound_CDR_Format.pdf
 * 
 */
@CsvRecord(separator = ",", skipFirstLine = false)//because we are streaming rows from the file we need this. I know batch processing is good but here unit of work is csv row
public class VodafoneThusIDA {
	
	/**
	 * Calling Party (A-number) 
	 * National number with no leading zero
	 * 
	 */
	@DataField(pos = 1, required = true)
	String aNumber;
	
	/**
	 * 
		CCTDateTime 
		YYYY-MM-DD 24:MM:SS.  
		Format: YYYY-MM-DD 24:MM:SS 
		2010-04-18 16:28:29 
		2010-06-05 12:03:01 
	 */
	@DataField(pos = 2, pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime cctDateTime;
	
	/**
	 * Dest-Code 
		Contains the Service/Destination description code. 
		Down to City/Town /Service code 
		This field contains the leading digits used to determine the actual destination description & Tariff   
		Example      “020” = London         
		       “001718” = USA – New York City   
		              “0141” = Glasgow         
		             “0030999” = Greece (Mobile) Cosmote 
	 */
	@DataField(pos = 3, required = true)
	String destinationCode;
	
	/**
	 * 
	Dest-ServiceDesc 
	Service/Destination Description 
	Example For outbound:    
	“Leeds”    “Sheffield”    
	“Canada – Ontario”   
	 “France – Cannes”   
	  “France - North East Region”  
	    “France - Paris Region” 

	 */
	@DataField(pos = 4, required = true)
	String destOrServiceDescription;
	
	/**
	 * 5 
	ServiceDestination 
	Contains the string dialled 
	i.e. the “B-Number”, or descriptors for events such as Wake-up calls etc.   
	This field would normally be used by a Retail-Billing system to determine the rating rules. 
	Also known as ‘Divert’ or ‘B-Number’ including leading zero. 
 
	Example For outbound:       “01483765118”       “0017183541207” 
	 */
	@DataField(pos = 5, required = true)
	String serviceDestination;
	
	/**
	 * 6 Dur-Secs Duration in Seconds.   
	 * Will always be a minimum of 1 second. 

	 */
	@DataField(pos = 6)
	int duration;
	
	/**
	 * 7 Unique-ID 
	Unique Identifier for the call.  Enables a full audit of the event to be traced back to network or switch. 
	Will be blank on interim CDRs such as dailies.  

	 */
	@DataField(pos = 7)
	String uuid;
	
	/**
	 * 	8 WS-Charge Wholesale Charge Wholesale Cost to Business Partner 
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
	 * 11 Charge-Point 
	Tariff code used to rate the event. 
	The coding is based on ISO-3 standards for country code. 
	For reference only and should not be used for rating purposes.  
	   A code between 6 & 9 characters. 
	    Position 1 to 3:    
	    ISO-3 country Code for geographic destinations/ 
	    Position 4 to 6  Indication of type of service      
	    	FXD = Fixed line      
	    	MOB = Mobile      
	    	SHC  = Shared Cost For example IRLFXDNAT  
	    	IRL = Ireland 
	    	FXD = Fixed 
	    	NAT = National IRLMOBVF 
	    	IRL = Ireland MOB  = Mobile 
	    	VF       = Vodafone    
	    	DEUFXD DEU = Germany 
	    	FXD  = Fixed 

	 */
	@DataField(pos = 11)
	String chargePoint;
	/**
	 * 	12 NGN-Term 
		Actual NGN Termination Number if present. 
		For Outbound this will always be blank 
	 */
	@DataField(pos = 12)
	String ngnTerminationNumber;
	
	/**
	 * 		13 Call Type Transmission means 
 		“V”= for Outbound voice call   “DI”  = Outbound ISDN 64k data call 

	 */
	@DataField(pos = 13)
	String callType;

	String supplier = "VodafoneThusIDA";

	public String getaNumber() {
		return aNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public LocalDateTime getCctDateTime() {
		return cctDateTime;
	}

	public void setCctDateTime(LocalDateTime cctDateTime) {
		this.cctDateTime = cctDateTime;
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

	public String getServiceDestination() {
		return serviceDestination;
	}

	public void setServiceDestination(String serviceDestination) {
		this.serviceDestination = serviceDestination;
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

	public String getNgnTerminationNumber() {
		return ngnTerminationNumber;
	}

	public void setNgnTerminationNumber(String ngnTerminationNumber) {
		this.ngnTerminationNumber = ngnTerminationNumber;
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
