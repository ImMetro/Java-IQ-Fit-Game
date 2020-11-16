package comp1110.ass2;
import static comp1110.ass2.Direction.*;
/**
 * This class will represent the different types of pieces in this game
 *
 * This type will encode the colour, direction .... add more if we need more in the future
 * Created by Peter Zhao 23/8/2020
 */
public enum PieceType {
    blue(NORTH, SOUTH, EAST, WEST),
    BLUE(NORTH, SOUTH, EAST, WEST),
    green(NORTH, SOUTH, EAST, WEST),
    GREEN(NORTH, SOUTH, EAST, WEST),
    indigo(NORTH, SOUTH, EAST, WEST),
    INDIGO(NORTH, SOUTH, EAST, WEST),
    lime(NORTH, SOUTH, EAST, WEST),
    LIME(NORTH, SOUTH, EAST, WEST),
    orange(NORTH, SOUTH, EAST, WEST),
    ORANGE(NORTH, SOUTH, EAST, WEST),
    pink(NORTH, SOUTH, EAST, WEST),
    PINK(NORTH, SOUTH, EAST, WEST),
    skyblue(NORTH, SOUTH, EAST, WEST),
    SKYBLUE(NORTH, SOUTH, EAST, WEST),
    red(NORTH, SOUTH, EAST, WEST),
    RED(NORTH, SOUTH, EAST, WEST),
    yellow(NORTH, SOUTH, EAST, WEST),
    YELLOW(NORTH, SOUTH, EAST, WEST),
    navy(NORTH, SOUTH, EAST, WEST),
    NAVY(NORTH, SOUTH, EAST, WEST);

    final private Direction aFacing;
    final private Direction bFacing;
    final private Direction cFacing;
    final private Direction dFacing;

    PieceType(Direction aFacing, Direction bFacing, Direction cFacing, Direction dFacing) {
        this.aFacing = aFacing;
        this.bFacing = bFacing;
        this.dFacing = dFacing;
        this.cFacing = cFacing;
    }
}
