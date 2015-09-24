import java.util.Scanner;

/**
 * Created by Tom on 24/09/2015.
 */
public class Run {
    private static Scanner input = new Scanner(System.in);
    private Board board;
    private ShiveType currentPlayer;
    private GameState gameState;

    public Run() {
        board = new Board(6,7);

        initGame();

        do {
            doPlayerMove(currentPlayer);
            board.paint();
            currentPlayer = (currentPlayer == ShiveType.YELLOW) ? ShiveType.RED : ShiveType.YELLOW;

        } while (gameState == GameState.PLAYING);
        if(gameState == GameState.YELLOW_WON) {
            System.out.println("Yellow WINS!");
        }else
        if(gameState == GameState.RED_WON){
            System.out.println("Red WINS!");
        }else
        {
            System.out.println("It's a draw!");
        }
    }

    public void initGame(){
        board.init();
        currentPlayer = ShiveType.YELLOW;
        gameState = GameState.PLAYING;
    }

    public void doPlayerMove(ShiveType currentPlayer) {
        boolean validInput = false;

        do {
            if (currentPlayer == ShiveType.YELLOW) {
                System.out.print("Yellow player, enter your move (column 1-7): ");
            } else {
                System.out.print("Red player, enter your move (column 1-7): ");
            }
            int selectedCol = input.nextInt()-1;
            if (selectedCol >= 0 && selectedCol < board.getColumns()){
                for (int row = board.getRows()-1; row >=0; row--){
                    if (board.getShiveType(row, selectedCol) == ShiveType.Empty){
                        board.setShiveType(row, selectedCol, currentPlayer);
                        validInput = true;
                        if(board.check4Win(board.cells[row][selectedCol])){
                            if(currentPlayer == ShiveType.RED)gameState = GameState.RED_WON;
                            else if (currentPlayer == ShiveType.YELLOW) gameState = GameState.YELLOW_WON;
                        }
                        break;
                    }
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
