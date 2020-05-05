public class Canadian extends Currency {
    public Canadian(double amount){
        super(amount);
        this.currency = "Canadian";
        Currency.add_currency(this);
    }
}
