import javax.swing.*;
import java.awt.event.*;
public class CustomerTransactionsPage extends JFrame implements ActionListener{
    //Page for customers to view their list of bank transactions

    JButton back;
    //customer cust;
    public CustomerTransactionsPage(/*Customer customer*/){
        //cust = customer;
        JLabel lab = new JLabel("Here are your recent transactions");
        lab.setBounds(50,50,500,50);
        add(lab);
        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(100, 850, 150, 30);
        add(back);

        //String trans = cust.viewTransactions();
        //String[] transArr = trans.split("\n");
        //JList transactions = new JList(transArr);
        String[] data = {"one", "two", "three", "four"};
        JList<String> transactions = new JList<String>(data);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(transactions);
        transactions.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 400, 400);
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
        new CustomerTransactionsPage(/*customer*/);
    }
}