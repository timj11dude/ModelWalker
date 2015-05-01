package dissdraft01;

import dissdraft01.walkers.Walker;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        catch (UnsupportedEncodingException | FileNotFoundException ex)
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
    /**
     * Create a new data entry row
     */
    public void save() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(count).append(suf);
        sb.append(Game.weightWalker).append(suf);
        sb.append(grid.walkers.size()).append(suf);
        sb.append(getAverageAge()).append(suf);
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
    
    public int getAverageWeight() {
        int totalWeight = 0;
        int averageWeight;
        for (UnitInterface i : grid.walkers) {
            totalWeight += i.getWeight();
        }
        return 0;
    }
    
    private String[] getWalkerNames() {
        List<UnitInterface> w = getWalkers();
        String[] names = new String[w.size()];
        int c = 0;
        for (UnitInterface i : w) {
            names[c] = i.toString();
            c++;
        }
        return names;
    }
    
    private int[] getWalkerAge() {
        List<UnitInterface> w = getWalkers();
        int[] ages = new int[w.size()];
        int c = 0;
        for (UnitInterface i : w) {
            ages[c] = i.getAge();
            c++;
        }
        return ages;
    }
    
    private int[] getWalkerX() {
        List<UnitInterface> w = getWalkers();
        int[] walkerX = new int[w.size()];
        int c = 0;
        for (UnitInterface i : w) {
            walkerX[c] = i.getX();
            c++;
        }
        return walkerX;
    }
    
    private int[] getWalkerY() {
        List<UnitInterface> w = getWalkers();
        int[] walkerY = new int[w.size()];
        int c = 0;
        for (UnitInterface i : w) {
            walkerY[c] = i.getY();
            c++;
        }
        return walkerY;
    }
    
    private double[] getWalkerWeight() {
        List<UnitInterface> w = getWalkers();
        double[] property = new double[w.size()];
        int c = 0;
        for (UnitInterface i : w) {
            property[c] = i.getWeight();
            c++;
        }
        return property;
    }
    
    private List<UnitInterface> getWalkers() {
        return Game.instance.grid.walkers;
    }
    public void close() {
        writer.close();
    }
}
