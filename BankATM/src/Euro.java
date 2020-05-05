public class Euro extends Currency{

    public Euro(double amount){
        super(amount);
        this.currency = "euro";
        Currency.add_currency(this);
    }
}
