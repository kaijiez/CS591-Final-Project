import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;


public class CustomerAccountsPage extends JFrame implements ActionListener{
    //Page with a list of all customer accounts
    
    // Customer cust
    JList Jtest;
    JList accountList;
    JLabel details;
    JButton makeChange, back;
    public CustomerAccountsPage(Customer cust){
        //JPanel panel = new JPanel();
        details = new JLabel();
        details.setSize(500,100); 
        details.setBounds(100,200,250,75);
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //this.cust=cust;
        /*for (account acc : cust.accounts) {
            accountList.add(acc.getaccount_id);
                
            
        }*/
//        ArrayList<String> test = new ArrayList<String>();
//        test.add("1");
//        test.add("2");
//        test.add("3");
//        test.add("4");
//        test.add("5");
        ArrayList<String> test = cust.getAccounts();
        
        Jtest = new JList(test.toArray());
        
        Jtest.setBounds(100, 100,75,75);
        //accountList.setBounds(100, 100,200,200);

        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(Jtest);
        Jtest.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 100, 200, 200);
        add(scrollPane);
        makeChange = new JButton("Make Change to Account");
        makeChange.addActionListener(this);
        makeChange.setBounds(300,150,300,30);  
        
        back = new JButton("Back to Home");
        
        back.addActionListener(this);
        back.setBounds(200, 350, 150, 30);
        add(back);
        //add(details);
        add(makeChange);
        //add(panel);
        setSize(1000,1000);
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
            int selected = accountList.getSelectedIndex();
            if(selected==-1){
                JOptionPane.showMessageDialog(this, "Please select an account");
            }
            else{
                //new CustomerAccountChangePage(//accounts.get(selected));
            }
        }
        
    }

    public static void main(String[] args) {
//        new CustomerAccountsPage();
    }
}
