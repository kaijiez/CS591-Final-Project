import javax.swing.*;
import java.awt.event.*;
import java.time.*;
public class ManagerTransactionsPage extends JFrame implements ActionListener{

    JButton back;
    public ManagerTransactionsPage(BankManager manager){
        JLabel title = new JLabel("All customer transactions");
        title.setBounds(100, 100, 200, 50);
        add(title);



        String[] allTransactions = manager.viewDailyTransactions(LocalDate.now().toString()).split("\n");
        
        JList<String> accsList = new JList<String>(allTransactions);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(accsList);
        accsList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 100, 700, 500);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(400, 800, 200, 50);
        add(back);

        setSize(1000,1000);
        setLayout(null);
        setLocation(500,500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        setVisible(false);
        dispose();

    }
    
}
