package net.gcicom.cdr.processor;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@ComponentScan(basePackages = "net.gcicom")
public class JPAConfiguration {

	

	
}
