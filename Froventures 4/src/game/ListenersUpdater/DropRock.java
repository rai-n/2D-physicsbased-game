
package game.ListenersUpdater;

import city.cs.engine.*;
import game.Bodies.rock;
import game.Bodies.Frog;
import game.GameController.Game;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A MouseListener that drops a rock on each mouse press.
 */
public class DropRock extends MouseAdapter {

    private WorldView view;
    private Body body;
    private Frog body2;
    private Game game;

    /**
     * Construct a listener.
     * @param view the view the mouse will be clicked on
     */

    public DropRock(WorldView view, Body body, Game game) {
        this.view = view;
        this.body = body;
        this.game = game;
    }

    /**
     * Create a rock at the current mouse position.
     * Applies some linear velocity.
     * @param e event object containing the mouse position
     */


    @Override
    public void mousePressed(MouseEvent e) {
        if (game.getPlayer().getRockCount() >0) {
            DynamicBody rock = new rock(view.getWorld());
            rock.setPosition(view.viewToWorld(e.getPoint()));
            rock.setLinearVelocity(new Vec2(4, 2));
            rock.setAngularVelocity(4);
            //float y = rock.getPosition().y;
            rock.addCollisionListener(new hitRock(body2));
            game.getPlayer().decrementRockCount();
        }
    }
}
