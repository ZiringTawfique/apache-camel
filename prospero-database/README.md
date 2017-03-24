Billing Database
====
- Currently, the canonical database structure is whatever's on Prospero.
- Eventually, we want a change-tracked human-readable model.

Requirements
----
- Should represent the complete intended database structure
- Include tables/views, *reference* data (BillType), *sample* data (Customer), functions/SPs, indexes/etc, permissions, ...
- Should be able to synchronize more than one environment from any current state to match this
- Should be graphically visible/modifiable
- Should be change-tracked human-readable text e.g. SQL (The binary MWB model was difficult to use)

Tools
----
- Liquibase/etc write down "create, alter, alter, alter" instead of the complete structure. Must go up/down ladder to modify.
- The binary MySQL Workbench model proved difficult to use.
- Use/modify a dev database, check in e.g. mysqldump each schema when modifying environments?


Testing
----
- Some tests should run against actual databases instead of abstractions, to prove that the changes made work.
- Run against a test DB, always have an outer transaction to revert.
- Run against a test DB, pick a fixed set of data ("customers named J") to own and not modify outside of.
- Run against a temporary database made from scripts (MariaDB4J, or manage binaries/folders manually)
- Run against an in-memory database e.g. H2 (was incompatible with MySQL syntax)
