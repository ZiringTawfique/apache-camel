package net.gcicom.cdr.processor;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.gcicom.order.processor.entity.output.CDRKey;
import net.gcicom.order.processor.entity.output.GCIChargeImport;
import net.gcicom.order.processor.repository.GCIChargeImportRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceOrderProcessorApplicationTests {

	@Autowired
	GCIChargeImportRepository repo;
	
	@Test
	public void contextLoads() {
		System.out.println("-----------here you go-------------------------------------------" );

		for(GCIChargeImport cdr : repo.findAll()) {
			
			System.out.println("-----------here you go-------------------------------------------" + cdr);
		}
		
		GCIChargeImport cdrs = repo.findOne(new CDRKey());
				
		
		
		
		
		
		
		
	}

}
