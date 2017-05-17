package cms.parkinggarageapp;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 *
 * @author cscherbert1
 */
public class ExternalSpecFactory {
    
    public final static Business getBusinessInstance(){
        Business b = null;

        // First read config setting in properties file
//        File file = new File("/temp/config.properties");
        File file = new File("src"+ File.separatorChar +"config.properties");
        Properties props = new Properties();
        FileInputStream inFile;
        try {
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();
        
            String className = props.getProperty("business");
            Class clazz = Class.forName(className);
//            Constructor constructor = 
//                    clazz
//                    .getConstructor(new Class[]{String.class, Integer.class});
//            String businessName = props.getProperty("name");
//            
//            String businessId = props.getProperty("id");
//            Integer id = Integer.parseInt(businessId);
//            
//            Object[] constructorArgs = new Object[]{
//                businessName, id
//            };
//            b = (Business)constructor.newInstance(constructorArgs);

            b= (Business)clazz.newInstance();
            
            
        } catch (Exception ex) {
            System.out.println("ERROR: you must place a copy of the\n "
                    + "config.properties file in the 'c:/temp' directory of "
                    + "your computer's main hard drive.");
        }
        
        return b;
    }
    
}
