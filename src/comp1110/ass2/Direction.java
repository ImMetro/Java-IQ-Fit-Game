package comp1110.ass2;

/**
 * This enumeration type represents the four cardinal directions
 *
 * Notice that this is an enumeration type, so none of the fields
 * change once the type is created (they are all declared final).
 *
 * Created yy Liuchao Wu 23/8/2020
 */

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    /**
     * Given an upper case character ('N', 'E', 'S', 'W'),
     * return the Direction associated with this character.
     *
     * @param direction a char value representing the `Direction` enum
     * @return the `Direction` associated with the char.
     */
    public static Direction fromChar(char direction) {
        Direction value = Direction.NORTH;
        switch(direction) {
            case 'N':
                value = Direction.NORTH;
                break;
            case 'S':
                value = Direction.SOUTH;
                break;
            case 'E':
                value =  Direction.EAST;
                break;
            case 'W':
                value = Direction.WEST;
                break;
            default:
        }
        return value;
    }

    /**
     * Return the single character associated with a `Direction`, which is
     * the first character of the direction name, as an upper case character
     * ('N', 'E', 'S', 'W')
     *
     * @return The first character of the name of the direction
     */
    public char toChar() {
        char letter = 'N';
        switch (this) {
            case NORTH:
                letter = 'N';
                break;
            case SOUTH:
                letter = 'S';
                break;
            case EAST:
                letter = 'E';
                break;
            case WEST:
                letter = 'W';
                break;
        }
        return letter;
    }
}
