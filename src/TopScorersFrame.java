import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton btnRefresh;
    private JButton btnClose;

    private PlayerService playerService;

    public TopScorersFrame() {

        playerService = new PlayerService();

        initializeComponents();
        initializeFrame();
        registerEvents();

        loadTopScorers();
    }

    private void initializeComponents() {

        JLabel lblTitle = new JLabel("TOP 5 SCORERS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        tableModel = new DefaultTableModel(
                new Object[]{"Rank", "Username", "Wins", "Draws", "Losses", "Score"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        btnRefresh = new JButton("Refresh");
        btnClose = new JButton("Close");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnClose);

        setLayout(new BorderLayout());

        add(lblTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void initializeFrame() {

        setTitle("Top 5 Scorers");
        setSize(650, 350);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    private void registerEvents() {

        btnRefresh.addActionListener(e -> loadTopScorers());

        btnClose.addActionListener(e -> dispose());

    }

    private void loadTopScorers() {

        tableModel.setRowCount(0);

        ArrayList<Player> players = playerService.getTopFiveScorers();

        int rank = 1;

        for (Player player : players) {

            tableModel.addRow(new Object[]{
                    rank++,
                    player.getUsername(),
                    player.getWins(),
                    player.getDraws(),
                    player.getLosses(),
                    player.getScore()
            });

        }

    }

}