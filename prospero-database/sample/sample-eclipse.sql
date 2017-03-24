-- 1. run these queries in SSMS while connected to EclipseSQL-GCI
-- 2. "Save Results As" CSV (may need to have/omit headers)
-- 3. import with LOAD DATA INFILE (preset columns) or Table Data Import Wizard (slow)

-- Customer
select
  cust.[Customer ID] as CustomerID,
  cust.[Account Number] as AccountNumber,
  cust.Company,
  cust.[Customer ID] as AddressID
from [EclipseSQL-GCI].dbo.Customers cust
where cust.[Customer ID] >= 234200 and cust.[Customer ID] <= 234300


-- Address
select
  1 as AddressID,
  'Global House' as Line1,
  '2 Crofton Close' as Line2,
  'Lincoln' as Line3,
  'Lincolnshire' as County,
  'LN3 4NT' as Postcode,
  'United Kingdom' as Country -- Customers in sample have 'UK' instead of 'United Kingdom'
union all
select
  cust.[Customer ID] as AddressID,
  cust.Address1 as Line1,
  cust.Address2 as Line2,
  cust.Address3 as Line3,
  cust.County,
  cust.Postcode,
  cust.Country
from [EclipseSQL-GCI].dbo.Customers cust
where cust.[Customer ID] >= 234200 and cust.[Customer ID] <= 234300
;

-- CustomerProductCharge
select
  bc.ID as CustomerProductChargeID,
  cust.[Customer ID] as CustomerID,
  cust.[Account Number] as AccountNumber,
  null as OrderNumber,
  Tel_ID as BillingReferenceID,
  Product_ID as ProductID,
  prod.ProductDescription as DefaultProductDescription, -- "TempProductDuringCleanUp"
  [Description] as ChargeInstanceDescription,
  Quantity as ChargeQuantity,
  [Repeat] as ProductChargeFrequencyID,
  PriceCost as UnitCostToGCI,
  Price as UnitChargeToCustomer,
  prod.TaxTypeFlag as ChargeTaxTypeFlag, -- is changing to be per-charge, then.
  Start as ChargeStartDate,
  [End] as ChargeEndDate,
  LastTo as ChargeBilledUntil,
  null as SupplierContractStartDate,
  null as SupplierContractEndDate,
  null as CustomerContractStartDate,
  null as CustomerContractEndDate,
  Credit as AllowCreditBackFlag,
  CustomerReference as CustomerCustomReference,
  ChargeCreated as ChargeInstanceCreateDate,
  DatabaseUser as ChargeInstanceCreateUser -- or is that 'last modify' user?
from [EclipseSQL-GCI].dbo.BILL_Charges bc
inner join [EclipseSQL-GCI].dbo.Customers cust on bc.Customer_ID = cust.[Customer ID]
left outer join [EclipseSQL-GCI].dbo.Products prod on bc.[Product_ID] = prod.[ProductID]
where cust.[Customer ID] >= 234200 and cust.[Customer ID] <= 234300


select * from [EclipseSQL-GCI].dbo.[BILL Phonenum Add]
where [Tel ID] = 1659852

select * from [EclipseSQL-GCI].dbo.[BILL Phonenums]
where [Tel ID] = 1659852


-- BillingReference
-- "Billing Reference" Bill Phonenums x "Group" Bill Main
	  -- other stuff from GenerateInvoiceBackups
	  -- att.Custom11: CustomerReferenceBK
	  -- (bill_charges) bc.CustomerReference: CustomerReferenceBC
	  -- BC.SerialNo as ProjectNumber
	  -- 'InChSum.ChargeInstanceDescription' - i don't have this
	  -- BM.Name as GroupName
select
  num.[Tel ID] as BillingReferenceID,
  cust.[Customer ID] as CustomerID,
  cust.[Account Number] as AccountNumber,
  null as OrderNumber, --??? OrderNumber is ambiguous. BT_OrderNo + CPS_OrderNo are blank
  null as NodeID, --???
  null as AssetID, --??? Not cross-referenced to Core yet.
  num.[Phonenumber] as BillingReference,
  [Description] as BillingReferenceDescription,
  CONVERT(date,att.ConDate) as BillingReferenceStartDate,
  CONVERT(date,att.DConDate) as BillingReferenceEndDate,
  CONVERT(date,att.User_ContractDate) as CustomerContractStartDate,
  DATEADD(month,att.User_ContractLen, CONVERT(date,att.User_ContractDate)) as CustomerContractEndDate, -- Start + Len
  CONVERT(date,att.SP_ContractDate) as SupplierContractStartDate,
  DATEADD(month,att.SP_ContractLen, CONVERT(date,att.SP_ContractDate)) as SupplierContractEndDate, -- Start + Len
  null as BillingReferenceCreateDate,
  null as BillingReferenceCreateUser,
  att.Custom11 as CustomerCustomReference, -- as seen in GenerateInvoiceBackups, blank for sample so far
  att.Custom6 as CustomerSiteName, -- as seen in GenerateInvoiceBackups, blank for sample so far
  null as InstallationPostCode,
  Costcode as CustomerCostCentre, -- as seen in GenerateInvoiceBackups, this works.
  att.Custom2 as CustomerPONumber, -- as seen in GenerateInvoiceBackups, blank for sample so far
  null as GCISalesManager,
  null as SupplierReference_1,
  null as SupplierReference_2,
  null as SupplierReference_3,
  null as GCICustomField_1,
  null as GCICustomField_2,
  null as GCICustomField_3
