public class Australian extends Currency {
    public Australian(double amount){
        super(amount);
        this.currency = "Australian";
        Currency.add_currency(this);
    }
}
