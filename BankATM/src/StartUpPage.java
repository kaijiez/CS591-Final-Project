import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;

public class StartUpPage extends JFrame{
    
    public StartUpPage(){
        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Creat Account");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        add(panel);
        panel.add(loginButton);
        panel.add(createAccountButton);

        LoginListener logList = new LoginListener();
        loginButton.addActionListener(logList);
        
        

        CreateAccountListener caListen = new CreateAccountListener();
        createAccountButton.addActionListener(caListen);
        


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
        JFrame frame = new StartUpPage();
        frame.setTitle("Main Page");    
        frame.setSize(500, 500);
        frame.setLocation(500, 500);

        frame.setVisible(true);

    }
}