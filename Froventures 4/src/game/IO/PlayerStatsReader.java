package game.IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class PlayerStatsReader {

    private String fileName;
    private ArrayList<Float> tokenList;


    /**
     * Initialise a new HighScoreReader
     * @param fileName the name of the high-score file
     */
    public PlayerStatsReader(String fileName) {
        this.fileName = fileName;
        tokenList = new ArrayList<>();


    }

    /**
     * Read the high-score data from the high-score file and print it to
     * the terminal window.
     */
    public ArrayList<Float> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader loader = null;

        try {
            fr = new FileReader(fileName);
            loader = new BufferedReader(fr);
            String line = loader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                line = loader.readLine();
                for (String tok: tokens){
                    tokenList.add(Float.parseFloat(tok));
                }
            }
            return tokenList;
        } finally {
            if (loader != null) {
                loader.close();
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
