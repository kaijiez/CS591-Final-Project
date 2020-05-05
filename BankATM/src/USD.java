public class USD extends Currency{

    public USD(double amount){
        super(amount);
        this.currency = "USD";
        Currency.add_currency(this);
    }
}
