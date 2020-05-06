import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class LoanApprovalPage extends JFrame implements ActionListener{
    //Page for the bank manager to reject or approve loans

    BankManager man;
    JButton approve, reject, back;
    JList<String> pendingLoanList;

    public LoanApprovalPage(BankManager manager){
        man = manager;
        JLabel title = new JLabel("Loans needing approval");
        title.setBounds(100, 100, 500, 100);
        add(title);

        //ArrayList<String> pendingLoans= man.getPendingLoans();
        pendingLoanList = new JList<String>((String[]) pendingLoans.toArray());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(pendingLoanList);
        pendingLoanList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds(100, 200, 400, 200);
        add(scrollPane);

        ArrayList<String> allLoans = new ArrayList<String>();
        for (Loan loan : man.lookUpAllLoans()) {
            allLoans.add(loan.toString());
            
        }

        approve = new JButton("Approve loan");
        approve.addActionListener(this);
        approve.setBounds(600, 200, 200, 100);
        add(approve);

        reject = new JButton("Reject loan");
        reject.addActionListener(this);
        reject.setBounds(600, 300, 200, 100);
        add(reject);

        JLabel allLabel = new JLabel("All loans out now");
        allLabel.setBounds(100, 450, 300, 50);
        add(allLabel);
        JList<String> allLoanList = new JList<String>((String[]) allLoans.toArray());
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(allLoanList);
        allLoanList.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setBounds(100, 501, 400, 200);
        add(scrollPane2);

        back = new JButton("Back");
        back.setBounds(400, 820, 300, 100);
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
            if(selected==-1){
                JOptionPane.showMessageDialog(this, "Please select a loan to approve");
            }
            else{
                //man.approveLoan(pendingLoans.get(selected),true);
                JOptionPane.showMessageDialog(this, "Loan approved!");

            }
        }
        else if(source.equals(reject)){
            int selected = pendingLoanList.getSelectedIndex();
            if(selected==-1){
                JOptionPane.showMessageDialog(this, "Please select a loan to Reject");
            }
            else{
                //man.approveLoan(pendingLoans.get(selected),false);
                JOptionPane.showMessageDialog(this, "Loan rejected!");

            }
        }

    }
    
}