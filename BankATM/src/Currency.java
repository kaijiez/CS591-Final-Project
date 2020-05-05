import java.util.*;
public abstract class Currency {
    protected String currency;
    protected double exchange_rate;
    protected static ArrayList<Currency> all_supported_currencies = new ArrayList<>();

    public Currency(double rate){
        this.exchange_rate = rate;
    }

    public static void add_currency(Currency curr){
        Currency.all_supported_currencies.add(curr);
    }

    public static ArrayList<Currency> get_all_supported_currencies(){
        return Currency.all_supported_currencies;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchange_rate() {
        return this.exchange_rate;
    }

    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public double convert_from_usd(double amount){
        return amount*this.exchange_rate;
    }

    public String toString(){
        String ret = "";
        ret += this.currency + "exchange rate to USD is " + this.exchange_rate + "\n";
        return ret;
    }
}
