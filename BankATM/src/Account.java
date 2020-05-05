import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.SQLite;

// Abstract class over securities, checking, and saving
public abstract class Account {
    protected double current_amount;
    protected String account_id;
    protected String type = "Account";


    public Account(double starting_amount, String account_id){
        this.current_amount = starting_amount;
        this.account_id = account_id;
        init();
    }
    
    //initialize current amount
    private void init(){
    	String query="SELECT * FROM Accounts WHERE Account_id = "+account_id;
    	ArrayList<ArrayList<String >> res=SQLite.query(query, new String[]{"Amount"},
    														  new String[]{"real"});
    	if(res!=null && res.size()>0){
    		if(res.get(0).get(0)!=null)
    		current_amount = Double.parseDouble(res.get(0).get(0));
    	}
    }
    

    public String getType() {
        return this.type;
    }
    
    public String getId(){
    	return account_id;
    }
    
    public double getAmount(){
    	return current_amount;
    }
    
  //format mm/dd
  	protected String getCurrentDate(){
  		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
  		return dtf.format(java.time.LocalDate.now());
  	}

    public String toString(){
        String ret = "";
        ret += "Total amount of money in " + this.type + " account: " + this.current_amount + "\n";
        ret += "Account ID number: " + this.account_id + "\n";
        return ret;
    }
}