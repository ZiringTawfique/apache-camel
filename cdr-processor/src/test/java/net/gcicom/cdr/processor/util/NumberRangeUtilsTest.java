package net.gcicom.cdr.processor.util;

import static org.junit.Assert.*;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberRangeUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNumberRange() {
		
		List<Long> s = NumberRangeUtils.getNumberRanges("07976826134");
		
		assertNotNull(s);
		assertEquals(12, s.size());
	}

	@Test
	public void testGetNumberRangeNullInput() {
		
		List<Long> s = NumberRangeUtils.getNumberRanges(null);
		
		assertNotNull(s);
		assertEquals(0, s.size());

	}
	
	@Test
	public void testGetNumberRangeEmptyInput() {
		
		List<Long> s = NumberRangeUtils.getNumberRanges("");
		
		assertNotNull(s);
		assertEquals(0, s.size());

	}
	
	@Test
	public void testGetNumberRangeInternationalInput() {
		
		List<Long> s = NumberRangeUtils.getNumberRanges("+17976826134");
		
		assertNotNull(s);
		assertEquals(11, s.size());

	}
	
	@Test
	public void testGetNumberRangeInternational00Input() {
		
		List<Long> s = NumberRangeUtils.getNumberRanges("0017976826134");
		
		assertNotNull(s);
		assertEquals(11, s.size());

	}
	
	@Test
	public void testGetNumberRangeCharInput() {
		
		List<Long> s = NumberRangeUtils.getNumberRanges("sdd34");
		
		assertNotNull(s);
		assertEquals(0, s.size());

	}
	
	
	@Test
	public void testRegex() {
		
		assertEquals(false, "sdd34".matches("\\+?\\d"));
	
		
		
	}
}
