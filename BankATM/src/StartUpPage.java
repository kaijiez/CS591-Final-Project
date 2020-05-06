import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

public class StartUpPage extends JFrame{
    //Initial landing page when the application starts
    
    public StartUpPage(){
        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");
        loginButton.setPreferredSize(new Dimension(300, 300));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 40));
        createAccountButton.setPreferredSize(new Dimension(400,300));
        createAccountButton.setFont(new Font("Arial", Font.PLAIN, 40));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        add(panel);
        panel.add(loginButton);
        panel.add(createAccountButton);

        LoginListener logList = new LoginListener();
        loginButton.addActionListener(logList);
        
        

        CreateAccountListener caListen = new CreateAccountListener();
        createAccountButton.addActionListener(caListen);
        setTitle("Main Page");    
        setSize(1000, 1000);
        setLocation(1000, 1000);

        setVisible(true);
        


    } //event handlers below

    public class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked the login button, need to bring you to the login page");
            new LoginPage();
            setVisible(false);
            dispose();
        }      
    }
    public class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicked the Create Account button, need to bring you to the new account page");
            new CreateAccountPage();

        }      
    }

    public static void main(String[] args) {
        new StartUpPage();
        

    }
}