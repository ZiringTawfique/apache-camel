/**
 * 
 */
package net.gcicom.cdr.processor.entity.input;

import java.time.LocalDate;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * @author Prashant.Nema
 *
 */
@CsvRecord(separator = ",", skipFirstLine = false)//because we are streaming rows from the file we need this. I know batch processing is good but here unit of work is csv row
public class AbzorbO2CDR {

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOriginatingNumber() {
		return originatingNumber;
	}

	public void setOriginatingNumber(String originatingNumber) {
		this.originatingNumber = originatingNumber;
	}

	public String getDialedNumber() {
		return dialedNumber;
	}

	public void setDialedNumber(String dialedNumber) {
		this.dialedNumber = dialedNumber;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getMb() {
		return Mb;
	}

	public void setMb(String mb) {
		Mb = mb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeBand() {
		return timeBand;
	}

	public void setTimeBand(String timeBand) {
		this.timeBand = timeBand;
	}

	public String getSalesprice() {
		return salesprice;
	}

	public void setSalesprice(String salesprice) {
		this.salesprice = salesprice;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public Long getTariff() {
		return tariff;
	}

	public void setTariff(Long tariff) {
		this.tariff = tariff;
	}

	public String getMobileClass() {
		return mobileClass;
	}

	public void setMobileClass(String mobileClass) {
		this.mobileClass = mobileClass;
	}

	public String getRemoteNetwork() {
		return remoteNetwork;
	}

	public void setRemoteNetwork(String remoteNetwork) {
		this.remoteNetwork = remoteNetwork;
	}

	@DataField(pos = 1, length=1)
	String callType;
	
	@DataField(pos = 2, required = true, length = 17)
	String originatingNumber;
	
	@DataField(pos = 3, required = true)
	String dialedNumber;
	
	@DataField(pos = 4, pattern = "dd/MM/yyyy", required = true)
	LocalDate date;
	
	//@DataField(pos = 5, pattern = "h:m:s", required = false) //TODO need solution here as it is not working
	@DataField(pos = 5, required = true)
	String time;
	
	@DataField(pos = 6, required = true)
	String duration;
	
	
	@DataField(pos = 7)
	String Mb;
	
	@DataField(pos = 8)
	String description;
	
	@DataField(pos = 9)
	String timeBand;
	
	@DataField(pos = 10)
	String salesprice;
	
	@DataField(pos = 11)
	String extension;
	
	@DataField(pos = 12)
	String user;
	
	@DataField(pos = 13)
	String department;
	
	@DataField(pos = 14)
	String countryOfOrigin;
	
	@DataField(pos = 15)
	String network;
	
	@DataField(pos = 17)
	String chargeCode;
	
	@DataField(pos = 16)
	Long tariff;
	
	@DataField(pos = 18)
	String mobileClass;
	
	@DataField(pos = 19)
	String remoteNetwork;
	

	String supplier = "AbzorbO2";
	
}
