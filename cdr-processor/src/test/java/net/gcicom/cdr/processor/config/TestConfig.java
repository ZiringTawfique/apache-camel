package net.gcicom.cdr.processor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = "net.gcicom.cdr.processor.config")
@ComponentScan(basePackages = {"net.gcicom.cdr.processor"})
public class TestConfig {

}
