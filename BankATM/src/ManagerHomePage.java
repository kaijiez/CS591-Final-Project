import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ManagerHomePage extends JFrame implements ActionListener{
    //Home page for the manager to access all of the functions the bank manager can use

    //Manager man
    protected JButton accounts, transactions, todayTransactions, loans, viewStockMarket, editStockMarket, logout; 
    public ManagerHomePage(String username){
        //man = new manager(username);
        JPanel panel = new JPanel();
        setTitle("Home Page");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel welcome = new JLabel("Welcome"+""/*man.name*/);
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(welcome);
        accounts = new JButton("View all customer accounts");
        addAButton(accounts, panel);
        transactions = new JButton("View recent Transactions");
        addAButton(transactions, panel);
        editStockMarket = new JButton("Edit the current Stock Market");
        addAButton(editStockMarket, panel);
        loans = new JButton("view all Customer Loans");
        addAButton(loans, panel);
        todayTransactions = new JButton("View all transactions from today");
        addAButton(todayTransactions, panel);
        logout = new JButton("Logout");
        addAButton(logout, panel);
        
        
        
        // panel.add(transactions);
        // panel.add(stockMarket);
        // panel.add(logout);

        //panel.setBounds(100, 100, 300, 300);       
        
        panel.setMinimumSize(new Dimension(500,500));
        add(panel);
        setMinimumSize(new Dimension(500,500));
        setLocation(500, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(accounts)){
            System.out.println("Accounts button");
            //new ManagerAccountsViewPage(); //tentatively done
            
        }
        else if(source.equals(transactions)){
            System.out.println("transactions button");
            //new ManagerTransactionsPage(); //tentatively done
        }
        else if(source.equals(editStockMarket)){
            System.out.println("Edit Stock Market Button");
            //new EditStockMarketPage(); //tentatively done
        }
        else if(source.equals(loans)){
            System.out.println("Loans button");
            //new loanApprovalPage();
        }
        else if(source.equals(todayTransactions)){
            System.out.println("Today's Transactions");
            //new ManTransactionsPage(); //tentatively implemented
        }
        else if(source.equals(logout)){
            System.out.println("Logout button");
            setVisible(false);
            dispose();
        }
    }

    private void addAButton(JButton button, Container container) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(100,50));
        container.add(Box.createVerticalStrut(15));
        container.add(button);
    }

    public static void main(String[] args) {
        new ManagerHomePage("Pat");
    }

    
}