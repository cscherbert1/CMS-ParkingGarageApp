package cms.parkinggarageapp;

import java.io.*;
import java.util.*;

/**
 *
 * @author cscherbert1
 */
public class FileService {
    private TextFileReader reader;
    private TextFileWriter writer;
    
    public FileService(){
        reader = new TextFileReader();
        writer = new TextFileWriter();
    }

    public final List readTextFile()throws IOException {
        List fileLines = reader.readTextFile();
        return fileLines;
    }
    
    public final void writeTextFile(double hoursParked, double feesCharged)throws IOException{
        writer.writeTextFile( hoursParked, feesCharged);
    }

}
