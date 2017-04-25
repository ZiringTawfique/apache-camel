package net.gcicom.cdr.processor.config;

import static net.gcicom.cdr.processor.config.AppProperties.DRIVER_CLASS_NAME;
import static net.gcicom.cdr.processor.config.AppProperties.IMPORTED_EVENT_DB_PASSWORD_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.IMPORTED_EVENT_DB_URL_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.IMPORTED_EVENT_DB_USER_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.RATING_DB_PASSWORD_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.RATING_DB_URL_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.RATING_DB_USER_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.ALL_SPARK_DB_PASSWORD_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.ALL_SPARK_DB_URL_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.ALL_SPARK_DB_USER_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.REFERENCE_DB_PASSWORD_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.REFERENCE_DB_URL_KEY;
import static net.gcicom.cdr.processor.config.AppProperties.REFERENCE_DB_USER_KEY;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:application.properties"})
public class DataSourceConfiguration {

	
	@Autowired
	Environment env;
	
    @Bean(name = "importedEventsEntityMF")
    @Primary
    public LocalContainerEntityManagerFactoryBean importedEventsEntityMF() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("importedEventsEntityMF");
        em.setBeanName("importedEventsEntityMF");
        em.setDataSource(importedEventsDS());
        em.setPackagesToScan(new String[] { "net.gcicom.domain.imported.events" });
        
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties p = hibernateSpecificProperties();
        p.setProperty("hibernate.ejb.entitymanager_factory_name", "importedEventsEntityMF");
        em.setJpaProperties(p);
        return em;
    }

    @Bean(name = "importedEventsDS")
    @Primary
    public DataSource importedEventsDS() {
    	
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
        em.setDataSource(ratingDS());
        em.setPersistenceUnitName("ratingEntityMF");
        em.setPackagesToScan(new String[] { "net.gcicom.domain.rating" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties p = hibernateSpecificProperties();
        p.setProperty("hibernate.ejb.entitymanager_factory_name", "ratingEntityMF");
        em.setJpaProperties(p);
        return em;
    }

    @Bean(name = "ratingDS")
    public DataSource ratingDS() {
    	
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(RATING_DB_URL_KEY));
        dataSource.setUsername(env.getProperty(RATING_DB_USER_KEY));
        dataSource.setPassword(env.getProperty(RATING_DB_PASSWORD_KEY));

        return dataSource;
    }
    
    @Bean(name = "allsparkEntityMF")
    public LocalContainerEntityManagerFactoryBean allsparkEntityMF() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(allsparkDS());
        em.setPersistenceUnitName("allsparkEntityMF");
        em.setPackagesToScan(new String[] { "net.gcicom.domain.allspark" });
        //em.setPersistenceProvider(new HibernatePersistenceProvider());
        HibernateJpaVendorAdapter a = new HibernateJpaVendorAdapter();
        
        em.setJpaVendorAdapter(a);
        Properties p = hibernateSpecificProperties();
        p.setProperty("hibernate.ejb.entitymanager_factory_name", "allsparkEntityMF");
        
        em.setJpaProperties(p);
        return em;
    }

    @Bean(name = "allsparkDS")
    public DataSource allsparkDS() {
    	
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(ALL_SPARK_DB_URL_KEY));
        dataSource.setUsername(env.getProperty(ALL_SPARK_DB_USER_KEY));
        dataSource.setPassword(env.getProperty(ALL_SPARK_DB_PASSWORD_KEY));

        return dataSource;
    }
    
    @Bean(name = "referenceEntityMF")
    public LocalContainerEntityManagerFactoryBean referenceEntityMF() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(referenceDS());
        em.setPersistenceUnitName("referenceEntityMF");
        em.setPackagesToScan(new String[] { "net.gcicom.domain.reference" });
        //em.setPersistenceProvider(new HibernatePersistenceProvider());
        HibernateJpaVendorAdapter a = new HibernateJpaVendorAdapter();
        
        em.setJpaVendorAdapter(a);
        Properties p = hibernateSpecificProperties();
        p.setProperty("hibernate.ejb.entitymanager_factory_name", "referenceEntityMF");
        
        em.setJpaProperties(p);
        return em;
    }

    @Bean(name = "referenceDS")
    public DataSource referenceDS() {
    	
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
        dataSource.setUrl(env.getProperty(REFERENCE_DB_URL_KEY));
        dataSource.setUsername(env.getProperty(REFERENCE_DB_USER_KEY));
        dataSource.setPassword(env.getProperty(REFERENCE_DB_PASSWORD_KEY));

        return dataSource;
    }
    @Bean
    public Properties hibernateSpecificProperties(){
    	
    	final Properties p = new Properties();
    	p.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        p.setProperty("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        p.setProperty("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        p.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        p.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
    	return p;
    	
    }
    
    @Bean(name = "defaultTm")
    public PlatformTransactionManager transactionManager() {
    	
    	JpaTransactionManager txManager = new JpaTransactionManager();
    	txManager.setEntityManagerFactory(importedEventsEntityMF().getObject());
    	return txManager;
    }

}
