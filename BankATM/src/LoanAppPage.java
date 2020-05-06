import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class LoanAppPage extends JFrame implements ActionListener{
    //Page for customers to apply for a loan and give a collateral

    //Customer cust;
    JButton confirm, back;
    JFormattedTextField amount;
    JTextField collateral;
    public LoanAppPage(/*customer customer*/){
        //cust = customer;
        JLabel title = new JLabel("Welcome to the loan application page");
        title.setBounds(100, 100, 500, 20);
        add(title);

        JLabel amntLabel = new JLabel("Please enter an amount");
        amntLabel.setBounds(100, 270, 200, 30);
        add(amntLabel);

        JLabel collatLabel = new JLabel("Please enter a collateral to be used");
        collatLabel.setBounds(100, 420, 300, 30);
        add(collatLabel);


        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amount = new JFormattedTextField(amountFormat);
        amount.setBounds(100, 302, 200, 100);
        add(amount);

        collateral = new JTextField();
        collateral.setBounds(100, 450, 200, 100);
        add(collateral);
        
        confirm = new JButton("Confirm Application");
        confirm.addActionListener(this);
        confirm.setBounds(400, 550, 300, 100);
        add(confirm);


        back = new JButton("Back");
        back.setBounds(400, 750, 300, 100);
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
        new LoanAppPage();
    }
}