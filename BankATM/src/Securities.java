import java.util.*;

import database.SQLite;

//securities account for customers to open up
public class Securities extends Account{
    protected ArrayList<Stock> stocks;
    protected double total_realized_profit;

    public Securities(double starting_amount, String account_id){
        super(starting_amount, account_id);
        this.type = "Securities";
        this.stocks = new ArrayList<>();
        this.total_realized_profit = 0;
        init();
    }

    // read all stocks from db, calculate realize_profit
    private void init(){
    	//get all stocks for this account from db
    	ArrayList<ArrayList<String>> res;
    	String query="SELECT * FROM HeldStocks INNER JOIN StockMarket ON HeldStocks.StockMarket_id = StockMarket.id WHERE Account_id = "+account_id;
    	res=SQLite.query(query, new String[]{"PriceBoughtAt","StockMarket_id","Amount","Name"},
    							new String[]{"real","integer","integer","text"});
    	if(res!=null){
    		for(int row=0; row<res.size();row++){
    			double originalPrice = Double.parseDouble(res.get(row).get(0));
    			String id = res.get(row).get(1);
    			int amount = Integer.parseInt(res.get(row).get(2));
    			String name = res.get(row).get(3);
    			
    			Stock s = new Stock(id,name,originalPrice,amount );
    			stocks.add(s);
    			
    		}
    	}
    	
    	//calculate all profit for this account
    	query="SELECT * FROM Accounts WHERE Account_id = "+account_id;
    	res=SQLite.query(query, new String[]{"StartingAmount"},
    							new String[]{"real"});
    	if(res!=null && res.size()>0){
    		if(res.get(0).get(0)!=null)
    		total_realized_profit = current_amount-Integer.parseInt(res.get(0).get(0));
    	}
    	
    }
    
    //money that is transferred from savings account
    public void transfer_from_saving(double amount){
        this.current_amount += amount;
        SQLite.update("Accounts", "id = "+account_id, new String[]{"Amount"}, 
        											  new String[]{Double.toString(this.current_amount += amount)}, 
        											  new String[]{"real"});
    }


    //adding a stock to stock collection after purchase
    public boolean purchase_stock(Stock stock){
        if(this.current_amount < stock.getPrice()){
            System.out.println("Insufficient funds in securities to purchase this stock.");
            return false;
        }
        stock.setOriginPrice(stock.getPrice());
        stocks.add(stock);
        System.out.println("Stock purchased!");
        
        //get current amount from stockmarket
        ArrayList<ArrayList<String>> res;
        String query="SELECT * FROM StockMarket WHERE id = "+stock.getId();
    	res=SQLite.query(query, new String[]{"Amount"},
    							new String[]{"integer"});
    	
    	if(res!=null && res.size()>0){
    		if(res.get(0).get(0)!=null && Integer.parseInt(res.get(0).get(0))>=stock.getAmount()){
    			int shares = Integer.parseInt(res.get(0).get(0))-stock.getAmount();
    			SQLite.update("StockMarket", "id = "+stock.getId(), new String[]{"Amount"}, 
																	new String[]{Integer.toString(shares)},
																	new String[]{"integer"});
    		}
    		
    	}
        
    	//add stock to heldstocks in db
    	
		SQLite.insert("HeldStocks", new String[]{"PriceBoughtAt","StockMarket_id","Account_id","DateBought","Amount"}, 
									new String[]{Double.toString(stock.getPrice()), stock.getId(),account_id,getCurrentDate()}, 
									new String[]{"real","integer","integer","text","integer"});
    	
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
        
      //get current amount from stockmarket
        ArrayList<ArrayList<String>> res;
        String query="SELECT * FROM StockMarket WHERE id = "+stock.getId();
    	res=SQLite.query(query, new String[]{"Amount"},
    							new String[]{"integer"});
    	
    	if(res!=null && res.size()>0){
    		if(res.get(0).get(0)!=null && Integer.parseInt(res.get(0).get(0))>=stock.getAmount()){
    			//add shares of stock back to stockmarket
    			int shares = Integer.parseInt(res.get(0).get(0))+stock.getAmount();
    			SQLite.update("StockMarket", "id = "+stock.getId(), new String[]{"Amount"}, 
																	new String[]{Integer.toString(shares)},
																	new String[]{"integer"});
    		}
    		
    	}
        
    	
    	//delete stock from heldstocks table in db
    	
        query="SELECT id FROM HeldStocks WHERE StockMarket_id = "+stock.getId()+" AND PriceBoughtAt = "+stock.getOriginalPrice()
        		+" AND Accound_id "+account_id+" AND Amount = "+stock.getAmount();
    	res=SQLite.query(query, new String[]{"id"},
    							new String[]{"integer"});
    	
    	if(res!=null && res.size()>0){
    		int stockId = Integer.parseInt(res.get(0).get(0));
    		SQLite.delete("HeldStocks", stockId);
    		
    	}

    }

    public double get_total_unrealized_profit(){
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
