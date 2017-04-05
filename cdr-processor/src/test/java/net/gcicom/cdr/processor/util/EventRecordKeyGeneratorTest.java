package net.gcicom.cdr.processor.util;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.gcicom.domain.imported.events.ImportedEvent;

public class EventRecordKeyGeneratorTest {

	private static final String DUMMY = "DUMMY";
	private static final Long L_DUMMY = 1L;
	
	@Rule 
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEventRecordHashNullArray() {
		thrown.expect(IllegalArgumentException.class);
		EventRecordKeyGenerator.getEventRecordHash(new Object[]{null});
	}
	
	@Test
	public void testGetEventRecordHashEmptyArray() {
		thrown.expect(IllegalArgumentException.class);
		EventRecordKeyGenerator.getEventRecordHash("");
	}
	
	@Test
	public void testGetEventRecordHashOneEmpty() {
		thrown.expect(IllegalArgumentException.class);
		EventRecordKeyGenerator.getEventRecordHash("Test", 1L, "");
	}
	
	@Test
	public void testGetEventRecordHashOneNull() {
		thrown.expect(IllegalArgumentException.class);
		EventRecordKeyGenerator.getEventRecordHash("Test", 1L, null);
	}
	
	@Test
	public void testGetEventRecordHash() {

		String digest = EventRecordKeyGenerator.getEventRecordHash("Test", 1L, "checkcheck");
		
		assertNotNull(digest);
		
		
	}
	
	@Test
	public void testGetEventRecordHashFromCDR() {
		
		ImportedEvent cdr = new ImportedEvent();
		
		cdr.setCustomerID(L_DUMMY);
		cdr.setDialledCLI(DUMMY);
		cdr.setEventDurationSecs(2);
		cdr.setEventFileID(L_DUMMY);
		cdr.setEventTime(LocalDateTime.now());
		cdr.setEventTypeID(L_DUMMY);
		cdr.setOriginatingCLI(DUMMY);
		cdr.setPreRatedEventFlag(0);
		cdr.setPresentationCLI(DUMMY);
		cdr.setSupplierAccountNumber(DUMMY);
		cdr.setSupplierID(100);
		cdr.setTerminatingCLI(DUMMY);
		cdr.setFileChecksum("aaa");
		String digest = EventRecordKeyGenerator.getEventRecordHash(cdr);
		
		assertNotNull(digest);
		
		
	}
	
	@Test
	public void testGetEventRecordHashEmptyCDR() {
		thrown.expect(IllegalArgumentException.class);
		EventRecordKeyGenerator.getEventRecordHash(new ImportedEvent());
	}

}
