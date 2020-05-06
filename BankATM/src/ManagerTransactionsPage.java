import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.time.*;
import java.util.Arrays;
public class ManagerTransactionsPage extends JFrame implements ActionListener{

    JButton back;
    public ManagerTransactionsPage(BankManager manager){
        JLabel title = new JLabel("All customer transactions");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setBounds(100, 50, 500, 100);
        add(title);


        String[] allTransactions= Arrays.copyOf(manager.getAllTransactions().toArray(), manager.getAllTransactions().toArray().length, String[].class);
        JList<String> accsList = new JList<String>(allTransactions);
        accsList.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(accsList);
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        accsList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 700, 500);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(400, 800, 200, 50);
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
    
}
