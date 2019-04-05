package game.GameController;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Bodies.Bird;
import game.Bodies.CthuluHand;
import game.Bodies.Snake;
import game.Bodies.Spike;
import game.ListenersUpdater.hitSnake;
import game.ListenersUpdater.hitSpike;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Level 3 of the game
 * @author Neeraj, Rai, Neeraj.Rai@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Level3 extends GameLevel{
    /**
     * Field used to hold the bird object which the game refers to.
     */
    private Bird bird;
    /**
     * Field used to hold the snake object which the game refers to.
     */
    private Snake snake;
    /**
     * Loading screaming sound.
     */
    private static SoundClip scream;
    /**
     * Populate the world.
     */
    @Override
    @SuppressWarnings("Duplicates")
    public void populate(Game game) {
        super.populate(game);
        // Creating the walls and floor
        Shape boxShape = new BoxShape(4, 0.5f);
        Body platform1 = new StaticBody(this, boxShape);
        // Positions of the boxes are placed such to fit a box type map.
        platform1.setPosition(new Vec2(-7, 1.4f));
        Body platform2 = new StaticBody(this, boxShape);
        platform2.setPosition(new Vec2(5, -5f));

        //Box-shape up represents a rectangular box which tends towards  a larger height
        //This is to prevent the tentacle which is a dynamic object from being thrown off the map
        Shape boxShapeUP = new BoxShape(0.5f, 50f);
        Body leftWall = new StaticBody(this, boxShapeUP);
        leftWall.setPosition(new Vec2(-20, -10.5f));

        // Loop to create 20 sections of the floor.
        int j = 20;
        while (j > 0) {
            Shape groundShape = new BoxShape(4, 0.5f);
            Body ground = new StaticBody(this, groundShape);
            ground.setPosition(new Vec2(-30 + j * 8.3f, -10.5f));
            BodyImage textureGround = (new BodyImage("data/Ground.png"));
            AttachedImage groundx = (new AttachedImage(ground, textureGround, 8f, 0, new Vec2(-0.5f, -3.525f)));
            j--;
        }
        //The player will have to partake in 10 waves and if they survive, they win.
        try {
            Timer timer = new Timer();
            int begin = 0;
            int timeInterval = 10000;
            timer.schedule(new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    startGame();
                    counter++;
                    if (counter >= 10) {

                        timer.cancel();
                    }
                }
            }, begin, timeInterval);
        } catch (NullPointerException fakeCrash){
            System.out.println(".");
        }
    }

    /**
     * Starts the game by selecting a value out of 3 values to use a finite attack.
     */
    public void startGame(){
        //Random value is generated 1 and 3.
        //Each value represents a different attack which the boss can use.
        int picker = ThreadLocalRandom.current().nextInt(1, 3);
        //Attack one function is called.
        if (picker == 1){
            attackOne();
        } else if (picker == 2){
            //A new object is created which the player has to make the snake hit the boss.
            CthuluHand bossLeft = new CthuluHand(getPlayer().getWorld());
            BodyImage cthulu = (new BodyImage("data/cthulu.png"));
            AttachedImage boss = (new AttachedImage(bossLeft, cthulu, 5f, 0, new Vec2(0, 0)));
            bossLeft.setPosition(new Vec2(-17, -4.5f));
            // Snakes spawn which have to collide with the boss's tentacles to damage the boss.
            // A timer is used to call the function every 10 second such that the snakes do not run out.
            attackTwo();
            timerSnake();
            bossLeft.addCollisionListener(new hitSnake(getPlayer(), this.snake));
        } else if (picker == 3){
            // Attack three decreases the players lives by one which is why they should stock pile their lives
            // as the game is purely based on Random Number Generator and the player could lose the game
            // even if they have a lot of lives.
            attackThree();
        }
    }

    /**
     * Attack one which creates a bird object and drops spikes every second.
     */
    @SuppressWarnings("Duplicates")
    public void attackOne() {
        //Reusing bird class as evil bird with different speed parameter and size parameter.
        Bird bird = new Bird(this);
        bird.setPosition(new Vec2(-15, 10));
        BodyImage birdimg = (new BodyImage("data/eBird.gif"));
        AttachedImage birdx = (new AttachedImage(bird, birdimg, 5f, 0, new Vec2(0, 0)));
        bird.setGravityScale(0f);
        bird.startWalking(6f);

        //The bird is created once and thus a timer is used to drop a spike on the ever
        //changing position of the bird every second, 30 times.
        this.bird = bird;
        try {
            Timer timer = new Timer();
            int begin = 0;
            int timeInterval = 1000;
            timer.schedule(new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    dropSpike();
                    counter++;
                    if (counter >= 30) {
                        timer.cancel();
                    }
                }
            }, begin, timeInterval);
        } catch (NullPointerException fakeInstance){
            System.out.println(".");
        }
    }

    /**
     * Function which is called to drop the spikes every second in attack one.
     */
    @SuppressWarnings("Duplicates")
    public void dropSpike() {
        //A spike object is created in the game world.
        for (int i = 0; i < 1; i++) {
            int randx = ThreadLocalRandom.current().nextInt(5, 17 + 1);
            Spike spike = new Spike(this);
            //coin.setPosition(new Vec2(i * randx - 5, -20));

            //The position of the spike is dependent on the variable position of the bird -4 so it appears
            //as though the bird is dropping the spikes.
            spike.setPosition(new Vec2(bird.getPosition().x, bird.getPosition().y - 4));
            spike.addCollisionListener(new hitSpike(getPlayer()));
            BodyImage spikeimg = (new BodyImage("data/spike.png"));
            AttachedImage spikeimgs = (new AttachedImage(spike, spikeimg, 2f, 0, new Vec2(0, 0)));
        }
    }

    /**
     * Attack two which creates snakes and makes them accelerate towards the boss's tentacles to harm the boss.
     */
    @SuppressWarnings("Duplicates")
    public void attackTwo() {
        //A snake object is created and listeners are added.
        //Its positions are added and also its position is changed using a random integer.
        Snake snake = new Snake(this);
        snake.startWalking(-10);
        snake.jump(3);
        BodyImage snakeimg = (new BodyImage("data/snake.gif"));
        AttachedImage snakeimages = (new AttachedImage(snake, snakeimg, 5f, 0, new Vec2(0, 0.3f)));
        snake.addCollisionListener(new hitSnake(getPlayer(), snake));
        snake.setPosition(new Vec2(ThreadLocalRandom.current().nextInt(16, 17 + 1), -8));
        this.snake = snake;
        }

    /**
     * A timer which when called, summons snakes every 10 seconds, 30 times.
     */
    @SuppressWarnings("Duplicates")
    public void timerSnake() {
        //A timer is used that runs function every 10 second, 30 times, so the snake
        //arrives in a regular interval.
        try {
            Timer timer = new Timer();
            int begin = 0;
            int timeInterval = 2500;
            timer.schedule(new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    attackTwo();
                    counter++;
                    if (counter >= 30) {
                        timer.cancel();
                    }
                }
            }, begin, timeInterval);
        } catch (NullPointerException fakeCrash){
            System.out.println(".");
        }
    }

    static {
        try {
            scream = new SoundClip("data/Audio/boss.wav");
            System.out.println("Loading scream sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * A function which decreases the player's lives by one and plays an evil sound.
     */
    public void attackThree() {
        scream.play();
        getPlayer().setLives(getPlayer().getLives() -1);
    }

    /**
     * Returns the initial station position for the player when the game level is initialised.
     * @return A Vec2 representing the start position.
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(8, -6);
    }
    /**
     * Returns the initial station position for the door when the game level is initialised.
     * @return A Vec2 representing the start position.
     */
    @Override
    public Vec2 doorPosition() {
        return new Vec2(-1000.4f, -900.6f);
    }

    /**
     * Completes the level if the boolean condition is met.
     * @return A boolean which relates to whether or not the boss has been killed.
     */
    @Override
    public boolean isCompleted() {
        return getPlayer().getBossHealth() ==0 ;
    }
}
