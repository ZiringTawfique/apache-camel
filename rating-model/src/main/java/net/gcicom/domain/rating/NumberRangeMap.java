package net.gcicom.domain.rating;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the NumberRangeMap database table.
 * 
 */
@Entity
@Table(name = "NumberRangeMap")
public class NumberRangeMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numberRangeMapID;

	private Long numberRange;

	@Column(columnDefinition = "BIT", length = 1)
	private boolean numberRangeBespokeFlag;

	private String numberRangeClassification;

	@Temporal(TemporalType.DATE)
	private Date numberRangeEndDate;

	private short numberRangeSource;

	@Temporal(TemporalType.DATE)
	private Date numberRangeStartDate;

	private String numberRangeType;

	public NumberRangeMap() {
	}

	public Long getNumberRangeMapID() {
		return this.numberRangeMapID;
	}

	public void setNumberRangeMapID(Long numberRangeMapID) {
		this.numberRangeMapID = numberRangeMapID;
	}

	public Long getNumberRange() {
		return this.numberRange;
	}

	public void setNumberRange(Long numberRange) {
		this.numberRange = numberRange;
	}

	public boolean getNumberRangeBespokeFlag() {
		return this.numberRangeBespokeFlag;
	}

	public void setNumberRangeBespokeFlag(boolean numberRangeBespokeFlag) {
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