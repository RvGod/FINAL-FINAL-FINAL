import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OrderTrackingPage extends JFrame implements ActionListener {
    private JTextField orderNameField, orderPhoneField;
    private JTextArea orderDetailsArea;
    private JButton trackOrderButton, backButton;

    public OrderTrackingPage() {
        setTitle("Order Tracking");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        JLabel headerLabel = new JLabel("Order Tracking", SwingConstants.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Body
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(3, 2, 10, 10));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bodyPanel.setBackground(new Color(255, 152, 0));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bodyPanel.add(nameLabel);

        orderNameField = new JTextField();
        orderNameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bodyPanel.add(orderNameField);

        JLabel phoneLabel = new JLabel("Phone Number/Email:");
        phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bodyPanel.add(phoneLabel);

        orderPhoneField = new JTextField();
        orderPhoneField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bodyPanel.add(orderPhoneField);

        trackOrderButton = new JButton("Track Order");
        trackOrderButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        trackOrderButton.setBackground(Color.BLACK);
        trackOrderButton.setForeground(Color.WHITE);
        trackOrderButton.addActionListener(this);
        bodyPanel.add(trackOrderButton);

        backButton = new JButton("HOME");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        bodyPanel.add(backButton);

        add(bodyPanel, BorderLayout.CENTER);

        // Footer (Order details display)
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        orderDetailsArea = new JTextArea();
        orderDetailsArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        orderDetailsArea.setEditable(false);
        footerPanel.add(new JScrollPane(orderDetailsArea), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == trackOrderButton) {
            String name = orderNameField.getText().trim();
            String phone = orderPhoneField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
                return;
            }

            boolean orderFound = false;
            try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
                String line;
                StringBuilder orderDetails = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    String[] orderData = line.split(",");
                    if (orderData.length > 7 && orderData[2].equalsIgnoreCase(name) && orderData[6].contains(phone)) {
                        orderDetails.append("Service: ").append(orderData[0]).append("\n")
                                    .append("Appliance: ").append(orderData[1]).append("\n")
                                    .append("Name: ").append(orderData[2]).append("\n")
                                    .append("Address: ").append(orderData[3]).append("\n")
                                    .append("Payment Type: ").append(orderData[4]).append("\n")
                                    .append("Payment Method: ").append(orderData[5]).append("\n")
                                    .append("Phone/Email: ").append(orderData[6]).append("\n")
                                    .append("Payment Status: ").append(orderData[7]).append("\n")
                                    .append("Order Status: ").append(orderData[8]).append("\n\n");
                        orderFound = true;
                    }
                }

                if (orderFound) {
                    orderDetailsArea.setText(orderDetails.toString());
                } else {
                    orderDetailsArea.setText("No orders found for the given details.");
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading data file: " + ex.getMessage());
            }
        } else if (e.getSource() == backButton) {
            new Dashboard();
            dispose();
        }
    }
}
