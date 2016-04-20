package authoringenvironment.view;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ImageView that serves as an icon for an actor.
 * @author amyzhao
 *
 */
public class ImageviewActorIcon extends ImageView {
	private IAuthoringActor myActor;
	private int myID;
	private boolean onLevel;
	
	/**
	 * Construct an icon for a given actor.
	 * @param actor: actor to construct an icon for.
	 */
	public ImageviewActorIcon(IAuthoringActor actor, double height) {
		myActor = actor;
		setImage(actor.getMyImageView().getImage());
		this.setFitHeight(height);
		this.setPreserveRatio(true);
		myID = actor.getMyID();
		onLevel = false;
		updateImageView();
	}
	
	public IAuthoringActor getActor() {
		Actor actor = new Actor();
		actor.setMyName(myActor.getMyName());
		actor.setMyImageViewName(myActor.getMyImageViewName());
		actor.setMyImageView(myActor.getMyImageView());
		actor.setMyID(myActor.getMyID());
		actor.setSize(myActor.getSize());
		actor.setX(0);
		actor.setY(0);
		return actor;
	}
	
	public IAuthoringActor getRefActor() {
		return myActor;
	}
	
	// if you have this already on the board, then it should reference the already new actor not the original actor the icon was made from
	/**
	 * Gets the actor associated with this icon.
	 * @return my actor.
	 */
	public void updateIconActorPosition(double x, double y) {
		myActor.setX(x);
		myActor.setY(y);
	}
	
	/**
	 * Gets the ID of the actor associated with this icon. (ID of actor and ID of its icon are the same).
	 * @return my ID.
	 */
	public int getID() {
		return myID;
	}
	
	/**
	 * Update the imageview based on the actor's current image.
	 */
	public void updateImageView() {
		setImage(myActor.getMyImageView().getImage());
		setPreserveRatio(true);
		if (onLevel) {
			setFitHeight(myActor.getMyImageView().getFitHeight());
		}
	}
	
	public void setOnLevel(boolean bool) {
		onLevel = bool;
	}
}
