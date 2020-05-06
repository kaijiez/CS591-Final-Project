import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
public class LoanApprovalPage extends JFrame implements ActionListener{
    //Page for the bank manager to reject or approve loans

    BankManager man;
    JButton approve, reject, back;
    JList<String> pendingLoanList;

    public LoanApprovalPage(BankManager manager){
        this.man = manager;
        JLabel title = new JLabel("Loans needing approval");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(100, 50, 500, 100);
        add(title);

        ArrayList<String> pendingLoans= man.getAppendingLoans();
        String[] pendingL=Arrays.copyOf(pendingLoans.toArray(), pendingLoans.toArray().length, String[].class);
        pendingLoanList = new JList<String>(pendingL);
        pendingLoanList.setFont(new Font("Arial", Font.PLAIN, 40));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(pendingLoanList);
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        pendingLoanList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 150, 400, 300);
        add(scrollPane);

        ArrayList<String> allLoans = new ArrayList<String>();
        for (Loan loan : man.lookUpAllLoans()) {
            allLoans.add(loan.toString());
            
        }
        String[] allL=Arrays.copyOf(allLoans.toArray(), allLoans.toArray().length, String[].class);
        
        approve = new JButton("Approve loan");
        approve.setFont(new Font("Arial", Font.PLAIN, 30));
        approve.addActionListener(this);
        approve.setBounds(600, 200, 300, 100);
        add(approve);

        reject = new JButton("Reject loan");
        reject.setFont(new Font("Arial", Font.PLAIN, 30));
        reject.addActionListener(this);
        reject.setBounds(600, 300, 300, 100);
        add(reject);

        JLabel allLabel = new JLabel("All loans out now");
        allLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        allLabel.setBounds(100, 500, 300, 100);
        add(allLabel);
        JList<String> allLoanList = new JList<String>(allL);
        allLoanList.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        scrollPane2.setViewportView(allLoanList);
        allLoanList.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setBounds(100, 600, 400, 300);
        add(scrollPane2);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 30));
        back.setBounds(600, 820, 300, 100);
        back.addActionListener(this);
        add(back);

        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source= e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();

        }
        else if(source.equals(approve)){
            int selected = pendingLoanList.getSelectedIndex();
            System.out.println("selected "+selected);
            if(selected==-1){
                JOptionPane.showMessageDialog(this, "Please select a loan to approve");
            }
            else{
                man.approveLoan(man.getPendingLoans().get(selected),true);
                JOptionPane.showMessageDialog(this, "Loan approved!");

            }
        }
        else if(source.equals(reject)){
            int selected = pendingLoanList.getSelectedIndex();
            if(selected==-1){
                JOptionPane.showMessageDialog(this, "Please select a loan to Reject");
            }
            else{
                man.approveLoan(man.getPendingLoans().get(selected),false);
                JOptionPane.showMessageDialog(this, "Loan rejected!");

            }
        }

    }
    
}