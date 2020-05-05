// Abstract class over securities, checking, and saving
public abstract class Account{
    protected double current_amount;
    protected String account_id;
    protected String type = "Account";

    public Account(double starting_amount, String account_id){
        this.current_amount = starting_amount;
        this.account_id = account_id;
    }

    public String getType() {
        return this.type;
    }
    

    public String toString(){
        String ret = "";
        ret += "Total amount of money in " + this.type + " account: " + this.current_amount + "\n";
        ret += "Account ID number: " + this.account_id + "\n";
        return ret;
    }
}