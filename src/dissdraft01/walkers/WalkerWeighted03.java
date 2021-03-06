package dissdraft01.walkers;

import dissdraft01.Game;
import dissdraft01.Grid;
import dissdraft01.GridReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerWeighted03 extends Walker
{
    private double larLength = 0;
    private double smalLength = Double.MAX_VALUE;
    private double larGrad = 0; 
    private double smalGrad = Double.MAX_VALUE;
    private double larGrass = 0;
    private double smalGrass = Double.MAX_VALUE;
    
    private List<GridReference> visited = new ArrayList<>();

    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     * @param grid
     */
    public WalkerWeighted03(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY, dest, grid);
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     * @param grid
     */
    public WalkerWeighted03(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords, dest, grid);
    }
    
    @Override
    public boolean move()
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
            boolean posChange = false;
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    int xTest = this.getX() + x;
                    int yTest = this.getY() + y;
                    if (yTest == start.getY() && xTest == start.getX() || (y==0 && x==0)) { continue; }
                    try {
                        if (visited.contains(new GridReference(xTest, yTest))) { continue; }
                    }
                    catch (NullPointerException e) {
                    }
                    double testLength = checkScale(0, heuristicDist((xTest), (yTest)));
                    //double testGrad = checkScale(1 ,heuristicGrad((xTest), (yTest)));
                    double testGras = checkScale(2, heuristicGrass((xTest), (yTest)));
                    double testWeight = testLength * Game.weightWalker + testGras * (1-Game.weightWalker);
                    
                    if (yTest == dest.getY() && xTest == dest.getX() ) {testWeight = 0.0; }
                    //System.out.println("X:"+x+"Y:"+y+"| Dist:"+testLength+"| Grass:"+testGras+"| Weighted:"+testWeight);
                    if (testWeight < shortest) {
                        shortest = testWeight;
                        try
                        {
                            pos = new GridReference((xTest), (yTest));
                            posChange=true;
                        }
                        catch (NullPointerException e)
                        {
                            
                        }
                        
                    }
                }
            }
            //System.out.println("NewPosition:"+pos.gridCoord());
            if (posChange){
                this.setX(pos.getX());
                this.setY(pos.getY());
                visited.add(pos);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    
    void preMove() {}
    void stepMove(int x, int y) {}
    boolean postMove() {return false;}
    
    private double heuristicGrad(int x, int y)
    {
        double curGrad;
        try {
            curGrad = Math.abs(dest.getY() - y) / Math.abs(dest.getX() - x);
        }
        catch (ArithmeticException e) {
            curGrad = 0;
        }
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
