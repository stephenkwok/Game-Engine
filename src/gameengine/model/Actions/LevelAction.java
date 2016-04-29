package gameengine.model.Actions;

import gameengine.controller.Level;
import gameengine.model.IGameElement;

/**
 * @author blakekaplan
 */
public abstract class LevelAction extends Action {

    public LevelAction(Level assignedLevel){
        super((IGameElement)assignedLevel);
    }

    public Level getMyLevel(){
        return (Level) getGameElement();
    }

    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void perform();
}
