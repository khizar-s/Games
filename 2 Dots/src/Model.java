/**
 * @author Khizar Siddiqui
 * Description: Model ADT Class
 */

import java.util.*;

/**
 * @brief An ADT representing the 2Dots Game Model
 */
public class Model {

    protected ArrayList<ArrayList<DotT>> board;
    protected int size;
    protected int red_left;
    protected int green_left;
    protected int blue_left;
    protected int orange_left;
    protected int pink_left;
    protected int turns_left;
    protected String finalmessage;
    
    /**
     * @brief Initializes a 2Dots Model
     * @param b Represents the the 2D-ArrayList representing the grid
     * @param red Represents the red dots left to reach
     * @param green Represents the green dots left to reach
     * @param blue Represents the blue dots left to reach
     * @param orange Represents the orange dots left to reach
     * @param pink Represents the pink dots left to reach
     * @param turns Represents the turns left
     */
    public Model(ArrayList<ArrayList<DotT>> b, int red, int green, int blue, int orange, int pink, int turns) {
        this.board = b;
        this.size = b.size();
        if (red < 0 || green < 0 || blue < 0 || orange < 0 || pink < 0) {
            throw new RuntimeException("Dots to reach can't be less than zero!");
        } else {
            this.red_left = red;
            this.green_left = green;
            this.blue_left = blue;
            this.orange_left = orange;
            this.pink_left = pink;
        }
        if (turns <= 0) {
            throw new RuntimeException("Turns have to be more than zero!");
        } else {
            this.turns_left = turns;
        }
    }

    /**
     * @brief Gets the grid of the game
     * @return A 2D-ArrayList representing the game's grid
     */
    public ArrayList<ArrayList<DotT>> get_board() {
        return this.board;
    }

    /**
     * @brief Gets the red dots
     * @return An integer representing the red dots left to reach
     */
    public int get_red() {
        return this.red_left;
    }
    
    /**
     * @brief Gets the green dots
     * @return An integer representing the green dots left to reach
     */
    public int get_green() {
        return this.green_left;
    }

    /**
     * @brief Gets the blue dots
     * @return An integer representing the blue dots left to reach
     */
    public int get_blue() {
        return this.blue_left;
    }

    /**
     * @brief Gets the orange dots
     * @return An integer representing the orange dots left to reach
     */
    public int get_orange() {
        return this.orange_left;
    }
    
    /**
     * @brief Gets the pink dots
     * @return An integer representing the pink dots left to reach
     */
    public int get_pink() {
    	return this.pink_left;
    }

    /**
     * @brief Gets the turns
     * @return An integer representing the turns left
     */
    public int get_turns() {
        return this.turns_left;
    }
    
    /**
     * @brief Gets the size
     * @return An integer representing the the size of the board
     */
    public int get_size() {
    	return this.size;
    }
    
    /**
     * @brief Gets the message when game is over
     * @return A String representing the message to display when the game is over
     */
    public String get_finalmessage() {
    	return finalmessage;
    }

    /**
     * @brief Gets the dot at a position
     * @param row Represents the row of the dot
     * @param col Represents the column of the dot
     * @return A 2D-ArrayList representing the board
     */
    public DotT getDot(int row, int col) {
        return this.board.get(row).get(col);
    }

    /**
     * @brief Performs a move
     * @param s Represents the list of points to run a move on
     * @details Checks for wrong entries of Points and updates board
     */
    public void move(ArrayList<PointT> s) {
        for (PointT p : s) {
            if (!validPoint(p)) {
                throw new RuntimeException("Point out of bounds!");
            }
        }
        if (!isconnected(s)) {
            throw new RuntimeException("Points not connected!");
        } else if (!isvalidmove(s)) {
            throw new RuntimeException("Same colors not selected!");
        } else {
            this.board = updateboard(s);
        }
    }

}