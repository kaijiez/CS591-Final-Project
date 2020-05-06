import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ManagerAccountsViewPage extends JFrame implements ActionListener{
    //Page where the manager can view every account at the bank currently.

    JButton back;
    public ManagerAccountsViewPage(){
        JLabel title = new JLabel("All customer accounts");
        title.setBounds(100, 100, 200, 50);
        add(title);

        

        ArrayList<Account> allAccs = BankManager.getAllAccounts();
        ArrayList<String> allAccsStr = new ArrayList<String>();
        for (Account account : allAccs) {
            allAccsStr.add(account.toString());
        }
        JList<String> accsList = new JList<String>((String[]) allAccsStr.toArray());

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