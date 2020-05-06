import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.text.NumberFormat;

public class LoanAppPage extends JFrame implements ActionListener{
    Customer cust;

    //Page for customers to apply for a loan and give a collateral

    JButton confirm, back;
    JFormattedTextField amount;
    JTextField collateral;
    public LoanAppPage(Customer cust){
        this.cust = cust;
        JLabel title = new JLabel("Welcome to the loan application page");
        title.setBounds(100, 100, 700, 100);
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        add(title);

        JLabel amntLabel = new JLabel("Please enter an amount");
        amntLabel.setBounds(100, 270, 400, 30);
        amntLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        add(amntLabel);

        JLabel collatLabel = new JLabel("Please enter a collateral to be used");
        collatLabel.setBounds(100, 620, 500, 30);
        collatLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        add(collatLabel);


        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amount = new JFormattedTextField(amountFormat);
        amount.setFont(new Font("Arial", Font.PLAIN, 30));
        amount.setBounds(100, 302, 300, 200);
        add(amount);

        collateral = new JTextField();
        collateral.setFont(new Font("Arial", Font.PLAIN, 30));
        collateral.setBounds(100, 650, 300, 200);
        add(collateral);
        
        confirm = new JButton("Confirm Application");
        confirm.addActionListener(this);
        confirm.setFont(new Font("Arial", Font.PLAIN, 30));
        confirm.setBounds(650, 550, 300, 100);
        add(confirm);


        back = new JButton("Back");
        back.setBounds(650, 700, 300, 100);
        back.setFont(new Font("Arial", Font.PLAIN, 30));
        back.addActionListener(this);
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
        else if(source.equals(confirm)){
        	 ((Number)amount.getValue()).doubleValue();
        	cust.requestLoans(((Number)amount.getValue()).doubleValue(),collateral.getText());
            
            //logic to add the new loan to the queue??
            // if(true){
            //     //add the loan
            // }
            // else{
            //     JOptionPane.showMessageDialog(this, "Please choose a different collateral");
            // }
        }

    }
    
    public static void main(String[] args) {
        new LoanAppPage(new Customer("dfd","htgh"));
    }
}