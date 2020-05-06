import database.SQLite;

// checking Account object that customers can have with methods for actions with money in this account
public class Checking extends Account implements Transaction{
	final static double TransactionFee = 50;
	
    public Checking(double starting_amount, String account_id , String dateCreated){
        super(starting_amount, account_id, dateCreated);
        this.type = "Checking";
    }

    @Override
    public boolean withdraw(double amount) {
    	amount+=TransactionFee;
        if (this.current_amount - amount < 0){
            System.out.println("Unable to withdraw amount due to insufficient funds.");
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
    	amount+=TransactionFee;
        System.out.println("Money deposited!");
        this.current_amount += amount;
        SQLite.update("Accounts", "id = "+account_id, new String[]{"Amount"}, 
				  new String[]{Double.toString(current_amount)}, 
				  new String[]{"real"});
        
        SQLite.insert("Transactions", new String[]{"Customer_id","Account_id","Amount","Date"}, 
				  new String[]{customer_id,account_id,Double.toString(amount),getCurrentDate()},
				  new String[]{"integer","integer","real","text"});
    }
}