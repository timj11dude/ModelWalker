package dissdraft01;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import dissdraft01.*;

/**
 * Timothy Jacobson
 * @author eeue74
 */
public class fileCSVOutput {
    private static PrintWriter writer;
    Grid grid;
    DateFormat df;
    Date date;
    int count;
    
    static final String suf = ", ";
    static final String newLine = ";";
    
    public fileCSVOutput(Grid grid) {
        this.grid = grid;
        df = new SimpleDateFormat("yyyyMMddHHmmss");
        date = new Date();
        try {
            writer = new PrintWriter("output/ModelWalkerOutput_"+df.format(date)+".csv","UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(fileCSVOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(fileCSVOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Counter"+suf);
        sb.append("Parameter setting"+suf);
        sb.append("Number of Walkers"+suf);
        sb.append("Average age of Walkers"+suf);
        sb.append("Average distance to target"+suf);
        sb.append(newLine);
        String build = sb.toString();

        writer.println(build);
        writer.flush();
        System.out.println(writer);
    }
    
    public void save() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(count+suf);
        sb.append(Game.weightWalker+suf);
        sb.append(grid.walkers.size()+suf);
        sb.append(getAverageAge()+suf);
        sb.append(getAverageDist());
        sb.append(newLine);
        String build = sb.toString();
        
        count++;
        
        writer.println(build);
        writer.flush();
    }
    
    public int getAverageAge() {
        int totalAge = 0;
        int averageAge;
        for (UnitInterface i : grid.walkers) {
            totalAge += i.getAge();
        }
        try {
            averageAge = totalAge / grid.walkers.size();
        }
        catch (ArithmeticException e) {
            averageAge = 0;
        }
        return averageAge;
    }
    
    public int getAverageDist() {
        int totalDist = 0;
        int averageDist;
        for (UnitInterface i : grid.walkers) {
            totalDist += i.getDistRemaining();
        }
        try {
            averageDist = totalDist / grid.walkers.size();
        }
        catch (ArithmeticException e) {
            averageDist = 0;
        }
        return averageDist;
    }
    
    public void close() {
        writer.close();
    }
}
