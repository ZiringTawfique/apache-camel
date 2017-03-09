package net.gcicom.cdr.processor;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.gcicom.cdr.processor.entity.output.CDRKey;
import net.gcicom.cdr.processor.entity.output.GCICDR;
import net.gcicom.cdr.processor.repository.GCICDRRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CdrProcessorApplicationTests {

	@Autowired
	GCICDRRepository repo;
	
	@Test
	public void contextLoads() {
		System.out.println("-----------here you go-------------------------------------------" );

		for(GCICDR cdr : repo.findAll()) {
			
			System.out.println("-----------here you go-------------------------------------------" + cdr);
		}
		
		GCICDR cdrs = repo.findOne(new CDRKey());
				
		
		
		
		
		
		
		
	}

}
