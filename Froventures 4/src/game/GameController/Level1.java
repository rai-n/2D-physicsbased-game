package game.GameController;

import city.cs.engine.*;
import game.Bodies.Bird;
import game.Bodies.Coin;
import game.Bodies.Peel;
import game.ListenersUpdater.hitPeel;
import game.ListenersUpdater.hitPoint;
import game.ListenersUpdater.hitRock;
import org.jbox2d.common.Vec2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

//Level 1
public class Level1 extends GameLevel {
    private Coin coin;
    private Bird bird;
    int totalPoints;


   //World is populated
    @Override
    @SuppressWarnings("Duplicates")
    public void populate(Game game) {
        super.populate(game);
        totalPoints = game.getPlayer().getCount();

        // make the ground
        // texture is added to the shape
        int j = 20;
        while (j > 0) {
            Shape groundShape = new BoxShape(4, 0.5f);
            Body ground = new StaticBody(this, groundShape);
            //Value of x changes relative to j so a pathway is generated
            ground.setPosition(new Vec2(-30 + j * 8.3f, -20.5f));
            BodyImage textureGround = (new BodyImage("data/Ground.png"));
            AttachedImage groundx = (new AttachedImage(ground, textureGround, 8f, 0, new Vec2(-0.5f, -3.525f)));
            j--;
        }
        //A loop with random variable is used to generate a unique set of peel obstacles
        for (int i = 0; i < 10; i++) {
            int randx = ThreadLocalRandom.current().nextInt(5, 17 + 1);
            Body peel = new Peel(this);
            peel.setPosition(new Vec2(i * randx - 10, -20));
            //Collision listeners are added to each of the peels
            peel.addCollisionListener(new hitPeel(getPlayer()));
            BodyImage peelimg = (new BodyImage("data/peel.png"));
            AttachedImage peeli = (new AttachedImage(peel, peelimg, 2f, 0, new Vec2(0, 0)));
        }

        Bird bird = new Bird(this);
        bird.setPosition(new Vec2(-20, 20));
        bird.addCollisionListener(new hitRock(getPlayer()));
        BodyImage birdimg = (new BodyImage("data/bird.gif"));
        AttachedImage birdx = (new AttachedImage(bird, birdimg, 5f, 0, new Vec2(0, 0)));
        bird.setGravityScale(0f);
        bird.startWalking(6f);
        this.bird = bird;
        float dx = bird.getPosition().x;
        try {
            Timer timer = new Timer();
            int begin = 0;
            int timeInterval = 4000;
            timer.schedule(new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    dropCoins();
                    counter++;
                    if (counter >= 40) {
                        timer.cancel();
                    }
                }
            }, begin, timeInterval);
        } catch (NullPointerException fakeCrash){
            System.out.println(".");
        }

    }

    public void dropCoins() {
        for (int i = 0; i < 1; i++) {
            int randx = ThreadLocalRandom.current().nextInt(5, 17 + 1);
            Coin coin = new Coin(this);
            //coin.setPosition(new Vec2(i * randx - 5, -20));
            coin.setPosition(new Vec2(bird.getPosition().x, bird.getPosition().y - 4));
            coin.addCollisionListener(new hitPoint(getPlayer()));
            BodyImage coinimg = (new BodyImage("data/Coin.gif"));
            AttachedImage coinimgs = (new AttachedImage(coin, coinimg, 2f, 0, new Vec2(0, 0)));
        }
    }





    @Override
    public Vec2 startPosition() {
        return new Vec2(-2, 5);
    }


    @Override
    public Vec2 doorPosition() {
        return new Vec2(136, -18);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= 4;
    }
}
