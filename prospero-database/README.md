Billing Database
====
- `structure` contains `CREATE TABLE` scripts for each database.
- `sample` contains CSVs with sample data, the queries to collect the sample (initially Eclipse+Core, later from Prospero) and a script for H2 to create `structure` with the sample data.

Automated Testing
----
H2 database runs inside the JVM and is partially syntax-compatible with MySQL. This should be usable inside tests from JPA.
- e.g. `jdbc:h2:mem;INIT=RUNSCRIPT FROM 'sample/insert/initH2Sample.sql'`
- Run code that uses and modifies the sample, then check results with further queries.
- We may want samples specialized to individual tests so that they're independent.

Modifying DB Structure
----
`mysql-sql-schema` compares a schema made from `CREATE TABLE` scripts to a live database to guide making changes.
It requires a local install of MySQL for its tools e.g. `mysqldbcompare` and `mysqldiff` (these only compare live databases)
It requires a running instance of MySQL to run the scripts in - prefer a local copy instead of live for this.