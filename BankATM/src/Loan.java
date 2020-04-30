//loan object that customers can take out from their account
public class Loan {
    protected String name;
    protected double borrowed_amount;
    protected Collateral collateral;

    public Loan (String name, double borrowed_amount, Collateral collateral){
        this.name = name;
        this.borrowed_amount = borrowed_amount;
        this.collateral = collateral;
    }

    public double getBorrowed_amount() {
        return borrowed_amount;
    }

    public void setBorrowed_amount(double borrowed_amount) {
        this.borrowed_amount = borrowed_amount;
    }
}
