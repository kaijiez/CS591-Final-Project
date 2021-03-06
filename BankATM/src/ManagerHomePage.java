import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ManagerHomePage extends JFrame implements ActionListener{
    BankManager man;
    // manager has a stockmarket

    //Home page for the manager to access all of the functions the bank manager can use

    protected JButton accounts, transactions, todayTransactions, loans, viewStockMarket, editStockMarket, logout; 
    public ManagerHomePage(String username, String password){
        man = new BankManager(username, password);
        JPanel panel = new JPanel();
        setTitle("Home Page");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel welcome = new JLabel("Welcome"+""/*man.name*/);
        welcome.setFont(new Font("Arial", Font.PLAIN, 40));
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(welcome);
        
        accounts = new JButton("View all customer accounts");
        accounts.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(accounts, panel);
        
        transactions = new JButton("View recent Transactions");
        transactions.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(transactions, panel);
        
        editStockMarket = new JButton("Edit the current Stock Market");
        editStockMarket.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(editStockMarket, panel);
        
        loans = new JButton("view all Customer Loans");
        loans.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(loans, panel);
        
        todayTransactions = new JButton("View all transactions from today");
        todayTransactions.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(todayTransactions, panel);
        
        logout = new JButton("Logout");
        logout.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(logout, panel);
        
        
        
        // panel.add(transactions);
        // panel.add(stockMarket);
        // panel.add(logout);

        //panel.setBounds(100, 100, 300, 300);       
        
        panel.setMinimumSize(new Dimension(1000,1000));
        add(panel);
        setMinimumSize(new Dimension(1000,1000));
        setLocation(500, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(accounts)){
            System.out.println("Accounts button");
            new ManagerAccountsViewPage(this.man);
        }
        else if(source.equals(transactions)){
            System.out.println("transactions button");
            new ManagerTransactionsPage(this.man);
        }
        else if(source.equals(editStockMarket)){
            System.out.println("Edit Stock Market Button");
            new EditStockMarketPage(man.getStockMarket());
        }
        else if(source.equals(loans)){
            System.out.println("Loans button");
            new LoanApprovalPage(this.man);
        }
        else if(source.equals(todayTransactions)){
            System.out.println("Today's Transactions");
            new ManTransactionsPage(this.man);
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
        new ManagerHomePage("dfdf","dfddfd");
    }

    
}