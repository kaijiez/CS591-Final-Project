import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
public class EditStockMarketPage extends JFrame implements ActionListener{
    //Page for the bank manager to edit the stock market

    JButton back, delete, addNew;
    JList<String> availStocks;
    StockMarket s;
    public EditStockMarketPage(StockMarket mk){
        this.s = mk;
        JLabel title = new JLabel("Edit Stock Market");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setBounds(100, 50, 400, 100);
        add(title);
        
        ArrayList<String> availStockStrings = new ArrayList<String>();
        for (Stock stock : StockMarket.get_open_positions()) {
            availStockStrings.add(stock.toString());
        }
        String[] availStock=Arrays.copyOf(availStockStrings.toArray(), availStockStrings.toArray().length, String[].class);
        availStocks = new JList<String>(availStock);
        availStocks.setFont(new Font("Arial", Font.PLAIN, 30));
        JScrollPane scrollPane2= new JScrollPane();
        scrollPane2.getHorizontalScrollBar().setPreferredSize(new Dimension(0,30));
        scrollPane2.setViewportView(availStocks);
        availStocks.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setBounds(100, 200, 500, 500);
        add(scrollPane2);
        
        addNew = new JButton("Add a new stock");
        addNew.setBounds(650, 300,  300, 100);
        addNew.addActionListener(this);
        add(addNew);
        

        delete = new JButton("delete this stock");
        delete.setFont(new Font("Arial", Font.PLAIN, 30));
        delete.setBounds(650, 600, 300, 100);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(350, 800, 300, 100);
        back.setFont(new Font("Arial", Font.PLAIN, 30));
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
        Object source = e.getSource();
        if(source.equals(back)){
            setVisible(false);
            dispose();
        }
        else if(source.equals(delete)){
            int selected = availStocks.getSelectedIndex();
            String id = (StockMarket.get_open_positions().get(selected).getId());
            this.s.removeStock(id);
            JOptionPane.showMessageDialog(this, "Successfully removed stock");
            dispose();
        }
        else if(source.equals(addNew)){
            new AddStockPage(s);
        }

    }
    
}