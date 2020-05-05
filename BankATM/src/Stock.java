import java.util.Random;

import database.SQLite;

// Stock object that customers can buy from stock market
public class Stock {
    //unique id of each stock
    protected String id;
    protected double price;
    protected String name;
    protected double original_price;	//optional attribute
    protected int shares;	// number of shares

    public Stock(String id, String name,double starting_price,int amount){
        this.price = starting_price;
        this.name = name;
        this.id = id;
        this.shares=amount;
        this.original_price=0;
    }
    
    // price current price, consistent in both stockmarket and heldstocks
    public void updatePrice(){
    	int changeRate = new Random().nextInt(10)-5;	//generate -5% to 5% fluctuation rate
    	price = price*(1+0.01*changeRate);
    	SQLite.update("StockMarket", "id = "+id, new String[]{"Price"}, 
    											 new String[]{Double.toString(price)}, 
    											 new String[]{"real"});
    }
    
    //update the stock amount 
    public void updateAmount(int amount){	
    	this.shares+=amount;
    	
    }

    public double get_unrealized_price(){
        return this.price- this.original_price;
    }
    
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getId() {
        return id;
    }
    public int getAmount(){
    	return shares;
    }
    
    public double getOriginalPrice(){
    	return original_price;
    }
    
    public void setOriginPrice(double price){
    	this.original_price =price;
    }

    public String toString(){
        String ret = "";
        ret += ("Stock " + this.id + "\n");
        ret += ("Current Value: $" + this.price + "\n");
        return ret;
    }
}
