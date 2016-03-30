package gameengine.model;

/**
 * This interface defines the public methods that each Action subclass will have. We want to keep our Actions modular so that any Action
 * can be used in the place of any other. This will make it easier to define the behaviors for any Actor object.
 *
 * @author blakekaplan
 */

public interface IAction {

    /**
     * Performs a specific procedure which will be specific to each Action subclass
     */
    public void perform();

}
