import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private char[] board;
    private Random random;

    public GameLogic() {
        board = new char[9];
        random = new Random();
        resetBoard();
    }

    // Mengosongkan papan permainan
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    // Menempatkan simbol pada papan
    public boolean makeMove(int index, char symbol) {

        if (index < 0 || index >= 9) {
            return false;
        }

        if (board[index] != ' ') {
            return false;
        }

        board[index] = symbol;
        return true;
    }

    // Mengecek apakah simbol tertentu menang
    public boolean checkWinner(char symbol) {

        int[][] patterns = {
                {0,1,2},
                {3,4,5},
                {6,7,8},

                {0,3,6},
                {1,4,7},
                {2,5,8},

                {0,4,8},
                {2,4,6}
        };

        for (int[] pattern : patterns) {

            if (board[pattern[0]] == symbol &&
                    board[pattern[1]] == symbol &&
                    board[pattern[2]] == symbol) {

                return true;
            }

        }

        return false;
    }

    // Mengecek apakah permainan seri
    public boolean isDraw() {

        for (char cell : board) {

            if (cell == ' ') {
                return false;
            }

        }

        return true;
    }

    // Komputer memilih salah satu kotak kosong secara acak
    public int computerMove() {

        ArrayList<Integer> emptyCells = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {

            if (board[i] == ' ') {
                emptyCells.add(i);
            }

        }

        if (emptyCells.isEmpty()) {
            return -1;
        }

        int randomIndex = random.nextInt(emptyCells.size());
        int selectedCell = emptyCells.get(randomIndex);

        board[selectedCell] = 'O';

        return selectedCell;
    }

    // Mengembalikan isi papan
    public char[] getBoard() {
        return board;
    }

    // Mengecek apakah papan sudah penuh
    public boolean isBoardFull() {

        for (char cell : board) {

            if (cell == ' ') {
                return false;
            }

        }

        return true;
    }

    // Mengambil isi satu kotak
    public char getCell(int index) {

        if (index < 0 || index >= 9) {
            return ' ';
        }

        return board[index];
    }

    // Mengubah isi satu kotak
    public void setCell(int index, char symbol) {

        if (index >= 0 && index < 9) {
            board[index] = symbol;
        }

    }

    // Mengembalikan ukuran papan
    public int getBoardSize() {
        return board.length;
    }

    // Mengecek apakah sebuah kotak masih kosong
    public boolean isCellEmpty(int index) {

        if (index < 0 || index >= 9) {
            return false;
        }

        return board[index] == ' ';
    }
}