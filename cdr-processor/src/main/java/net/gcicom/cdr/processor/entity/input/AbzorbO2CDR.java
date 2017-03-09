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

	@DataField(pos = 4, pattern = "dd/MM/yyyy", required = true)
	LocalDate date;
	
	@DataField(pos = 6, required = true)
	String duration;
	
	//@DataField(pos = 5, pattern = "h:m:s", required = false) //TODO need solution here as it is not working
	@DataField(pos = 5, required = true)
	String time;
	
	@DataField(pos = 2, required = true)
	String originatingNumber;
	
	@DataField(pos = 3, required = true)
	String dialedNumber;
	
	@DataField(pos = 1)
	String callType;
	
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
	
	@DataField(pos = 16)
	String chargeCode;
	
	@DataField(pos = 17)
	String tariff;
	
	@DataField(pos = 18)
	String mobileClass;
	
	@DataField(pos = 19)
	String remoteNetwork;
	

	String supplier = "AbzorbO2";
	
}
