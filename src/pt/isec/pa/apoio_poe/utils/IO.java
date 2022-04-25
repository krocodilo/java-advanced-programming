package pt.isec.pa.apoio_poe.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class IO {

    public static ArrayList<ArrayList<String>> readCSV(String filename) throws Exception {

        ArrayList<ArrayList<String>> content = new ArrayList<>();

        ArrayList<String> lines = readFileLines( filename );

        StringBuilder errors = new StringBuilder();
        int lineNum = 1;

        for(String line : lines){
            ArrayList<String> lineContent = new ArrayList<>( List.of( line.split(",") ) );
            if( lineContent.size() <= 1 )
                errors.append("\n").append( String.format("[%d] - %s", lineNum, line) );
            else
                content.add( lineContent );
            lineNum++;
        }
        if( ! errors.isEmpty() )
            throw new Exception("Found errors in the following lines:" + errors);

        return content;
    }

    private static ArrayList<String> readFileLines(String filename) throws Exception {

        ArrayList<String> lines = new ArrayList<>();

        try ( BufferedReader in = openFile(filename) ) {
            String line;
            while ((line = in.readLine()) != null)
                lines.add(line);
        } catch (FileNotFoundException e) {
            throw new Exception("Unable to open file'" + filename + "'.");
        }
        return lines;
    }

    private static BufferedReader openFile(String filename) throws FileNotFoundException {
        return new BufferedReader( new FileReader( filename ) );
    }
}
