
package dissdraft01;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Timothy Jacobson
 * @author eeue74
 */
public class fileCSVOutput {
    PrintWriter writer;
    Grid grid;
    DateFormat df;
    Date date;
    int count;
    
    public void fileCSVOutput(Grid grid) {
        this.grid = grid;
        df = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        date = new Date();
        try {
            writer = new PrintWriter("ModelWalkerOutput_"+df+".csv","UTF-*");
        }
        catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(fileCSVOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(fileCSVOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            writer = null;
        }
    }
    
    public void save() {
        StringBuilder sb = new StringBuilder();
        String suf = ", ";
        sb.append(count+suf);
        sb.append(grid.walkers.size()+suf);
        sb.append(getAverageAge()+suf);
        writer.println();
    }
    
    public int getAverageAge() {
        int totalAge = 0;
        for (UnitInterface i : grid.walkers) {
            totalAge += i.getAge();
        }
        int averageAge = totalAge / grid.walkers.size();
        return averageAge;
    }
    
    public int getAverageDist() {
        int totalDist = 0;
        for (UnitInterface i : grid.walkers) {
            totalDist += i.getDistRemaining();
        }
        int averageDist = totalDist / grid.walkers.size();
        return averageDist;
    }
    
    public void close() {
        writer.close();
    }
}
