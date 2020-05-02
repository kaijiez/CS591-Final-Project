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
        password = new JLabel("password");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        createAccount = new JButton("Create Account");
        createAccount.addActionListener(this);
        setTitle("CREATE ACCOUNT FORM");

        panel.add(username);
        panel.add(usernameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(createAccount);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocation(500,500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = usernameField.getText();
        String passTry = passwordField.getText();//deprecated but it doesn't matter
        System.out.println(user+passTry);
        /* logic to check if there's a user in the db already with that username, if not, then create a user with that password
        if(db.query(SELECT username FROM customers WHERE username = user) !=null/.exists){
            sysout("Username already in use, please enter a new username")
        }
        else{
            db.query(Insert new username, password) //idk syntax for creating a new entry
            //but basically this will add a new row with a new user
            setVisible(false);
            dispose();
        }


        */
        setVisible(false);
        dispose();

    }

    public static void main(String[] args) {
        new CreateAccountPage();
    }
}