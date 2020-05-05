import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;
public class OpenAccountPage extends JFrame implements ActionListener{
    //Customer cust
    JButton confirm, cancel;
    JRadioButton checking, savings;
    JFormattedTextField amount;
    public OpenAccountPage(/*customer customer*/){
        //cust = customer;
        confirm = new JButton("Confirm open account");
        confirm.setBounds(400, 650, 200, 100);
        confirm.addActionListener(this);
        add(confirm);

        cancel = new JButton("Cancel");
        cancel.setBounds(400, 850, 100, 50);
        cancel.addActionListener(this);
        add(cancel);
        JLabel type = new JLabel("What type of account would you like to open?");
        type.setBounds(300, 100, 400, 100);
        add(type);

        JRadioButton checking = new JRadioButton("Checking");
        checking.setBounds(300, 302, 100, 50);
        add(checking);
        savings = new JRadioButton("Savings");
        savings.setBounds(300, 353, 100, 50);
        add(savings);
        ButtonGroup group = new ButtonGroup();
        group.add(savings);
        group.add(checking);
        add(savings);
        JLabel amountLabel = new JLabel("How much would you like to deposit to start?");
        amountLabel.setBounds(400, 250, 400, 100);
        add(amountLabel);
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amount = new JFormattedTextField(amountFormat);
        amount.setBounds(400, 350, 100, 50);
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
        new OpenAccountPage();
    }
    
}