package authoringenvironment.view;

import gameengine.model.IAuthoringActor;
import javafx.scene.image.ImageView;

/**
 * ImageView that serves as an icon for an actor.
 * @author amyzhao
 *
 */
public class ImageviewActorIcon extends ImageView {
	private static final int ICON_HEIGHT = 75;
	private IAuthoringActor myActor;
	private int myID;
	
	/**
	 * Construct an icon for a given actor.
	 * @param actor: actor to construct an icon for.
	 */
	public ImageviewActorIcon(IAuthoringActor actor) {
		setImage(actor.getImageView().getImage());
		this.setFitWidth(ICON_HEIGHT);
		this.setPreserveRatio(true);
		myID = actor.getMyID();
	}
	
	/**
	 * Gets the actor associated with this icon.
	 * @return my actor.
	 */
	public IAuthoringActor getActor() {
		return myActor;
	}
	
	/**
	 * Gets the ID of the actor associated with this icon. (ID of actor and ID of its icon are the same).
	 * @return my ID.
	 */
	public int getID() {
		return myID;
	}
}
