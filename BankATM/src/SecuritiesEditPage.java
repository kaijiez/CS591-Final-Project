import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SecuritiesEditPage extends JFrame implements ActionListener{
    Securities sec;
    JList<String> yourStocks, availStocks;
    JButton sell,buy, cancel;

    public SecuritiesEditPage(Securities securities){
        sec = securities;

        JLabel yourAccount = new JLabel("Here are your current account details for account: ");//+sec.getAccountId);
        yourAccount.setBounds(100, 100, 500, 10);
        add(yourAccount);
        JLabel yourDetails = new JLabel("Unrealized Profit: "+/*sec.getUnrealizedProfit()+*/"\nRealized Profit: "/*+sec.getRealizedProfit()*/);
        yourDetails.setBounds(100, 120, 200, 10);
        add(yourDetails);
        JLabel yourStockLab = new JLabel("Owned stocks:");
        yourStockLab.setBounds(100, 140, 100, 10);
        add(yourStockLab);
        
        ArrayList<String> stockStrings = new ArrayList<String>();
        for (Stock stock : sec.getStocks()) {
            stockStrings.add(stock.toString());
        }
        yourStocks = new JList<String>((String[]) stockStrings.toArray());
        JScrollPane scrollPane1= new JScrollPane();
        scrollPane1.setViewportView(yourStocks);
        yourStocks.setLayoutOrientation(JList.VERTICAL);
        scrollPane1.setBounds(100, 160, 500, 300);
        add(scrollPane1);
        sell = new JButton("Sell this stock");
        sell.setBounds(650, 160, 300, 100);
        add(sell);



        ArrayList<String> availStockStrings = new ArrayList<String>();
        for (Stock stock : StockMarket.get_open_positions()) {
            stockStrings.add(stock.toString());
        }
        availStocks = new JList<String>((String[]) availStockStrings.toArray());
        JScrollPane scrollPane2= new JScrollPane();
        scrollPane2.setViewportView(availStocks);
        availStocks.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setBounds(100, 660, 500, 200);
        add(scrollPane2);

        buy = new JButton("Buy this stock");
        buy.setBounds(650, 500, 300, 100);
        add(buy);

        cancel = new JButton("Back");
        cancel.setBounds(400, 860, 200, 100);
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
                sec.sell_stock(selectedSell);
                JOptionPane.showMessageDialog(this, "Stock Sold!");
                setVisible(false);
                dispose();
            }
        }
        else if(source.equals(buy)){
            int selectedBuy = availStocks.getSelectedIndex();
            if(selectedBuy == -1){
                JOptionPane.showMessageDialog(this, "Please select an stock to buy.");
            }
            else{
                if(sec.purchase_stock(StockMarket.get_open_positions().get(selectedBuy))){
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
        new SecuritiesEditPage(new Securities(100, "123"));
    }

}
