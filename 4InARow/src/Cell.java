/**
 * Created by Tom on 24/09/2015.
 */
public class Cell {
    private int row;
    private int col;
    private TokenType tokenType;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        clear();
    }

    public void clear() {
        tokenType = TokenType.Empty;
    }

    public void paint() {
        System.out.print(tokenType.getColour());
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
