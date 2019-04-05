package game.View;

import city.cs.engine.UserView;
import city.cs.engine.World;
import game.GameController.Game;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * A UserView inherited class to apply parallax scrolling feature within the game.
 * <p>
 *     It is also used to paint special GUI components and text on the foreground of the game.
 *     Different pictures may be loaded in different levels.
 * </p>
 */
public class MyView extends UserView {
    /**
     *Object Game is used to store the current instance of game which is a container for all currently running
     * game objects.
     **/
    private Game game;
    /**
     * Represents a x displacement of +25 which is added to the x position by a multiple factor each frame.
     * */
    private int dx= 25;
    /**
     * Represent the x position of the graphic at a given time.
     * */
    private int x;
    /**
     * Represent the y position of the graphic at a given time.
     * */
    private int y;
    /**
     * Represent the imageicon of an image file.
     * */
    //Level 1
    private static ImageIcon icon = new ImageIcon("data/1.png");
    private static ImageIcon icon2 = new ImageIcon("data/2.png");
    private static ImageIcon icon3 = new ImageIcon("data/3.png");
    private static ImageIcon icon4 = new ImageIcon("data/4.png");
    private static ImageIcon icon5 = new ImageIcon("data/5.png");
    private static ImageIcon icon6 = new ImageIcon("data/6.png");
    private static ImageIcon icon7 = new ImageIcon("data/7.png");
    private static ImageIcon icon8 = new ImageIcon("data/8.png");
    private static ImageIcon icon9 = new ImageIcon("data/9.png");
    private static ImageIcon icon10 = new ImageIcon("data/10.png");
    private static ImageIcon icon11 = new ImageIcon("data/11.png");
    //Interfacing
    private static ImageIcon rock = new ImageIcon("data/rockUI.png");
    private static ImageIcon heart = new ImageIcon("data/heartUI.gif");
    private static ImageIcon splash = new ImageIcon("data/splash.jpg");
    //Level 2
    private static ImageIcon l21 = new ImageIcon("data/l21.png");
    private static ImageIcon l22 = new ImageIcon("data/l22.png");
    private static ImageIcon l23 = new ImageIcon("data/l23.png");
    private static ImageIcon l24 = new ImageIcon("data/l24.png");
    private static ImageIcon l25 = new ImageIcon("data/l25.png");
    private static ImageIcon l26 = new ImageIcon("data/l26.png");
    private static ImageIcon l27 = new ImageIcon("data/l27.png");
    //Non-Parallax Scrolling Background images
    private static ImageIcon boss = new ImageIcon("data/boss.jpg");
    private static ImageIcon space = new ImageIcon("data/space.png");

