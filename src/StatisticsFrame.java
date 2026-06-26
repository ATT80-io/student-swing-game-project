import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;

    private JLabel lblUsername;
    private JLabel lblWins;
    private JLabel lblLosses;
    private JLabel lblDraws;
    private JLabel lblScore;

    private JButton btnRefresh;
    private JButton btnClose;

    public StatisticsFrame(Player player) {

        this.currentPlayer = player;
        this.playerService = new PlayerService();

        initializeComponents();
        initializeFrame();
        registerEvents();

        loadStatistics();
    }

    private void initializeComponents() {

        JLabel lblTitle = new JLabel("MY STATISTICS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        lblUsername = new JLabel();
        lblWins = new JLabel();
        lblLosses = new JLabel();
        lblDraws = new JLabel();
        lblScore = new JLabel();

        btnRefresh = new JButton("Refresh");
        btnClose = new JButton("Close");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWins.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLosses.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDraws.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRefresh.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(15));
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblUsername);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblWins);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblLosses);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblDraws);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblScore);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnRefresh);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnClose);

        add(panel);

    }

    private void initializeFrame() {

        setTitle("Statistics");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    private void registerEvents() {

        btnRefresh.addActionListener(e -> loadStatistics());

        btnClose.addActionListener(e -> dispose());

    }

    private void loadStatistics() {

        Player latestPlayer = playerService.getPlayerById(currentPlayer.getId());

        if (latestPlayer == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load statistics."
            );

            return;
        }

        currentPlayer = latestPlayer;

        lblUsername.setText("Username : " + currentPlayer.getUsername());
        lblWins.setText("Wins : " + currentPlayer.getWins());
        lblLosses.setText("Losses : " + currentPlayer.getLosses());
        lblDraws.setText("Draws : " + currentPlayer.getDraws());
        lblScore.setText("Score : " + currentPlayer.getScore());

    }

}