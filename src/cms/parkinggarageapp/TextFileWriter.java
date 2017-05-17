package cms.parkinggarageapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author cscherbert1
 */
public class TextFileWriter {
    
    public final void writeTextFile(double hoursParked, double feesCharged)throws IOException{
        boolean append = false;   // you can change this

	  // This references the file in the working directory
	  File data = new File("src" + File.separatorChar + "garageTotals.txt");
	  		
      // This is where we setup our streams (append = false means overwrite)
      // new FileWriter() creates the file if doesn't exit
	  PrintWriter out = new PrintWriter(
						new BufferedWriter(
						new FileWriter(data, append)));
	  
	  // print statements do actual work of writing data
	  // note that print statements work similar to Sytem.out.println,
	  // where data is converted to strings
//	  out.print(5.25); //print double, output as a String
//	  out.print('c'); //print char, output as a String
//	  out.print(true); //print boolean, output as a String
//	  out.println("Java"); //print String, output as a String
////	  out.println( new Dog() ); //print object, output as a String
//	  out.print("End of file"); //print String, output as a String

        out.println(hoursParked);
        out.print(feesCharged);
          
	  out.close();  // be sure you close your streams when done!!
	
//	  System.out.println("Wrote file to: " + data.getAbsolutePath());
    }
    
}
