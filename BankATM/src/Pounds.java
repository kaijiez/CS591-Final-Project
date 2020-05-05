public class Pounds extends Currency {
    public Pounds(double amount){
        super(amount);
        this.currency = "Pounds";
        this.exchange_rate = 1.25;
        Currency.add_currency(this);
    }
}
