package net.gcicom.cdr.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import net.gcicom.cdr.processor.common.SupplierMap;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Profile(value = "${spring.profiles.active}")
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CdrProcessorApplication.class);
	}
	
	
	@Autowired
	public SupplierMap smap;


}
