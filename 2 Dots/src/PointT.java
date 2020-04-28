/**
 * @author Khizar Siddiqui
 * Description: Point ADT Class
 */

/**
 * @brief An ADT representing a Point
 *
 */
public class PointT {

    protected int R;
    protected int C;

    /**
     * @brief Initializes a PointT
     * @param row Represents what row the point is in
     * @param col Represents what column the point is in
     */
    public PointT(int row, int col) {
        this.R = row;
        this.C = col;
    }

    /**
     * @brief Gets the row
     * @return An integer representing the point's row
     */
    public int row() {
        return this.R;
    }

    /**
     * @brief Gets the column
     * @return An integer representing the point's column
     */
    public int col() {
        return this.C;
    }
    
    /**
     * @brief Checks for equality
     * @param object Represents the point to compare with
     * @details Override the equals function to include functionality for my custom class
     */
    @Override
    public boolean equals(Object object){
        boolean ret = false;
        if (object != null && object instanceof PointT) {
            ret = (this.R == ((PointT) object).row() && this.C == ((PointT) object).col());
        }
        return ret;
    }
}