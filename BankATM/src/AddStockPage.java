import javax.swing.*;
import java.awt.event.*;
import java.text.*;
public class AddStockPage extends JFrame implements ActionListener{
    //Page for the manager to input and add a new stock to the stock market

    JButton back, add;
    JTextField stockName;
    JFormattedTextField stockPrice;
    StockMarket s;
    public AddStockPage(StockMarket s){
    	this.s = s;
        JLabel title = new JLabel("Add a new stock");
        title.setBounds(100, 100, 300, 50);
        add(title);

        JLabel namelab = new JLabel("Stock name");
        namelab.setBounds(100, 150, 300, 50);
        add(namelab);
        stockName = new JTextField();
        stockName.setBounds(100, 200, 300, 75);
        add(stockName);

        JLabel amountLab = new JLabel("Stock price");
        amountLab.setBounds(100, 300, 300, 50);
        add(amountLab);

        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        stockPrice = new JFormattedTextField(amountFormat);
        stockPrice.setBounds(100, 350, 300, 75);
        add(stockPrice);

        add = new JButton("Add stock");
        add.addActionListener(this);
        add.setBounds(500, 275, 150, 70);
        add(add);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(400, 700, 200, 100);
        add(back);

        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object source = e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();
        }
        else if(source.equals(add)){
            if(stockPrice.getValue()==null){
                JOptionPane.showMessageDialog(this, "Please enter a stock price!");
            }
            else{
                double amount = ((Number)stockPrice.getValue()).doubleValue();
                String name = stockName.getText();
                if(name.equals("")){
                    JOptionPane.showMessageDialog(this, "Please enter a stock name!");
                
                }
                else if(!(amount >0)){
                    JOptionPane.showMessageDialog(this, "Please enter a positive stock price!");    
                }
                else{
                    //add new stock(amount, name); what ever the call is
                	s.createStock(name, amount, 25);
                    JOptionPane.showMessageDialog(this, "Successfully added stock");
                    dispose();
                }
            }
        }
    }

    public static void main(String[] args) {
//        new AddStockPage();
    }
    
}