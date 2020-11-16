package comp1110.ass2;

import java.util.*;

/**
 * This class provides the text interface for the IQ Fit Game
 * <p>
 * The game is based directly on Smart Games' IQ-Fit game
 * (https://www.smartgames.eu/uk/one-player-games/iq-fit)
 */
public class FitGame {

    /**
     * Determine whether a piece placement is well-formed according to the
     * following criteria:
     * - it consists of exactly four characters
     * - the first character is a valid piece descriptor character (b, B, g, G, ... y, Y)
     * - the second character is in the range 0 .. 9 (column)
     * - the third character is in the range 0 .. 4 (row)
     * - the fourth character is in valid orientation N, S, E, W
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        char[] pieceArray = new char[]{'b','B', 'g', 'G', 'i', 'I', 'l', 'L', 'n', 'N', 'o', 'O', 'p', 'P', 'r', 'R', 's', 'S', 'y', 'Y'};
        if (piecePlacement.length() == 4) {
            // Fourth Character
            if (piecePlacement.charAt(3) == 'N' || piecePlacement.charAt(3) == 'S' || piecePlacement.charAt(3) == 'E' || piecePlacement.charAt(3) == 'W') {
                // Second Character, this is because characters are converted into ASCII - Peter Zhao
                if (piecePlacement.charAt(1) >= 48 && piecePlacement.charAt(1) <= 57) {
                    // Third Character, same reasoning as above - Peter Zhao
                    if (piecePlacement.charAt(2) >= 48 && piecePlacement.charAt(2) <= 52) {
                        // First Character
                        for (int i = 0; i <= pieceArray.length-1; i++) {
                            if (piecePlacement.charAt(0) == pieceArray[i]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false; // FIXME Task 2: determine whether a piece placement is well-formed
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10); done. -- Peter Zhao
     * - each piece placement is well-formed done. -- Peter Zhao
     * - no shape appears more than once in the placement
     * - the pieces are ordered correctly within the string
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String placement) {
        // If placement string is empty return False - Peter Zhao
        if (placement.equals("")) {
            return false;
        }
        // If placement string isn't divisible by 4, return False - Peter Zhao
        if (placement.length() % 4 != 0) {
            return false;
        }
        String[] subStringArray = new String[placement.length()/4];
        // This will add all the substrings (Pieces/4) to an array hence the name substringArray - Peter Zhao
        // substring idea : https://howtodoinjava.com/java/string/get-first-4-characters/
        for (int h = 0; h < placement.length()/4; h++) {
            subStringArray[h] = placement.substring(4*h, 4*h + 4);
        }
        // Hashset: https://www.geeksforgeeks.org/hashset-in-java/#:~:text=Last%20Updated%3A%2019-02-,class%20permits%20the%20null%20element.
        // Convert the Array into a hashset
        // I am doing this because a property of Hashsets is that they DO NOT allow duplicates -- Peter Zhao
        // So if an element is duplicated, it will not be stored in the hashset -- Peter Zhao
        Set<String> subStringHash = new HashSet<>();
        for (int p = 0; p < subStringArray.length; p++) {
            subStringHash.add(subStringArray[p]);
        }
        // Check for Duplicate elements
        if (subStringHash.size() != subStringArray.length) {
            return false;
        }
        // Gets all the shapes an puts it into an array -- Peter Zhao
        char[] shapeArray = new char [placement.length()/4];
        for (int q = 0; q < placement.length()/4; q++) {
            shapeArray[q] = placement.charAt(4*q);
        }
        // Check for Duplicate Shapes -- Peter Zhao
        for (int r = 0; r < shapeArray.length; r++) {
            for (int s = r + 1; s < shapeArray.length; s++) {
                if (shapeArray[r] == shapeArray[s]) {
                    return false;
                }
            }
        }
        // Checks if pieces are well formed -- Peter Zhao
        for (int k = 0; k < subStringArray.length; k++) {
            if (!isPiecePlacementWellFormed(subStringArray[k])) {
                return false;
            }
        }
        // Checks for Alphabetical -- Peter Zhao
        char[] upperShape = new char [placement.length()/4];
        for (int s = 0; s < shapeArray.length; s = s + 1) {
            upperShape[s] = Character.toUpperCase(shapeArray[s]);
        }

        for (int t = 0; t < upperShape.length; t = t + 1) {
            for (int u = t + 1; u < upperShape.length; u = u + 1) {
                if (upperShape[t] > upperShape[u]) {
                    return false;
                }
            }
        }
        return true; // FIXME Task 3: determine whether a placement is well-formed
    }

    private final static List<Sizes> blankGame = List.of(
            Sizes.B, Sizes.G, Sizes.I, Sizes.L, Sizes.N, Sizes.O, Sizes.P,Sizes.R,
            Sizes.S, Sizes.Y, Sizes.b, Sizes.g, Sizes.i, Sizes.l, Sizes.n,Sizes.o,
            Sizes.p, Sizes.r, Sizes.s, Sizes.y
    );

    private final static char[] s = new char[]{'N', 'S', 'W', 'E'};
    /**
     * Determine whether a placement string is valid.
     *
     * To be valid, the placement string must be:
     * - well-formed, and
     * - each piece placement must be a valid placement according to the
     *   rules of the game:
     *   - pieces must be entirely on the board
     *   - pieces must not overlap each other
     *
     * @param placement A placement string
     * @return True if the placement sequence is valid
     */
    public static boolean isPlacementValid(String placement) {
        int[][] board = new int[5][10];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(board[i], 0); // 0 for free space
        }
        if (isPlacementWellFormed(placement)) {
            System.out.println(placement);
            if (placement.isBlank()) {
                return true;
            } else {
                char[][] pieces = new char[placement.toCharArray().length/4][];
                for (int i = 0, j = 0; i < placement.toCharArray().length; i+=4, j++) {
                    pieces[j] = new char[]{
                            placement.charAt(i), placement.charAt(i+1),
                            placement.charAt(i+2), placement.charAt(i+3)
                    };
                }

                for (char[] piece : pieces) {
                    Sizes sizes = Sizes.valueOf(String.valueOf(piece[0]));
                    int xPos = Integer.parseInt(String.valueOf(piece[1]));
                    int yPos = Integer.parseInt(String.valueOf(piece[2]));
                    char orientation = piece[3];
                    boolean d = false;
                    switch (orientation) { // Check if it's in the board
                        case 'N':
                        case 'S': {
                            if (sizes.w - 1 + xPos < 10 && sizes.h - 1 + yPos < 5)
                                d = true;
                            break;
                        }
                        case 'E':

                        case 'W': {
                            if (sizes.h - 1 + xPos < 10 && sizes.w - 1 + yPos < 5)
                                d = true;
                            break;
                        }
                    }

                    if (!d) // if out of the board
                        return false;

                    switch (orientation) {
                        case 'N': {
                            for (int j = 0; j < sizes.w; j++) {
                                if (board[yPos][xPos + j] == 0) {
                                    board[yPos][xPos + j] = 1;
                                } else return false;
                            }
                            if (board[yPos + 1][xPos + sizes.at[0] - 1] == 0) {
                                board[yPos + 1][xPos + sizes.at[0] - 1] = 1;
                            } else return false;

                            if (sizes.at.length == 2) {
                                if (board[yPos + 1][xPos + sizes.at[1] - 1] == 0) {
                                    board[yPos + 1][xPos + sizes.at[1] - 1] = 1;
                                } else return false;
                            }

                            break;
                        }
                        case 'S': {
                            for (int j = 0; j < sizes.w; j++) {
                                if (board[yPos + 1][xPos + j] == 0) {
                                    board[yPos + 1][xPos + j] = 1;
                                } else return false;
                            }
                            if (board[yPos][xPos + sizes.w - sizes.at[0]] == 0) {
                                board[yPos][xPos + sizes.w - sizes.at[0]] = 1;
                            } else return false;

                            if (sizes.at.length == 2) {
                                if (board[yPos][xPos + sizes.w - sizes.at[1]] == 0) {
                                    board[yPos][xPos + sizes.w - sizes.at[1]] = 1;
                                } else return false;
                            }

                            break;
                        }
                        case 'E': {
                            for (int j = 0; j < sizes.w; j++) {
                                if (board[yPos + j][xPos + 1] == 0) {
                                    board[yPos + j][xPos + 1] = 1;
                                } else return false;
                            }
                            if (board[yPos + sizes.at[0] - 1][xPos] == 0) {
                                board[yPos + sizes.at[0] - 1][xPos] = 1;
                            } else return false;

                            if (sizes.at.length == 2) {
                                if (board[yPos + sizes.at[1] - 1][xPos] == 0) {
                                    board[yPos + sizes.at[1] - 1][xPos] = 1;
                                } else return false;
                            }

                            break;
                        }
                        case 'W': {
                            for (int j = 0; j < sizes.w; j++) {
                                if (board[yPos + j][xPos] == 0) {
                                    board[yPos + j][xPos] = 1;
                                } else return false;
                            }
                            if (board[yPos + sizes.w - sizes.at[0]][xPos + 1] == 0) {
                                board[yPos + sizes.w - sizes.at[0]][xPos + 1] = 1;
                            } else return false;

                            if (sizes.at.length == 2) {
                                if (board[yPos + sizes.w - sizes.at[1]][xPos + 1] == 0) {
                                    board[yPos + sizes.w - sizes.at[1]][xPos + 1] = 1;
                                } else return false;
                            }

                            break;
                        }
                    }
                    System.out.println();
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 10; k++) {
                            System.out.print(board[j][k] + "\t");
                        }
                        System.out.println();
                    }
                }
            }
            System.out.println("Placement : "+placement+" is valid!");
            return true;
        }
        return false; // FIXME Task 5: determine whether a placement string is valid
    }
    private static int[][] board = new int[5][10];
    /**
     * Given a string describing a placement of pieces, and a location
     * that must be covered by the next move, return a set of all
     * possible next viable piece placements which cover the location.
     *
     * For a piece placement to be viable it must:
     *  - be a well formed piece placement
     *  - be a piece that is not already placed
     *  - not overlap a piece that is already placed
     *  - cover the location
     *
     * @param placement A starting placement string
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of all viable piece placements, or null if there are none.
     */
    static Set<String> getViablePiecePlacements(String placement, int col, int row) {
        Set<String> viable = null;
        Set<String> pieces = new HashSet<>();
        if (placement.isBlank()) { // If blank check for each possible piece variation if fits inside -> (col, row)
            for (Sizes sizes : blankGame)
                for (char orientation : s)
                    if (isPlacementValid(sizes.name()+row+col+orientation) && board[col][row] == 1) {
                        pieces.add(sizes.name()+row+col+orientation);
                    }
            return pieces;
        } else if (!isPlacementValid(placement))
            return null;
        else for (int i = 0; i+4 < placement.length(); i+=4) {
                String piece = placement.substring(i, i+3);
                pieces.add(piece);
            }

        for (Sizes sizes : blankGame) { // check for each possible piece variation if fits inside -> (col, row)
            for (char orientation : s) {
                String piece = sizes.name()+row+col+orientation;
                if (!pieces.contains(piece)) {
                    if (isPlacementValid(placement+piece) && board[col][row] == 1) {
                        if (viable == null) viable = new HashSet<>();
                        viable.add(piece);
                    }
                }
                if (row < 5 && row > 0 && col < 9 && col > 0) {
                    for (int i = -1; i < 2; i++) {
                        piece = sizes.name()+(row-1)+(col+i)+orientation;
                        if (!pieces.contains(piece)) {
                            if (isPlacementValid(placement+piece) && board[col][row] == 1) {
                                if (viable == null) viable = new HashSet<>();
                                viable.add(piece);
                            }
                        }

                        piece = sizes.name()+(row+1)+(col+i)+orientation;
                        if (!pieces.contains(piece)) {
                            if (isPlacementValid(placement+piece) && board[col][row] == 1) {
                                if (viable == null) viable = new HashSet<>();
                                viable.add(piece);
                            }
                        }

                        piece = sizes.name()+(row+i)+(col+1)+orientation;
                        if (!pieces.contains(piece)) {
                            if (isPlacementValid(placement+piece) && board[col][row] == 1) {
                                if (viable == null) viable = new HashSet<>();
                                viable.add(piece);
                            }
                        }

                        piece = sizes.name()+(row+i)+(col-1)+orientation;
                        if (!pieces.contains(piece)) {
                            if (isPlacementValid(placement+piece) && board[col][row] == 1) {
                                if (viable == null) viable = new HashSet<>();
                                viable.add(piece);
                            }
                        }
                        piece = sizes.name()+(row+i+1)+(col-1)+orientation;
                        if (!pieces.contains(piece)) {
                            if (isPlacementValid(placement+piece) && board[col][row] == 1) {
                                if (viable == null) viable = new HashSet<>();
                                viable.add(piece);
                            }
                        }
                        piece = sizes.name()+(row-1)+(col+i+1)+orientation;
                        if (!pieces.contains(piece)) {
                            if (isPlacementValid(placement+piece) && board[col][row] == 1) {
                                if (viable == null) viable = new HashSet<>();
                                viable.add(piece);
                            }
                        }
                    }

                }
            }
        }

        System.out.println("Viable for : "+placement+" are "+viable);
        return viable;
        // FIXME Task 6: determine the set of all viable piece placements given existing placements
    }

    /**
     * Return the solution to a particular challenge.
     **
     * @param challenge A challenge string.
     * @return A placement string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        String result = "";
        List<String> pieces = new ArrayList<>();
        for (int i = 0; i < challenge.length() / 4; i++) {
            pieces.add(String.valueOf(challenge.charAt(i*4)+challenge.charAt(i*4+1)+challenge.charAt(i*4+2)+challenge.charAt(i*4+3)));
        }

        Map<String, Set<Character>> map = new HashMap<>();
        List<String> available = new ArrayList<>();
        for (Sizes sizes : blankGame) {
            for (char c : s) {
                available.add(sizes.name() + c);
            }
        }
        if (isPlacementValid(challenge)) {
            int counterColumn = 0;
            int x=0, y=0;
            int counterRow = 0;
            boolean isUpper = false;
            char candidate = ' ';
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board[i][j] == 0) {
                        if (x == 0)
                            x = j;
                        if (y == 0)
                            y = i;
                        counterRow++;
                    }
                }
                if (counterRow==4) {
                    if (y+1<5) {
                        int counter = 0;
                        for (int j = x; j < counterRow+x; j++) {
                            if(board[y+1][j] == 0)
                                counter++;
                        }
                        if (counter >= 2) {
                            isUpper = true;
                        }


                    } else if (y-1>0) {

                    }
                } else if (counterRow==3) {

                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {

                }
            }

        }

        return result;  // FIXME Task 9: determine the solution to the game, given a particular challenge
    }
}
