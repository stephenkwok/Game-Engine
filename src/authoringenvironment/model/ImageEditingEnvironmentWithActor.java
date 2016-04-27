package authoringenvironment.model;

import java.util.Observable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.scene.image.ImageView;

/**
 * 
 * 
 * @author Stephen
 *
 */

public class ImageEditingEnvironmentWithActor extends ImageEditingEnvironment{

	private IAuthoringActor myActor;
	@XStreamOmitField
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
		myActor.setScaleX(myImageView.getScaleX());
		myActor.setScaleY(myImageView.getScaleY());
		setChanged();
		notifyObservers(myActor);
		closePopUp();
	}
	
}
