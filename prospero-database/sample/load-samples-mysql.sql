/* G drive is on local machine. Can MySQL read network addresses? */

load data local infile 'G:/JackCoady/MainProjects/AllSpark/Billing/prospero-database/sample/csv/NumberRangeMap.csv'
replace
into table RatingDB.NumberRangeMap
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;


load data local infile 'G:/JackCoady/MainProjects/AllSpark/Billing/prospero-database/sample/csv/Address.csv'
replace
into table AllSpark.Address
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;


load data local infile 'G:/JackCoady/MainProjects/AllSpark/Billing/prospero-database/sample/csv/Customer.csv'
replace
into table AllSpark.Customer
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;


load data local infile 'G:/JackCoady/MainProjects/AllSpark/Billing/prospero-database/sample/csv/BillingReference.csv'
replace
into table AllSpark.BillingReference
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;


load data local infile 'G:/JackCoady/MainProjects/AllSpark/Billing/prospero-database/sample/csv/CustomerProductCharge.csv'
replace
into table AllSpark.CustomerProductCharge
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;


load data local infile 'G:/JackCoady/MainProjects/AllSpark/Billing/prospero-database/sample/csv/RatedEvent.csv'
replace
into table RatedEventsDB.RatedEvent
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;

