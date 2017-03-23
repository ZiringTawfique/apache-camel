package net.gcicom.cdr.processor.test.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;

@Configuration
public abstract class TestDatabaseConfiguration {

	@Bean
	private static MariaDB4jSpringService initializeTestDBs() throws Exception {
		
		return new MariaDB4jSpringService();

	}
	
	
	public static void start() throws Exception {
		
		initializeTestDBs();
	}
	
}
