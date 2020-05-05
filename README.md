# CS591-Final-Project
Group #3

Pat Duffil - U84513865
Kaijie Zhou - U54295267
Cathy Kim - U51788489

Compilation Instructions:
javac BankDriver.java
java BankDriver

Usability of each class:
Account: Abstract superclass that has general attributes (i.e. current amount) and methods to checkings, saving and securities accounts
Checking: account(s) user has that allows for withdrawing/depositing money
Saving: account(s) user has that allows for withdraw/deposit and dealing with securities account (we assume that there is a 1:1 relationship between a securities account and savings so each account can only be associated with one of the other)
Securities: account to hold stocks owned by bank users and deals with any methods that deal with user's stock (e.g. selling/buying/etc.)

Loan: represents loans that customers can take out given a collateral (we assume that bank has unlimited amount of money for example to loan out)

Collateral: used as object that loans require to be created and taken out from the bank (we assume for now that all loans only need a single collateral and that any given collateral is worth enough to take out a certain loan)

Stock: represents object traded/bought from stockmarket into securities account

Stock Market: represents place where all open positions and stocks circulated within bank accounts are kept

BankUser: Abstract class to types of users in the bank with general attributes and methods inherited by customers as well as bank manager
BankManager: Administrator of bank who controls loan requests from users, stock prices, as well as customer transaction overviews
Customer: Regular user of bank who can have checking accounts, saving accounts, and securities account

Transaction: Interface that only checking and saving accounts use to implement withdraw/deposit (we assume that the securities account cannot be directly withdrawn/depositied into but changed via transfers from saving accounts)

BankDriver: simply starting point that initializes a lot of pre-bank creation objects (e.g. bank manager, bank's stocks)

Currency: represents physical currencies supported by the bank, abstract class with methods/attributes inherited by subclasses
    -Euro
    -Canadian Dollars
    -Australian Dollars
    -Pounds
    -USD
CurrencyConverter: class that converts any money to chosen currency

Front-End pages:
CreateAccountPage: where users will choose username/password for new account
CustomerAccountsPage: overview of all customer accounts in the bank
CustomerHomePage: user's main page of all accounts info and possible actions to take on accounts
EditStockMarketPage: admin can change stock values
LoginPage: where user will input username/password to get to home page
ManagerHomePage: admin's main page which offers options like customer overview /changing stock prices
StartUpPage: Page where users can choose to either login or create a new account
StockMarketPage: page where users can see open positions in stock market
SecuritiesEditPage: page where customers can buy/sell their stock
