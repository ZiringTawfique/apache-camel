package net.gcicom.common.util;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArchiveFileUtilTest {

	private static final String LOCATION = "src/test/data/BTOpenreach/.compressed/";
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUnGzip() {
		
		ArchiveFileUtil.unGzip(LOCATION + "Daily_63324_20161201_1227_V1.txt.gz", LOCATION + "Daily_63324_20161201_1227_V1.txt");

		assertTrue(Files.exists(new File(LOCATION).toPath(), LinkOption.NOFOLLOW_LINKS));
	}

	@Test
	public void testUnCompress() {
		
		ArchiveFileUtil.unCompress(LOCATION + "CDRM_EDGE_BTO_CDR_20170218051504_1.zip", LOCATION);
		assertTrue(Files.exists(new File(LOCATION).toPath(), LinkOption.NOFOLLOW_LINKS));

	}

	
}