import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
public class StockMarketPage extends JFrame implements ActionListener {
    Customer cust;
    JButton back, edit;
    JList<String> accountList;
    public StockMarketPage(Customer customer){
        cust=customer;
        JLabel market = new JLabel("Welcome to the Stock Market");
        market.setBounds(400, 100, 250, 100);
        add(market);

        JLabel choose = new JLabel("Please choose an account to use in the market");
        choose.setBounds(100, 150, 300, 50);
        add(choose);
        //Arraylist<Securities> accounts = cust.getSecurities;
        //Arraylist<String> ids= new Arraylist<String>();
        // for (Securities sec : accounts) {
        //     ids.add(sec.getAccountId);
        // }
//        String[] data = {"one", "two", "three", "four"};
        String[] data = Arrays.copyOf(cust.getAccounts().toArray(), cust.getAccounts().toArray().length, String[].class);
        accountList = new JList<String>(data);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(accountList);
        accountList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 400, 200);
        add(scrollPane);
        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(100, 800, 100, 30);
        add(back);

        edit = new JButton("Use this account");
        edit.addActionListener(this);
        edit.setBounds(650, 350, 250, 100);
        add(edit);
        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();
        }
        else if(source.equals(edit)){
            int selected = accountList.getSelectedIndex();
            if(selected == -1){
                JOptionPane.showMessageDialog(this, "Please select an account to use.");
            }
            else{
            	if(selected<cust.getAccList().size()){
            		if(cust.getAccList().get(selected).getType().toLowerCase().equals("securities")){
            			 Securities selectedSec = (Securities)cust.getAccList().get(selected);
            			 System.out.println("yes");
                        new SecuritiesEditPage(selectedSec);
            		}
            	}
                
                setVisible(false);
                dispose();
            }
        }

    }
    
    public static void main(String[] args) {
//        new StockMarketPage();
    }
}