package game.ListenersUpdater;

import game.GameController.Game;
import game.GameController.GameLevel;
import org.jbox2d.common.Vec2;

public class LevelOver extends GameLevel {



    /**
     * Populate the world.
     */
    @Override @SuppressWarnings("Duplicates")
    public void populate(Game game) {
        super.populate(game);
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0,0);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(0,0);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getLives() == 5;
    }



}
