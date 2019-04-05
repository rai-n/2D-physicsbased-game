package game.Bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Frog character which constructs the object and holds fields regarding game performance.
 * @author Neeraj, Rai, Neeraj.Rai@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Frog extends Walker {

    /**
     * The number of times the player has died.
     */
    private int deathCount;
    /**
     * The number of times the player has picked up a coin.
     */
    private int coinCount;

    /**
     * The number of rocks the player can currently throw.
     */
    private int rockCount;
    /**
     * The number of times the player has died.
     */
    /**
     * The number of lives remaining of the player.
     */
    private int lives;
    /**
     * The number of lives remaining of the boss.
     */
    private int bossHealth;
    /**
     * The number of snakes killed by the player.
     */
    private int snakesKilled;

    /**
     * @param world the world where the player will reside.
     */
    public Frog(World world) {
        super(world);
        this.bossHealth = 5;
        this.deathCount = 0;
        this.coinCount = 0;
        this.rockCount = 5;
        this.lives = 5;
        this.snakesKilled = 0;


        //Wheel has a non-circular image so the fixtures appear out of size of the image.
        //Wheel has been split into two shapes to add better visuals.

        //Top
        Shape wheelt = new PolygonShape(0.28f,-1.09f, -0.9f,-1.14f, -0.86f,-0.76f, -0.61f,-0.39f, -0.13f,-0.34f, 0.18f,-0.66f);
        Fixture wheelFixturet = new SolidFixture(this, wheelt);
        //Left-bottom half of wheel
        Shape wheelbl = new PolygonShape(-0.4f,-1.14f, -0.89f,-1.14f, -0.88f,-1.3f, -0.82f,-1.46f, -0.75f,-1.59f, -0.66f,-1.73f, -0.57f,-1.84f, -0.42f,-1.9f);
        Fixture wheelFixturebl = new SolidFixture(this, wheelbl);

        //Right-bottom half of wheel
        Shape wheelbr = new PolygonShape(-0.37f,-1.12f, 0.3f,-1.14f, 0.29f,-1.36f, 0.23f,-1.57f, 0.11f,-1.76f, -0.04f,-1.87f, -0.22f,-1.93f, -0.34f,-1.91f);
        Fixture wheelFixturebr = new SolidFixture(this, wheelbr);
        Shape frog = new PolygonShape(-0.75f,-0.28f, 0.03f,-0.25f, 0.37f,1.84f, -0.73f,1.94f, -0.82f,-0.08f);
        Fixture frogFixture = new SolidFixture(this, frog);
    }

    /**
     * This method is used to display the snakes killed in Level 2.
     * @return Returns the number of snakes killed by the player
     */
    public int getSnakesKilled() {
        return snakesKilled;
    }
    /**
     * This method is used to display the snakes killed in Level 2.
     * @param snakesKilled Sets the number of snakes killed by the player
     */
    public void setSnakesKilled(int snakesKilled) {
        this.snakesKilled = snakesKilled;
    }
    /**
     * This method is used to display the coin count of the player throughout the levels.
     * <p>
     *     The value for count is saved at the end of each level and is added back onto the next
     *     level to ensure saving of points every level.
     * </p>
     * @return Returns the number of snakes killed by the player
     */
    public int getCount(){
        return coinCount;
    }

    /***
     * This method is used to set the value of count.
     * <p>
     *     The value for count is saved at the end of each level and is added back onto the next
     *     level to ensure saving of points every level.
     *      </p>
     * @param count A value for count can be set via this parameter.
     */
    public void setCount(int count){
        this.coinCount = count;
    }

    /***
     * This method is used to set the lives of the player.
     * @param lives A value for lives which is set for the player.
     */
    public void setLives(int lives){
        this.lives = lives;
    }

    /***
     * This method is used to get the lives count for the player.
     * @return A value representing the current lives of the player.
     */
    public int getLives(){
        return this.lives;
    }

    /***
     * This method is used to get the current rock count for the player.
     * @return A value representing the current rock count of the player.
     */
    public int getRockCount(){
        return rockCount;
    }

    /***
     * This method is used to set the current rock count for the player.
     * @param rockCount A value to set to the number of rocks of the player.
     */
    public void setRockCount(int rockCount){
        this.rockCount = rockCount;
    }

    /***
     * This function is used to decrement the number of rocks the player has.
     */
    public void decrementRockCount(){
        this.rockCount --;
    }

    /***
     * This function is used to increment the number of rocks the player has.
     */
    public void incrementCoins(){
        coinCount++;
        System.out.println("No of coins: " + coinCount);
    }

    /***
     * This function is used to display the health of the boss.
     * @return Returns the current health of the boss.
     */
    public int getBossHealth() {
        return bossHealth;
    }

    /***
     * This function is used to display the health of the boss.
     * @param bossHealth The parameter provided is applied to the current health of the boss.
     */
    public void setBossHealth(int bossHealth) {
        this.bossHealth = bossHealth;
    }
}



