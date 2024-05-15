/**
 * 
 */
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Reporter;

/**
 * @author ankitsharma 13 May 2024
 */
public class PropertyReader {
	
	public static Properties getProperties(String filename) throws IOException {
        Properties configProperties = new Properties();
        InputStream stream = null;
        try {
            File propertiesFile = new File("src/main/resources/" + filename + ".properties");
            if(propertiesFile.exists()){
            	stream = new FileInputStream(propertiesFile);
            	configProperties.load(stream);
            }
        }
        catch(FileNotFoundException e){
            Reporter.log("File Not Found: "+filename);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	if(stream != null) {
        		stream.close();
        	}
        }
        return configProperties;
    }

}
