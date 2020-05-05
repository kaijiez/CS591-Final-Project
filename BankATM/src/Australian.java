public class Australian extends Currency {
    public Australian(double amount){
        super(amount);
        this.currency = "Australian";
        this.exchange_rate = 0.71;
        Currency.add_currency(this);
    }
}
