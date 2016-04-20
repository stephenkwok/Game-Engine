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
	private IAuthoringActor associatedActor;

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
		associatedActor = null;
		updateImageView();
	}

	public IAuthoringActor getActor() {
		if (associatedActor == null) {
			associatedActor = new Actor();
			associatedActor.setMyName(myActor.getMyName());
			associatedActor.setMyImageViewName(myActor.getMyImageViewName());
			associatedActor.setMyImageView(myActor.getMyImageView());
			associatedActor.setMyID(myActor.getMyID());
			associatedActor.setSize(myActor.getSize());
			associatedActor.setX(0);
			associatedActor.setY(0);
		}
		return associatedActor;
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
