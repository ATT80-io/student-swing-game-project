import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;

    private JButton[] buttons;

    private JLabel lblStatus;

    private JButton btnBack;

    private boolean gameOver;

    public GameFrame(Player player) {

        this.currentPlayer = player;

        playerService = new PlayerService();
        gameLogic = new GameLogic();

        initializeComponents();
        initializeFrame();
        registerEvents();
    }

    private void initializeComponents() {

        buttons = new JButton[9];

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        Font font = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton("");

            buttons[i].setFont(font);

            buttons[i].setFocusPainted(false);

            boardPanel.add(buttons[i]);
        }

        lblStatus = new JLabel(
                "Your Turn (X)",
                SwingConstants.CENTER
        );

        lblStatus.setFont(new Font("Arial", Font.BOLD, 18));

        btnBack = new JButton("Back to Menu");

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(lblStatus, BorderLayout.CENTER);
        bottomPanel.add(btnBack, BorderLayout.SOUTH);

        setLayout(new BorderLayout());

        add(boardPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

    }

    private void initializeFrame() {

        setTitle("Tic Tac Toe");

        setSize(450, 500);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void registerEvents() {

        for (int i = 0; i < buttons.length; i++) {

            int index = i;

            buttons[i].addActionListener(e -> playerMove(index));

        }

        btnBack.addActionListener(e -> {

            MainMenuFrame menu = new MainMenuFrame(currentPlayer);

            menu.setVisible(true);

            dispose();

        });

    }

    private void playerMove(int index) {

        if (gameOver) {
            return;
        }

        if (!gameLogic.makeMove(index, 'X')) {
            return;
        }

        buttons[index].setText("X");

        buttons[index].setEnabled(false);

        if (gameLogic.checkWinner('X')) {

            finishGame("WIN");

            return;

        }

        if (gameLogic.isDraw()) {

            finishGame("DRAW");

            return;

        }

        lblStatus.setText("Computer Thinking...");

        computerMove();

    }

    private void computerMove() {

        int move = gameLogic.computerMove();

        if (move == -1) {

            finishGame("DRAW");

            return;

        }

        buttons[move].setText("O");

        buttons[move].setEnabled(false);

        if (gameLogic.checkWinner('O')) {

            finishGame("LOSE");

            return;

        }

        if (gameLogic.isDraw()) {

            finishGame("DRAW");

            return;

        }

        lblStatus.setText("Your Turn (X)");

    }

    private void finishGame(String result) {

        gameOver = true;

        for (JButton button : buttons) {

            button.setEnabled(false);

        }

        playerService.updateStatistics(currentPlayer, result);

        switch (result) {

            case "WIN":

                lblStatus.setText("You Win!");

                JOptionPane.showMessageDialog(
                        this,
                        "Congratulations! You Win!"
                );

                break;

            case "LOSE":

                lblStatus.setText("Computer Wins!");

                JOptionPane.showMessageDialog(
                        this,
                        "Computer Wins!"
                );

                break;

            case "DRAW":

                lblStatus.setText("Draw!");

                JOptionPane.showMessageDialog(
                        this,
                        "Game Draw!"
                );

                break;

        }

        int option = JOptionPane.showConfirmDialog(
                this,
                "Play Again?",
                "Tic Tac Toe",
                JOptionPane.YES_NO_OPTION
        );
        if (option == JOptionPane.YES_OPTION) {

            resetGame();

        } else {

            MainMenuFrame menu = new MainMenuFrame(currentPlayer);

            menu.setVisible(true);

            dispose();

        }

    }

    private void resetGame() {

        gameLogic.resetBoard();

        gameOver = false;

        for (JButton button : buttons) {

            button.setText("");

            button.setEnabled(true);

        }

        lblStatus.setText("Your Turn (X)");

    }

}