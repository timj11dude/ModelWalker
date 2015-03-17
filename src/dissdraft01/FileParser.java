
package dissdraft01;

import dissdraft01.walkers.WalkerAngle;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Timothy Jacobson
 * @author eeue74
 */
public class FileParser {
    private final DocumentBuilder builder;
    private final XPath path;
    
    
    GridReference[] dests;
    WalkerAngle[] walkers;
    GrassPatch[] patches;
    Grid grid;
    
    
    public FileParser(Grid grid)
            throws ParserConfigurationException
    {
        DocumentBuilderFactory dbfactory
                = DocumentBuilderFactory.newInstance();
        builder = dbfactory.newDocumentBuilder();
        XPathFactory xpfactory = XPathFactory.newInstance();
        path = xpfactory.newXPath();
        this.grid = grid;
    }
    
    public void load(String fileName)
            throws SAXException, IOException, XPathExpressionException
    {
        File f = new File(fileName);
        Document doc = builder.parse(f);
        
        int numDest = Integer.parseInt(path.evaluate("count(/ModelWalker/Destinations/Destination)",doc));
        dests = new GridReference[numDest];
        for (int i = 1; i <= numDest; i++)
        {
            dests[i-1]
                    = new GridReference(Integer.parseInt(
                            path.evaluate(
                            "/ModelWalker/Destinations/Destination[" + i + "]/xCoord", doc)),
                    Integer.parseInt(path.evaluate(
                            "/ModelWalker/Destinations/Destination[" + i + "]/yCoord", doc)));
        }
        int numWalk = Integer.parseInt(path.evaluate("count(/ModelWalker/Walkers/Walker)",doc));
        walkers = new WalkerAngle[numWalk];
        for (int i = 1; i <= numWalk; i++)
        {
            walkers[i-1]
                    = new WalkerAngle(
                    Integer.parseInt(
                    path.evaluate(
                    "/ModelWalker/Walkers/Walker["+i+"/Point/xCoord", doc)),
                    Integer.parseInt(
                    path.evaluate(
                    "/ModelWalker/Walkers/Walker["+i+"/Point/xCoord",doc)),
                    dests[0],
                    grid);
        }
    }
    
}
