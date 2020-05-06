import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.text.NumberFormat;
public class OpenAccountPage extends JFrame implements ActionListener{
    //Page where a customer can open a new bank account with the bank

    //Customer cust
    JButton confirm, cancel;
    JRadioButton checking, savings;
    JFormattedTextField amount;
    Customer cust;
    public OpenAccountPage(Customer cust){
        this.cust = cust;
        confirm = new JButton("Confirm open account");
        confirm.setBounds(300, 650, 400, 100);
        confirm.setFont(new Font("Arial", Font.PLAIN, 30));
        confirm.addActionListener(this);
        add(confirm);

        cancel = new JButton("Cancel");
        cancel.setBounds(300, 820, 300, 100);
        cancel.setFont(new Font("Arial", Font.PLAIN, 40));
        cancel.addActionListener(this);
        add(cancel);
        JLabel type = new JLabel("What type of account would you like to open?");
        type.setFont(new Font("Arial", Font.PLAIN, 30));
        type.setBounds(100, 100, 700, 100);
        add(type);

        checking = new JRadioButton("Checking");
        checking.setBounds(300, 325, 200, 75);
        checking.setFont(new Font("Arial", Font.PLAIN, 30));
        add(checking);
        
        savings = new JRadioButton("Savings");
        savings.setBounds(300, 400, 200, 75);
        savings.setFont(new Font("Arial", Font.PLAIN, 30));
        add(savings);
        
        ButtonGroup group = new ButtonGroup();
        group.add(savings);
        group.add(checking);
        add(savings);
        JLabel amountLabel = new JLabel("How much would you like to deposit to start?");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        amountLabel.setBounds(300, 250, 600, 100);
        add(amountLabel);
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amount = new JFormattedTextField(amountFormat);
        amount.setFont(new Font("Arial", Font.PLAIN, 30));
        amount.setBounds(500, 350, 250, 200);
        add(amount);


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
        if(source.equals(cancel)){
            setVisible(false);
            dispose();
        }
        else if(source.equals(confirm)){
            double money = ((Number)amount.getValue()).doubleValue();
            if(checking.isSelected()){
//            	System.out.println(money);
            	cust.createCheckingOrSaving("Checking", money);
                //if able to open(val){
                    //backend call to create account
                    //JOptionPane.showMessageDialog(this, "Successfully created account");
                    // setVisible(false);
                    // dispose();
                //}
                //else{
                    //JOptionPane.showMessageDialog(this, "Please input an amount greater than x!");
                //}
            }
            else if(savings.isSelected()){
            	cust.createCheckingOrSaving("Saving", money);
                /*
                if able to open(val){
                    backendcall to create savings
                    JOptionPane.showMessageDialog(this, "Successfully created account");
                    setVisible(false);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this, "You please input an amount greater than x!");
                }
                */
            }
            else{
                JOptionPane.showMessageDialog(this, "Please select an account type");
            }   
        }
        

    }

    public static void main(String[] args) {
//        new OpenAccountPage();
    }
    
}