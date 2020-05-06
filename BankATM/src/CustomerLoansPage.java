import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerLoansPage extends JFrame implements ActionListener{
    JButton back;
    Customer cust;

    public CustomerLoansPage(Customer cust){
        this.cust = cust;
        JLabel title = new JLabel("All of your loans");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setBounds(100, 100, 300, 100);
        add(title);

        ArrayList<String> loanStrs = new ArrayList<String>();
        for (Loan loan : cust.getLoans()) {
            loanStrs.add(loan.toString());
        }
        String[] s=Arrays.copyOf(loanStrs.toArray(), loanStrs.toArray().length, String[].class);
        JList<String> loans = new JList<String>((String[]) s);
        loans.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        scrollPane.setViewportView(loans);
        loans.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 500, 500);
        add(scrollPane);



        back = new JButton("Back");
        back.setBounds(100, 750, 300, 100);
        back.setFont(new Font("Arial", Font.PLAIN, 40));
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
        Object source = e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();
        }

    }
    public static void main(String[] args) {
        new CustomerLoansPage(new Customer("1","1"));
    }
    
}