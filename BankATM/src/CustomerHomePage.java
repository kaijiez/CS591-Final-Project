import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CustomerHomePage extends JFrame implements ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = 5667596637661588200L;
     Customer cust;
    protected JButton accounts, transactions, stockMarket, logout, newAcc, loanApp, currLoans; 
    public CustomerHomePage(Customer cust){
        this.cust=cust;
        JPanel panel = new JPanel();
        setTitle("Home Page");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel welcome = new JLabel("Welcome"+""/*cust.name*/);
        welcome.setFont(new Font("Arial", Font.PLAIN, 40));
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(welcome);
        
        newAcc = new JButton("Open a new account");
        newAcc.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(newAcc, panel);
        
        accounts = new JButton("View accounts");
        accounts.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(accounts, panel);
        
        transactions = new JButton("View recent Transactions");
        transactions.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(transactions, panel);
        
        loanApp = new JButton("Apply for a loan");
        loanApp.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(loanApp, panel);
        
        currLoans = new JButton("View current Loans");
        currLoans.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(currLoans, panel);
        
        stockMarket = new JButton("View the current Stock Market");
        stockMarket.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(stockMarket, panel);
        
        logout = new JButton("Logout");
        logout.setFont(new Font("Arial", Font.PLAIN, 40));
        addAButton(logout, panel);
        
        
        
        // panel.add(transactions);
        // panel.add(stockMarket);
        // panel.add(logout);

        //panel.setBounds(100, 100, 300, 300);       
        

        panel.setMinimumSize(new Dimension(500,500));
        add(panel);
        setMinimumSize(new Dimension(1000,1000));
        setLocation(500, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(newAcc)){
            System.out.println("Open account button"); //tentatively done
            new OpenAccountPage(cust);
        }
        else if(source.equals(accounts)){
            System.out.println("Accounts button");
            new CustomerAccountsPage(cust);//tentatively done
        }
        else if(source.equals(transactions)){
            System.out.println("transactions button");
            new CustomerTransactionsPage(cust); //tentatively done
        }
        else if(source.equals(stockMarket)){
            System.out.println("Stock market button");
            new StockMarketPage(cust); //tentatively done
        }
        else if(source.equals(loanApp)){
            System.out.println("Loanapp button");
            new LoanAppPage(cust); //tentatively done
        }
        else if(source.equals(currLoans)){
            System.out.println("current loans button");
            new CustomerLoansPage(cust);
        }
        else if(source.equals(logout)){
            System.out.println("Logout button"); //done
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
        new CustomerHomePage(new Customer("1","1"));
    }

    
}