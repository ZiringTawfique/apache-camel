-- Customer
SELECT 
  Customer_ID as CustomerID,
  Account_Number as AccountNumber,
  Company,
  Customer_ID as AddressID
FROM eclipse.Customers 
where Customer_ID >= 200500 and Customer_ID <= 200600;

-- Address
SELECT 
  Customer_ID as AddressID,
  Address1 as Line1,
  Address2 as Line2,
  Address3 as Line3,
  County,
  Country,
  Postcode
FROM eclipse.Customers 
where Customer_ID >= 200500 and Customer_ID <= 200600;

-- CustomerProductCharge
select 
  charge.ID as CustomerProductChargeID,
  Customer_ID as CustomerID,
  '' as AccountNumber,
  '' as OrderNumber,
  Tel_ID as BillingReferenceID,
  '' as DefaultProductDescription,
  Description as ChargeInstanceDescription,
  
from eclipse.BillCharges charge
where Customer_ID >= 200500 and Customer_ID <= 200600;