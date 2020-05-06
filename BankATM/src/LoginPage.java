import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginPage extends JFrame implements ActionListener{
    //Main login page where either customers or managers can log in

    JLabel username, password;
    JTextField usernameField;
    JButton login;
    JPasswordField passwordField;

    public LoginPage(){
        //JFrame frame = new JFrame("Login");
        JPanel panel = new JPanel(new GridLayout(3,0,50,50));
        
        username = new JLabel("username");
        password = new JLabel("password");
        username.setFont(new Font("Arial", Font.PLAIN, 40));
        password.setFont(new Font("Arial", Font.PLAIN, 40));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 40));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 40));
        login = new JButton("Login");
        login.setPreferredSize(new Dimension(300, 300));
        login.setFont(new Font("Arial", Font.PLAIN, 40));
        login.addActionListener(this);
        setTitle("LOGIN FORM");
        // username.setBounds(80, 70, 200, 30);
        // password.setBounds(80, 110, 200, 30);
        // usernameField.setBounds(300, 70, 200, 30);
        // passwordField.setBounds(300, 110, 200, 30);
        // login.setBounds(150, 160, 100, 30);
      
        panel.add(username);
        panel.add(usernameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(login);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 1000);
        setLocation(500,500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String user = usernameField.getText();
        String passTry = passwordField.getText();//deprecated but it doesn't matter
        //System.out.println(user+passTry);
        //if(dbquery true){
            //loginpage(user)
        //}
        Customer customer = new Customer(user,passTry);
        BankManager manager = new BankManager(user,passTry);
        //check for customer login
        if(customer.logIn()){
        	System.out.println("Success! moving to main page");
            new CustomerHomePage(customer);
            setVisible(false);
            dispose();
        }
        //check for bankmanager login
        else if(manager.logIn()){
        	System.out.println("Success! Moving to Manager home page");
            new ManagerHomePage("admin", "admin");
            setVisible(false);
            dispose();
        }
        else{
        	System.out.println("Login unsuccessful, invalid username or password");
        }
        

    }

    

    public static void main(String[] args) {
        new LoginPage();
        
    }
}