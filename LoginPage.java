import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener {


    Color colorOrange = new Color(255, 152, 0);
    JLabel title = new JLabel("FixTrack");
    JLabel title2 = new JLabel("Pro");
    Icon menuIcon = new ImageIcon("hamburger32rev.png");
    JButton menuBtn = new JButton();
    JLabel txt = new JLabel("Login");
    Font font1 = new Font("SansSerif", Font.BOLD, 34);
    Font font3 = new Font("SansSerif", Font.BOLD, 20);
    JPasswordField passwordText;
    private JTextField userText;
    private JLabel labelUser, labelPass;
    private JButton loginBtn;

    AuthenticateCredentials auth = new AuthenticateCredentials() {
        @Override
        public boolean validateCredentials(String username, String password) {
            return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
        }
    };

    LoginPage() {
        // Setup UI components
        setupUI();

        // Load user database
        if (auth.userDatabase.isEmpty()) {
            JOptionPane.showMessageDialog(this, "User database could not be loaded. Check the file path.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        loginBtn.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            if (auth.validateCredentials(username, password)) {
                AdminDashboard d = new AdminDashboard();
                d.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + username + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void setupUI() {
        // Header panel
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 50));
        header.setBackground(Color.BLACK);

        title.setFont(font1);
        title.setForeground(Color.WHITE);
        title2.setFont(font1);
        title2.setForeground(colorOrange);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        menuBtn.setIcon(menuIcon);
        menuBtn.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        menuBtn.setBackground(Color.BLACK);
        menuBtn.setFocusPainted(false);

        JPopupMenu dropdownMenu = new JPopupMenu();
        JMenuItem option1 = new JMenuItem("HOME");
        dropdownMenu.add(option1);

        option1.addActionListener(e -> {
            new Dashboard();
            this.dispose();
          });

        menuBtn.addActionListener(e -> dropdownMenu.show(menuBtn, 0, menuBtn.getHeight()));

        header.add(menuBtn, BorderLayout.LINE_END);
        header.add(title, BorderLayout.CENTER);
        header.add(title2, BorderLayout.LINE_START);
        this.add(header, BorderLayout.PAGE_START);

        // Body panel
        JPanel body = new JPanel();
        body.setBackground(colorOrange);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txt.setForeground(Color.BLACK);
        txt.setFont(font1);
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelUser = new JLabel("Username");
        labelUser.setForeground(Color.BLACK);
        labelUser.setFont(font3);
        labelUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        userText = new JTextField(20);
        userText.setFont(font3);
        userText.setMaximumSize(new Dimension(250, 30));

        labelPass = new JLabel("Password");
        labelPass.setFont(font3);
        labelPass.setForeground(Color.BLACK);
        labelPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordText = new JPasswordField(20);
        passwordText.setFont(font3);
        passwordText.setMaximumSize(new Dimension(250, 30));

        loginBtn = new JButton("Login");
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(colorOrange);
        loginBtn.setFont(font3);
        loginBtn.setFocusable(false);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        body.add(txt);
        body.add(Box.createRigidArea(new Dimension(0, 30)));
        body.add(labelUser);
        body.add(userText);
        body.add(Box.createRigidArea(new Dimension(0, 20)));
        body.add(labelPass);
        body.add(passwordText);
        body.add(Box.createRigidArea(new Dimension(0, 50)));
        body.add(loginBtn);

        this.add(body, BorderLayout.CENTER);

        // Footer panel
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 50));
        footer.setBackground(Color.BLACK);
        this.add(footer, BorderLayout.PAGE_END);

        // Frame settings
        this.setTitle("FixTrackPro");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle additional actions, if necessary
    }

    // public static void main(String[] args) {
    //     new LoginPage();
    // }
}
