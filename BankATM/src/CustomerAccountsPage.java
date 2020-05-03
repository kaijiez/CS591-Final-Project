import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class CustomerAccountsPage extends JFrame implements ActionListener{
    //Customer cust
    JList Jtest;
    JLabel details;
    JButton makeChange, back;
    public CustomerAccountsPage(/*Customer cust*/){
        //JPanel panel = new JPanel();
        details = new JLabel();
        details.setSize(500,100); 
        details.setBounds(100,200,250,75);
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //this.cust=cust;
        /*for (account acc : cust.accounts) {
            if(acc.type.equals("checking") || acc.getType()=="savings"){*/
                
            //}
        /*}*/
        ArrayList<String> test = new ArrayList<String>();
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");

        Jtest = new JList<>(test.toArray());
        Jtest.setBounds(100, 100,75,75);
        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(Jtest);
        Jtest.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 100, 200, 100);
        add(scrollPane);
        makeChange = new JButton("Make Change to Account");
        makeChange.addActionListener(this);
        makeChange.setBounds(300,150,80,30);  
        
        back = new JButton("Back to Home");
        
        back.addActionListener(this);
        back.setBounds(200, 350, 150, 30);
        add(back);
        //add(details);
        add(makeChange);
        //add(panel);
        setSize(500,500);
        setLayout(null);
        setLocation(500,500);
        // setMinimumSize(new Dimension(500,500));
        // setLocation(500, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(back)){
            System.out.println("Back button");
            setVisible(false);
            dispose();
        }
        else if(e.getSource().equals(makeChange)){
            System.out.println("bring to accountChangePage(getSelectedText)");
            details.setText(Jtest.getSelectedValue().toString());
            //index arraylsit here
            //new CustomerAccountChangePage(cust.accountsaccountList.getSelectedIndex())
            
        }
        
    }

    public static void main(String[] args) {
        new CustomerAccountsPage();
    }
}
