import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.SQLite;

public class BankDriver {
	
	private static void dbTestCases(){
//		SQLite.init();
		
//		SQLite.insert("Customers", new String[]{"Username","Password"}, new String[]{"Bob","BobPassword"}, new String[]{"TEXT","TEXT"});
//		SQLite.insert("Accounts", new String[]{"Type","Amount","Customer_id","DateCreated"}, 
//								  new String[]{"Checking","12","1","05/05"}, 
//								  new String[]{"text","real","integer","text"});
//
//		SQLite.update("Customers", "Name = 'Bob'", new String[]{"Name"}, new String[]{"Bobby"}, new String[]{"Text"});
		
//		String testQuery = "SELECT id, Name FROM Customers WHERE UserName = 'Bob' ";
//		String res=SQLite.query(testQuery, new String[]{"id","Name"}, new String[]{"Integer","Text"});
//		System.out.println(res.split("\t")[0]);
		
//		SQLite.insert("Accounts", new String[]{"Type", "Amount","Customer_id","DateCreated"},
//				  new String[]{"Saving",String.valueOf("100"),"1","05/04"},
//				  new String[]{"text","real","integer","text"});
		
//		SQLite.insert("Loans", new String[]{"id","Amount","Customer_id","Collateral","Interest","ApplyDate"},
//				  new String[]{"2","2000","1","lotion","0.05","05/04"},
//				  new String[]{"integer","real","integer","text","real","text"});
//		
//		String sql="SELECT t.Account_id, a.Type, t.Amount, t.Date FROM Transactions t INNER JOIN Accounts a on "
//				+ "t.Customer_id = a.Customer_id AND t.Account_id = a.id "
//				+ "WHERE t.Customer_id = 1 "
//				+ "ORDER BY Date DESC";
//		SQLite.query(sql, new String[]{"Account_id","Type","Amount","Date"}, 
//						  new String[]{"integer","text","integer","text"});
		
//		String query="SELECT * FROM Loans WHERE Customer_id = "+1;
//		SQLite.query(query, new String[]{"id","Amount","Collateral","Interest","ApplyDate","ApproveDate"}, 
//								new String[]{"integer","real","text","real","text","text"});
		
//		String query="SELECT Username, Type, a.id, t.amount, Date FROM Transactions t "
//				+ "INNER JOIN Customers c ON t.Customer_id = c.id "
//				+ "INNER JOIN Accounts a ON t.Account_id = a.id "
//				+ "WHERE Date = '05/04' ";
//		SQLite.query(query, new String[]{"Username","Type","id","amount","Date"}, 
//							new String[]{"text","text","integer","real","text"});
		
		String query="SELECT Amount, Customer_id, Collateral, ApplyDate FROM Loans WHERE ApproveDate IS NULL";
		ArrayList<ArrayList<String>> res;
		res=SQLite.query(query, new String[]{"Amount","Customer_id","Collateral","ApplyDate"}, 
							new String[]{"real","integer","text","text"});
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new StockMarket();
		dbTestCases();
		
		  
	}

}
