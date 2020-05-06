import java.util.ArrayList;

import database.SQLite;

// Saving Account object that customers can have with methods for actions with money in this account
public class Saving extends Account implements Transaction{
    protected static double securities_transfer_minimum = 1000;
    protected static double minimum_after_securities_transfer = 2500;
    protected boolean securities_exist;
    protected Saving savingAccount;
    protected double interest;		//account interest rate
    final static double Interest=0.05;	//fixed interest rate

    public Saving(double starting_amount, String account_id, String dateCreated){
        super(starting_amount, account_id, dateCreated);
        this.type = "Saving";
        this.securities_exist = false;
        interest=0;
    }
    
    //read data for this saving account from db
    public void init(){
    	if(current_amount>=5000){
    		this.setInterest();
    	}
    	String query="SELECT * FROM Accounts WHERE Account_id = "+account_id;
    	ArrayList<ArrayList<String >> res=SQLite.query(query, new String[]{"DateCreated"},
    														  new String[]{"text"});
    	if(res!=null && res.size()>0 && interest!=0){
    		//if current 1 month has pass since the month the account is created, add interest to balance
    		if(Integer.parseInt(getCurrentDate().split("/")[0])!=Integer.parseInt(res.get(0).get(0).split("/")[0])){
    			current_amount+=Interest*current_amount;
    			SQLite.update("Account", "id = "+account_id, new String[]{"Amount"}, 
    														 new String[]{Double.toString(current_amount)}, 
    														 new String[]{"real"});
    		}
    	}
    }

    // transferring some money from savings to securities
    public boolean transfer_to_securities(double amount, Securities account){
        if(this.current_amount < (minimum_after_securities_transfer + securities_transfer_minimum)){
            System.out.println("Insufficient funds. Must have at least $2,500 after proposed transfer.");
            return false;
        }
        if(amount < securities_transfer_minimum){
            System.out.println("Invalid amount. Must transfer minimum of $1,000.");
            return false;
        }
        account.transfer_from_saving(amount);
        //update the amount in db
        SQLite.update("Accounts", "id = "+account_id, new String[]{"Amount"}, 
        											  new String[]{Double.toString(current_amount-amount)}, 
        											  new String[]{"real"});
        
        SQLite.insert("Transactions", new String[]{"Customer_id","Account_id","Amount","Date"}, 
        							  new String[]{customer_id,account_id,Double.toString(-amount),getCurrentDate()},
        							  new String[]{"integer","integer","real","text"});
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if(this.securities_exist){
            if(this.current_amount-amount < this.minimum_after_securities_transfer){
                System.out.println("Unable to withdraw and you must have at least " + this.minimum_after_securities_transfer + "in savings");
                return false;
            }
            else{
                System.out.println("Money withdrawn!");
                this.current_amount -= amount;
                SQLite.update("Accounts", "id = "+account_id, new String[]{"Amount"}, 
						  new String[]{Double.toString(current_amount)}, 
						  new String[]{"real"});
                
                SQLite.insert("Transactions", new String[]{"Customer_id","Account_id","Amount","Date"}, 
						  new String[]{customer_id,account_id,Double.toString(-amount),getCurrentDate()},
						  new String[]{"integer","integer","real","text"});
                return true;
            }
        }
        else if (this.current_amount - amount < 0){
            System.out.println("Unable to withdraw due to insufficient funds.");
            return false;
        }
        else{
            System.out.println("Money withdrawn!");
            this.current_amount -= amount;
            SQLite.update("Accounts", "id = "+account_id, new String[]{"Amount"}, 
					  new String[]{Double.toString(current_amount)}, 
					  new String[]{"real"});
            
            SQLite.insert("Transactions", new String[]{"Customer_id","Account_id","Amount","Date"}, 
					  new String[]{customer_id,account_id,Double.toString(-amount),getCurrentDate()},
					  new String[]{"integer","integer","real","text"});
            return true;
        }
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Money deposited!");
        this.current_amount += amount;
        SQLite.update("Accounts", "id = "+account_id, new String[]{"Amount"}, 
				  new String[]{Double.toString(current_amount)}, 
				  new String[]{"real"});
        
        SQLite.insert("Transactions", new String[]{"Customer_id","Account_id","Amount","Date"}, 
				  new String[]{customer_id,account_id,Double.toString(amount),getCurrentDate()},
				  new String[]{"integer","integer","real","text"});
    }
    
    public void setInterest(){
    	interest=Interest;
    }
}