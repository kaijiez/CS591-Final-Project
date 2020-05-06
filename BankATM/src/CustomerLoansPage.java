import javax.swing.*;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.awt.event.*;
import java.util.ArrayList;

public class CustomerLoansPage extends JFrame implements ActionListener{
    //Page for customer to view their current loans with the bank

    JButton back;
    //Customer cust

    public CustomerLoansPage(/*Customer customer*/){
        //cust = customer;
        JLabel title = new JLabel("All of your loans");
        title.setBounds(100, 100, 300, 100);
        add(title);

        ArrayList<String> loanStrs = new ArrayList<String>();
        for (Loan loan : customer.getLoans()) {
            loanStrs.add(loan.toString());
        }
        JList<String> loans = new JList<String>((String[]) loanStrs.toArray());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(loans);
        loans.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 500, 500);
        add(scrollPane);



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
        Object source = e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();
        }

    }
    
}