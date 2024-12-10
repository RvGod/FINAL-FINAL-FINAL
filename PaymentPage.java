import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PaymentPage extends JFrame implements ActionListener{
  JFrame frame = new JFrame();
  String[] names;
  Color colorOrange = new Color(255, 152,0);
  JLabel title = new JLabel("FixTrack");
  JLabel title2 = new JLabel("Pro"); 
  Icon menuIcon = new ImageIcon("hamburger32rev.png");
  JButton menuBtn = new JButton();
  JLabel txt = new JLabel();
  // JLabel txt2 = new JLabel("Choose a service of choice");
  Font font1 = new Font("SansSerif", Font.BOLD, 34);
  Font font2 = new Font("SansSerif", Font.BOLD, 25);
  Font font3 = new Font("SansSerif", Font.BOLD, 20);
  Font font4 = new Font("SansSerif", Font.BOLD, 16);
  private JPanel header, body, footer,PayPanel,Panel2;
  private JTextField textField1,textField2,textField3,textFieldphone;
  private JLabel label1,label2,label3, label4,labelPhone;
  private JCheckBox cButton1, cButton2, cButton3;
  private JRadioButton CODbtn, OLbtn;
  JButton btn;
  JLabel print = new JLabel();
  ButtonGroup group1, group2;
    String name, address, emailphone;

  public String getName(){
    return textField1.getText();
  }
  public String getAdress(){
    return textField2.getText();
  }
  public String getEmail(){
    return textField3.getText();
  }
  
  public String getPhone(){
    return textFieldphone.getText();
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    String Name = textField1.getText() != null ? textField1.getText() : "";
    String Address = textField2.getText() != null ? textField2.getText() : "";
    String emailphone = !textField3.getText().isEmpty() ? textField3.getText() : textFieldphone.getText();
    String payType = CODbtn.isSelected() ? "COD" :
                 OLbtn.isSelected() ? "ONLINE PAYMENT" :
                 " "; 
    String payMethod = 
    CODbtn.isSelected() ? "cash" :
    cButton2.isSelected() ? "paypal" :
    cButton1.isSelected() ? "gcash" :
     cButton3.isSelected() ? "maya" :
    ""; 

  
    if (e.getSource() == CODbtn) {
        payType = "COD";
        group2.clearSelection();
        textFieldphone.setEnabled(true);
        labelPhone.setVisible(true);
        textFieldphone.setVisible(true);
        textField3.setVisible(false);
        label3.setVisible(false);
        PayPanel.setVisible(false);

    } else if (e.getSource() == OLbtn) {
        labelPhone.setVisible(false);
        textFieldphone.setVisible(false);
        PayPanel.setVisible(true);

    } else if (e.getSource() == cButton2) {
        label3.setVisible(true);
        textField3.setVisible(true);
        labelPhone.setVisible(false);
        textFieldphone.setVisible(false);
        PayPanel.setVisible(true);
        textFieldphone.setEnabled(false);
        textFieldphone.setText("");
        textField3.setText("");
        textField3.setEnabled(true);

    } else if (e.getSource() == cButton1 || e.getSource() == cButton3) {
        labelPhone.setVisible(true);
        textFieldphone.setVisible(true);
        label3.setVisible(false);
        textField3.setVisible(false);
        textFieldphone.setEnabled(true);
        textField3.setText("");
    }


    if (e.getSource() == btn) {
        try (FileWriter writer = new FileWriter("data.txt", true)) {  
            writer.write(Name + "," + Address + "," + payType + "," + payMethod + "," + emailphone+"\n");
            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving data: " + ex.getMessage());
        }

        int targetLine = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
          
          String line;
          int currentLine = 1;
          while ((line = reader.readLine()) != null) {
            targetLine++;
            
            
            if (currentLine == targetLine) {
                System.out.println(line);

                break;
            }
        }

        if (currentLine < targetLine) {
            System.out.println("The file does not contain line " + targetLine);
        }
    
        } catch (IOException ex) {
          System.out.println("Error reading the file: " + ex.getMessage());
        }
    }

    frame.revalidate();
    frame.repaint();
}

    PaymentPage(){
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    header = new JPanel();
    header.setLayout(new BorderLayout());
    header.setPreferredSize(new Dimension(0, 50));
    header.setBackground(Color.BLACK);
    add(header);
  
    title.setFont(font1);
    title.setForeground(Color.white);
    title2.setFont(font1);
    title2.setForeground(colorOrange);
  
    panel.add(title2, BorderLayout.LINE_START);
    header.add(title, BorderLayout.LINE_START);
    panel.setBackground(Color.BLACK);
    JPopupMenu dropdownMenu = new JPopupMenu();
  
    JMenuItem option1 = new JMenuItem("Option 1");
    JMenuItem option2 = new JMenuItem("Option 2");
    JMenuItem option3 = new JMenuItem("Option 3");
  
    dropdownMenu.add(option1);
    dropdownMenu.add(option2);
    dropdownMenu.add(option3);
  
    option1.addActionListener(e -> JOptionPane.showMessageDialog(header, "You selected Option 1"));
    option2.addActionListener(e -> JOptionPane.showMessageDialog(header, "You selected Option 2"));
    option3.addActionListener(e -> JOptionPane.showMessageDialog(header, "You selected Option 3"));
  
    menuBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dropdownMenu.show(menuBtn, 0, menuBtn.getHeight());
        }
    });
    panel.add(menuBtn);
    header.add(panel);
    menuBtn.setIcon(menuIcon);
    menuBtn.setBorder(BorderFactory.createEmptyBorder(0 , 20, 0, 20));
    menuBtn.setBackground(Color.black);
    menuBtn.setFocusPainted(false);
    header.add(menuBtn, BorderLayout.LINE_END);
  
    add(header, BorderLayout.PAGE_START);
    
    body = new JPanel();
    body.setBackground(Color.red);
    body.setLayout(new BorderLayout());
  
    JPanel textPanel = new JPanel();
    textPanel.setBackground(colorOrange);
    textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20)); 
    txt.setForeground(Color.black);
    txt.setFont(font1);
    textPanel.add(txt);
    body.add(textPanel, BorderLayout.NORTH);
  
    Panel2= new JPanel();
    Panel2.setPreferredSize(new Dimension(650, 0));
    Panel2.setBackground(colorOrange);
    Panel2.setLayout(new GridLayout(7,2, -250,20));
    textField1 = new JTextField(18);
    textField2 = new JTextField(18);
    textField3 = new JTextField(18);
    
    textField1.setFont(font3);
    textField2.setFont(font3);
    textField3.setFont(font3);
  
  
    label1 = new JLabel("Name");
    label1.setForeground(Color.black);
    label1.setFont(font3);
    label2 = new JLabel("Address");
    label2.setForeground(Color.black);
    label2.setFont(font3);
    label3 = new JLabel("Email");
    label3.setForeground(Color.black);
    label3.setFont(font3);
    label4 = new JLabel("Payment Method");
    label4.setForeground(Color.black);
    label4.setFont(font3);
    
    JPanel paymentPanel = new JPanel();
    paymentPanel.setBackground(colorOrange);
    paymentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
    
    CODbtn = new JRadioButton("Cash On Delivery");
    CODbtn.setForeground(Color.BLACK);
    CODbtn.setBackground(colorOrange);
    CODbtn.setFont(font4);
    CODbtn.setFocusable(false);
    CODbtn.addActionListener(this);
  
    OLbtn = new JRadioButton("Online Payment"); 
    OLbtn.setForeground(Color.BLACK);
    OLbtn.setBackground(colorOrange);
    OLbtn.setFont(font4);
    OLbtn.setFocusable(false);
    OLbtn.addActionListener(this);
  
    group1 = new ButtonGroup();
    group1.add(CODbtn);
    group1.add(OLbtn);
    
    paymentPanel.add(CODbtn);
    paymentPanel.add(OLbtn);
  
    Panel2.add(label1);
    Panel2.add(textField1);
    Panel2.add(label2);
    Panel2.add(textField2);
    Panel2.add(label4);
    Panel2.add(paymentPanel);
  
    cButton1 = new JCheckBox("GCASH");
    cButton2 = new JCheckBox("PAYPAL");
    cButton3 = new JCheckBox("MAYA");
    cButton1.setFont(font4);
    cButton2.setFont(font4);
    cButton3.setFont(font4);
  
    cButton1.setFocusable(false);
    cButton2.setFocusable(false);
    cButton3.setFocusable(false);
  
    ImageIcon checkIcon = new ImageIcon("checkmark32.png");
    ImageIcon paypalIcon = new ImageIcon("paypal32.png");
    ImageIcon gcashIcon = new ImageIcon("gcash32.png");
    ImageIcon mayaIcon = new ImageIcon("maya32.png");
  
    cButton2.setIcon(paypalIcon);
    cButton2.setSelectedIcon(checkIcon);
    // rButton2.setBorderPainted(false); 
    // rButton2.setContentAreaFilled(false); 
    // rButton2.setFocusPainted(false); 
    // rButton2.setOpaque(false);
    cButton1.setIcon(gcashIcon);
    cButton1.setSelectedIcon(checkIcon);
    cButton3.setIcon(mayaIcon);
    cButton3.setSelectedIcon(checkIcon);
  
    cButton1.setBackground(Color.BLACK);
    cButton1.setForeground(Color.white);
    cButton2.setBackground(Color.BLACK);
    cButton2.setForeground(Color.white);
    cButton3.setBackground(Color.BLACK);
    cButton3.setForeground(Color.white);
  
    cButton1.addActionListener(this);
    cButton2.addActionListener(this);
    cButton3.addActionListener(this);

     group2 = new ButtonGroup();
    group2.add(cButton1);
    group2.add(cButton2);
    group2.add(cButton3);
    PayPanel = new JPanel();
    PayPanel.setBackground(colorOrange);
    PayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50,14));
    PayPanel.add(cButton1);
    PayPanel.add(cButton2);
    PayPanel.add(cButton3);
    
    Panel2.add(new JLabel());
    Panel2.add(PayPanel);
     
    
    
    labelPhone = new JLabel("Phone Number");
    labelPhone.setForeground(Color.BLACK);
    labelPhone.setFont(font3);
    
    Panel2.add(labelPhone);
    textFieldphone = new JTextField(18);
    
    textFieldphone.setFont(font3);
    Panel2.add(textFieldphone);
    Panel2.add(label3);
    Panel2.add(textField3);
    Panel2.add(new JLabel());
    
    btn = new JButton("Pay");
    btn.setBackground(Color.BLACK);
    btn.setForeground(colorOrange);
    btn.setFont(font3);
    btn.setFocusable(false);
    btn.addActionListener(this);

    Panel2.add(btn);
    body.add(Panel2, BorderLayout.WEST);
    add(body, BorderLayout.CENTER);
    
    JPanel spacerPanel = new JPanel();
    spacerPanel.setPreferredSize(new Dimension(0, 50)); 
    spacerPanel.setBackground(colorOrange); 
    body.add(spacerPanel, BorderLayout.SOUTH);
  
    JPanel leftpPanel = new JPanel();
    leftpPanel.setPreferredSize(new Dimension(50, 0));
    leftpPanel.setBackground(colorOrange);
    add(leftpPanel, BorderLayout.WEST);
  
    JPanel rightPanel = new JPanel();
    rightPanel.setPreferredSize(new Dimension(50, 0));
    rightPanel.setBackground(colorOrange);
    add(rightPanel, BorderLayout.EAST);
    print = new JLabel();
  
    footer = new JPanel(); 
    footer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
    footer.setPreferredSize(new Dimension(0, 50));
    footer.setBackground(Color.BLACK);
    label3.setVisible(false);
    textField3.setVisible(false);
    PayPanel.setVisible(false);
    labelPhone.setVisible(false);
    textFieldphone.setVisible(false);
    add(footer, BorderLayout.PAGE_END);
    
    body.add(print);
    

    setTitle("FixTrackPro");
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1375, 875);
    setResizable(false);
    }
  
    
    public static void saveTextToFile(String text) {
      // Specify the file to write to
      String fileName = "output.txt";

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
          // Write the text to the file (appending to the file)
          writer.write(text);
          writer.newLine(); // Add a new line after the text

          // Optional: Notify the user that the text has been saved
          System.out.println("Text saved to file.");
      } catch (IOException e) {
          e.printStackTrace();
          // Optional: Show an error message
          JOptionPane.showMessageDialog(null, "An error occurred while saving the file.");
      }
  
    }
}
