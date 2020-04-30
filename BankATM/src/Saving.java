// Saving Account object that customers can have with methods for actions with money in this account
public class Saving extends Accounts{
    protected static double securities_transfer_minimum = 1000;
    protected static double minimum_after_securities_transfer = 2500;

    public Saving(double starting_amount, String account_id){
        super(starting_amount, account_id);
    }

    // transfering some money from savings to securities
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
        return true;
    }
}