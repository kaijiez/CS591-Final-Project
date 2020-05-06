import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Arrays;

public class CustomerAccountChangePage extends JFrame implements ActionListener{
    //Page for a customer to make a deposit, withdrawl, or create a securities account from an existing savings account

    Account acc;
    JRadioButton deposit, withdraw;
    JFormattedTextField amount;
    JButton confirm, back, makeSec;
    Customer cust;
    public CustomerAccountChangePage(Account account, Customer cust){
        this.acc = account;
        this.cust=cust;
        if(acc.getType().equals("Securities")){
        	
            JLabel accLabel = new JLabel("Please visit the Stock market to make changes to Account: "+acc.account_id);
            accLabel.setFont(new Font("Arial", Font.PLAIN, 25));
            accLabel.setBounds(150, 100, 1000, 100);
            add(accLabel);
            JLabel currBal = new JLabel("Current Balance: "+acc.current_amount);
            currBal.setFont(new Font("Arial", Font.PLAIN, 25));
            currBal.setBounds(300, 201, 400, 100);
            add(currBal);
            back = new JButton("Back");
            back.addActionListener(this);
            back.setFont(new Font("Arial", Font.PLAIN, 30));
            back.setBounds(300, 800, 300, 100);
            add(back);
        }
        else{
        	if(acc.getType().equals("Saving")){
        		
        		if(cust.checkOpenSecurities(account.getId(),1000)!=-1){
        		
                    makeSec = new JButton("Create a securities account!");
                    makeSec.setBounds(700, 600, 200, 50);
                    makeSec.addActionListener(this);
                    add(makeSec);
                }
        	}
            
            
            JLabel accLabel = new JLabel("Change to Account: "+acc.account_id);
            accLabel.setBounds(300, 100, 400, 100);
            accLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            add(accLabel);
            
            JLabel currBal = new JLabel("Current Balance: "+acc.current_amount);
            currBal.setBounds(300, 201, 400, 100);
            currBal.setFont(new Font("Arial", Font.PLAIN, 30));
            add(currBal);

            withdraw = new JRadioButton("Withdraw");
            withdraw.setBounds(300, 302, 200, 50);
            withdraw.setFont(new Font("Arial", Font.PLAIN, 30));
            add(withdraw);
            deposit = new JRadioButton("Deposit");
            deposit.setBounds(300, 353, 200, 50);
            deposit.setFont(new Font("Arial", Font.PLAIN, 30));
            add(deposit);
            ButtonGroup group = new ButtonGroup();
            group.add(withdraw);
            group.add(deposit);
            add(deposit);
            add(withdraw);
            // amount = new JTextField("0");
            NumberFormat amountFormat = NumberFormat.getNumberInstance();
            amount = new JFormattedTextField(amountFormat);
            amount.setFont(new Font("Arial", Font.PLAIN, 30));
            amount.setBounds(500, 302, 200, 200);
            add(amount);
            
            confirm = new JButton("Confirm Transaction");
            confirm.addActionListener(this);
            confirm.setBounds(300, 550, 400, 100);
            confirm.setFont(new Font("Arial", Font.PLAIN, 30));
            add(confirm);

            back = new JButton("Back");
            back.addActionListener(this);
            back.setFont(new Font("Arial", Font.PLAIN, 30));
            back.setBounds(300, 800, 300, 100);
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
            	if(acc.getType().toLowerCase().equals("checking")){
            		Checking checking = (Checking) acc;
            		checking.deposit(((Number)amount.getValue()).doubleValue());
            	}
            	else if(acc.getType().toLowerCase().equals("saving")){
            		Saving saving = (Saving) acc;
            		saving.deposit(((Number)amount.getValue()).doubleValue());
            	}
            
                setVisible(false);
                dispose();
            }
            else if(withdraw.isSelected()){
                //backend call to check if the person has that amount to withdraw
                //if(true){display success}
                // else{
                //     JOptionPane.showMessageDialog(this, "You don't have enough money to do this!");
                // }
            	if(acc.getType().toLowerCase().equals("checking")){
            		Checking checking = (Checking) acc;
            		checking.withdraw(((Number)amount.getValue()).doubleValue());
            	}
            	else if(acc.getType().toLowerCase().equals("saving")){
            		Saving saving = (Saving) acc;
            		saving.withdraw(((Number)amount.getValue()).doubleValue());
            	}
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Please select an action");
            }
            
        }
        else if(e.getSource().equals(makeSec)){
            //logic to make securities account
//        	cust.createSecurities(amount, savingAccountId)
        	if(acc.getType().toLowerCase().equals("saving")){
        		Saving account = (Saving) acc;
	        	if(cust.createSecurities(1050, account.getId())){
	        		JOptionPane.showMessageDialog(this, "Securities account created!");
	        	}
        	}
            dispose();
        }


    }
    public static void main(String[] args) {
        Customer test = new Customer("dfdf","123");
        Account test2 = new Securities(100, "1234","dfdf");
        new CustomerAccountChangePage(test2,test);
    }
}