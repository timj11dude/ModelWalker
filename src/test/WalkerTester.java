/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dissdraft01.Walker;
import dissdraft01.Grid;
import dissdraft01.GridReference;
/**
 *
 * @author tim
 */
public class WalkerTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grid grid = new Grid();
        Walker walker = new Walker(new GridReference(1, 1), new GridReference(1, 10), grid);
        System.out.println("Distance to 1, 2: " +  walker.distance(1, 1, 1, 2));
        System.out.println("Distance to 2, 2: " +  walker.distance(1, 1, 2, 2));
        System.out.println("Distance to 0, 2: " +  walker.distance(1, 1, 0, 2));
        
        System.out.println("Distance from 1, 2: " +  walker.distance(1, 2, 1, 10));
        System.out.println("Distance from 2, 2: " +  walker.distance(2, 2, 1, 10));
        System.out.println("Distance from 0, 2: " +  walker.distance(0, 2, 1, 10));
        
        System.out.println("Distance gained from 1, 2: " +  (walker.distance(1, 10) - walker.distance(1, 2, 1, 10)));
        System.out.println("Distance gained from 2, 2: " +  (walker.distance(1, 10) - walker.distance(2, 2, 1, 10)));
        System.out.println("Distance gained from 0, 2: " +  (walker.distance(1, 10) - walker.distance(0, 2, 1, 10)));
        
        System.out.println("Distance weighted from 1, 2: " +  walker.weighted(1, 2));
        System.out.println("Distance weighted from 2, 2: " +  walker.weighted(2, 2));
        System.out.println("Distance weighted from 0, 2: " +  walker.weighted(0, 2));
        
        System.out.println("Angle dif from 1, 2: " + walker.angle(1, 2));
        System.out.println("Angle dif from 2, 2: " + walker.angle(2, 2));
        System.out.println("Angle dif from 0, 2: " + walker.angle(0, 2));
    }
    
}
