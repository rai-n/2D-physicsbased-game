package game.ListenersUpdater;

import city.cs.engine.*;
import game.Bodies.Frog;

/**
 * Collision listener for frog to collect points
 */
public class hitPoint implements CollisionListener {
    private Frog frog;

    public hitPoint(Frog frog) {
        this.frog = frog;
    }

    @Override
    public void collide(CollisionEvent e) {
        //Destroying the object if the frog collided with the point
        //Also incrementing frog's points
        if (e.getOtherBody() == frog) {
            System.out.println("Frog collided with points");
            frog.incrementCoins();
            e.getReportingBody().destroy();

        }
    }





}
