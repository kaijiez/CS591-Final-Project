import java.util.ArrayList;

import database.SQLite;

public class BankManager extends BankUser {
	final static String Username = "admin";
	final static String Password = "admin";
	private StockMarket stockMarket;
	
	public BankManager(String Username, String Password){
		super(Username,Password);
		stockMarket = new StockMarket();
		
	}
	
	public boolean logIn(String username, String password){
		if(Username==username && Password==password){
			return true;
		}
		return false;
	}
	
//	display accounts balances, loans and transactions for a specific customer
	public String lookUpCustomer(String id){
		String query="SELECT Username,Password FROM Customers WHERE id = "+id;
		ArrayList<ArrayList<String>> res;
		res=SQLite.query(query, new String[]{"Username","Password"}, 
							new String[]{"text","text"});
		Customer customer=null;
		String display="";
		// instance a new customer object with username and password, and use login to get all data
		if(res!=null){
			for(int row=0;row<res.size();row++){
				String username = res.get(row).get(0);
				String password = res.get(row).get(1);
				customer = new Customer(username,password);
				customer.logIn();
				display+="All account balances: \n"+customer.viewAllAccountsBalances()+"\n";
				display+="All loans the customer have: \n"+customer.viewLoans()+"\n";
				display+="All recent transactions are: \n"+customer.viewTransactions()+"\n";
			}
		}
		
		return display;
	}
	
	
	//display all customers
	public String lookUpAllCustomers(){
		String display="";
		String query="SELECT id, Username FROM Customers";
		ArrayList<ArrayList<String>> res;
		res=SQLite.query(query, new String[]{"id", "Username"}, 
							new String[]{"integer","text"});
		if(res!=null){
			for(int row = 0;row<res.size();row++){
				display+="id: "+res.get(row).get(0)+" Username: "+res.get(row).get(1)+"\n";
			}
		}
		
		return display;
	}
	
	//return all loans in db
	public ArrayList<Loan> lookUpAllLoans(){
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Loan> loans = new ArrayList<Loan>();
		String query="SELECT Username,Password FROM Customers";
		ArrayList<ArrayList<String>> res;
		res=SQLite.query(query, new String[]{"Username","Password"}, 
							new String[]{"text","text"});
		if(res!=null){
			for(int row = 0;row<res.size();row++){
				Customer customer =new Customer(res.get(row).get(0),res.get(row).get(1));
				customer.logIn();
				customers.add(customer);
			}
			
			for(Customer c: customers){
				loans.addAll(c.getLoans());
			}
		}
		return loans;
	}
	
	//update loan approvedate in db if approved, other delete loan from db
	public void approveLoan(Loan loan, boolean approve){
		String id=loan.getId();
		if(approve){
			SQLite.update("Loans", "id = "+id, new String[]{"ApproveDate"}, new String[]{getCurrentDate()}, new String[]{"text"});
			System.out.println("loan is updated");
		}
		else{
			SQLite.delete("Loans", Integer.parseInt(id));
			System.out.println("loan has been deleted from db");
		}
				
		
	}
	
	public String viewDailyTransactions(String date){
		String display="";
		ArrayList<ArrayList<String>> res;
		String query="SELECT Username, Type, a.id, t.amount, Date FROM Transactions t "
				+ "INNER JOIN Customers c ON t.Customer_id = c.id "
				+ "INNER JOIN Accounts a ON t.Account_id = a.id "
				+ "WHERE Date = '05/04' ";
		res=SQLite.query(query, new String[]{"Username","Type","id","amount","Date"}, 
							new String[]{"text","text","integer","real","text"});
		if(res!=null){
			for(int row = 0;row<res.size();row++){
				display+="User: "+res.get(row).get(0)+" on "+res.get(row).get(1)+" id: "+res.get(row).get(2)+
						" make a transaction of "+res.get(row).get(3)+" on "+res.get(row).get(4)+"\n";
			}
		}
		return display;
		
	}
	
	public void addStock(String name, double price){
		stockMarket.createStock(name, price);
	}
	
	// delete stock from stockmarket based on id
	public void removeStock(String id){
		stockMarket.removeStock(id);
	}
	
}
