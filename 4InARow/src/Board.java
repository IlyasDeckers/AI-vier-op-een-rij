import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 24/09/2015.
 */
public class Board {
    private int rows;
    private int columns;
    Cell[][] cells;

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
                System.out.println("-------------");
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

    public ShiveType getShiveType(int row, int col) {
        return cells[row][col].getShiveType();
    }

    public void setShiveType(int row, int col, ShiveType shiveType) {
        cells[row][col].setShiveType(shiveType);
    }

    public boolean check4Win(Cell cell)
    {
        int col = cell.getCol();
        int row = cell.getRow();
        return (checkVertically(row, col, 4) || checkHorizontally(row, col, 4)
                || checkDiagonally1(row, col, 4)
                || checkDiagonally2(row, col, 4));

    }

    public String getPlayerOfTokenAt(int row, int col)
    {
        if(row < rows && col < columns) {
            Cell cell = cells[row][col];
            return cell.getShiveType().getColour();
        }
        return ShiveType.Empty.getColour();
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
                        && getPlayerOfTokenAt(row + i - j,col + i - j).equals(getPlayerOfTokenAt(row,col)) && !getPlayerOfTokenAt(row,col).equals(ShiveType.Empty.getColour()))
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
                        && getPlayerOfTokenAt(row + i - j,col - i + j).equals(getPlayerOfTokenAt(row,col)) && !getPlayerOfTokenAt(row,col).equals(ShiveType.Empty.getColour()))
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
        while (col - i >= 0 && getPlayerOfTokenAt(row,col - i).equals(getPlayerOfTokenAt(row, col)) && !getPlayerOfTokenAt(row,col).equals(ShiveType.Empty.getColour()))
        {
            adjacentSameTokens++;
            i++;
        }
        i = 1;
        while (col + i < columns && getPlayerOfTokenAt(row,col + i).equals(getPlayerOfTokenAt(row, col)) && !getPlayerOfTokenAt(row,col).equals(ShiveType.Empty.getColour()))
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
        while (row + i < rows && getPlayerOfTokenAt(row + i, col).equals(getPlayerOfTokenAt(row, col)) && !getPlayerOfTokenAt(row,col).equals(ShiveType.Empty.getColour()))
        {
            adjacentSameTokens++;
            i++;
        }
        return (adjacentSameTokens >= nrOfTokens);
    }


}
