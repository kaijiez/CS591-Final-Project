import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginPage extends JFrame implements ActionListener{
    JLabel username, password;
    JTextField usernameField;
    JButton login;
    JPasswordField passwordField;

    public LoginPage(){
        //JFrame frame = new JFrame("Login");
        JPanel panel = new JPanel(new GridLayout(3,0,50,50));
        
        username = new JLabel("username");
        password = new JLabel("password");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        login = new JButton("Login");
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
        setSize(500, 500);
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
        if(user.equals("boop")){
            if(passTry.equals("boop")){
                System.out.println("Success! moving to main page");
                new CustomerHomePage(user);
                setVisible(false);
                dispose();
            }
            else{
                System.out.println("Login unsuccessful, invalid username or password");       
            }
        }
        else if(user.equals("admin")){//backend check if admin, if true, new admin page
            if(passTry.equals("admin")){
                System.out.println("Success! Moving to Manager home page");
                new ManagerHomePage("admin");
                setVisible(false);
                dispose();
            }
            else{
                System.out.println("Login unsuccessful, invalid username or password");
            }
        }
        else{
            System.out.println("Login unsuccessful, invalid username or password");       
        }

    }

    

    public static void main(String[] args) {
        JFrame frame = new LoginPage();
        
    }
}