import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DeliveryStatusPage extends JFrame implements ActionListener {
    Color colorOrange = new Color(255, 152,0);
    JLabel title = new JLabel("FixTrack");
    JLabel title2 = new JLabel("Pro"); 
    Icon menuIcon = new ImageIcon("hamburger32rev.png");
    JButton menuBtn = new JButton();
    JLabel txt = new JLabel("Maintenance");
    Font font1 = new Font("SansSerif", Font.BOLD, 34);
    Font font2 = new Font("SansSerif", Font.BOLD, 25);
    Font font3 = new Font("SansSerif", Font.BOLD, 20);

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton, updateButton;
    private JComboBox<String> statusComboBox;
    private ArrayList<String[]> orderData = new ArrayList<>();
    private JLabel searchLabel;
    public DeliveryStatusPage() {
        JPanel panel = new JPanel();
        setTitle("Delivery Status");
        setSize(1375, 875);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
 
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(0, 50));
        headerPanel.setBackground(Color.black);

        JLabel titleLabel = new JLabel("Delivery Status Tracking");
        titleLabel.setForeground(colorOrange);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPopupMenu dropdownMenu = new JPopupMenu();
  
        JMenuItem option1 = new JMenuItem("HOME");
       
        dropdownMenu.add(option1);
     
    
    
        option1.addActionListener(e -> {
        AdminDashboard d = new AdminDashboard();
        d.setVisible(true);
        this.dispose();
        });

    
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownMenu.show(menuBtn, 0, menuBtn.getHeight());
            }
        });
       
        panel.add(menuBtn);
        panel.setBackground(colorOrange);
        panel.setPreferredSize(new Dimension(50,50));
        menuBtn.setIcon(menuIcon);
        menuBtn.setBorder(BorderFactory.createEmptyBorder(0 , 20, 0, 20));
        menuBtn.setBackground(Color.black);
        menuBtn.setFocusPainted(false);
        headerPanel.add(menuBtn, BorderLayout.EAST);

        

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setPreferredSize(new Dimension(0,100));
        searchPanel.setBackground(Color.black);
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.setForeground(Color.black);
        searchButton.setFocusable(false);
        searchButton.setBackground(colorOrange);
        searchButton.addActionListener(this);
        searchLabel = new JLabel("Search by Name");
        searchLabel.setForeground(colorOrange);
        searchPanel.add(searchLabel);
        searchPanel.setForeground(colorOrange);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);


        String[] columns = {"Service", "Appliance", "Name", "Address", "Payment", "Method", "Contact", "Balance", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        JPanel updatePanel = new JPanel(new FlowLayout());
        statusComboBox = new JComboBox<>(new String[]{"Picked Up", "Repairing/Maintaining", "Shipped", "Delivered"});
        updateButton = new JButton("Update Status");
        updateButton.addActionListener(this);
        updatePanel.add(new JLabel("Update Status:"));
        updatePanel.add(statusComboBox);
        updatePanel.add(updateButton);
        add(updatePanel, BorderLayout.EAST);

  
        loadOrderData();

        setVisible(true);
    }

    private void loadOrderData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) { 
                    orderData.add(parts);
                    tableModel.addRow(parts);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading order data: " + e.getMessage());
        }
    }

    private void saveOrderData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            for (String[] order : orderData) {
                writer.write(String.join(",", order));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving order data: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchText = searchField.getText().toLowerCase();
            tableModel.setRowCount(0); // Clear table
            for (String[] order : orderData) {
                if (order[2].toLowerCase().contains(searchText)) {
                    tableModel.addRow(order);
                }
            }
        } else if (e.getSource() == updateButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String newStatus = (String) statusComboBox.getSelectedItem();
                orderData.get(selectedRow)[8] = newStatus; 
                tableModel.setValueAt(newStatus, selectedRow, 8);
                saveOrderData(); 
                JOptionPane.showMessageDialog(this, "Status updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select an order to update.");
            }
        }
    }
}
