AllSpark:GCI Service order Engine:
Service Order processor
=================================================

This module is meant to import service order .Which has Billing references and Customer Product charges



# Run

## Developer m/c run

	mvn clean wildfly:run
	mvn -Dspring.profiles.active=local -DskipTests=true spring-boot:run
	
	Command To run in embedded tomcat inside spring boot
	mvn spring-boot:run
	
## Dev Server

Application server should be started with -Dspring.profiles.active=development jvm arguments, this is assuming development server is using an installation of an application server. 
	
	**Above active profile will pickup application.properties and application-{env}.properties where env is development or local**  

# Package

	mvn clean package


# Service Order Pocessor 





### Add Environment Properties

Add below supplier specific properties in application.properties to support schedule timer run.

	gci.service.order.file.in.location=
	gci.service.order.file.error.location=
	gci.service.order.file.out.location=
	gci.{user_defined}.timer=
	gci.{user_defined}.autostart=true 

gci.service.order.file.in.location -->	The location where the new service order , excel sheet should be placed 
gci.service.order.file.error.location --> Aftre processing records which fails validation are placed in this location
gci.service.order.file.out.location   --> Records which passed validations are placed in this lcoation
 
autostart must be true unless you don't want to run it. For example on a developer machine, you may want to avoid running lots of polling jobs automatically

You can use [RegExr](http://regexr.com/ "Regular Expression Generator")  to generate & test regular expression of a desired file pattern.

for timer, application uses [Quartz](http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger "Cron Trigger") (or java) style timer cron expression so any such type valid expression should be sufficient. You can use non technical easy to use interface [Cron Maker](http://www.cronmaker.com/ "Cron Maker") to generate cron expression

**It is prudent to use environment specific application.properties instead one common one** 



ServiceOrderProcessor Flow
The Input excel sheet is processed by APache camel Routes

Excel is mapped to Dto using the below bean and its methods
    .bean(excelConverterBean,"processExcelData")

The dto is  saved to database - according to the logic either in AllSpark`.`BillingReference or both (AllSpark`.`CustomerProductCharge and AllSpark`.`BillingReference  )
     .bean(billingReferenceMapper, "convertToBillingReference")
	 
	 
There is quite a bit of validation  done in the class


					//ALl the field level  validation -NULL checks primarily 
					BillingReferenceValidator.billingReferenceNullValidations(source);
					
					
					//ALl the field level  validation -Allowed characters checks primarily 
					BillingReferenceValidator.billingReferenceAllowedCharactersCheck(source);
					
					//ALl the field level  validation -Date validations checks primarily 
					BillingReferenceValidator.billingReferenceDateValidations(source);
					
				
				If the record passed validation they are persited in the database and the same record is written in to a excel sheet  at the below location 
				//gci.service.order.file.out.location
				
				The reocrds that fails validation are written in to a excel at the below location
				//gci.service.order.file.error.location
				
				
When ever the validation fails , we throw exceptions and the ServiceOrderProcessorErrorHandler writes the error record in to the excel sheet.
				
				
&copy; GCI


//Notes
When migrating to Wildfly the  HibernateJpaVendorAdapter has to be used  as the JpaVendor Adapter

We use PlatformTransactionManager for persisitence