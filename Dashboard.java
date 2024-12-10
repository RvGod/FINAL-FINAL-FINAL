import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.event.*;
public class Dashboard extends JFrame implements ActionListener{
    Color colorOrange = new Color(255, 152,0);
    JLabel title = new JLabel("FixTrack");
    JLabel title2 = new JLabel("Pro"); 
    Icon menuIcon = new ImageIcon("hamburger32rev.png");
    JButton menuBtn = new JButton();
    JLabel txt = new JLabel("Welcome!");
    JLabel txt2 = new JLabel("Choose a service of choice");
    Font font1 = new Font("SansSerif", Font.BOLD, 34);
    Font font2 = new Font("SansSerif", Font.BOLD, 25);
    Font font3 = new Font("SansSerif", Font.BOLD, 20);
    JButton repairBtn = new JButton("REPAIR");
    JButton maintenanceBtn = new JButton("MAINTENANCE");
    String serviceType;
    public Dashboard() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.setTitle("FixTrack Pro");
        this.setSize(700,400);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(600, 50));
    
        header.setBackground(Color.BLACK);

        title.setFont(font1);
        title.setForeground(Color.white);
        title2.setFont(font1);
        title2.setForeground(colorOrange);
   
        panel.add(title2, BorderLayout.LINE_START);
        header.add(title, BorderLayout.LINE_START);
        
        panel.setBackground(Color.BLACK);
        JPopupMenu dropdownMenu = new JPopupMenu();

        JMenuItem option1 = new JMenuItem("HOME");
        JMenuItem option2 = new JMenuItem("LOGIN");

        dropdownMenu.add(option1);
        dropdownMenu.add(option2);
    

        option1.addActionListener(e -> {
             new Dashboard();
           
            this.dispose();
          });

          option2.addActionListener(e -> {
            new LoginPage();
            this.dispose();
          });
    

        menuBtn.setIcon(menuIcon);
        menuBtn.setBorder(BorderFactory.createEmptyBorder(0 , 20, 0, 20));
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownMenu.show(menuBtn, 0, menuBtn.getHeight());
            }
        });
        panel.add(menuBtn);
        header.add(panel);
        menuBtn.setBackground(Color.black);
        menuBtn.setFocusPainted(false);
        menuBtn.addActionListener(this);
        header.add(menuBtn, BorderLayout.LINE_END);

        this.add(header, BorderLayout.PAGE_START);

        JPanel body = new JPanel();
        body.setBackground(colorOrange);
        body.setPreferredSize(new Dimension(200, 250));
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        txt.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt.setForeground(Color.black);
        txt.setFont(font1);
        body.add(txt);

        txt2.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt2.setForeground(Color.black);
        txt2.setFont(font2);
        body.add(txt2);

        this.add(body, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        btnPanel.setPreferredSize(new Dimension(600, 120));
        
        repairBtn.setPreferredSize(new Dimension(200, 50));
        repairBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        repairBtn.setFont(font3);
        repairBtn.setFocusPainted(false);
        repairBtn.addActionListener(this);
        repairBtn.setForeground(Color.black);
        repairBtn.setBackground(colorOrange);
        btnPanel.add(repairBtn);

        maintenanceBtn.setPreferredSize(new Dimension(200, 50));
        maintenanceBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        maintenanceBtn.setFont(font3);
        maintenanceBtn.setFocusPainted(false);
        maintenanceBtn.addActionListener(this);

        maintenanceBtn.setForeground(Color.black);
        maintenanceBtn.setBackground(colorOrange);
        btnPanel.add(maintenanceBtn);
        // btnPanel.setBackground(Color.pink);
        btnPanel.setBackground(Color.BLACK);

        this.add(btnPanel, BorderLayout.PAGE_END);
        setVisible(true);
    }

    public String getRepair(){
        return serviceType = "repair";
    }

    public String getMaintenance(){
        return  serviceType = "maintenance";
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuBtn) {
           
        }

        if(e.getSource() == repairBtn) {
            new RepairPage();
            this.dispose();
        }

        if(e.getSource() == maintenanceBtn) {
             new MaintenancePage();
            
            this.dispose();
        }
    }
}
