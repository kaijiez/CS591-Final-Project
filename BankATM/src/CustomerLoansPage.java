import javax.swing.*;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerLoansPage extends JFrame implements ActionListener{
    JButton back;
    Customer cust;

    public CustomerLoansPage(Customer cust){
        this.cust = cust;
        JLabel title = new JLabel("All of your loans");
        title.setBounds(100, 100, 300, 100);
        add(title);

        ArrayList<String> loanStrs = new ArrayList<String>();
        for (Loan loan : cust.getLoans()) {
            loanStrs.add(loan.toString());
        }
        String[] s=Arrays.copyOf(loanStrs.toArray(), loanStrs.toArray().length, String[].class);
        JList<String> loans = new JList<String>((String[]) s);

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