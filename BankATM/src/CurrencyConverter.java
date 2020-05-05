// takes a desired currency and returns converted money amount from USD to that currency
public class CurrencyConverter {
    public static double convert_currency(Currency currency, double amount){
        return currency.convert_from_usd(amount);
    }
}
