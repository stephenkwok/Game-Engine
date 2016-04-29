package authoringenvironment.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.scene.image.ImageView;

import java.util.List;

import authoringenvironment.model.IAuthoringActor;

public class ActorGroup implements IEditableGameElement {
	private static final String GROUP = " Group";
	private List<IAuthoringActor> myActors;
	private String myName;
	private ImageView myImageView;
	
	public ActorGroup(String name, ImageView imageview, List<IAuthoringActor> myStartingActors) {
		myActors = myStartingActors;
		setName(name);
		setImageView(imageview);
	}
	
	@Override
	public void setName(String name) {
		myName = name + GROUP;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public ImageView getImageView() {
		return myImageView;
	}

	@Override
	public void setImageView(ImageView imageView) {
		myImageView = imageView;
	}
	
	public void addActorToGroup(IAuthoringActor actors) {
		myActors.add(actors);
	}
	
	public List<IAuthoringActor> getGroup() {
		return myActors;
	}
	
}
