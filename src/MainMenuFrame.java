import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    private Player currentPlayer;

    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {

        this.currentPlayer = player;

        initializeComponents();
        initializeFrame();
        registerEvents();
    }

    private void initializeComponents() {

        JLabel lblTitle = new JLabel("MAIN MENU", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblWelcome = new JLabel(
                "Welcome, " + currentPlayer.getUsername(),
                SwingConstants.CENTER
        );

        btnStartGame = new JButton("Start Game");
        btnStatistics = new JButton("My Statistics");
        btnTopScorers = new JButton("Top 5 Scorers");
        btnExit = new JButton("Exit");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStartGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTopScorers.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(15));
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblWelcome);
        panel.add(Box.createVerticalStrut(20));

        panel.add(btnStartGame);
        panel.add(Box.createVerticalStrut(10));

        panel.add(btnStatistics);
        panel.add(Box.createVerticalStrut(10));

        panel.add(btnTopScorers);
        panel.add(Box.createVerticalStrut(10));

        panel.add(btnExit);

        add(panel);
    }

    private void initializeFrame() {

        setTitle("Main Menu");
        setSize(350, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }

    private void registerEvents() {

        btnStartGame.addActionListener(e -> {

            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);

            dispose();

        });

        btnStatistics.addActionListener(e -> {

            StatisticsFrame statisticsFrame =
                    new StatisticsFrame(currentPlayer);

            statisticsFrame.setVisible(true);

        });

        btnTopScorers.addActionListener(e -> {

            TopScorersFrame topScorersFrame =
                    new TopScorersFrame();

            topScorersFrame.setVisible(true);

        });

        btnExit.addActionListener(e -> System.exit(0));

    }

}