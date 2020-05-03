import database.SQLite;

public class Main {
	
	private static void dbTestCases(){
//		SQLite.init();
		
//		SQLite.insert("Customers", new String[]{"Username","Password"}, new String[]{"Bob","BobPassword"}, new String[]{"TEXT","TEXT"});
//		SQLite.insert("Accounts", new String[]{"Type","Amount","Customer_id"}, new String[]{"Checking","12","1"}, new String[]{"text","real","integer"});
//
//		SQLite.update("Customers", "Name = 'Bob'", new String[]{"Name"}, new String[]{"Bobby"}, new String[]{"Text"});
		
//		String testQuery = "SELECT id, Name FROM Customers WHERE UserName = 'Bob' ";
//		String res=SQLite.query(testQuery, new String[]{"id","Name"}, new String[]{"Integer","Text"});
//		System.out.println(res.split("\t")[0]);
		
//		SQLite.insert("Accounts", new String[]{"Type", "Amount","Customer_id"},
//				  new String[]{"Saving",String.valueOf("100"),"1"},
//				  new String[]{"text","real","integer"});
		
//		SQLite.insert("Transactions", new String[]{"Customer_id", "Account_id","Amount","Date"},
//				  new String[]{"1","3",String.valueOf("100"),"5/3"},
//				  new String[]{"integer","integer","real","text"});
		
		String sql="SELECT * FROM Transactions INNER JOIN Accounts on Transactions.Customer_id = Accounts.Customer_id AND Transactions.Account_id = Accounts.id  WHERE Transactions.Customer_id = 1";
		SQLite.query(sql, new String[]{"Account_id","Type","Amount","Date"}, 
						  new String[]{"integer","text","integer","text"});
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		dbTestCases();
	}

}
