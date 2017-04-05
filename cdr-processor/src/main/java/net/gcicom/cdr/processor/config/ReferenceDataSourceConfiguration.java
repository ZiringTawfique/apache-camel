package net.gcicom.cdr.processor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"net.gcicom.cdr.processor.repository.reference"}, 
	entityManagerFactoryRef="referenceEntityMF", transactionManagerRef = "defaultTm")
public class ReferenceDataSourceConfiguration {

	
   
}
