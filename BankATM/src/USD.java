public class USD extends Currency{

    public USD(double amount){
        super(amount);
        this.currency = "USD";
        this.exchange_rate = 1;
        Currency.add_currency(this);
    }
}
