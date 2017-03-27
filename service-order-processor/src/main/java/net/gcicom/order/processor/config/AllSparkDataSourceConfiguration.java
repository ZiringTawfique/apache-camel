package net.gcicom.order.processor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"net.gcicom.order.processor.repository"}, entityManagerFactoryRef = "allsparkEntityMF")
public class AllSparkDataSourceConfiguration {
	
}
