import java.util.ArrayList;

import database.SQLite;

public class Customer extends BankUser {
//	private ArrayList<String> currencyTypes;
	private ArrayList<Collateral> collaterals;
	private ArrayList<Loan> loans;
	final static double OpenOrCloseFee = 50;
	final static double LoanInterest = 0.05;
	
	public Customer(String Username, String Password){
		super(Username,Password);
		collaterals = new ArrayList<Collateral>();
//		currencyTypes = new ArrayList<String>();
	}
	
	//add customer to db when first sign up
	public boolean signUp(){
		//check if username and password already exist
		String query;
		ArrayList<ArrayList<String>> res;
		query="SELECT id FROM Customers WHERE Username = "+Username;
		res=SQLite.query(query, new String[]{"id"}, new String[]{"integer"});
		
		if(res!=null){
			if(res.size()==0){
				int tempid=SQLite.insert("Customers", new String[]{"Username","Password"}, 
						   new String[]{Username,Password}, 
						   new String[]{"text","text"});
				id = Integer.toString(tempid);
				return true;
			}
			System.out.println("username and password already taken");
		}
		
		return false;
	}
	
	
	
	// read id, accounts from db if customer is in db
	public boolean logIn(){
		String query;
		ArrayList<ArrayList<String>> res;
		
		//get id from db
		query="SELECT id FROM Customers WHERE Username = "+Username+" AND "+"Password = "+Password;
		res=SQLite.query(query, new String[]{"id"}, new String[]{"integer"});
		if(res!=null){
			if(res.size()>0){
				id=res.get(0).get(0);
			}
			else{
				System.out.println("no user id is found, the user has not signed up yet");
				return false;
			}
		}
		
		//get all accounts from db
		query="SELECT * FROM Accounts WHERE Customer_id = "+id;
		res=SQLite.query(query, new String[]{"id","Type","Amount","Customer_id","DateCreated"}, 
								new String[]{"integer","text","real","integer","text"});
		if(res!=null){
			for(int row=0;row<res.size();row++){
				int id = Integer.parseInt(res.get(row).get(0));
				String type=res.get(row).get(1);
				double amount = Double.parseDouble(res.get(row).get(2));
				String cid = res.get(row).get(3);
				String date = res.get(row).get(4);
				if(type.toLowerCase().equals("checking")){
					Checking checking = new Checking(amount,Integer.toString(id),date);
					checking.setCustomerId(cid);
					accounts.add(checking);
					
				}
				else if(type.toLowerCase().equals("saving")){
					Saving saving = new Saving(amount,Integer.toString(id),date);
					saving.setCustomerId(cid);
					accounts.add(saving);
					
				}
				else if(type.toLowerCase().equals("securities")){
					Securities securities = new Securities(amount,Integer.toString(id),date);
					securities.setCustomerId(cid);
					accounts.add(securities);
					
				}
				
			}
		}
		
		// read all loans for the customer from db, and create collaterals from loans
		query="SELECT * FROM Loans WHERE Customer_id = "+id;
		res=SQLite.query(query, new String[]{"id","Amount","Collateral","Interest","ApplyDate","ApproveDate"}, 
								new String[]{"integer","real","text","real","text","text"});
		if(res!=null){
			for(int row=0;row<res.size();row++){
				String id = res.get(row).get(0);
				double amount = Double.parseDouble(res.get(row).get(1));
				
				String collateral = res.get(row).get(2);
				Collateral _collateral = new Collateral(collateral);
				_collateral.setUsed(true);
				collaterals.add(_collateral);
				
				double interest=Double.parseDouble(res.get(row).get(3));
				String applyDate = res.get(row).get(4);
				String approveDate = res.get(row).get(5);
				Loan loan = new Loan(id, amount,_collateral,interest);
				loan.setApplyDate(applyDate);
				loan.setApproveDate(approveDate);
				loans.add(loan);
				
				
			}
		}
		return true;
		
	}
	
	public boolean createCheckingOrSaving(String type,double amount){
		amount=(1-OpenOrCloseFee)*amount;
		//add new account to the owner's accounts
		
		if(type.toLowerCase().equals("checking")){
			int id = SQLite.insert("Accounts", new String[]{"Type", "Amount","Customer_id"},
					  new String[]{type,String.valueOf(amount),getId()},
					  new String[]{"text","real","integer"});
			this.accounts.add(new Checking(amount,Integer.toString(id),getCurrentDate()));
			
			return true;
			
		}
		else if(type.toLowerCase().equals("saving")){
			int id = SQLite.insert("Accounts", new String[]{"Type", "Amount","Customer_id"},
					  new String[]{type,String.valueOf(amount),getId()},
					  new String[]{"text","real","integer"});
			Saving saving = new Saving(amount,Integer.toString(id),getCurrentDate());
			saving.setInterest();
			this.accounts.add(saving);
			return true;
			
		}
		return false;

	}
	
