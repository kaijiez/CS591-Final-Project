import java.util.Random;

import database.SQLite;

// Stock object that customers can buy from stock market
public class Stock {
    //unique id of each stock
    protected String id;
    protected double price;
    private String name;

    public Stock(String id, String name,double starting_price){
        this.price = starting_price;
        this.name = name;
        this.id = id;
    }
    
    
    public void update(){
    	int changeRate = new Random().nextInt(10)-5;	//generate -5% to 5% fluctuation rate
    	price = price*(1+0.01*changeRate);
    	SQLite.update("StockMarket", "id = "+id, new String[]{"Price"}, 
    											 new String[]{Double.toString(price)}, 
    											 new String[]{"real"});
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

    public String toString(){
        String ret = "";
        ret += ("Stock " + this.id + "\n");
        ret += ("Current Value: $" + this.price + "\n");
        return ret;
    }
}
