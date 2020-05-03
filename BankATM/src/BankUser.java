import java.util.ArrayList;

import database.SQLite;

public abstract class BankUser {
	protected String id;
	protected String Username;
	protected String Password;
	protected ArrayList<Account> accounts;

	public BankUser(String Username, String Password){
		this.id="";
		this.Username = Username;
		this.Password = Password;
		accounts = new ArrayList<Account>();
	}
	
	
	protected String getId(){
		return id;
	}
	
}
