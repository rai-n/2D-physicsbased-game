package game.ListenersUpdater;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Bodies.CthuluHand;
import game.Bodies.Snake;
import game.Bodies.Frog;
import org.jbox2d.common.Vec2;

/**
 * Collision listener that allows frog to interact with snake
 */
public class hitSnake implements CollisionListener {
    private Frog frog;
    private Snake snake;
    //Constructor to initialise the objects
    public hitSnake(Frog frog, Snake snake) {
        this.frog = frog;
        this.snake = snake;

    }

    @Override
    public void collide(CollisionEvent e) {
        //add filter so snake can't collide with snake
        if (e.getReportingFixture() == snake.getSnakeFix() && e.getOtherBody() instanceof  Frog) {
            System.out.println("Snake was hit");
            e.getReportingBody().destroy();
            frog.incrementCoins();
            frog.setSnakesKilled(frog.getSnakesKilled()+1);
        //add filter so frog collides with the body of snake and kills the frog
            //destroys the snake and reduces the frog's points
        } else if ((e.getReportingFixture() == snake.getFBody()) && e.getOtherBody() instanceof Frog) {
            frog.setAngle(180);
            frog.setPosition(new Vec2(2, -6));
            frog.setAngle(0);
            frog.setLives(frog.getLives() - 1);
            // If the snake hits the boss's hand, destroy the snake but also reduce the boss' health
        } else if (e.getReportingBody() instanceof Snake && e.getOtherBody() instanceof CthuluHand){
            e.getReportingBody().destroy();
            if (frog.getBossHealth() > 0) {
                frog.setBossHealth(frog.getBossHealth() - 1);
                if (frog.getBossHealth() == 0){
                    System.out.println("Boss health is now: " + frog.getBossHealth());
                }
            }
            //If two snakes collide, the reporting body gets destroyed
        } else if (e.getOtherBody() instanceof Snake && e.getReportingBody() instanceof  Snake){
            e.getReportingBody().destroy();
        }
    }
}
    

