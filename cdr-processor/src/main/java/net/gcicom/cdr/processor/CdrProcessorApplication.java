package net.gcicom.cdr.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "net.gcicom.cdr.processor")
public class CdrProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdrProcessorApplication.class, args);
	}
}
