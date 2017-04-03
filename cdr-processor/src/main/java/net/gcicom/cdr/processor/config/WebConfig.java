package net.gcicom.cdr.processor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"net.gcicom.cdr.processor"})
public class WebConfig extends WebMvcConfigurerAdapter {

}
