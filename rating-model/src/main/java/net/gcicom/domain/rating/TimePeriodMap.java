package net.gcicom.domain.rating;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalTime;


/**
 * The persistent class for the TimePeriodMap database table.
 * 
 */
@Entity
public class TimePeriodMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "TimePeriodID")
	private short id;

	@Column(name = "TimePeriodCode")
	private String code;

	@Column(name = "TimePeriodDescription")
	private String description;
	
	@Column(name = "TimePeriodEndDay")
	private int endDay;

	@Column(name = "TimePeriodEndTime")
	private LocalTime endTime;
	
	@Column(name = "TimePeriodStartDay")
	private int startDay;

	@Column(name = "TimePeriodStartTime")
	private LocalTime startTime;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEndDay() {
		return endDay;
	}

	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getStartDay() {
		return startDay;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	
}