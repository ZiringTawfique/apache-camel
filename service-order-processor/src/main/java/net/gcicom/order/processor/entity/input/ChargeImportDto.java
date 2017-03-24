/**
 * 
 */
package net.gcicom.order.processor.entity.input;

import java.time.LocalDate;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * @author Sathish Natarajan
 *
 */
@CsvRecord(separator = ",", skipFirstLine = false)
public class ChargeImportDto {

	@DataField(pos = 2, required = true)
	String itemType;
	
	@DataField(pos = 3, required = true)
	String accountNumber;
	
	@DataField(pos = 1)
	String actionCode;
	
	


	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

		

	String supplier = "GCI";
	
}
