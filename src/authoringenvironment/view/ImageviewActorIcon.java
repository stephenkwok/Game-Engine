package authoringenvironment.view;

import gameengine.model.Actor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageviewActorIcon extends ImageView {
	private static final int ICON_HEIGHT = 75;
	private Actor myActor;
	private int myID;
	
	public ImageviewActorIcon(Actor actor) {
		setImage(actor.getImage());
		this.setFitWidth(ICON_HEIGHT);
		this.setPreserveRatio(true);
		myID = actor.getID();
	}
	
	public Actor getActor() {
		return myActor;
	}
	
	public int getID() {
		return myID;
	}
}
