import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class RepairPage extends JFrame implements ActionListener {
  Color colorOrange = new Color(255, 152,0);
  JLabel title = new JLabel("FixTrack");
  JLabel title2 = new JLabel("Pro"); 
  Icon menuIcon = new ImageIcon("hamburger32rev.png");
  JButton menuBtn = new JButton();
  JLabel txt = new JLabel("Repair");
  Font font1 = new Font("SansSerif", Font.BOLD, 34);
  Font font2 = new Font("SansSerif", Font.BOLD, 25);
  Font font3 = new Font("SansSerif", Font.BOLD, 20);

  private ImageIcon laptopIcon, tvIcon, computerIcon, phoneIcon;
  private JPanel header, body, footer;
  private JButton choice1,choice2,choice3,choice4;
  RepairPage(){
  JPanel panel = new JPanel();
  panel.setLayout(new BorderLayout());
  laptopIcon = new ImageIcon("laptop128rev.png");
  tvIcon = new ImageIcon("tv128.png");
  computerIcon = new ImageIcon("computer128.png");
  phoneIcon = new ImageIcon("smartphone128.png");
  
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
  menuBtn.addActionListener(this);
  header.add(menuBtn, BorderLayout.LINE_END);
  
  add(header, BorderLayout.PAGE_START);

  body = new JPanel();
  body.setBackground(colorOrange);
  body.setLayout(new BorderLayout());

  
  JPanel textPanel = new JPanel();
  textPanel.setBackground(colorOrange);
  textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20)); 
  txt.setForeground(Color.black);
  txt.setFont(font1);

  textPanel.add(txt);
  body.add(textPanel, BorderLayout.NORTH); 


  JPanel buttonPanel = new JPanel();
  buttonPanel.setBackground(colorOrange);
  buttonPanel.setLayout(new GridLayout(2, 2, 25, 25));

  choice1 = new JButton("LAPTOP");
  choice2 = new JButton("PHONE");
  choice3 = new JButton("COMPUTER");
  choice4 = new JButton("TV");
  
  choice1.setVerticalTextPosition(AbstractButton.BOTTOM);
  choice1.setHorizontalTextPosition(AbstractButton.CENTER);
  choice1.setFont(new Font("impact", Font.BOLD,70));
  choice1.setIconTextGap(-100);
  choice1.setForeground(colorOrange);
  choice1.setBackground(Color.black);
  choice1.setIcon(laptopIcon);
  // choice1.setBorderPainted(false); 
  // choice1.setContentAreaFilled(false); 
  choice1.setFocusPainted(false); 
  // choice1.setOpaque(false);
  choice1.addActionListener(this);
  
  choice2.setVerticalTextPosition(AbstractButton.BOTTOM);
  choice2.setHorizontalTextPosition(AbstractButton.CENTER);
  choice2.setFont(new Font("impact", Font.BOLD,70));
  choice2.setIconTextGap(-100);
  choice2.setForeground(colorOrange);
  choice2.setBackground(Color.black);
  choice2.setIcon(phoneIcon);
  // choice2.setBorderPainted(false); 
  // choice2.setContentAreaFilled(false); 
  choice2.setFocusPainted(false); 
  //choice2.setOpaque(false);
  choice2.addActionListener(this);
  
  choice3.setVerticalTextPosition(AbstractButton.BOTTOM);
  choice3.setHorizontalTextPosition(AbstractButton.CENTER);
  choice3.setFont(new Font("impact", Font.BOLD,70));
  choice3.setIconTextGap(-100);
  choice3.setForeground(colorOrange);
  choice3.setBackground(Color.black);
  choice3.setIcon(computerIcon);
  // choice3.setBorderPainted(false); 
  // choice3.setContentAreaFilled(false); 
  choice3.setFocusPainted(false); 
  // choice3.setOpaque(false);
  choice3.addActionListener(this);

  choice4.setVerticalTextPosition(AbstractButton.BOTTOM);
  choice4.setHorizontalTextPosition(AbstractButton.CENTER);
  choice4.setFont(new Font("impact", Font.BOLD,70));
  choice4.setIconTextGap(-100);
  choice4.setForeground(colorOrange);
  choice4.setBackground(Color.black);
  choice4.setIcon(tvIcon);
  // choice4.setBorderPainted(false); 
  //choice4.setContentAreaFilled(false); 
  choice4.setFocusPainted(false); 
  // choice4.setOpaque(false);
  choice4.addActionListener(this);

  buttonPanel.add(choice1);
  buttonPanel.add(choice2);
  buttonPanel.add(choice3);
  buttonPanel.add(choice4);

  body.add(buttonPanel, BorderLayout.CENTER);

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
  

  footer = new JPanel(); 
  footer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
  footer.setPreferredSize(new Dimension(0, 50));
  footer.setBackground(Color.BLACK);

  add(footer, BorderLayout.PAGE_END);

  setTitle("FixTrackPro");
  setVisible(true);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setSize(1375, 875);
  setResizable(false);
  }
  public String getLaptop(){
    return "laptop";
  }
  public String getPhone(){
    return "phone";
  }
  public String getCom(){
    return "computer";
  }
  public String getTV(){
    return "tv";
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == choice1){
      new PaymentPageLaptop();
      this.dispose();
    }
    if(e.getSource() == choice2){
      new PaymentPagePhone();
      this.dispose();
    }
    if(e.getSource() == choice3){
      new PaymentPageComputer();
      this.dispose();
    }
    if(e.getSource() == choice4){
      new PaymentPageTV();
      this.dispose();
    }
  }
}
