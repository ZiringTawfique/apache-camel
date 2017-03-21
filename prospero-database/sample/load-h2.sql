-- Guard against running on the live server? Any kind of "if *something* exists, stop immediately"
-- well, h2's "CSVREAD" isn't compatible.

INSERT INTO AllSpark.CustomerProductCharges SELECT * FROM CSVREAD('CustomerProductCharges.csv');