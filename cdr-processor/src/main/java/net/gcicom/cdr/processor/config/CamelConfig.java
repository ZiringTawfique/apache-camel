package net.gcicom.cdr.processor.config;

import org.apache.camel.model.ContextScanDefinition;
import org.apache.camel.spring.CamelContextFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.gcicom.cdr.processor.supplier")
public class CamelConfig {

	@Bean
    public CamelContextFactoryBean camelContextFactoryBean(
            ApplicationContext applicationContext) {
        CamelContextFactoryBean ccfb = new CamelContextFactoryBean();
        ccfb.setApplicationContext(applicationContext);
        ccfb.setId("camelContext");
        ccfb.setContextScan(new ContextScanDefinition());
        return ccfb;
    }
	
	
}
