AllSpark:GCI Billing Engine
==========================

# Proposed Features

## Rating

## Charging

## Discounts

## Billing 

## Special Conditions (TODO)



# Technology Stack..



| Technology   	|      Version      	|  Remark 	|
|----------	|:-------------:	|------:	|
| Java 	|  1.8 	| 	|
| MariaDB 	|    5.x   	|    	|
| CentOS 	|  	|   	|        
| Spring 	|  	|   	|      
| Glassfish |4.11		|  |   	

# Getting Started with Dev Environment

#### Prerequisite

Java version: 1.8.0_121, vendor: Oracle Corporation

Apache Maven 3.3.9

git version 2.12.0.windows.1

#### Install Prerequisite

Go to dev (** \\ws-sma-bd01\AllSpark **) folder and copy full dev folder or required programs and install it. It is better to keep everything development in `C:/dev` as it minimizes variation in every developer machine

While installing GIT you can keep all default option as is.

Set `JAVA_HOME` and `M2_HOME` to 

	M2_HOME=C:\dev\apache-maven-3.3.9

	JAVA_HOME=C:\Program Files\Java\jdk1.8.0_121

Set PATH to 

	%PATH%;%M2_HOME%\bin;%JAVA_HOME%\bin


#### Verify

Type following on command prompt


	mvn -version //validate maven

	git --version //validate git
	
	java -version //validate java
	
# Databases

Billing database has number of schemas as listed here. Please see project prospero-database to see tables and schema in details


|  Schema | Usage  |
|---|---|
| _AllSpark_   | Common database for horizontally related tables  |
|  _ChargingDB_  | Database for charging feature  |
|  _RatingDB_  | Database for rating feature  |
|  _ImportedEventsDB_  | Database to store imported events after processing CDR files received from suppliers  |
|  _EventDB_  | Database for events feature  |
|  _CustomerDB_  | Database to store customer details  |
|  _InvoiceDB_  | Database to store invoice details  |
|  _RatedEventsDB_  | Database to store rated events  |
|  _ReferenceDB_  | TODO  |	

**Development database** 

	Host - 213.130.128.206

	user - billingUser

	password - Ask any team member
	
# Debug Mode

One can run any java process in debug mode by passing command line parameters as shown below. 8082 port number where debug process will be run and can be attached using any IDE.


	"-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8082"
	
# An Approach to Development

For all features and epic development, we'll be following popular [GITFlow](http://nvie.com/posts/a-successful-git-branching-model/) branching model as depicted below. 
![Gitflow branching model](git-model@2x.png "Gitflow") 

&copy; GCI




