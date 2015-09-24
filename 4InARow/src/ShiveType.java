/**
 * Created by Tom on 24/09/2015.
 */
public enum ShiveType {
    RED("R"), YELLOW("Y"), Empty(" ");

    private String colour;
    ShiveType(String colour) {
        this.colour = colour;
    }

    public String getColour() {

        return colour;
    }
}
