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
  cust.[Customer ID] as AddressID,
  cust.Address1 as Line1,
  cust.Address2 as Line2,
  cust.Address3 as Line3,
  cust.County,
  cust.Postcode,
  cust.Country
from [EclipseSQL-GCI].dbo.Customers cust
where cust.[Customer ID] >= 234200 and cust.[Customer ID] <= 234300


-- CustomerProductCharge
select
  bc.ID as CustomerProductChargeID,
  cust.[Customer ID] as CustomerID,
  cust.[Account Number] as AccountNumber,
  --'NOT_YET_NEEDED' as OrderNumber,
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
  -- SupplierStart/End, CustomerStart/End not yet needed
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
select
  num.[Tel ID] as BillingReferenceID,
  cust.[Customer ID] as CustomerID,
  cust.[Account Number] as AccountNumber,
  --??? as NodeID
  --??? OrderNumber is ambiguous. BT_OrderNo + CPS_OrderNo are blank
  num.[Phonenumber] as BillingReference,
  CONVERT(date,att.User_ContractDate) as BillingReferenceStartDate,
  DATEADD(month,att.User_ContractLen, CONVERT(date,att.User_ContractDate)) as BillingReferenceEndDate, -- Start + Len
  CONVERT(date,att.SP_ContractDate) as SupplierContractStartDate,
  DATEADD(month,att.SP_ContractLen, CONVERT(date,att.SP_ContractDate)) as SupplierContractEndDate -- Start + Len
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
  ( case
      when Billed = 1 then 0 -- dummy
	  else null
	end
  ) as BilledInvoiceChargeSummary, -- "which CallCharges line was this billed in"
  InvoiceNumber as BilledInvoiceNumber,
  InvoiceBatchNum as BilledInvoiceBatchNumber,
  AccountingPeriod,
  SupplierRatingPattern as EventReference,
  --EventID?, 'SupplierCDRID' is blank.
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
  --RatingPlanID? -- isn't recorded
  RateMethod as RateMethodID,
  MarkedUp as MarkedUpFlag,
  PreRatedCall as PreRatedCallFlag,
  Bundled as BundledFlag,
  PPC,
  PPM,
  RawCost as RawCustomerCharge, --?
  BundledDuration,
  BilledDuration,
  VATable as VATFlag,
  VAT as VATCharge,
  ratedEvent.Country,
  [File_ID] as EventFileID,
  CarrierNo as CarrierID,
  CarrierTariffCode as CarrierTariffID
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