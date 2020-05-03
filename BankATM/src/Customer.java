import java.util.ArrayList;

import database.SQLite;

public class Customer extends BankUser {
//	private ArrayList<String> currencyTypes;
	private ArrayList<Collateral> collaterals;
	
	public Customer(String Username, String Password){
		super(Username,Password);
		collaterals = new ArrayList<Collateral>();
		logIn();
//		currencyTypes = new ArrayList<String>();
	}
	
	//add customer to db when first sign up
	public void signUp(){
		int tempid=SQLite.insert("Customers", new String[]{"Username","Password"}, 
				   new String[]{Username,Password}, 
				   new String[]{"text","text"});
		id = Integer.toString(tempid);
	}
	
	
	
	// read id, acconts from db if customer is in db
	private void logIn(){
		String query;
		ArrayList<ArrayList<String>> res;
		
		//get id from db
		query="SELECT id FROM Customers WHERE Username = "+Username+" AND "+"Password = "+Password;
		res=SQLite.query(query, new String[]{"id"}, new String[]{"integer"});
		if(res!=null){
			id=res.get(0).get(0);
		}
		
		//get all accounts from db
		query="SELECT * FROM Accounts WHERE Customer_id = "+id;
		res=SQLite.query(query, new String[]{"id","Type","Amount","Customer_id"}, 
								new String[]{"integer","text","real","integer"});
		if(res!=null){
			for(int row=0;row<res.size();row++){
				int id = Integer.parseInt(res.get(row).get(0));
				String type=res.get(row).get(1);
				double amount = Double.parseDouble(res.get(row).get(2));
				if(type.toLowerCase().equals("checking")){
					accounts.add(new Checking(amount,Integer.toString(id)));
					
				}
				else if(type.toLowerCase().equals("saving")){
					accounts.add(new Saving(amount,Integer.toString(id)));
					
				}
				else if(type.toLowerCase().equals("securities")){
					accounts.add(new Securities(amount,Integer.toString(id)));
					
				}
				
			}
		}
		
	}
	
	public void createCheckingOrSaving(String type,double amount){
		
		//add new account to the owner's accounts
		int id = SQLite.insert("Accounts", new String[]{"Type", "Amount","Customer_id"},
				  new String[]{type,String.valueOf(amount),getId()},
				  new String[]{"text","real","integer"});
		
		if(type.toLowerCase().equals("checking")){
			this.accounts.add(new Checking(amount,Integer.toString(id)));
			
		}
		else if(type.toLowerCase().equals("saving")){
			this.accounts.add(new Saving(amount,Integer.toString(id)));
			
		}


	}
	
	//check if the saving account has over $5000, and return the amount
	private int checkOpenSecurities(String savingAccountId, double amount){
		
		for(Accounts a: accounts){
			if(a.getId().equals(savingAccountId)){
				if(a.getAmount()>=5000 && a.getAmount()-amount>=2500 && amount>=1000){
					return (int) a.getAmount();
				}
			}
		}
		return -1;
	}
	
	// create securiteis account by transfer money from a saving account
	public void createSecurities(double amount, String savingAccountId){
		
		int updatedAmount=checkOpenSecurities(savingAccountId,amount);
		if(updatedAmount!=-1){
			//calculate the updated saving account balance
			if(updatedAmount<=amount){
				System.out.println("invalid amount to transfer from saving");
				return;
			}
			updatedAmount-=amount;
			//transfer the money from the saving account
			SQLite.update("Accounts", "id = "+savingAccountId, new String[]{"Amount"},
															   new String[]{Integer.toString(updatedAmount)}, 
															   new String[]{"real"});
			
			//create new securities account, and add to db
			int id = SQLite.insert("Accounts", new String[]{"Type", "Amount","Customer_id"},
					  new String[]{"Securities",String.valueOf(amount),getId()},
					  new String[]{"text","real","integer"});
			this.accounts.add(new Securities(amount,Integer.toString(id)));
		}
		
	}
	
	public String viewTransactions(){
		String transactions="";
		ArrayList<ArrayList<String>> res;
		String sql="SELECT * FROM Transactions INNER JOIN Accounts on "
				+ "Transactions.Customer_id = Accounts.Customer_id AND Transactions.Account_id = Accounts.id"
				+ "WHERE Transactions.Customer_id = "+id;
		res=SQLite.query(sql, new String[]{"Account_id","Type","Amount","Date"}, 
						  new String[]{"integer","text","integer","text"});
		if(res!=null){
			for(int row=0;row<res.size();row++){
				transactions+=res.get(row).get(1)+"Account id "+res.get(row).get(0)+" made a transaction of "
							+res.get(row).get(2)+" on "+res.get(row).get(3)+"\n";
			}
		}
		return transactions;
	}
	
	// request loads given amount and the specific collateral index
	public void requestLoans(int amount , int collateralIndex ){
		if(collaterals.get(collateralIndex).isUsed()==false){
			SQLite.insert("Loans", new String[]{"Amount","Customer_id","Collateral","Interest"},
					   new String[]{Integer.toString(amount),id, collaterals.get(collateralIndex).getName(),"0.05"},
					   new String[]{"real","integer","text","real"});
			collaterals.get(collateralIndex).setUsed(true);
		}
		else{
			System.out.println("collateral is already being used");
		}
		
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
	
	

}
