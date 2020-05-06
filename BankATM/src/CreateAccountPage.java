import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateAccountPage extends JFrame implements ActionListener{
    JLabel username, password;
    JTextField usernameField;
    JButton createAccount;
    JPasswordField passwordField;

    public CreateAccountPage(){
        JPanel panel = new JPanel(new GridLayout(3,1));
        
        username = new JLabel("username");
        password = new JLabel("password");;
        username.setFont(new Font("Arial", Font.PLAIN, 40));
        password.setFont(new Font("Arial", Font.PLAIN, 40));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 40));
        createAccount = new JButton("Create Account");
        createAccount.setFont(new Font("Arial", Font.PLAIN, 40));
        createAccount.addActionListener(this);
        setTitle("CREATE ACCOUNT FORM");

        panel.add(username);
        panel.add(usernameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(createAccount);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 1000);
        setLocation(500,500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = usernameField.getText();
        String passTry = passwordField.getText();//deprecated but it doesn't matter
        System.out.println(user+passTry);
        //Code here on backend to create new customer
        //if(true){created user}
        //else{input new username}
        Customer customer = new Customer(user,passTry);
        Boolean success=customer.signUp();
        if(!success){
        	System.out.println("try again, either username or password is taken");
        }
        else{
        	System.out.println("");
        }
        
        setVisible(false);
        dispose();

    }

    public static void main(String[] args) {
        new CreateAccountPage();
    }
}