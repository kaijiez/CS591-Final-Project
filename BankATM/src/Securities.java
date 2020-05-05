import java.util.*;

//securities account for customers to open up
public class Securities extends Account{
    protected ArrayList<Stock> stocks;
    protected double total_realized_profit;

    public Securities(double starting_amount, String account_id){
        super(starting_amount, account_id);
        this.type = "Securities";
        this.stocks = new ArrayList<>();
        this.total_realized_profit = 0;
    }

    //money that is transferred from savings account
    public void transfer_from_saving(double amount){
        this.current_amount += amount;
    }


    //adding a stock to stock collection after purchase
    public boolean purchase_stock(Stock stock){
        if(this.current_amount < stock.getPrice()){
            System.out.println("Insufficient funds in securities to purchase this stock.");
            return false;
        }
        stocks.add(stock);
        System.out.println("Stock purchased!");
        return true;
    }

    public ArrayList<Stock> getStocks() {
        return this.stocks;
    }

    //receiving money from selling stock and removing from owned stock list
    public void sell_stock(int index){
        Stock stock = this.stocks.get(index);
        this.current_amount += stock.getPrice();
        this.total_realized_profit += stock.get_unrealized_price();
        this.stocks.remove(stock);

    }

    public double total_unrealized_profit(){
        double total = 0;
        if(this.stocks.size() != 0) {
            for (Stock s : this.stocks) {
                total += s.get_unrealized_price();
            }
        }
        return total;
    }

    public double get_total_realized_profit(){
        return this.total_realized_profit;
    }
}