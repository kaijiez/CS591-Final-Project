public class USD extends Currency{

    public USD(double amount){
        super(1);
        this.currency = "USD";
        Currency.add_currency(this);
    }
}
