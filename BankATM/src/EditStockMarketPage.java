import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class EditStockMarketPage extends JFrame implements ActionListener{
    JButton back, delete, addNew;
    JList<String> availStocks;
    StockMarket s;
    public EditStockMarketPage(StockMarket mk){
        this.s = mk;
        JLabel title = new JLabel("Edit Stock Market");
        title.setBounds(100, 100, 200, 50);
        add(title);
        
        ArrayList<String> availStockStrings = new ArrayList<String>();
        for (Stock stock : StockMarket.get_open_positions()) {
            availStockStrings.add(stock.toString());
        }
        String[] availStock=Arrays.copyOf(availStockStrings.toArray(), availStockStrings.toArray().length, String[].class);
        availStocks = new JList<String>(availStock);
        JScrollPane scrollPane2= new JScrollPane();
        scrollPane2.setViewportView(availStocks);
        availStocks.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setBounds(100, 200, 500, 500);
        add(scrollPane2);

        delete = new JButton("delete this stock");
        delete.setBounds(650, 400, 300, 100);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(400, 800, 200, 100);
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
            //new addStockPage();
        }

    }
    
}