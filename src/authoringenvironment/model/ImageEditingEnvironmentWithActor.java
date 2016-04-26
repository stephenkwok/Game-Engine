package authoringenvironment.model;

import java.util.Observable;

import javafx.scene.image.ImageView;

/**
 * 
 * 
 * @author Stephen
 *
 */

public class ImageEditingEnvironmentWithActor extends ImageEditingEnvironment{

	private IAuthoringActor myActor;
	private ImageView myImageView;
	
	public ImageEditingEnvironmentWithActor(IAuthoringActor actor) {
		super(actor.getImageView());
		myActor = actor;
		myImageView = actor.getImageView();
	}

	@Override
	public void update(Observable o, Object arg) {
		myActor.setSize(myImageView.getFitHeight());
		myActor.setOpacity(myImageView.getOpacity());
		myActor.setRotate(myImageView.getRotate());
		System.out.println("My Actor's Rotate: " + myActor.getRotate());
		System.out.println("My Images's Rotate: " + myImageView.getRotate());
		setChanged();
		notifyObservers(myActor);
		closePopUp();
	}
	
}
