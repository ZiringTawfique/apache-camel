package net.gcicom.common.util;

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
		GZIPInputStream gzip = null;
		FileOutputStream fos = null;
	      try {
		      byte[] bf = new byte[1024];

	    	  gzip = new GZIPInputStream(new FileInputStream(source));
	    	  fos = new FileOutputStream(fileName);
	    	  
	    	  int bytes;

	    	  while ((bytes = gzip.read(bf)) > 0) {

	    		  LOG.info("Writing to {}", fileName);
	    		  fos.write(bf, 0, bytes);
	    		  fos.flush();

	          }
	    	  
	    	  
	    	  
	      } catch (IOException e) {
	    	  
	    	  LOG.error("Error while processing error {}", e);
	    	  
	      } finally {
			
	    	  IOUtils.closeQuietly(gzip);
	    	  IOUtils.closeQuietly(fos);
	    	  
		}
	}
	
	
	/** Uncompresses zip and tar files
	 * @param source
	 * @param location
	 */
	public static void unCompress(String source, String location) {

		LOG.info("unCompress {}", source);
		
		ArchiveInputStream tis = null;
		
	      try {

	    	  tis = new ArchiveStreamFactory().createArchiveInputStream(new BufferedInputStream(new FileInputStream(source)));
	    	  
	    	  ArchiveEntry entry;
	    	  
	          while((entry = tis.getNextEntry()) !=null ){
	    		  
		    	  FileOutputStream fos = new FileOutputStream(location+entry.getName());
		    	  
		    	  LOG.info("Writing to {}", location);
	    		  IOUtils.copy(tis, fos);
		    	  
		    	  fos.flush();
		    	  fos.close();
		    	  
	    	  }
	          

	    	  
	      } catch (IOException e) {
	    	  
	    	  LOG.error("Error while processing error {}", e);
	    	  
	      } catch (ArchiveException e) {
	    	  
	    	  LOG.error("Error while processing error {}", e);

	      } finally {
			
	    	  IOUtils.closeQuietly(tis);
	      }
	}

}
