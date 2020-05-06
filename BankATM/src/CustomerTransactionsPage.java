import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
public class CustomerTransactionsPage extends JFrame implements ActionListener{
    JButton back;
    Customer cust;
    public CustomerTransactionsPage(Customer cust){
        this.cust = cust;
        JLabel lab = new JLabel("Here are your recent transactions");
        lab.setBounds(50,50,500,50);
        lab.setFont(new Font("Arial", Font.PLAIN, 30));
        add(lab);
        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 30));
        back.addActionListener(this);
        back.setBounds(100, 850, 150, 75);
        add(back);

        //String trans = cust.viewTransactions();
        //String[] transArr = trans.split("\n");
        //JList transactions = new JList(transArr);
//        String[] data = {"one", "two", "three", "four"};
        String[] data = cust.viewTransactions().split("\n");
        JList<String> transactions = new JList<String>(data);
        transactions.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(transactions);
        transactions.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 150, 600, 600);
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 30));
        add(scrollPane);

        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


	@Override
	public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
		
	}


    public static void main(String[] args) {
        new CustomerTransactionsPage(new Customer("s","s"));
    }
}