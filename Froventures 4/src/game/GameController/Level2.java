package game.GameController;

import city.cs.engine.*;
import game.Bodies.Snake;
import game.ListenersUpdater.hitSnake;
import org.jbox2d.common.Vec2;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

//Level 2
public class Level2 extends GameLevel {

    int totalCount;

    //Data structure linked list used to store the snakes
    LinkedList<Snake> snakes= new LinkedList<>();
    /**
     * Populate the world.
     */
    @Override @SuppressWarnings("Duplicates")
    public void populate(Game game) {
        super.populate(game);
        // make the ground

        int j = 60;
        while (j > 0){
            //Loop is used to generate a nice path of floor
            Shape groundShape = new BoxShape(4, 0.5f);
            Body ground = new StaticBody(this, groundShape);
            ground.setPosition(new Vec2(-100+j*8.3f, -20.5f));
            BodyImage textureGround = (new BodyImage("data/Ground.png"));
            AttachedImage groundx = (new AttachedImage(ground, textureGround, 8f, 0, new Vec2(-0.5f, -3.525f)));
            j--;
        }
        //20 snakes are generated using a loop with various attached fields and methods
            for (int i = 0; i < 20; i++) {
                Snake snake = new Snake(this);
                snake.startWalking(-10);
                snake.jump(3);
                BodyImage snakeimg = (new BodyImage("data/snake.gif"));
                AttachedImage snakeimages = (new AttachedImage(snake, snakeimg, 5f, 0, new Vec2(0, 0.3f)));
                snake.addCollisionListener(new hitSnake(getPlayer(),snake));
                //Each of the snakes are added to a LinkedList where they may be access using a loop elsewhere.
                snakes.add(snake);
            }
            //Each of the snake's positions are overwritten uniquely in the LinkedList.
            for(int i = 0; i< 20; i++){
                Snake snake = snakes.get(i);
                snake.setPosition(new Vec2(20+i * ThreadLocalRandom.current().nextInt(16, 25 + 1)  , -15));
            }
            //Some snakes are placed on the other side and are applied compliment velocity.
        for(int i = 0; i< 3; i++){
            Snake snake = snakes.get(i);
            snake.startWalking(10);
            snake.setPosition(new Vec2(-80+i * ThreadLocalRandom.current().nextInt(16, 25 + 1)  , -15));
        }
        //Testing for object handling
        System.out.println(snakes);


    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-4, -13);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(-1000.4f, -9.600f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCount() >= 0;
    }






}