from [EclipseSQL-GCI].dbo.[BILL Phonenum Add] att -- so far, looks like they're 1:1 w/ Phonenum
inner join [EclipseSQL-GCI].dbo.[BILL Phonenums] num on att.[Tel ID] = num.[Tel ID]
inner join [EclipseSQL-GCI].dbo.[BILL Main] gr on num.[Billing ID] = gr.[Billing ID]
inner join [EclipseSQL-GCI].dbo.Customers cust on gr.[Customer ID] = cust.[Customer ID]
where cust.[Customer ID] >= 234200 and cust.[Customer ID] <= 234300



select 
  *
from [EclipseSQL-GCI].dbo.[Call Data]
where [CustomerID] >= 234200 and [CustomerID] <= 234300 -- IS INDEXED ON CUSTOMER ID


-- RatedEvent
select
  ID as EventRecordID,
  RecordCreationDate as EventRecordCreateDate,
  CustomerID,
  cust.[Account Number] as AccountNumber,
  Billed as BilledFlag,
  null as BilledInvoiceChargeSummaryID, -- Migration? case when Billed = 1 then 0 else null end "which CallCharges line was this billed in"
  null as BilledInvoiceNumber, --Migration? InvoiceNumber as BilledInvoiceNumber,
  null as BilledInvoiceBatchNumber, --Migration? InvoiceBatchNum as BilledInvoiceBatchNumber,
  AccountingPeriod,
  SupplierRatingPattern as EventReference,
  null as EventID, --?, 'SupplierCDRID' is blank.
  OriginatingCLI,
  DialledCLI,
  TerminatingCLI,
  PresentationCLI, -- Appears blank.
  Ext as Extension,
  [Type] as EventTypeID,
  EventTime,
  Duration as EventDuration,
  [Weekday] as WeekDayFlag,
  Rate as TimePeriod, -- looks like 1, 2, 3. not sure of order.
  Band as EventBandID, -- This is a string, not an ID.
  CarrierCost,
  null as RatingPlanID, -- not sure
  RateMethod as RateMethodID,
  MarkedUp as MarkedUpFlag,
  PreRatedCall as PreRatedCallFlag,
  Bundled as BundledFlag,
  PPC,
  PPM,
  RawCost as RawCustomerCharge, --?
  BundledDuration,
  BilledDuration,
  null as CustomerCharge, --?
  VATable as VATFlag,
  VAT as VATCharge,
  ratedEvent.Country,
  [File_ID] as EventFileID,
  CarrierNo as CarrierID,
  CarrierTariffCode as CarrierTariffID,
  CarrierNo as CarrierAccountID, -- CarrierAccountCode is text
  null as SupplierRecordReference,
  null as SupplierEventBand,
  SupplierRatingPattern as SupplierRatingPattern,
  ChargeTaxTypeFlag as ChargeTaxTypeFlag,
  Duplicate as DuplicateFlag
from [EclipseSQL-GCI].dbo.[Call Data] ratedEvent
inner join [EclipseSQL-GCI].dbo.Customers cust on [CustomerID] = cust.[Customer ID]
where [CustomerID] >= 234200 and [CustomerID] <= 234300 -- IS INDEXED ON CUSTOMER ID









-- close to recent so the data is better
select max([Customer ID]) from [EclipseSQL-GCI].dbo.Customers

-- (there are 2 customers in here that don't have any charges or phone numbers)
-- (not yet set up, ran until cancelled or something else?)
select
  cust.[Customer ID],
  cust.Company,
  (
    select count(*)
	from [EclipseSQL-GCI].dbo.BILL_Charges bc
	where bc.[Customer_ID] = cust.[Customer ID]
  ) as Charges,
  (
    select count(*)
	from [EclipseSQL-GCI].dbo.[BILL Main] gr
	where gr.[Customer ID] = cust.[Customer ID]
  ) as Groups,
  (
    select count(*)
	from [EclipseSQL-GCI].dbo.[BILL Main] gr
	inner join [EclipseSQL-GCI].dbo.[BILL Phonenums] num on gr.[Billing ID] = num.[Billing ID]
	where gr.[Customer ID] = cust.[Customer ID]
  ) as Phonenums
from [EclipseSQL-GCI].dbo.Customers cust
where cust.[Customer ID] >= 234200 and cust.[Customer ID] <= 234300


/*
--InvoicingCompany
SELECT 
  [CompanyID] as InvoicingCompanyID,
  [CoName] as InvoicingCompanyName
FROM [EclipseSQL-GCI].[dbo].[Companies]
*/