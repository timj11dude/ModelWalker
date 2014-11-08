package dissdraft01;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Unit extends GridReference
{
    protected GridReference dest;

    public Unit(int corX, int corY, GridReference dest)
    {
        super(corX, corY);
        this.dest = dest;
    }
    public Unit(GridReference coords, GridReference dest)
    {
        super(coords.getX(), coords.getY());
        this.dest = dest;
    }
    
    public void setCurr(GridReference curr)
    {
        setX(curr.getX());
        setY(curr.getY());
    }
    
    public void setDest(GridReference dest)
    {
        this.dest = dest;
    }

    public Boolean Move()
    {
        //System.out.println("My current coords are:" + this);
        /*
         *Check if Unit is at it's destination
         */
        System.out.println(this.gridCoord());
        if (this.getX() == dest.getX() && this.getY() == dest.getY())
        {
            return false;
        } else
        {        
            /*
            *Check if the distance is shortest in which cell near the Unit
            */
            double pos00 = (Math.sqrt(((Math.pow(((this.getX() - 1) - this.dest.getX()), 2) + Math.pow(((this.getY() - 1) - this.dest.getY()), 2))))) * Math.sqrt(2);
            double pos01 = Math.sqrt(((Math.pow(((this.getX()) - this.dest.getX()), 2) + Math.pow(((this.getY() - 1) - this.dest.getY()), 2))));
            double pos02 = (Math.sqrt(((Math.pow(((this.getX() + 1) - this.dest.getX()), 2) + Math.pow(((this.getY() - 1) - this.dest.getY()), 2))))) * Math.sqrt(2);
            double pos10 = Math.sqrt(((Math.pow(((this.getX() - 1) - this.dest.getX()), 2) + Math.pow(((this.getY()) - this.dest.getY()), 2))));
            double pos12 = Math.sqrt(((Math.pow(((this.getX() + 1) - this.dest.getX()), 2) + Math.pow(((this.getY()) - this.dest.getY()), 2))));
            double pos20 = (Math.sqrt(((Math.pow(((this.getX() - 1) - this.dest.getX()), 2) + Math.pow(((this.getY() + 1) - this.dest.getY()), 2))))) * Math.sqrt(2);
            double pos21 = Math.sqrt(((Math.pow(((this.getX()) - this.dest.getX()), 2) + Math.pow(((this.getY() + 1) - this.dest.getY()), 2))));
            double pos22 = (Math.sqrt(((Math.pow(((this.getX() + 1) - this.dest.getX()), 2) + Math.pow(((this.getY() + 1) - this.dest.getY()), 2))))) * Math.sqrt(2);
            
            if (pos00 <= pos01 && pos00 <= pos02 && pos00 <= pos10 && pos00 <= pos12 && pos00 <= pos20 && pos00 <= pos21 && pos00 <= pos22)
            {
                this.setX(this.getX() - 1);
                this.setY(this.getY() - 1);
                return true;
            }
            else if (pos01 <= pos02 && pos01 <= pos10 && pos01 <= pos12 && pos01 <= pos20 && pos01 <= pos21 && pos01 <= pos22)
            {
                this.setY(this.getY() - 1);
                return true;
            }
            else if (pos02 <= pos10 && pos02 <= pos12 && pos02 <= pos20 && pos02 <= pos21 && pos02 <= pos22)
            {
                this.setX(this.getX() + 1);
                this.setY(this.getY() - 1);
                return true;
            }
            else if (pos10 <= pos12 && pos10 <= pos20 && pos10 <= pos21 && pos10 <= pos22)
            {
                this.setX(this.getX() - 1);
                return true;
            }
            else if (pos12 <= pos20 && pos12 <= pos21 && pos12 <= pos22)
            {
                this.setX(this.getX() + 1);
                return true;
            }
            else if (pos20 <= pos21 && pos20 <= pos22)
            {
                this.setX(this.getX() - 1);
                this.setY(this.getY() + 1);
                return true;
            }
            else if (pos21 <= pos22)
            {
                this.setY(this.getY() + 1);
                return true;
            }
            else
            {
                this.setY(this.getY() + 1);
                this.setX(this.getX() + 1);
                return true;
            }
            
            
            /*
             *Check if the distance along the Y-axis is longer than on the X-axis
             */

            /*if (Math.abs(this.getX() - dest.getX()) < Math.abs(this.getY() - dest.getY()))
            {
                //Move one step in the direction towards the target
                //System.out.println("Y>X" + (this.getY() - dest.getY() - (this.getY() - dest.getY() - 1)));
                this.setY(this.getY() + (this.getY() - dest.getY() - (this.getY() - dest.getY() - 1)));
                return true;
            }
            else
            {
                //System.out.println("X>Y" + (this.getX() - dest.getX() - (this.getX() - dest.getX() - 1)));
                this.setX(this.getX() + ((this.getX() - dest.getX()) - (this.getX() - dest.getX() - 1)));
                return true;
            }
                    */
        }
    }
}
