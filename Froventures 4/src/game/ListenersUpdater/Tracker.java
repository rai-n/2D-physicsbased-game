package game.ListenersUpdater;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.geom.Point2D;

/**
 * The tracker used to pan the x position of player if they touch the canvas width.
 */
public class Tracker implements StepListener {
    /**
     * The view
     */
    private WorldView view;

    /**
     * The body
     */
    private Body body;
    /**
     * The difference in y position of the body
     */
    private float dy;

    /**
     * The constructor sets the initial values for body and view.
     * <p>The value of dy is also assigned such that the initial y position is maintained throughout
     * the gameplay. This is a fix to a problem where if the player's dx is more than screen width, reassigning
     * the player's position would artificially increase the y position of the player's tracker if they jumped when calling the function.  </p>
     * @param view
     * @param body
     */
    public Tracker(WorldView view, Body body ) {
        //For the tracker the player's position of Y is tracked and is used later
        //to form a new value for x and dy to ensure no variance in y for such an event
        //where the player's dx would be more than width of the screen
        this.view = view;
        this.body = body;
        view.setCentre(body.getPosition());
        this.dy = body.getPosition().y;
    }

    /**
     * A function which is called every frame
     * <p>
     *     In this case, it is used to check for angle of bike every frame to see if the frog has fallen.
     * </p>
     * @param e A step event
     */
    @Override
    public void preStep(StepEvent e) {
        sendStart();

    }

    /**
     * A function to realign the center position of the tracker as the frog leaves the width of the screen.
     * @param e A step event
     */
    @Override
    public void postStep(StepEvent e) {
        //The positions of the player is assigned from when the tracker is first initialised to get the original value of y
        Vec2 pWorld = body.getPosition();
        Point2D pScreen = view.worldToView(pWorld);
        //As the real value of y does not vary, it may remain constant throughout and thus it is referred to as dy.
        if (pScreen.getX() < 50 || pScreen.getX() >= view.getWidth() - 50) {
            //The position of the center is set for the Tracker.
           view.setCentre(new Vec2(body.getPosition().x, dy));
        }


    }

    /**
     *Function sendStart is called which checks if the player's angle exceeds a certain threshold.
     *If so, send them to the start as they would have 'fallen' from the unicycle.
     */
    public void sendStart() {
        if (body.getAngleDegrees() > 65 || body.getAngleDegrees() < -65) {
            body.setAngle(180);
            body.setPosition(new Vec2(2, -6));
            body.setAngle(0);
        }
    }
}
