// Abstract class over securities, checking, and saving
public abstract class Accounts{
    protected double current_amount;
    protected String account_id;
    protected String type;

    public Accounts(double starting_amount, String account_id){
        this.current_amount = starting_amount;
        this.account_id = account_id;
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
}