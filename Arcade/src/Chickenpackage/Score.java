package Chickenpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import java.io.IOException;

public class Score {

    private int score;
    private int highScore;
    private int level;
    private int highLevel;
    private String scoreFile = "score.txt";

    Score() {
        score = 0;
        highScore = 0;
        level = 1;
        highLevel = 0;
    }

    public String getScore(){
        return String.valueOf(score);
    }

    public String getLevel(){
        return String.valueOf(level);
    }

    public void alterScore(int points) {
        score += points;
    }

    public void alterLevel() {
        level++;
    }

    public void checkScore() {
        if (score == 0) {
            readFromFile();
        } else {
            if (highScore < score) {
                writeToFile();
            }
        }
    }

    public void readFromFile() {
        try {
            File myObj = new File(scoreFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextInt()) {
                highScore = myReader.nextInt();
                highLevel = myReader.nextInt();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter(scoreFile);
            myWriter.write(String.valueOf(score) + " ");
            myWriter.write(String.valueOf(level));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g, Game game) {
        Font small = new Font("", Font.BOLD, 18);
        String scoreString = "Score: " + String.valueOf(score);
        String highScoreString = "High Score: " + String.valueOf(highScore) ;
        String levelString = "Level: " + String.valueOf(level);
        String highLevelString = "Highest Level: " + String.valueOf(highLevel);
        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(scoreString, 15, 30);
        g.drawString(levelString, 125, 30);
        g.drawString(highScoreString, game.getWidth() / 2 - 50, 30);
        g.drawString(highLevelString, game.getWidth() - 165, 30);
    }
}
