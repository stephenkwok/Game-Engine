package gameengine.model;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public interface IDisplayActor {
	/**
	 * Gets filepath of actor's image 
	 * @return filepath of actor's image
	 */
	public String getMyImageViewName();
	
	/**
	 * Sets filepath for actor's image 
	 * @param myImageViewName
	 */
	public void setMyImageViewName(String myImageViewName);
	
	/**
	 * Gets actor's image
	 * @return an ImageView representing actor's image
	 */
	public ImageView getImageView();

    
}
