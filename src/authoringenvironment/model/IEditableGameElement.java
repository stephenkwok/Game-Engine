package authoringenvironment.model;

import javafx.scene.image.ImageView;

/**
 * 
 * Interface for all game elements that can be edited in Game Authoring Environment (actors and levels)
 * 
 * @author Stephen
 *
 */

public interface IEditableGameElement {
	
	public void setMyName(String name);

	public String getName();
	
	public ImageView getImageView();

	public void setImageView(ImageView imageView);
	
	public void setMyID(int ID);
	
	public int getMyID();
}

