package game.ListenersUpdater;

import city.cs.engine.*;
import game.Bodies.Frog;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Collision listener that allows frog to decrease point by getting hit by traps
 */
public class hitPeel implements CollisionListener {
    private Frog frog;
    public hitPeel(Frog frog) {
        this.frog = frog;
    }

    @Override
    public void collide(CollisionEvent e) {
        //If the frog hit the peel destroy the peel and remove one of frog's lives
        //Adding the collision listeners
        if (e.getOtherBody() == frog) {
            System.out.println("Frog collided with peel// Changing position for test");
            frog.jump(2);
            frog.setPosition(new Vec2(2,2));
            hitRock rock = new hitRock(frog);
            frog.setLives(frog.getLives() -1);
            e.getReportingBody().destroy();

        }
    }





    
}