    /**
     * Contructor used to create a default world with fixed sizes and given game instance
     * @param game the game container for all game objects
     * @param height height of the window
     * @param width width of the window
     * @param world the current constructed world container for all world objects
     * */
    public MyView(World world, Game game, int width, int height) {
        super(world, 800, 550);
        this.game = game;
    }
    /**
     * Function used to draw layered images of the game at different speeds.
     * @param graphics The graphics layer where values can be mapped onto the background.
     * */
    @Override
    @SuppressWarnings("Duplicates")
    public void paintBackground(Graphics2D graphics) {
        //Positions of the character are derived
        this.x = (int) game.getPlayer().getPosition().x;
        this.y = (int) game.getPlayer().getPosition().y;
        Vec2 pWorld = game.getPlayer().getPosition();

        //Value on screen is mapped onto from in game length of the displacement x.
        Point2D pScreen = this.worldToView(pWorld);
        int dx = (int) ((Point2D.Float) pScreen).x;

        //Displacement dx of a variant constant is added onto a value to create an effect
        //where the images appear to be moving at different rates relative to the position of the player.
        //--> 2. Get the draw multiple graphics
        // Icon 1 is the closest which means the negation of x for icon one should be the largest
        // icon1 -> 0 -> pScreen
        if (game.getLevel() == 1 || game.getLevel() == 0) {
            graphics.drawImage(icon11.getImage(), 0 - (int) (dx * 0.01f), -8, this);
            graphics.drawImage(icon10.getImage(), 10 - (int) (dx * 0.2f), -8, this);
            graphics.drawImage(icon9.getImage(), 20 - (int) (dx * 0.4f), -8, this);
            graphics.drawImage(icon8.getImage(), 30 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(icon7.getImage(), 0 - (int) (dx * 0.01f), -8, this);
            graphics.drawImage(icon6.getImage(), 60 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(icon5.getImage(), 60 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(icon4.getImage(), 70 - (int) (dx * 0.2f), -8, this);
            graphics.drawImage(icon3.getImage(), 80 - (int) (dx * 0.4f), -8, this);
            graphics.drawImage(icon2.getImage(), 90 - (int) (dx * 0.6f), -8, this);
            graphics.drawImage(icon.getImage(), 100 - (int) (dx * 1.0f), -8, this);
            //graphics.drawImage(icon.getImage(), 0-(int)(((Point2D.Float) pScreen).x), 0, this);*/
            //graphics.drawImage(icon4.getImage(), 0-(int)(((Point2D.Float) pScreen).x * (int)10.125f), (int)(pScreen.getY()*0.0005), this);
        }
        else if (game.getLevel() == 2){
            graphics.drawImage(l27.getImage(), 0 - (int) (dx * 0.01f), -8, this);
            graphics.drawImage(l26.getImage(), 60 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(l25.getImage(), 60 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(l24.getImage(), 70 - (int) (dx * 0.2f), -8, this);
            graphics.drawImage(l23.getImage(), 80 - (int) (dx * 0.4f), -8, this);
            graphics.drawImage(l22.getImage(), 90 - (int) (dx * 0.6f), -8, this);
            graphics.drawImage(l21.getImage(), 100 - (int) (dx * 1.0f), -8, this);
        }
        else if (game.getLevel() == 3){
            graphics.drawImage(boss.getImage(), 0,0,this);

        } else if (game.getLevel() == 5){
            graphics.drawImage(space.getImage(), 0,0,this);
        }
    }

    /**
     * Function is used to draw graphics on the foreground, on the same layer as game objects.
     * @param graphics Graphics used to map values onto the foreground.
     */
    @Override
    @SuppressWarnings("Duplicates")
    public void paintForeground(Graphics2D graphics) {
        //If the game has ended, create an end-game sequence such that the user cannot see the game objects
        if (game.getLevel() == 4) {
            if (dx < 600){
                dx += 5;
            }
            //A parallax screen is displayed which has a different speed increase rate and therefore
            //adds an effect like a quick time-lapse to end the current game level.
            graphics.drawImage(icon11.getImage(), 0 - (int) (dx * 0.01f), -8, this);
            graphics.drawImage(icon10.getImage(), 10 - (int) (dx * 0.2f), -8, this);
            graphics.drawImage(icon9.getImage(), 0 - (int) (dx * 0.4f), -8, this);
            graphics.drawImage(icon8.getImage(), 0 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(icon7.getImage(), 0 - (int) (dx * 0.01f), -8, this);
            graphics.drawImage(icon6.getImage(), 0 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(icon5.getImage(), 0 - (int) (dx * 0.1f), -8, this);
            graphics.drawImage(icon4.getImage(), 10 - (int) (dx * 0.2f), -8, this);
            graphics.drawImage(icon3.getImage(), 10 - (int) (dx * 0.4f), -8, this);
            graphics.drawImage(icon2.getImage(), 40 - (int) (dx * 0.6f), -8, this);
            graphics.drawImage(icon.getImage(), 40 - (int) (dx * 1.0f), -8, this);
        }
        //Text is used to show the points and rocks count using accessors.
        // Graphics is used to represent each point and is drawn on the foreground as shown.
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        graphics.drawString("Points: " + game.getPlayer().getCount(), 10, 90);
        if (game.getLevel() != 1) {
            graphics.drawString("Rocks: " + game.getPlayer().getRockCount(), 10, 55);
            for (int i = 0; i < game.getPlayer().getRockCount(); i++) {
                graphics.drawImage(rock.getImage(), 15 * i, 60, this);
            }
        }
        // Different values are shown on different levels -> as the number of snakes killed
        // and the rock count would not be shown on the first game level.
        if (game.getLevel() == 2){
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            graphics.drawString("Kill 5 snakes",320,120);
            if (game.getPlayer().getSnakesKilled() >= 5){
                game.goNLevel(2);
                game.restartSync();
            }
        }
        //This is used to show a death screen in the game and the current level
        if (game.getLevel() != 4) {
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            graphics.drawString("Level: "+game.getLevel(), 350, 90);
        } else{
            //If the player is out of lives, they receive a restart message
            //otherwise, they are advised to simply move onto the next level.
            if (game.getPlayer().getLives() >0) {
                graphics.setFont(new Font("TimesRoman", Font.BOLD, 45));
                graphics.drawString("Level over! (Combo-box for level)", 100, 180);
            } else {
                graphics.setFont(new Font("TimesRoman", Font.BOLD, 180));
                graphics.drawString("You died! (Combo-box for level)", 100, 80);
            }
        }
        //This is used to show the current health of the boss and draw corresponding images
        // like shown before.
        if (game.getLevel() == 3){
            graphics.setFont(new Font("TimesRoman", Font.BOLD, 25));
            graphics.drawString("BOSS HEALTH", 300, 20);
            for (int i = 0; i < game.getPlayer().getBossHealth(); i++) {
                graphics.drawImage(heart.getImage(), 300 +  15 * i,25 , this);
            }
            //In such a scenario where level is 3, if the boss has no health, the level is ended.
            if (game.getPlayer().getBossHealth() == 0){
                game.goNLevel(3);
                game.restartSync();
            }
        }
        if (game.getLevel() == 5){
            System.out.println(game.getPlayer().getPosition());
        }
        //If the player's lives is more than zero, the lives is always shown as it is a vital part of the game.
        if (game.getPlayer().getLives() > 0) {
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            graphics.drawString("Lives: " + game.getPlayer().getLives(), 10, 10);
            //Images are used to represent each live count as a heart.
            for (int i = 0; i < game.getPlayer().getLives(); i++) {
                graphics.drawImage(heart.getImage(), -15 + 15 * 2 * i, -5, this);
            }
        } else {
            //If the player has zero lives/ hearts remaining, the world is ended and the player is send to
            //change level screen.
            getWorld().stop();
            game.goNLevel(3);
            game.restartSync();
        }
    }
}