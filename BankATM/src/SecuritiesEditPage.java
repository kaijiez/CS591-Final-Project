import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SecuritiesEditPage extends JFrame implements ActionListener{
    Securities sec;
    JList<String> yourStocks, availStocks;
    JButton sell,buy, cancel;

    public SecuritiesEditPage(Securities securities){
        sec = securities;

        JLabel yourAccount = new JLabel("Here are your current account details for account: ");//+sec.getAccountId);
        yourAccount.setBounds(100, 100, 500, 10);
        add(yourAccount);
        JLabel yourDetails = new JLabel("Unrealized Profit: "+sec.get_total_unrealized_profit()+"\nRealized Profit: "+securities.get_total_realized_profit());
        yourDetails.setBounds(100, 120, 300, 10);
        add(yourDetails);
        JLabel yourStockLab = new JLabel("Owned stocks:");
        yourStockLab.setBounds(100, 140, 100, 10);
        add(yourStockLab);
        
        ArrayList<String> stockStrings = new ArrayList<String>();
        for (Stock stock : sec.getStocks()) {
            stockStrings.add(stock.toString());
        }
        String[] s=Arrays.copyOf(stockStrings.toArray(), stockStrings.toArray().length, String[].class);
        yourStocks = new JList<String>(s);
        JScrollPane scrollPane1= new JScrollPane();
        scrollPane1.setViewportView(yourStocks);
        yourStocks.setLayoutOrientation(JList.VERTICAL);
        scrollPane1.setBounds(100, 160, 500, 300);
        add(scrollPane1);
        sell = new JButton("Sell this stock");
        sell.setBounds(650, 160, 300, 100);
        sell.addActionListener(this);
        add(sell);



        ArrayList<String> availStockStrings = new ArrayList<String>();
        for (Stock stock : StockMarket.get_open_positions()) {
            availStockStrings.add(stock.toString());
        }
        String[] as=Arrays.copyOf(availStockStrings.toArray(), availStockStrings.toArray().length, String[].class);
        availStocks = new JList<String>(as);
        JScrollPane scrollPane2= new JScrollPane();
        scrollPane2.setViewportView(availStocks);
        availStocks.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setBounds(100, 660, 500, 200);
        add(scrollPane2);

        buy = new JButton("Buy this stock");
        buy.setBounds(650, 500, 300, 100);
        buy.addActionListener(this);
        add(buy);

        cancel = new JButton("Back");
        cancel.setBounds(400, 860, 200, 100);
        cancel.addActionListener(this);
        add(cancel);

        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        System.out.println("action!");
        if(source.equals(cancel)){
            setVisible(false);
            dispose();
        }
        else if(source.equals(sell)){
            int selectedSell = yourStocks.getSelectedIndex();
            if(selectedSell == -1){
                JOptionPane.showMessageDialog(this, "Please select an stock to sell.");
            }
            else{
            	System.out.println("selling stock");
                sec.sell_stock(selectedSell,5);
                JOptionPane.showMessageDialog(this, "Stock Sold!");
                setVisible(false);
                dispose();
            }
        }
        else if(source.equals(buy)){
        	System.out.println("here");
            int selectedBuy = availStocks.getSelectedIndex();
            if(selectedBuy == -1){
                JOptionPane.showMessageDialog(this, "Please select an stock to buy.");
            }
            else{
            	System.out.println("buying stock");
            	System.out.println(StockMarket.get_open_positions().get(selectedBuy).getId());
                if(sec.purchase_stock(StockMarket.get_open_positions().get(selectedBuy),5)){
                    JOptionPane.showMessageDialog(this, "Successfully Bought stock!");
                    setVisible(false);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this, "You don't have enough money!");
                    setVisible(false);
                    dispose();
                }
            }
        }
        
    }


    public static void main(String[] args) {
//        new SecuritiesEditPage(new Securities(100, "123"));
    }

}
