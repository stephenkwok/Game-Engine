package authoringenvironment.model;

import javafx.scene.image.Image;

/**
 *  The ImageLibrary will contain all images loaded by the user. It will use the singleton design pattern and whenever the user
 *  adds a new image, the ImageLibrary will store a map of filename --> Image, and whenever an actor or level's image is changed,
 *  it can access it from the ImageLibrary rather than reloading the files repeatedly.
 * @author amyzhao
 *
 */

public interface ImageLibrary {

	/**
	 * Loads the image indicated by the filename and adds it to the map as a new entry (filename, image).
	 * @param filename: name of the image file.
	 */
	public void addImage(String filename);
	
	/**
	 * Gets the image matching the filename from the map.
	 * @param filename: name of the image file.
	 * @return image matching the filename.
	 */
	public Image getImage(String filename);
	
}
