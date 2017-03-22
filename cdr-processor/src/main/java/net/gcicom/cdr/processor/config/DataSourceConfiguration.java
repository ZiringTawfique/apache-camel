package net.gcicom.cdr.processor.config;

import static net.gcicom.cdr.processor.config.AppProperties.DRIVER_CLASS_NAME;
import static net.gcicom.cdr.processor.config.AppProperties.IMPORTED_EVENT_DB_PASSWORD_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.IMPORTED_EVENT_DB_URL_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.IMPORTED_EVENT_DB_USER_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.RATING_DB_PASSWORD_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.RATING_DB_URL_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.RATING_DB_USER_KEY;

import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@PropertySource({"classpath:application.properties"})
public class DataSourceConfiguration {

	
	@Autowired
	Environment env;
	
    @Bean(name = "importedEventsEntityMF")
    @Primary
    public LocalContainerEntityManagerFactoryBean importedEventsEntityMF() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setBeanName("importedEventsEntityMF");
        em.setDataSource(getImportedEventsDS());
        em.setPackagesToScan(new String[] { "net.gcicom.domain.imported.events" });
        
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        Properties p = hibernateSpecificProperties();
        p.setProperty("hibernate.ejb.entitymanager_factory_name", "importedEventsEntityMF");
        em.setJpaProperties(p);
        return em;
    }

    @Bean(name = "getImportedEventsDS")
    @Primary
    public DataSource getImportedEventsDS() {
    	
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(IMPORTED_EVENT_DB_URL_KEY));
        dataSource.setUsername(env.getProperty(IMPORTED_EVENT_DB_USER_KEY));
        dataSource.setPassword(env.getProperty(IMPORTED_EVENT_DB_PASSWORD_KEY));

        return dataSource;
    }
    
    @Bean(name = "ratingEntityMF")
    public LocalContainerEntityManagerFactoryBean ratingEntityMF() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getRatingDS());
        em.setPackagesToScan(new String[] { "net.gcicom.domain.rating" });
        em.setPersistenceProvider(new HibernatePersistenceProvider());

        Properties p = hibernateSpecificProperties();
        p.setProperty("hibernate.ejb.entitymanager_factory_name", "ratingEntityMF");
        em.setJpaProperties(p);
        return em;
    }

    @Bean(name = "getRatingDS")
    public DataSource getRatingDS() {
    	
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(RATING_DB_URL_KEY));
        dataSource.setUsername(env.getProperty(RATING_DB_USER_KEY));
        dataSource.setPassword(env.getProperty(RATING_DB_PASSWORD_KEY));

        return dataSource;
    }
    
    @Bean
    public Properties hibernateSpecificProperties(){
    	
    	final Properties p = new Properties();
    	p.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        p.setProperty("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        p.setProperty("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        p.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("spring.jpa.hibernate.cache.use_second_level_cache"));
        p.setProperty("hibernate.cache.use_query_cache", env.getProperty("spring.jpa.hibernate.cache.use_query_cache"));
        
    	return p;
    	
    }

}
