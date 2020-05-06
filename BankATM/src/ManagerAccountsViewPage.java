import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagerAccountsViewPage extends JFrame implements ActionListener{
    JButton back;
    public ManagerAccountsViewPage(BankManager manager){
        JLabel title = new JLabel("All customer accounts:");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setBounds(100, 50, 500, 100);
        add(title);

        

        ArrayList<Account> allAccs = manager.lookUpAllAccounts();
        ArrayList<String> allAccsStr = new ArrayList<String>();
        for (Account account : allAccs) {
            allAccsStr.add(account.toString());
        }
        String[] allAccounts= Arrays.copyOf(allAccsStr.toArray(), allAccsStr.toArray().length, String[].class);
        JList<String> accsList = new JList<String>(allAccounts);
        accsList.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        scrollPane.setViewportView(accsList);
        accsList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 700, 500);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(400, 800, 200, 100);
        back.setFont(new Font("Arial", Font.PLAIN, 40));
        back.addActionListener(this);
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
    	if(e.getSource().equals(back)){
    		setVisible(false);
            dispose();
    	}

    }
    public static void main(String[] args) {
        new ManagerAccountsViewPage(new BankManager("dfd","dfd"));
    }
    
}