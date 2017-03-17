package net.gcicom.cdr.processor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.gcicom.cdr.processor.entity.output.GCICDR;
import net.gcicom.cdr.processor.repository.GCICDRRepository;

@RunWith(SpringRunner.class)
public class CdrProcessorApplicationTests extends SpringRouteBuilder {

	@Test
	public void contextLoads() {

		//GCICDR cdrs = repo.findOne(new CDRKey());

		
		
	}

	@Override
	@Test
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("file:./src/test/data/BTOpenreach/?move=.success"
                + "&moveFailed=.error"
        		+ "&scheduler=spring&scheduler.cron=0+0/1+8-18+?+*+MON-FRI")
        .unmarshal().zip()
        .to("log:zip?level=INFO&showHeaders=true")
           .choice()
                .when(body().isNotNull())
                    .log("Extracting file: ${file:name}")
                    .to("file:./src/test/data/BTOpenreach/?fileName=${file:name}")    
            .endChoice()                                
        .end();
		
		System.out.println("-----------here you go-new------------------------------------------" );

	}
	
	
	@Test
	public void uncompressTest() {
		
		
		try {
			
			ZipInputStream zis = new ZipInputStream(new FileInputStream("./src/test/data/BTOpenreach/CDRM_EDGE_BTO_CDR_20170218051504_1.zip"));

		    ZipEntry entry;
		    
		    while ((entry = zis.getNextEntry()) != null) {
		    	
		        File f = new File("/tmp", entry.getName());
		        if (f.getParentFile().mkdirs() && !entry.isDirectory()) {
		        	
		            FileOutputStream fos = new FileOutputStream(f);
		            IOUtils.copy(zis, fos);
		            IOUtils.closeQuietly(fos);
		        }
		    }

		    IOUtils.closeQuietly(zis);
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
