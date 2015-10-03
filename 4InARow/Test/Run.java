import java.util.Scanner;

/**
 * Created by Tom on 24/09/2015.
 */
public class Run {
    private static Scanner input = new Scanner(System.in);
    private Board board;
    private TokenType currentPlayer;
    private GameState gameState;

    public Run() {
        board = new Board(6, 6);

        initGame();

        do {
            doPlayerMove(currentPlayer);
            board.paint();
            currentPlayer = (currentPlayer == TokenType.BLACK) ? TokenType.WHITE : TokenType.BLACK;

        } while (gameState == GameState.PLAYING);
        if (gameState == GameState.BLACK_WON) {
            System.out.println("Black WINS!");
        } else if (gameState == GameState.WHITE_WON) {
            System.out.println("White WINS!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public void initGame() {
        board.init();
        currentPlayer = TokenType.BLACK;
        gameState = GameState.PLAYING;
    }

    public void doPlayerMove(TokenType currentPlayer) {
        boolean validInput = false;

        do {
            if (currentPlayer == TokenType.BLACK) {
                System.out.print("Black player, enter your move (column 1-6) (row 1-6) (vb 3,5): ");
            } else {
                System.out.print("White player, enter your move (column 1-6) (row 1-6) (vb 3,5): ");
            }
            String in = input.nextLine();
            int selectedCol = Integer.parseInt(in.split(",")[0]) - 1;
            int selectedRow = Integer.parseInt(in.split(",")[1]) - 1;
            if (selectedCol >= 0 && selectedCol < board.getColumns() && selectedRow >= 0 && selectedRow < board.getColumns()) {

                if (board.getShiveType(selectedRow, selectedCol) == TokenType.Empty) {
                    board.setShiveType(selectedRow, selectedCol, currentPlayer);
                    validInput = true;
                    if (board.check4Win(board.cells[selectedRow][selectedCol])) {
                        if (currentPlayer == TokenType.WHITE) gameState = GameState.WHITE_WON;
                        else if (currentPlayer == TokenType.BLACK) gameState = GameState.BLACK_WON;
                    }
                    break;
                }


            } else {
                System.out.println("This move is invalid");
            }
        } while (!validInput);
    }

    public static void main(String[] args) {

        new Run();
    }
}
