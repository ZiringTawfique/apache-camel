AllSpark:GCI Billing Engine:CDR File processor
=================================================

This module is meant to import CDR files supplied by various suppliers.



# Run


	mvn clean wildfly:run

# Package

	mvn clean package


# New Supplier On-boarding


### Prerequisites

Access to CDRFormatSpecification spreadsheet,  Supplier Specification at file://ws-sma-bd01/AllSpark/CDR%20Spec/ 


### Add Environment Properties

Add below supplier specific properties in application.properties to support schedule timer run.

	gci.{user_definedSupplierIdentifier}.file.in.location=
	gci.{user_definedSupplierIdentifier}.file.name.pattern=
	gci.{user_definedSupplierIdentifier}.timer=
	gci.{user_definedSupplierIdentifier}.autostart=true 

autostart must be true unless you don't want to run it. For example on a developer machine, you may want to avoid running lots of polling jobs automatically

You can use [RegExr](http://regexr.com/ "Regular Expression Generator")  to generate & test regular expression of a desired file pattern.

for timer, application uses [Quartz](http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger "Cron Trigger") (or java) style timer cron expression so any such type valid expression should be sufficient. You can use non technical easy to use interface [Cron Maker](http://www.cronmaker.com/ "Cron Maker") to generate cron expression

**It is prudent to use environment specific application.properties instead one common one** 

### Add an Entry to Supplier Map

Go to net.gcicom.cdr.processor.common.SupplierMap.java and add an entry to SupplierMap with in the init() method. This entry basically maps file pattern to a supplier name by which you can lookup a supplier id from ReferenceDB.SupplierMap table.

Example - 

		s.put(ntsVodathusFP, NTS_VODATHUS);
		
Above entry maps ntsVodathusFP file pattern to NTS_VODATHUS supplier

### Add a Supplier's CDR object

Add a POJO to map supplier specific feed. It is an instance of @CsvRecord. To successfully maps these field to each field of feed, one should refer [specifications](file://ws-sma-bd01/AllSpark/CDR%20Spec)  of that supplier. @CsvRecord implementation pretty much process any text file including all popular formats like csv, tsv etc

Example VodafoneThusIDA.java POJO is mapping VodafoneThusIDA supplier as per its  specification at (file://ws-sma-bd01/AllSpark/CDR%20Spec/Vodaphone/THUS/Vodafone%20Thus/New_Outbound_CDR_Format.pdf "VodafoneThusIDA Specification") 


### Add a CDRMapper

This class is an instance of CDRMapper and convert supplier specific CDR to GCI format CDR. This class also perform required validation and populate missing or required fields from database. 

You may need to write few utility methods to retrieve or transform required data. For example to get supplier name from input file name VodafoneThusIDAMapper has 
an utility method called getSupplierName which manipulate file name to return a supplier name.



### Add a Processor

Adding a processor effectively means assembling everything mentioned above. It is an instance of BaseProcessor.java and contains two method.

configure() essentially is boiler plate method, however one needs to implement mapCSVRowToVendorCdr(). It has been left to implement by programmer because 
mapping process and resulting data handling varies from supplier to supplier.  



&copy; GCI
