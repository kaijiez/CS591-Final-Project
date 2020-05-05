// Abstract class over securities, checking, and saving
public abstract class Account {
    protected double current_amount;
    protected String account_id;
    protected String type = "Account";
    protected Currency preferred_currency;

    public Account(double starting_amount, String account_id){
        this.current_amount = starting_amount;
        this.account_id = account_id;
        this.preferred_currency = new USD(this.current_amount);
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

    public String toString(){
        String ret = "";
        ret += "Total amount of money in " + this.type + " account: " + this.current_amount + "\n";
        ret += "Account ID number: " + this.account_id + "\n";
        return ret;
    }
}