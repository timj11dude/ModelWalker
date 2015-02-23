/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dissdraft01;

/**
 *
 * @author tim
 */
public interface UnitInterface {
    // constant declarations, if any

   // method signatures
   
   void setCurr(GridReference curr);
   
   void setDest(GridReference dest);
   
   Boolean move();

    /**
     * Provides a means of comparing the input coordinates,
     * and checking if they are equal to the coordinates of the object.
     * @param coords GridReference
     * @return Boolean
     */
    Boolean equals(GridReference coords);

    /**
     * Provides a means of comparing the input coordinates,
     * and checking if they are equal to the coordinates of the object.
     * @param x Integer
     * @param y Integer
     * @return Boolean
     */
    Boolean equal(int x, int y);

    /**
     * Returns the X coordinate of the object
     * @return Integer
     */
    int getX();

    /**
     * Returns the Y coordinate of the object
     * @return Integer
     */
    int getY();

    /**
     * Generates a string of the current coordinates of the object
     * @return String
     */
    String gridCoord();
    
    int getAge();
    double getDistRemaining();
}
