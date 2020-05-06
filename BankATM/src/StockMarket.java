import java.util.*;

import database.SQLite;

//keeps track of all existing stocks the bank controls
public class StockMarket {
    protected static ArrayList<Stock> allStocks;
    
    public StockMarket(){
    	allStocks = new ArrayList<Stock>();
//    	allStocks.add(new Stock("0","preimeum testing stock",100,999));
    	SQLite.update("StockMarket", "id = 0", new String[]{"Amount"}, new String[]{"999"}, new String[]{"integer"});
    	init();
    }
    
    // read all stockmarket stocks from db
    private void init(){
    	ArrayList<ArrayList<String>> res;
    	String query = "SELECT * FROM StockMarket";
    	res=SQLite.query(query, new String[]{"id","Name","Price","Amount"}, new String[]{"integer","text","real","integer"});
    	
    	if(res!=null){
    		for(int row=0; row<res.size();row++){
                allStocks.add(new Stock(res.get(row).get(0),res.get(row).get(1), 
                		Double.parseDouble(res.get(row).get(2)), 
                		Integer.parseInt(res.get(row).get(3))));
        	}
    	}
    	
    }
    
    // update stock currenct price periodically
    public void updatePrice(){
    	for(Stock s: allStocks){
    		s.updatePrice();
    	}
    }
    
    //update stock amount in stock market and db
    public void updateAmount(String stockid, int amount){
    	for(Stock a: allStocks){
    		if(a.getId()==stockid){
    			int updatedAmount= a.getAmount()-amount;
    			a.updateAmount(amount);
    			SQLite.update("StockMarket", "id = "+stockid, new String[]{"Amount"}, 
    														  new String[]{Integer.toString(updatedAmount)}, 
    														  new String[]{"integer"});
    		}
    	}
    }
    
    public ArrayList<Stock> getAllStocks(){
    	return allStocks;
    }

    public static ArrayList<Stock> get_open_positions(){
    	ArrayList<Stock> open_positions= new ArrayList<Stock>();
    	for(Stock s: allStocks){
    		if(s.getAmount()>0){
    			open_positions.add(s);
    		}
    	}
    	return open_positions;
    }


    

    public void createStock(String name, double price, int amount){
    	int newId=SQLite.insert("StockMarket", new String[]{"Name","Price"}, 
    								 new String[]{name,Double.toString(price)}, 
    								 new String[]{"text","real"});
    	Stock stock = new Stock(Integer.toString(newId),name,price,amount);
    	allStocks.add(stock);

    }
    
    public void removeStock(String id){
    	
    	SQLite.delete("StockMarket", Integer.parseInt(id));
    	for(Stock s: allStocks){
    		if(s.getId().equals("id")){
    			allStocks.remove(s);
    		}
    	}
    }



}
