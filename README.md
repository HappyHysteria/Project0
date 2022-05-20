# Project0

dbConfig file set to access the SQL table for all data inquiries and modifications.
All transactions (valid or invalid) will create a message sent to file Transactions.txt for logging purposes.
If Transactions.txt is not loaded, the project will create a new file upon execution.
Customers can create accounts that will be pending Employee approval. Account management is disabled until account is approved.
Each user will have 2 accounts consisting of both checking and saving accounts.
Employee information stored in separate table. Employee can log in and approve accounts manually, but will also deny automatically if initial deposit minimum is not met. Customer cannot log in as an Employee, and new Employee accounts cannot be created through the program.
Wire transfers (in or out) will be handled through checking account balance.
Requirement for stored procedure is used for account balance inquiry
