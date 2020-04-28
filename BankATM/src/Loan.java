public class Loan {
    protected String name;
    protected double borrowed_amount;

    public Loan (String name, double borrowed_amount){
        this.name = name;
        this.borrowed_amount = borrowed_amount;
    }

    public double getBorrowed_amount() {
        return borrowed_amount;
    }

    public void setBorrowed_amount(double borrowed_amount) {
        this.borrowed_amount = borrowed_amount;
    }
}
