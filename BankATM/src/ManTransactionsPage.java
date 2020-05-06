import java.awt.event.*;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.*;
public class ManTransactionsPage extends JFrame implements ActionListener{
    //Page for the manager to view all of the transactions for a day.

    JButton back;
    BankManager man;
    public ManTransactionsPage(BankManager manager){
        man = manager;
        JLabel title = new JLabel("Transactions for day: "+LocalDate.now().toString());
        title.setBounds(100, 100, 500, 100);
        add(title);

        String[] transacs = man.viewDailyTransactions(LocalDate.now().toString()).split("\n");
        JList<String> transaclist = new JList<String>(transacs);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(transaclist);
        transaclist.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 400, 500);
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
        // TODO Auto-generated method stub

    }
    
    public static void main(String[] args) {
        //new ManTransactionsPage();
    }
}