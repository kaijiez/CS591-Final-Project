import java.util.*;

//securities account for customers to open up
public class Securities extends Account{
    protected ArrayList<Stock> stocks;

    public Securities(double starting_amount, String account_id){
        super(starting_amount, account_id);
        this.type = "Securities";
        this.stocks = new ArrayList<>();
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

    //print all current stocks
    public void see_all_stocks(){
        if(this.stocks.size() != 0) {
            for (Stock s : this.stocks) {
                System.out.println(s);
            }
        }
        else{
            System.out.println("Currently own no stocks, take a look at the stock market!");
        }
    }

    //receiving money from selling stock and removing from owned stock list
    public void sell_stock(int index){
        this.current_amount += this.stocks.get(index).getPrice();
        this.stocks.remove(index);
    }

    public String toString(){
        String ret = "";
        ret += super.toString();
        for (Stock stock: this.stocks){
            ret += stock.toString();
        }
        return ret;
    }
}
