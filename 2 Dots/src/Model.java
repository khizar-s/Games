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

    /**
     * @brief Updates board
     * @param s Represents the list of points to run a move on
     * @details Moves dots above selected points down and generates new dots in top row
     */
    private ArrayList<ArrayList<DotT>> updateboard(ArrayList<PointT> s) {
        ArrayList<ArrayList<DotT>> updated = this.board;
    	for (PointT p : s) {
            updated.get(p.row()).set(p.col(), DotT.EMPTY);
        }
    	for (PointT p : s) {
    		while (updated.get(p.row()).get(p.col()) == DotT.EMPTY) {
	            for (int r = p.row(); r >= 0; r--) {
	            	if (r == 0) {
	        			updated.get(r).set(p.col(), randomColor());
	            	} else {
	            		updated.get(r).set(p.col(), updated.get(r-1).get(p.col()));
	            	}
	            }
    		}
        }
        return updated;
    }

    /**
     * @brief Decrements turns
     */
    public void decrement_turn() {
        turns_left--;
    }

    /**
     * @brief Updates the counters
     * @param s Represents the list of points to run a move on
     * @details Doesn't let counters go below 0
     */
    public void updatecount(ArrayList<PointT> s) {
        for (PointT p : s) {
            DotT c = getDot(p.row(), p.col());
            if (c == DotT.RED) {
            	if (red_left > 0) {
                    red_left--;
                }                    
            } else if (c == DotT.GREEN) {
            	if (green_left > 0) {
                    green_left--;
                }
            } else if (c == DotT.BLUE) {
            	if (blue_left > 0) {
                    blue_left--;
                }
            } else if (c == DotT.ORANGE) {
            	if (orange_left > 0) {
                    orange_left--;
                }
            } else {
            	if (pink_left > 0) {
                    pink_left--;
                }
            }
        }
    }

    /**
     * @brief Checks for same color of dots
     * @param s Represents the list of points to run a move on
     * @return True/False depending on whether all the points have the same color
     */
    private boolean isvalidmove(ArrayList<PointT> s) {
        for (PointT p : s) {
            if (this.board.get(p.row()).get(p.col()) != this.board.get(s.get(0).row()).get(s.get(0).col())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @brief Checks for point validity
     * @param p Represents the point to check for
     * @return True/False depending on whether the point has valid rows and columns
     */
    private boolean validPoint(PointT p) {
        return (isvalidRow(p.row()) && isvalidCol(p.col()));
    }

    /**
     * @brief Checks for row validity
     * @param i Represents the row to check for 
     * @return True/False depending on whether the row is within range
     */
    private boolean isvalidRow(int i) {
        return (i >= 0 && i < size);
    }

    /**
     * @brief Checks for column validity
     * @param i Represents the column to check for 
     * @return True/False depending on whether the column is within range
     */
    private boolean isvalidCol(int i) {
        return (i >= 0 && i < size);
    }

    /**
     * @brief Checks whether points are connected
     * @param s Represents the list of points to run a move on
     * @return True/False depending on whether points are connected
     * @details Checks to see if next point is connected to current point
     */
    private boolean isconnected(ArrayList<PointT> s) {
    	boolean ret = false;
        for (int i = 0; i < s.size()-1; i++) {
            if (s.get(i).row() == s.get(i+1).row()) {
                if (Math.abs(s.get(i).col() - s.get(i+1).col()) == 1) {
                    ret = true;
                }
            } else if (s.get(i).col() == s.get(i+1).col()) {
                if (Math.abs(s.get(i).row() - s.get(i+1).row()) == 1) {
                    ret = true;
                }
            } else {
            	ret = false;
            	break;
            }
        }
        return ret;
    }

    /**
     * @brief Checks to see if game is over
     * @return True/False depending on whether the game is over
     * @details Game is over if all counters are zero when turns are not less than zero, or 
     * when turns reach zero but there still exist counters to reach
     */
    public Boolean isgameover() {
    	boolean ret;
        if (get_blue() == 0 &&
        		get_green() == 0 &&
                get_red() == 0 &&
                get_orange() == 0 &&
                get_pink() == 0 &&
                get_turns() >= 0) {
        	ret = true;
        	finalmessage = "You Won!";
        } else if ((get_blue() > 0 ||
        		get_green() > 0 ||
                get_red() > 0 ||
                get_orange() > 0 ||
                get_pink() > 0) &&
                get_turns() == 0) {
        	ret = true;
        	finalmessage = "You Lost!";
        } else {
        	ret = false;
        }
        return ret;
    }
    
    /**
     * @brief Generates a random color
     * @return A random DotT color
     */
    public static DotT randomColor() {
        double j = Math.random();
        if (j < 0.2) {
            return DotT.RED;
        } else if (j < 0.4) {
            return DotT.BLUE;
        } else if (j < 0.6) {
            return DotT.GREEN;
        } else if (j < 0.8) {
            return DotT.ORANGE;
        } else {
            return DotT.PINK;
        }
    }
}