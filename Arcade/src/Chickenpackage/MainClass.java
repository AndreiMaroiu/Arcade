package Chickenpackage;

import java.awt.*;
import java.io.IOException;

public class MainClass 
{
    private static Game game;
    private static Display showGame;

    public static void main(String args[]) throws IOException 
    {

        game = new Game();
        game.setPreferredSize(new Dimension(game.getGameWidth() * game.getGameScale(), game.getGameHeight() * game.getGameScale()));
        game.setMinimumSize(new Dimension(game.getGameWidth() * game.getGameScale(), game.getGameHeight() * game.getGameScale()));
        game.setMaximumSize(new Dimension(game.getGameWidth() * game.getGameScale(), game.getGameHeight() * game.getGameScale()));

        showGame = new Display(game);
    }
}
