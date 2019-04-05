package game.IO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Demonstrates how high-score data can be written to a text file.
 */
public class PlayerStatsWriter {

    private String fileName;

    public PlayerStatsWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeHighScore(int level, int score, int lives, int rockCount , float x, float y) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
                writer = new FileWriter(fileName);
                writer.write(level + "," + score + ","+ lives + ","+ rockCount + "," +x + "," + y + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
      /*  HighScoreWriter hsWriter = new HighScoreWriter("/data/file.txt");
        for (int i = 0; i < args.length; i += 2) {
            String name = args[i];
            int score = Integer.parseInt(args[i + 1]);
            hsWriter.writeHighScore(name, score);
        }*/
    }
}
