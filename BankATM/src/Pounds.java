public class Pounds extends Currency {
    public Pounds(double amount){
        super(amount);
        this.currency = "Pounds";
        Currency.add_currency(this);
    }
}
