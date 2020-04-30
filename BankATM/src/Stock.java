// Stock object that customers can buy from stock market
public class Stock {
    //unique id of each stock
    protected String id;
    protected double price;

    public Stock(double starting_price){
        this.price = starting_price;
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
