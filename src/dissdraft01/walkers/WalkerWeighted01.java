package dissdraft01.walkers;

import dissdraft01.Grid;
import dissdraft01.GridReference;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerWeighted01 extends Walker
{
    private double larLength;
    private double smalLength;
    private double larGrad; 
    private double smalGrad;
    private double larGrass;
    private double smalGrass;

    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     * @param grid
     */
    public WalkerWeighted01(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY, dest, grid);
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     * @param grid
     */
    public WalkerWeighted01(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords, dest, grid);
    }
    
    GridReference pos;
    boolean posChange;
    
    void preMove() {
        larLength = 0;
        smalLength = Double.MAX_VALUE;
        larGrad = 0; 
        smalGrad = Double.MAX_VALUE;
        larGrass = 0;
        smalGrass = Double.MAX_VALUE;
        
        weight = Double.MAX_VALUE;
        updateScale();
        pos = new GridReference(0,0);
        posChange = false;
    }
    
    void stepMove(int nx, int ny) {
        //Gather weightings for each variable
        double testLength = checkScale(0 ,heuristicDist((nx), (ny)));
        double testGrad = checkScale(1 ,heuristicGrad((nx), (ny)));
        double testGras = checkScale(2 ,heuristicGrass((nx), (ny)));
        double testWeight = testLength * 0.1 + testGrad * 0.1 + testGras * 0.8;
        
        if (testWeight < weight) {
            weight = testWeight;
            try
            {
            pos = new GridReference(nx, ny);
            posChange = true;
            }
            catch (NullPointerException e)
            {
            }
        }
    }
    
    boolean postMove() {
        if (posChange){
            this.setX(pos.getX());
            this.setY(pos.getY());
            return true;
        }
        else
        {
            return false;
        }
    }

    private double heuristicGrad(int x, int y)
    {
        double c = 0;
        try {
            c = Math.abs(dest.getY() - y) / Math.abs(dest.getX() - x);
        }
        catch (ArithmeticException e) {
            System.err.print(e);
        }
        //System.out.println("CurGrad:"+curGrad+"| staGrad:"+this.grad);
        if (Double.isInfinite(Math.abs(c - this.grad)))
        { return 0;
        }
        return Math.abs(c - this.grad);
    }
    
    private double heuristicDist(int x, int y)
    {
        return Math.sqrt(Math.pow((dest.getX() - (double)x), 2) + Math.pow((dest.getY() - (double)y), 2));
    }
    private double heuristicGrass(int x, int y)
    {
        try
        {
            return grid.getGrass(x, y).getCurHeight();
        }
        catch (NullPointerException e)
        {
            System.err.print(e);
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
        double out = 0;
        switch (type){
            case 0: out = ((value - smalLength) / (larLength - smalLength)) * 100; break;
            case 1: out = ((value - smalGrad) / (larGrad - smalGrad)) * 100; break;
            case 2: out = ((value - smalGrass) / (larGrass - smalGrass)) * 100; break;
        }
        Double out2 = out;
        if (!out2.isInfinite() && !out2.isNaN()) {
            return out;
        }
        else {
            return 0;
        }
    }
}