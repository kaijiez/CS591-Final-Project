import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class EditStockMarketPage extends JFrame implements ActionListener{
    JButton back, delete, addNew;
    JList<String> availStocks;
    public EditStockMarketPage(){
        JLabel title = new JLabel("Edit Stock Market");
        title.setBounds(100, 100, 200, 50);
        add(title);
        
        ArrayList<String> availStockStrings = new ArrayList<String>();
        for (Stock stock : StockMarket.get_open_positions()) {
            availStockStrings.add(stock.toString());
        }
        availStocks = new JList<String>((String[]) availStockStrings.toArray());
        JScrollPane scrollPane2= new JScrollPane();
        scrollPane2.setViewportView(availStocks);
        availStocks.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setBounds(100, 200, 500, 500);
        add(scrollPane2);

        delete = new JButton("Buy this stock");
        delete.setBounds(650, 400, 300, 100);
        add(delete);

        back = new JButton("Back");
        back.setBounds(400, 860, 200, 100);
        add(back);

        setSize(1000, 1000);
        setLocation(300, 300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();
        }
        else if(source.equals(delete)){
            int selected 
        }

    }
    
}