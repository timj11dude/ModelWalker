package dissdraft01.walkers;

import dissdraft01.Grid;
import dissdraft01.GridReference;
import dissdraft01.UnitInterface;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerWeighted01 extends Walker implements UnitInterface
{
    private double larLength = 0;
    private double smalLength = Double.MAX_VALUE;
    private double larGrad = 0; 
    private double smalGrad = Double.MAX_VALUE;
    private double larGrass = 0;
    private double smalGrass = Double.MAX_VALUE;

    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     */
    public WalkerWeighted01(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY, dest, grid);
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     */
    public WalkerWeighted01(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords, dest, grid);
    }
    
    public Boolean move()
    {
        age++;
        //System.out.println("My current coords are:" + this.gridCoord());
        /*
         *Check if Unit is at it's destination
         */
        //System.out.println(this.gridCoord());
        if (this.getX() == dest.getX() && this.getY() == dest.getY())
        {
            return false;
        } else
        {        
            double shortest = Double.MAX_VALUE;
            updateScale();
            GridReference pos = new GridReference(0,0);
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    if (this.getY() + y == start.getY() && this.getX() + x == start.getX() || (y==0 && x==0)) { continue; }
                    double testLength = checkScale(0 ,heuristicDist((this.getX() + x), (this.getY() + y)));
                    double testGrad = checkScale(1 ,heuristicGrad((this.getX() + x), (this.getY() + y)));
                    double testGras = checkScale(2 ,heuristicGrass((this.getX() + x), (this.getY() + y)));
                    double testWeight = testLength * 0.0001 + testGrad * 0.9 + testGras * 0.00005;
                    
                    if (this.getY() + y == dest.getY() && this.getX() + x == dest.getX() ) {testWeight = 0.0; }
                    System.out.println("X:"+x+"Y:"+y+"| Dist:"+testLength+"| Grad:"+testGrad+"| Grass:"+testGras+"| Weighted:"+testWeight);
                    if (testWeight < shortest) {
                        shortest = testWeight;
                        try
                        {
                        pos = new GridReference((this.getX() + x), (this.getY() + y));
                        }
                        catch (NullPointerException e)
                        {
                            
                        }
                        
                    }
                }
            }
            //System.out.println("NewPosition:"+pos.gridCoord());
            this.setX(pos.getX());
            this.setY(pos.getY());
            return true;
        }
    }
    
    public double weighted(int x, int y)
    {
        //Weighting based on the proximity of the destination since the start.
        //Gets smaller the closer to the destination.
        double weightD = distance(x, y, dest.getX(), dest.getY()) / distance(start, dest);
        double weightT = Math.max((100 - Math.pow((age / 20), 2))/100, 0);
        //Decrease the weight based on the age of the walker.
        double weight = Math.min(weightD, weightT);
        weight = 0;
        //System.out.println("X:"+ x + "Y:" + y + "Weight:" + weight);
        double distFromCur2Dest = distance(dest.getX(), dest.getY());
        double distFromTar2Dest = distance(x, y, dest.getX(), dest.getY());
        double dist2Tar = distance(x, y);
        dist2Tar = 0;
        
        return (distFromCur2Dest - distFromTar2Dest - dist2Tar) + weight * heuristicGrass(x, y);
   
    }
    private double heuristicGrad(int x, int y)
    {
        double curGrad = Math.abs(dest.getY() - y) / Math.abs(dest.getX() - x);
        //System.out.println("CurGrad:"+curGrad+"| staGrad:"+this.grad);
        if (Double.isInfinite(Math.abs(curGrad - this.grad)))
        { return 0;
        }
        return Math.abs(curGrad - this.grad);
    }
    private double heuristicDist(int x, int y)
    {
        return Math.sqrt(Math.pow((dest.getX() - (double)x), 2) + Math.pow((dest.getY() - (double)y), 2));
    }
    private double heuristicGrass(int x, int y)
    {
        if (this.equal(x, y)) { return Double.MAX_VALUE; }
        try
        {
            return grid.getGrass(x, y).getCurHeight();
        }
        catch (NullPointerException e)
        {
            return Double.MAX_VALUE;
        }
    }
    private void updateScale()
    {
        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                if (smalLength > heuristicDist((this.getX() + x), (this.getY() + y)))
                {
                    smalLength = heuristicDist((this.getX() + x), (this.getY() + y));
                }
                if (larLength < heuristicDist((this.getX() + x), (this.getY() + y)))
                {
                    larLength = heuristicDist((this.getX() + x), (this.getY() + y));
                }
                if (smalGrad > heuristicGrad((this.getX() + x), (this.getY() + y)))
                {
                    smalGrad = heuristicGrad((this.getX() + x), (this.getY() + y));
                }
                if (larGrad < heuristicGrad((this.getX() + x), (this.getY() + y)))
                {
                    larGrad = Double.MAX_VALUE;
                }
                if (smalGrass > heuristicGrass((this.getX() + x), (this.getY() + y)))
                {
                    smalGrass = heuristicGrass((this.getX() + x), (this.getY() + y));
                }
                try
                {
                larGrass = grid.getGrass(x, y).getMaxHeight();
                }
                catch (NullPointerException e)
                {

                }         
            }
        }
    }
    /**
     * @return Scale value out of the degree between best and worst option of it's type.
     */
    private double checkScale(int type, double value)
    {
        switch (type){
            case 0: return ((value - smalLength) / (larLength - smalLength)) * 100;
            case 1: return ((value - smalGrad) / (larGrad - smalGrad)) * 100;
            case 2: return ((value - smalGrass) / (larGrass - smalGrass)) * 100;
        }
        return 100;
    }
}
