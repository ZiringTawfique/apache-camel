package net.gcicom.cdr.processor.util;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DateTimeUtilTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWeekDayFlag() {
		
		assertEquals(6, DateTimeUtil.getWeekDayFlag(LocalDateTime.of(2017, 3, 4, 12, 3)));
	}
	
	@Test
	public void testGetWeekDayFlagFail() {
		
		thrown.expect(IllegalArgumentException.class);
		DateTimeUtil.getWeekDayFlag(null);
	}
	
	@Test
	public void testGetDurationInSeconds() {
		
		assertEquals(1813, DateTimeUtil.getDurationInSeconds("00:30:13"));
	}
	
	@Test
	public void testGetDurationInSecondsInvalidDuration() {
		
		thrown.expect(IllegalArgumentException.class);
		DateTimeUtil.getDurationInSeconds("003013");
	}
	
	@Test
	public void testGetDurationInSecondsEmptyDuration() {
		
		thrown.expect(IllegalArgumentException.class);
		DateTimeUtil.getDurationInSeconds("");
	}
	
	//11:24:36.52
	@Test
	public void testGetDurationInSeconds11243652() {
		
		assertEquals(41076, DateTimeUtil.getDurationInSeconds("11:24:36.52"));
	}

}
