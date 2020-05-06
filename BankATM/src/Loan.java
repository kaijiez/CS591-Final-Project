//loan object that customers can take out from their account
public class Loan {
    protected String name;
    protected double borrowed_amount;
    protected Collateral collateral;
    protected String applyDate;
    protected String approveDate;
    protected String id;
    protected double interest;

    public Loan (String id, double borrowed_amount, Collateral collateral, double interest){
        this.borrowed_amount = borrowed_amount;
        this.collateral = collateral;
        this.id = id;
        this.interest = interest;
    }
    
    public String getId(){return id;}
    public Collateral getCollateral(){return collateral;}
    public double getBorrowed_amount() {return borrowed_amount;}
    public String getApplyDate(){return applyDate;}
    public String getApproveDate(){return approveDate;}
    public double getInterest(){return interest;}

    public void setBorrowed_amount(double borrowed_amount) {this.borrowed_amount = borrowed_amount;}
    public void setApplyDate(String date){applyDate = date;}
    public void setApproveDate(String date){approveDate = date;}
    
    public String toString(){
    	return "loan id: "+id+" with amount of "+borrowed_amount+" using Collateral: "+collateral.getName()+
    			" is requested on: "+applyDate+" and approved on: "+approveDate;
    }
}
