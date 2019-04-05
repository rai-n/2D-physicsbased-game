package game.ListenersUpdater;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Bodies.Frog;
import game.GameController.Game;

/**
 * Listener for collision with a door.  When the player collides with a door,
 * if the current level is complete the game is advanced to the next level. 
 */
public class DoorListener implements CollisionListener {
    private Game game;
    public DoorListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        Frog player = game.getPlayer();
        //&& game.isCurrentLevelCompleted()
        if (e.getOtherBody() == player) {
            System.out.println("World being made...");
            //Changes the player's level to the screen level if they won a level
            game.goNLevel(4);

        }
    }
}
