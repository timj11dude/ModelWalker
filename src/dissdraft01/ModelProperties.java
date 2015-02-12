
package dissdraft01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Timothy Jacobson
 * @author eeue74
 */
public class ModelProperties {

    private Properties prop = new Properties();
    private OutputStream output = null;
    private String propFileName = "config.properties";
    
    public ModelProperties() throws IOException{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        //System.out.println(inputStream.toString());
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        System.out.println(prop.getProperty("grid.size.height"));
        inputStream.close();
        System.out.println(prop.getProperty("grid.size.height"));
    }
    /*
    public void saveDefaultProperties() {
        try {
 
		output = new FileOutputStream("config.properties");
 
		// set the properties value
		prop.setProperty("grid.size.height", "100");
		prop.setProperty("grid.size.width", "100");
 
		// save properties to project root folder
		prop.store(output, null);
 
	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
	}
    } */
    
    public Properties getProperties() {
        System.out.println(prop.getProperty("grid.size.height"));
        return prop;
    }
}