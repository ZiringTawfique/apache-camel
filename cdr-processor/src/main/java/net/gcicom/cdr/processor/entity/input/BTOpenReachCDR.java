/**
 * 
 */
package net.gcicom.cdr.processor.entity.input;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * It mpas a CSV row of BT Openreach csv file excluding Event:
 * Event: "01708867097","350","2017021712355200",,,,,,,,,,"12244921","239744316","0761090089994","0","07455060009","0","00:00:28.49","00000001",,,"2",,"265",,"0",,"0455805712","S","5","Mobile telephone - fm6","MED9616370","voice","0.0",

 *
 */
@CsvRecord(separator = ",", skipFirstLine = false)
public class BTOpenReachCDR {
	
	private String supplier = "BTOpenReach";
	
	@DataField(pos = 1, required = true, length = 17)
	String originatingNumber;
	


	@DataField(pos = 2, required = true)
	private Integer eventType;
	
	@DataField(pos = 3, required = true)
	private String eventTime;
	
	@DataField(pos = 4)
	private String empty_1;
	
	@DataField(pos = 5)
	private String empty_2;
	
	@DataField(pos = 6)
	private String empty_3;
	
	@DataField(pos = 7)
	private String empty_4;
	
	@DataField(pos = 8)
	private String empty_5;
	
	@DataField(pos = 9)
	private String empty_6;
	
	@DataField(pos = 10)
	private String empty_7;
	
	@DataField(pos = 11)
	private String empty_8;
	
	@DataField(pos = 12)
	private String empty_9;
	
	
	@DataField(pos = 13)
	private String r4bt_1;
	
	@DataField(pos = 14, required = true)
	private String dunsId;

	@DataField(pos = 15)
	private String r4bt_2;

	@DataField(pos = 16)
	private String r4bt_3;

	@DataField(pos = 17, required = true)
	private String dialedNumber;

	@DataField(pos = 18)
	private String pbxSuffix;
	
	@DataField(pos = 19, required = true, pattern="hh:mm:ss.zz")
	private String duration;
	
	@DataField(pos = 20)
	private String r4bt_4;
	
	
	@DataField(pos = 21)
	private String r4bt_5;
	
	
	@DataField(pos = 22)
	private String r4bt_6;

	@DataField(pos = 23)
	private String r4bt_7;

	@DataField(pos = 24)
	private String r4bt_8;

	@DataField(pos = 25)
	private String r4bt_9;

	@DataField(pos = 26)
	private String r4bt_10;

	@DataField(pos = 27)
	private String reRateIndicator;

	@DataField(pos = 28)
	private String r4bt_11;

	@DataField(pos = 29, required = true)
	private String accountNumber;

	@DataField(pos = 30)
	private String recordStatus;

	@DataField(pos = 31, required = true)
	private String wholesalePrice;

	@DataField(pos = 32)
	private String phoneBookCode;

	@DataField(pos = 33)
	private String r4bt_12;

	/**
	 * For event type 350
	 * This attribute has changed from Service Type to Bearer type group which is essentially the same thing. 
	 * Both values are used to indicate whether the call was ‘voice’ or ‘data’
	 */
	@DataField(pos = 34, required = true) 
	private String bearerTypeGroup;

	@DataField(pos = 35)
	private String r4bt_13;

	@DataField(pos = 36)
	private String r4bt_14;

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public String getOriginatingNumber() {
		return originatingNumber;
	}

	public void setOriginatingNumber(String originatingNumber) {
		this.originatingNumber = originatingNumber;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getDunsId() {
		return dunsId;
	}

	public void setDunsId(String dunsId) {
		this.dunsId = dunsId;
	}

	public String getDialedNumber() {
		return dialedNumber;
	}

	public void setDialedNumber(String dialedNumber) {
		this.dialedNumber = dialedNumber;
	}

	public String getPbxSuffix() {
		return pbxSuffix;
	}

	public void setPbxSuffix(String pbxSuffix) {
		this.pbxSuffix = pbxSuffix;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getReRateIndicator() {
		return reRateIndicator;
	}

	public void setReRateIndicator(String reRateIndicator) {
		this.reRateIndicator = reRateIndicator;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(String wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public String getPhoneBookCode() {
		return phoneBookCode;
	}

	public void setPhoneBookCode(String phoneBookCode) {
		this.phoneBookCode = phoneBookCode;
	}

	public String getBearerTypeGroup() {
		return bearerTypeGroup;
	}

	public void setBearerTypeGroup(String bearerTypeGroup) {
		this.bearerTypeGroup = bearerTypeGroup;
	}
	
	
}
