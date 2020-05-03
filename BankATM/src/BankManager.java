import java.util.ArrayList;

import database.SQLite;

public class BankManager extends BankUser {

	private StockMarket stockMarket;
	
	public BankManager(String Username, String Password){
		super(Username,Password);
		stockMarket = new StockMarket();
		
	}
	
	//display all information for a specific customer
	public String lookUpCustomer(String id, String Username){
		String query="";
		return"";
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
}