	//check if the saving account has over $5000, and return the amount
	private int checkOpenSecurities(String savingAccountId, double amount){
		
		for(Account a: accounts){
			if(a.getId().equals(savingAccountId)){
				if(a.getAmount()>=5000 && a.getAmount()-amount>=2500 && amount>=1000){
					return (int) a.getAmount();
				}
			}
		}
		return -1;
	}
	
	// create securiteis account by transfer money from a saving account
	public boolean createSecurities(double amount, String savingAccountId){
		amount=(1-OpenOrCloseFee)*amount;
		int updatedAmount=checkOpenSecurities(savingAccountId,amount);
		if(updatedAmount!=-1){
			//calculate the updated saving account balance
			if(updatedAmount<=amount){
				System.out.println("invalid amount to transfer from saving");
				return false;
			}
			updatedAmount-=amount;
			//transfer the money from the saving account
			SQLite.update("Accounts", "id = "+savingAccountId, new String[]{"Amount"},
															   new String[]{Integer.toString(updatedAmount)}, 
															   new String[]{"real"});
			
			//create new securities account, and add to db
			int newid = SQLite.insert("Accounts", new String[]{"Type", "Amount","Customer_id","StartingAmount"},
					  new String[]{"Securities",String.valueOf(amount),getId(),String.valueOf(amount)},
					  new String[]{"text","real","integer","real"});
			Securities securities = new Securities(amount,Integer.toString(newid),getCurrentDate());
			securities.setCustomerId(id);
			this.accounts.add(new Securities(amount,Integer.toString(newid),getCurrentDate()));
			return true;
		}
		return false;
	}
	
	//pull all transacitons for customer from newest to oldest, and return a string to display all transactions
	public String viewTransactions(){
		String display="";
		ArrayList<ArrayList<String>> res;
		String sql="SELECT t.Account_id, a.Type, t.Amount, t.Date FROM Transactions t INNER JOIN Accounts a on "
				+ "t.Customer_id = a.Customer_id AND t.Account_id = a.id "
				+ "WHERE t.Customer_id = 1 "
				+ "ORDER BY Date DESC";
		res=SQLite.query(sql, new String[]{"Account_id","Type","Amount","Date"}, 
						  new String[]{"integer","text","integer","text"});
		if(res!=null){
			for(int row=0;row<res.size();row++){
				display+=res.get(row).get(1)+"Account id "+res.get(row).get(0)+" made a transaction of "
							+res.get(row).get(2)+" on "+res.get(row).get(3)+"\n";
			}
		}
		return display;
	}
	
	//return a string to display all loans
	public String viewLoans(){
		String display="";
		for(Loan l: loans){
			display+="loan id: "+l.getId()+" ,amount: "+l.getBorrowed_amount()+" ,collateral: "+l.getCollateral().getName()
					+" ,interest: "+l.getInterest()+" ,applyDate: "+l.getApplyDate()+" ,approveDate: "+l.getApproveDate()+"\n";
		}
		
		return display;
	}
	
	//get all accounts the customer have
	public String viewAllAccountsBalances(){
		String display="";
		for(Account a: accounts){
			display+=a.getType()+" account has "+a.getAmount()+"\n";
		}
		return display;
	}
	
	// request loads given amount and the specific collateral index
	public boolean requestLoans(int amount , int collateralIndex , String collateral ){
		if(collaterals.get(collateralIndex).isUsed()==false){
			int newid=SQLite.insert("Loans", new String[]{"Amount","Customer_id","Collateral","Interest","ApplyDate"},
					   new String[]{Integer.toString(amount),id, collateral,Double.toString(LoanInterest),getCurrentDate()},
					   new String[]{"real","integer","text","real","text"});
//			collaterals.get(collateralIndex).setUsed(true);
			loans.add(new Loan(Integer.toString(newid), amount, new Collateral("collateral"),0.05));
			return true;
		}
		else{
			System.out.println("collateral is already being used");
		}
		return false;
	}
	
	public String displayCollaterals(){
		int id=0;
		String display="";
		for(Collateral c: collaterals){
			display+="id: "+id+" "+c.getName()+" , status: "+c.isUsed()+"\n";
			id+=1;
		}
		return display;
	}
	
	public void addCollaterals(Collateral c){
		collaterals.add(c);
	}
	
	public ArrayList<Loan> getLoans(){
		return loans;
	}
	
	public ArrayList<Securities> retrieveSecurities(){
		ArrayList<Securities> securities = new ArrayList<Securities>();
		for (Account a: accounts){
			Securities s = (Securities) (a);
			if(a.getType().equals("Securities")){
				securities.add(s);
			}
		}
		return securities;
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	

}
