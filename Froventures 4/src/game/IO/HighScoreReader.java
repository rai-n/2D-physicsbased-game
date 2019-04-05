package game.IO;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class HighScoreReader {

    private String fileName;
    int counter = 0;
    HashMap<Integer, String> map;

    /**
     * Initialise a new HighScoreReader
     * @param fileName the name of the high-score file
     */
    public HighScoreReader(String fileName) {
        this.fileName = fileName;
        map =  new HashMap<>();
    }

    /**
     * Read the high-score data from the high-score file and print it to
     * the terminal window.
     */
    public HashMap<Integer,String> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;

        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {

                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                line = reader.readLine();
                //Each of the values are mapped onto a HashMap which is used to assign the variables
                //onto the leaderboard.
                map.put(score, name);
            }
           return map;



        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }



    }
    
    public static void main(String[] args) throws IOException {
     /*   HighScoreReader demo = new HighScoreReader("data/highscore.txt");
        demo.readScores();*/
    }
}
