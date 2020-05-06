import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.Arrays;
public class StockMarketPage extends JFrame implements ActionListener {

    Customer cust;
    //Landing page where the customer will move to the stock market using a selected securities account


    JButton back, edit;
    JList<String> accountList;
    public StockMarketPage(Customer customer){
        cust=customer;


        JLabel market = new JLabel("Welcome to the Stock Market");
        market.setFont(new Font("Arial", Font.PLAIN, 40));
        market.setBounds(300, 50, 550, 100);
        add(market);

        JLabel choose = new JLabel("Please choose an account to use in the market");
        choose.setFont(new Font("Arial", Font.PLAIN, 30));
        choose.setBounds(100, 150, 700, 50);
        add(choose);

        String[] data = Arrays.copyOf(cust.getAccounts().toArray(), cust.getAccounts().toArray().length, String[].class);
        accountList = new JList<String>(data);
        accountList.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        scrollPane.setViewportView(accountList);
        accountList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(50, 250, 500, 500);
        add(scrollPane);
        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 30));
        back.addActionListener(this);
        back.setBounds(100, 800, 200, 100);
        add(back);

        edit = new JButton("Use this account");
        edit.setFont(new Font("Arial", Font.PLAIN, 30));
        edit.addActionListener(this);
        edit.setBounds(600, 350, 300, 100);
        add(edit);
        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();
        }
        else if(source.equals(edit)){
            int selected = accountList.getSelectedIndex();
            if(selected == -1){
                JOptionPane.showMessageDialog(this, "Please select an account to use.");
            }
            else{

            	if(selected<cust.getAccList().size()){
            		if(cust.getAccList().get(selected).getType().toLowerCase().equals("securities")){
            			 Securities selectedSec = (Securities)cust.getAccList().get(selected);
            			 System.out.println("yes");
                        new SecuritiesEditPage(selectedSec);
            		}
            	}
                

                setVisible(false);
                dispose();
            }
        }

    }
    
    public static void main(String[] args) {

    }
}