public class Euro extends Currency{

    public Euro(double amount){
        super(amount);
        this.currency = "euro";
        this.exchange_rate = 1.08;
        Currency.add_currency(this);
    }
}
