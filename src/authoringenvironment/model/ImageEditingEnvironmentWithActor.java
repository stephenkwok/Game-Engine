package authoringenvironment.model;

import java.util.Observable;

import authoringenvironment.view.ImageEditingEnvironment;
import javafx.scene.image.ImageView;

/**
 * This class extends the ImageEditingEnvironment and allows the author to not only
 * edit an Actor's ImageView but also the Actor itself 
 * 
 * @author Stephen
 *
 */

public class ImageEditingEnvironmentWithActor extends ImageEditingEnvironment{

	private final IAuthoringActor myActor;
	private final ImageView myImageView;
	
	/**
	 * Creates an Image Editing Environment that also edits an Actor
	 * @param actor: the Actor to be edited
	 */
	public ImageEditingEnvironmentWithActor(IAuthoringActor actor) {
		super(actor.getImageView());
		myActor = actor;
		myImageView = actor.getImageView();
	}

	/**
	 * Sets an Actor's size, opacity, rotation, scaleX, and scaleY according
	 * to the changes made to its ImageView. Also notifies its observers 
	 * and closes the ImageEditingEnvironment
	 */
	@Override
	public void update(Observable o, Object arg) {
		myActor.setSize(myImageView.getFitHeight());
		myActor.setOpacity(myImageView.getOpacity());
		myActor.setRotate(myImageView.getRotate());
		myActor.setScaleX(myImageView.getScaleX());
		myActor.setScaleY(myImageView.getScaleY());
		setChanged();
		notifyObservers(myActor);
		closePopUp();
	}
	
}
