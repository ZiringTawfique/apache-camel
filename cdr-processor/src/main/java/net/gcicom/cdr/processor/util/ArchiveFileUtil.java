package net.gcicom.cdr.processor.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class ArchiveFileUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ArchiveFileUtil.class);
	
	/**
	 * @param source
	 * @param outputLocation
	 */
	public static void unGzip(String source, String fileName) {

		LOG.debug("unGzip");

	      try {
		      byte[] bf = new byte[1024];

	    	  GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(source));
	    	  FileOutputStream fos = new FileOutputStream(fileName);
	    	  
	    	  int bytes;

	    	  while ((bytes = gzip.read(bf)) > 0) {

	    		  LOG.info("Writing to {}", fileName);
	    		  fos.write(bf, 0, bytes);
	    		  fos.flush();

	          }
	    	  
	    	  gzip.close();

	    	  fos.close();
	    	  
	      } catch (IOException e) {
	    	  
	    	  LOG.error("Error while processing error {}", e);
	    	  
	      }
	}
	
	
	/** Uncompresses zip and tar files
	 * @param source
	 * @param location
	 */
	public static void unCompress(String source, String location) {

		LOG.info("unCompress {}", source);
	      try {

	    	  ArchiveInputStream tis = new ArchiveStreamFactory().createArchiveInputStream(new BufferedInputStream(new FileInputStream(source)));
	    	  
	    	  ArchiveEntry entry;
	    	  
	          while((entry = tis.getNextEntry()) !=null ){
	    		  
		    	  FileOutputStream fos = new FileOutputStream(location+entry.getName());
		    	  
		    	  LOG.info("Writing to {}", location);
	    		  IOUtils.copy(tis, fos);
		    	  
		    	  fos.flush();
		    	  fos.close();
		    	  
	    	  }
	          
	          tis.close();

	    	  
	      } catch (IOException e) {
	    	  
	    	  LOG.error("Error while processing error {}", e);
	    	  
	      } catch (ArchiveException e) {
	    	  
	    	  LOG.error("Error while processing error {}", e);

		}
	}

}
