# Project0

dbConfig file set to access the SQL table for all data inquiries and modifications.
All transactions (valid or invalid) will create a message sent to file Transactions.txt for logging purposes.
If Transactions.txt is not loaded, the project will create a new file upon execution.
Each user will have 2 accounts consisting of both checking and saving accounts.
Employee will reject account creation if the accounts don't meet the minimum deposit requirements.
Wire transfers (in or out) will be handled through checking account balance.
Requirement for stored procedure is used for account balance inquiry
