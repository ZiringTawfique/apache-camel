package net.gcicom.cdr.processor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;


public class ImportEventsLoaderInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext sctx) throws ServletException {
		WebApplicationContext ctx = getContext();
		sctx.addListener(new ContextLoaderListener(ctx));
        ServletRegistration.Dynamic dispatcher = sctx.addServlet("DispatcherServlet", new DispatcherServlet(ctx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
        sctx.setInitParameter("logbackConfigLocation","classpath:logback.xml");
        sctx.addListener(LogbackConfigListener.class);
		
	}

	private WebApplicationContext getContext() {
		
		AnnotationConfigWebApplicationContext acwctx = new AnnotationConfigWebApplicationContext();
		acwctx.setConfigLocations("net.gcicom.cdr.processor.config");
		return acwctx;
	}

}
