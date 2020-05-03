import java.util.*;

import database.SQLite;

//keeps track of all existing stocks the bank controls
public class StockMarket {
    protected ArrayList<Stock> allStocks;
    
    public StockMarket(){
    	allStocks = new ArrayList<Stock>();
    	init();
    }
    
    // read all stockmarket stocks from db
    private void init(){
    	ArrayList<ArrayList<String>> res;
    	String query = "SELECT * FROM StockMarket";
    	res=SQLite.query(query, new String[]{"id","Name","Price"}, new String[]{"integer","text","real"});
    	
    	if(res!=null){
    		for(int row=0; row<res.size();row++){
        		allStocks.add(new Stock(res.get(row).get(0),res.get(row).get(1), Double.parseDouble(res.get(row).get(2))));
        	}
    	}
    	
    }
    
    // update stock currenct price periodically
    public void update(){
    	for(Stock s: allStocks){
    		s.update();
    	}
    }
    
    public ArrayList<Stock> getStocks(){
    	return allStocks;
    }
    
    public void addStock(String name, double price){
    	
    }
    
    public void removeStock(String id){
    	
    }

}
