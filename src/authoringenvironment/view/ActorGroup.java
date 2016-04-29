package authoringenvironment.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.scene.image.ImageView;

import java.util.List;

import authoringenvironment.model.IAuthoringActor;

public class ActorGroup implements IEditableGameElement {
	private List<IAuthoringActor> myActors;
	private String myName;
	
	public ActorGroup(String name, List<IAuthoringActor> myStartingActors) {
		myActors = myStartingActors;
		setName(name);
	}
	
	@Override
	public void setName(String name) {
		myName = name;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public ImageView getImageView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setImageView(ImageView imageView) {
		// TODO Auto-generated method stub
		
	}
	
	public void addActorToGroup(IAuthoringActor actors) {
		myActors.add(actors);
	}
	
	public List<IAuthoringActor> getGroup() {
		return myActors;
	}
	
}
