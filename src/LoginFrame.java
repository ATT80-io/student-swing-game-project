import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private PlayerService playerService;

    public LoginFrame() {

        playerService = new PlayerService();

        initializeComponents();
        initializeFrame();
        registerEvents();
    }

    private void initializeComponents() {

        JLabel lblTitle = new JLabel("TIC TAC TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);

        btnLogin = new JButton("Login");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUsername, gbc);

        gbc.gridx = 1;
        panel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPassword, gbc);

        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        add(panel);
    }

    private void initializeFrame() {

        setTitle("Tic Tac Toe Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }

    private void registerEvents() {

        btnLogin.addActionListener(e -> login());

        txtPassword.addActionListener(e -> login());

    }

    private void login() {

        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username and Password must be filled!",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Player player = playerService.login(username, password);

        if (player != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful!"
            );

            MainMenuFrame menuFrame = new MainMenuFrame(player);
            menuFrame.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPassword.setText("");

        }

    }

}