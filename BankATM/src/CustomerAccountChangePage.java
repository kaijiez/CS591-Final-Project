import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.text.NumberFormat;

public class CustomerAccountChangePage extends JFrame implements ActionListener{
    Account acc;
    JRadioButton deposit, withdraw;
    JFormattedTextField amount;
    JButton confirm, back;
    public CustomerAccountChangePage(Account account){
        this.acc = account;
        if(acc.getType().equals("Securities")){
            JLabel accLabel = new JLabel("Please visit the Stock market to make changes to Account: "+acc.account_id);
            accLabel.setBounds(300, 100, 400, 100);
            add(accLabel);
            JLabel currBal = new JLabel("Current Balance: "+acc.current_amount);
            currBal.setBounds(300, 201, 400, 100);
            add(currBal);
            back = new JButton("Back");
            back.addActionListener(this);
            back.setBounds(400, 800, 300, 100);
            add(back);
        }
        else{
            JLabel accLabel = new JLabel("Change to Account: "+acc.account_id);
            accLabel.setBounds(300, 100, 400, 100);
            add(accLabel);
            
            JLabel currBal = new JLabel("Current Balance: "+acc.current_amount);
            currBal.setBounds(300, 201, 400, 100);
            add(currBal);

            withdraw = new JRadioButton("Withdraw");
            withdraw.setBounds(300, 302, 100, 50);
            add(withdraw);
            deposit = new JRadioButton("Deposit");
            deposit.setBounds(300, 353, 100, 50);
            add(deposit);
            ButtonGroup group = new ButtonGroup();
            group.add(withdraw);
            group.add(deposit);
            add(deposit);
            add(withdraw);
            // amount = new JTextField("0");
            NumberFormat amountFormat = NumberFormat.getNumberInstance();
            amount = new JFormattedTextField(amountFormat);
            amount.setBounds(400, 302, 100, 100);
            add(amount);
            
            confirm = new JButton("Confirm Transaction");
            confirm.addActionListener(this);
            confirm.setBounds(400, 550, 300, 100);
            add(confirm);

            back = new JButton("Back");
            back.addActionListener(this);
            back.setBounds(400, 800, 300, 100);
            add(back);

        }
        
        
        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(back)){
            System.out.println("Back button");
            setVisible(false);
            dispose();
        }
        else if(e.getSource().equals(confirm)){
            if(deposit.isSelected()){
                //backend call to change the account details
                setVisible(false);
                dispose();
            }
            else if(withdraw.isSelected()){
                //backend call to check if the person has that amount to withdraw
                //if(true){display success}
                // else{
                //     JOptionPane.showMessageDialog(this, "You don't have enough money to do this!");
                // }
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Please select an action");
            }
        }


    }
    public static void main(String[] args) {
        Account test = new Checking(100,"123");
        Account test2 = new Securities(100, "1234");
        new CustomerAccountChangePage(test);
    }
}