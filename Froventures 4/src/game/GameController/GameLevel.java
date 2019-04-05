package game.GameController;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.World;
import game.Bodies.Door;
import game.ListenersUpdater.DoorListener;
import game.Bodies.Frog;
import game.View.MyView;
import org.jbox2d.common.Vec2;

/**
 * The controller of the game where each world is populated.
 */
public abstract class GameLevel extends World {
    private Frog player;
    int totalPoints;
    private MyView view;

    public Frog getPlayer() {
        return player;
    }

    
    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(Game game) {
        // make a character
        player = new Frog(this);
        player.setPosition(startPosition());
        BodyImage frogbody = (new BodyImage("data/frogR.gif"));
        BodyImage gunimg = (new BodyImage("data/gun.png"));
        AttachedImage image = (new AttachedImage(player, frogbody, 5f, 0, new Vec2(0, 0.3f)));

        //offsets for image-set
        player.setLinearVelocity(new Vec2(2,0));

        //a door is generated in each level with given values
        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));


    }


    /** The initial position of the player. */
    public abstract Vec2 startPosition();
    
    /** The position of the exit door. */
    public abstract Vec2 doorPosition();
    
    /** Is this level complete? */
    public abstract boolean isCompleted();



}
