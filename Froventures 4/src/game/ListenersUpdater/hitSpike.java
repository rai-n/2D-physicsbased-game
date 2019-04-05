package game.ListenersUpdater;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Bodies.Floor;
import game.Bodies.Frog;

/**
 * Collision listener for frog to collect points or hit rock.
 */
public class hitSpike implements CollisionListener {
    private Frog frog;
    //Constructor used to initialise the object.
    public hitSpike(Frog frog) {
        this.frog = frog;
    }

    public void collide(CollisionEvent e) {
        //If frog collides with a spike, reduce its hp but also break the spike
        //Break the spike if it collides with anything.
        if (e.getOtherBody() == frog) {
            System.out.println("Frog collided with spike");
            frog.setLives(frog.getLives() - 1);
            e.getReportingBody().destroy();
        }
        else if (!(e.getOtherBody()  == frog)){
            e.getReportingBody().destroy();

        }
    }
}
