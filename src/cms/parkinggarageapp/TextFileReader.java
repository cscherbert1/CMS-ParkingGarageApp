package cms.parkinggarageapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author cscherbert1
 */
public class TextFileReader {
    
   public final List readTextFile()throws IOException {
        
        File file = new File("src" + File.separatorChar + "garageTotals.txt");
        
        BufferedReader in = null;
        List<String> fileLines = new ArrayList<>();

        in = new BufferedReader(new FileReader(file));
        //read the first line outside the loop
        String line = in.readLine();
        //begin loop, check if the line read is null
        while (line != null) {
            //do something with the line read
//            System.out.println(line);
            fileLines.add(line);
            //read the next line inside the loop
            line = in.readLine(); 
        }
        //close the file
        in.close();
        
        return fileLines;
    }
    
}
