public class Canadian extends Currency {
    public Canadian(double amount){
        super(amount);
        this.currency = "Canadian";
        this.exchange_rate = 0.71;
        Currency.add_currency(this);
    }
}
