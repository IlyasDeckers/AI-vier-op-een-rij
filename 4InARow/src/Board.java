/**
 * Created by Tom on 24/09/2015.
 */
public class Board {
    private int rows;
    private int columns;
    private Cell[][] cells;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        cells = new Cell[rows][columns];
        for (int row = 0; row < rows; ++row){
            for (int col = 0; col < columns; ++col) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void init() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < columns; ++ col) {
                cells[row][col].clear();
            }
        }
    }

    public void paint() {
        for (int row = 0; row < rows; ++row){
            for (int col = 0; col < columns; ++col){
                cells[row][col].paint();
                if (col < columns -1) System.out.print("|");
            }
            System.out.println();
            if (row < rows-1){
                System.out.println("-----------");
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public TokenType getShiveType(int row, int col) {
        return cells[row][col].getTokenType();
    }

    public void setShiveType(int row, int col, TokenType tokenType) {
        cells[row][col].setTokenType(tokenType);
    }

    public boolean check4Win(Cell cell)
    {
        int col = cell.getCol();
        int row = cell.getRow();
        return (checkVertically(row, col, 5) || checkHorizontally(row, col, 5)
                || checkDiagonally1(row, col, 5)
                || checkDiagonally2(row, col, 5));

    }
    //boolean direction:
    //if true rotate clockwise
    //if false rotate counterclockwise
    public void turnBoard(int chosenPart, boolean direction) {
        Cell[][] chosenBoard = new Cell[3][3];
        switch (chosenPart) {
            case 1:
                for (int row = 0; row < 3; row ++) {
                    for (int col = 0; col <3;col++){
                        chosenBoard[row][col] = cells[row][col];
                    }
                }
                break;
            case 2:
                for (int row = 0; row < 3; row ++) {
                    for (int col = 3; col < 6;col++){
                        chosenBoard[row][col] = cells[row][col];
                    }
                }
                break;
            case 3:
                for (int row = 3; row < 6; row ++) {
                    for (int col = 0; col <3;col++){
                        chosenBoard[row][col] = cells[row][col];
                    }
                }
                break;
            case 4:
                for (int row = 3; row < 6; row ++) {
                    for (int col = 3; col <6;col++){
                        chosenBoard[row][col] = cells[row][col];
                    }
                }
                break;
        }
        Cell[][] turnedBoard = new Cell[3][3];

        if (direction) {
            for (int row = 0; row < 3; row ++) {
                for (int col = 0; col <3;col++){
                    if (row == 0) {
                        turnedBoard[col][2] = chosenBoard[row][col];
                    }
                    turnedBoard[row][col] = chosenBoard[row][col];
                }
            }
        }
    }

    public String getPlayerOfTokenAt(int row, int col)
    {
        if(row < rows && col < columns) {
            Cell cell = cells[row][col];
            return cell.getTokenType().getColour();
        }
        return TokenType.Empty.getColour();
    }

    //for checking nrOfTokens (win situation: nrOfTokens = 4)
    private boolean checkDiagonally1(int row, int col, int nrOfTokens)
    {
        for (int j = 0; j < nrOfTokens; j++)
        {
            int adjacentSameTokens = 0;
            for (int i = 0; i < nrOfTokens; i++)
            {
                if ((col + i - j) >= 0 && (col + i - j) < columns
                        && (row + i - j) >= 1 && (row + i - j) < rows
                        && getPlayerOfTokenAt(row + i - j,col + i - j).equals(getPlayerOfTokenAt(row,col)) && !getPlayerOfTokenAt(row,col).equals(TokenType.Empty.getColour()))
                {
                    adjacentSameTokens++;
                }
            }
            if (adjacentSameTokens >= nrOfTokens)
                return true;
        }
        return false;
    }

    private boolean checkDiagonally2(int row, int col, int nrOfTokens)
    {
        for (int j = 0; j < nrOfTokens; j++)
        {
            int adjacentSameTokens = 0;
            for (int i = 0; i < nrOfTokens; i++)
            {
                if ((col - i + j) >= 0 && (col - i + j) < columns
                        && (row + i - j) >= 1 && (row + i - j) < rows
                        && getPlayerOfTokenAt(row + i - j,col - i + j).equals(getPlayerOfTokenAt(row,col)) && !getPlayerOfTokenAt(row,col).equals(TokenType.Empty.getColour()))
                {
                    adjacentSameTokens++;
                }
            }
            if (adjacentSameTokens >= nrOfTokens)
                return true;
        }
        return false;
    }

    private boolean checkHorizontally(int row, int col, int nrOfTokens)
    {
        int adjacentSameTokens = 1;
        int i = 1;
        while (col - i >= 0 && getPlayerOfTokenAt(row,col - i).equals(getPlayerOfTokenAt(row, col)) && !getPlayerOfTokenAt(row,col).equals(TokenType.Empty.getColour()))
        {
            adjacentSameTokens++;
            i++;
        }
        i = 1;
        while (col + i < columns && getPlayerOfTokenAt(row,col + i).equals(getPlayerOfTokenAt(row, col)) && !getPlayerOfTokenAt(row,col).equals(TokenType.Empty.getColour()))
        {
            adjacentSameTokens++;
            i++;
        }
        return (adjacentSameTokens >= nrOfTokens);
    }

    private boolean checkVertically(int row, int col, int nrOfTokens)
    {
        int adjacentSameTokens = 1;
        int i = 1;
        while (row + i < rows && getPlayerOfTokenAt(row + i, col).equals(getPlayerOfTokenAt(row, col)) && !getPlayerOfTokenAt(row,col).equals(TokenType.Empty.getColour()))
        {
            adjacentSameTokens++;
            i++;
        }
        return (adjacentSameTokens >= nrOfTokens);
    }


}
