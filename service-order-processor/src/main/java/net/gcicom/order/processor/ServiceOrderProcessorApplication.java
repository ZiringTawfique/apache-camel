package net.gcicom.order.processor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "net.gcicom.order.processor")
public class ServiceOrderProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderProcessorApplication.class, args);
	}
}
