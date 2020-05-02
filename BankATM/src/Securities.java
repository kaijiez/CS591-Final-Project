import java.util.*;

//securities account for customers to open up
public class Securities extends Accounts {
    protected ArrayList<Collateral> collateral;
    protected ArrayList<Stock> stocks;

    public Securities(double starting_amount, String account_id){
        super(starting_amount, account_id);
        this.type = "Securities";
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

    //receiving money from selling stock and removing from owned stock list
    public void sell_stock(int index){
        this.current_amount += this.stocks.get(index).getPrice();
        this.stocks.remove(index);
    }

}
