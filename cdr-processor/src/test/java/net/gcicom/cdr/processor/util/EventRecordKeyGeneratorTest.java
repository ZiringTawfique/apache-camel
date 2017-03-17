package net.gcicom.cdr.processor.util;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.gcicom.cdr.processor.entity.output.GCICDR;

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
		
		GCICDR cdr = new GCICDR();
		
		cdr.setCustomerId(L_DUMMY);
		cdr.setDialledCLI(DUMMY);
		cdr.setEventDurationSecs(2);
		cdr.setEventFileId(L_DUMMY);
		cdr.setEventTime(Timestamp.valueOf(LocalDateTime.now()));
		cdr.setEventTypeId(L_DUMMY);
		cdr.setOriginatingCLI(DUMMY);
		cdr.setPreRatedEventFlag(DUMMY);
		cdr.setPresentationCLI(DUMMY);
		cdr.setSupplierAccountNumber(DUMMY);
		cdr.setSupplierId(L_DUMMY);
		cdr.setTerminatingCLI(DUMMY);

		String digest = EventRecordKeyGenerator.getEventRecordHash(cdr);
		
		assertNotNull(digest);
		
		
	}
	
	@Test
	public void testGetEventRecordHashEmptyCDR() {
		thrown.expect(IllegalArgumentException.class);
		EventRecordKeyGenerator.getEventRecordHash(new GCICDR());
	}

}
