// Saving Account object that customers can have with methods for actions with money in this account
public class Saving extends Account implements Transaction{
    protected static double securities_transfer_minimum = 1000;
    protected static double minimum_after_securities_transfer = 2500;
    protected boolean securities_exist;

    public Saving(double starting_amount, String account_id){
        super(starting_amount, account_id);
        this.type = "Saving";
        this.securities_exist = false;
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
            return true;
        }
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Money deposited!");
        this.current_amount += amount;
    }
}