package game.ListenersUpdater;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Bodies.Bird;
import game.Bodies.Snake;
import game.Bodies.rock;
import game.Bodies.Frog;

/**
 * Collision listener that allows frog to decrease point by getting hit by traps
 */
public class hitRock implements CollisionListener {
    private Frog frog;

    public hitRock(Frog frog) {
        this.frog = frog;
    }

    @Override
    public void collide(CollisionEvent e) {
        //Destroying snakes if the rock collides with one
        //Destroying the rock as-well in the collision.
        System.out.println("Testing collision drop rock");
        if (e.getReportingBody() instanceof rock && ((e.getOtherBody() instanceof Snake))) {
            System.out.println("Rock collision");
            e.getOtherBody().destroy();
            //play snake destroy sound
            e.getReportingBody().destroy();
            if (e.getOtherBody() instanceof Bird){
                e.getOtherBody().destroy();
                //play bird sound
            }

        }
    }


}
