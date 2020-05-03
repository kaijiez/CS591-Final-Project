// Collateral object that customers can put up to take out a loan
public class Collateral {
    protected String name;
    protected boolean used;

    public Collateral(String name){
        this.name = name;
        this.used = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String toString(){
        return this.name;
    }
}
