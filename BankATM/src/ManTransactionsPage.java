import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.*;
public class ManTransactionsPage extends JFrame implements ActionListener{

    JButton back;
    BankManager man;
    public ManTransactionsPage(BankManager manager){
        man = manager;
        JLabel title = new JLabel("Transactions for day: "+LocalDate.now().toString());
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setBounds(100, 50, 800, 100);
        add(title);
        
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
//		String todayDate=dtf.format(java.time.LocalDate.now());
        System.out.println(LocalDate.now().toString());
        String[] transacs = man.viewDailyTransactions(LocalDate.now().toString()).split("\n");
        JList<String> transaclist = new JList<String>(transacs);
        transaclist.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(transaclist);
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        transaclist.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 700, 500);
        add(scrollPane);
        
        

        back = new JButton("Back");
        back.setBounds(300, 750, 300, 100);
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
    	if(e.getSource().equals(back)){
    		setVisible(false);
    		dispose();
    	}

    }
    
    public static void main(String[] args) {
        //new ManTransactionsPage();
    }
}