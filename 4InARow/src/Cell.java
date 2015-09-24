/**
 * Created by Tom on 24/09/2015.
 */
public class Cell {
    private int row;
    private int col;
    private ShiveType shiveType;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        clear();
    }

    public void clear() {
        shiveType = ShiveType.Empty;
    }

    public void paint() {
        System.out.print(shiveType.getColour());
    }

    public ShiveType getShiveType() {
        return shiveType;
    }

    public void setShiveType(ShiveType shiveType) {
        this.shiveType = shiveType;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
