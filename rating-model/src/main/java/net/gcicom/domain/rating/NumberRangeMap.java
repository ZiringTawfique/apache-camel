package net.gcicom.domain.rating;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the NumberRangeMap database table.
 * 
 */
@Entity
@Table(name = "NumberRangeMap")
@NamedQuery(name="NumberRangeMap.findAll", query="SELECT n FROM NumberRangeMap n")
public class NumberRangeMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String numberRangeMapID;

	private BigInteger numberRange;

	private byte numberRangeBespokeFlag;

	private String numberRangeClassification;

	@Temporal(TemporalType.DATE)
	private Date numberRangeEndDate;

	private short numberRangeSource;

	@Temporal(TemporalType.DATE)
	private Date numberRangeStartDate;

	private String numberRangeType;

	public NumberRangeMap() {
	}

	public String getNumberRangeMapID() {
		return this.numberRangeMapID;
	}

	public void setNumberRangeMapID(String numberRangeMapID) {
		this.numberRangeMapID = numberRangeMapID;
	}

	public BigInteger getNumberRange() {
		return this.numberRange;
	}

	public void setNumberRange(BigInteger numberRange) {
		this.numberRange = numberRange;
	}

	public byte getNumberRangeBespokeFlag() {
		return this.numberRangeBespokeFlag;
	}

	public void setNumberRangeBespokeFlag(byte numberRangeBespokeFlag) {
		this.numberRangeBespokeFlag = numberRangeBespokeFlag;
	}

	public String getNumberRangeClassification() {
		return this.numberRangeClassification;
	}

	public void setNumberRangeClassification(String numberRangeClassification) {
		this.numberRangeClassification = numberRangeClassification;
	}

	public Date getNumberRangeEndDate() {
		return this.numberRangeEndDate;
	}

	public void setNumberRangeEndDate(Date numberRangeEndDate) {
		this.numberRangeEndDate = numberRangeEndDate;
	}

	public short getNumberRangeSource() {
		return this.numberRangeSource;
	}

	public void setNumberRangeSource(short numberRangeSource) {
		this.numberRangeSource = numberRangeSource;
	}

	public Date getNumberRangeStartDate() {
		return this.numberRangeStartDate;
	}

	public void setNumberRangeStartDate(Date numberRangeStartDate) {
		this.numberRangeStartDate = numberRangeStartDate;
	}

	public String getNumberRangeType() {
		return this.numberRangeType;
	}

	public void setNumberRangeType(String numberRangeType) {
		this.numberRangeType = numberRangeType;
	}

}