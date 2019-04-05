package game.GameController;

import city.cs.engine.*;
import game.Bodies.Bird;
import game.Bodies.Coin;
import game.ListenersUpdater.hitPoint;
import game.ListenersUpdater.hitRock;
import org.jbox2d.common.Vec2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

//A level where the frog can freely collect coins play with gravity
public class InfinityRunner extends GameLevel {
    private Coin coin;
    private Bird bird;
    int totalPoints;
    Timer timer;

    @Override
    @SuppressWarnings("Duplicates")
    public void populate(Game game) {
        super.populate(game);
        totalPoints = game.getPlayer().getCount();
        //A new bird object is created which will drop the coins which the frog can pick up
        Bird bird = new Bird(this);
        bird.setPosition(new Vec2(-20, 20));
        bird.addCollisionListener(new hitRock(getPlayer()));
        BodyImage birdimg = (new BodyImage("data/bird.gif"));
        AttachedImage birdx = (new AttachedImage(bird, birdimg, 5f, 0, new Vec2(0, 0)));
        bird.setGravityScale(0f);
        bird.startWalking(6f);
        this.bird = bird;
        float dx = bird.getPosition().x;
        //Timer is used to drop coins which the frog can collect.

        //Exception handling for when the timer is completed its task.
        try {
            Timer timer = new Timer();
            int begin = 0;
            int timeInterval = 1000;
            timer.schedule(new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    dropCoins();
                    counter++;
                    if (counter >= 400) {
                        timer.cancel();
                    }
                }
            }, begin, timeInterval);
        } catch (NullPointerException fakeCrash){
            System.out.println(".");
        }


    }
    //A bunch of coins are dropped using a loop
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
