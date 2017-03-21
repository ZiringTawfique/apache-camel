/****** Script for SelectTopNRows command from SSMS  ******/
SELECT 
	[Id] as NumberRangeMapID,
	NumberRange,
	ChargeBand as NumberRangeClassification,
	(case
	   when SourceId = 0 then 1 -- It's a manually entered custom one.
	   -- For ranges, I only read INTL_REGULAR, are there unique ones in INTL_BESPOKE?
	   else 0 
	end) as NumberRangeBespokeFlag,
	NumberRangeType,
    SourceId as NumberRangeSource,
    [StartDate] as NumberRangeStartDate,
    [EndDate] as NumberRangeEndDate
FROM [Localref].[BTImports].[NumberRangeMap_Data]