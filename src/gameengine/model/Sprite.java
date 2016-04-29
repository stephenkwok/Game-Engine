package gameengine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author blakekaplan
 */
public class Sprite {

	private static final String DEFAULT_IMAGE_NAME = "hellokitty.gif";
	private List<String> myImages;
	private int spriteIndex;

	public Sprite() {
		myImages = new ArrayList<>();
		myImages.add(DEFAULT_IMAGE_NAME);
		spriteIndex = 0;
	}

	public Sprite(String singleImage) {
		myImages = new ArrayList<>();
		myImages.add(singleImage);
	}

	public Sprite(List<String> images) {
		myImages = images;
	}

	/**
	 * Adds a new image to the Actor's Sprite
	 * 
	 * @param newImage
	 *            The image to add
	 */
	public void addImage(String newImage) {

		if (myImages.contains(DEFAULT_IMAGE_NAME))
			myImages.remove(DEFAULT_IMAGE_NAME);
		myImages.add(newImage);
	}

	/**
	 * Gets the current Sprite image
	 * 
	 * @return The current Sprite image
	 */
	public String getCurrentImage() {
		return myImages.get(spriteIndex);
	}

	/**
	 * Advances the Sprite by one image and sets the Sprite index accordingly
	 */
	private void advanceSprite() {
		spriteIndex = (spriteIndex + 1) % myImages.size();
	}

	/**
	 * Gets the image string for the next Sprite image
	 * 
	 * @return The string for the next Sprite image
	 */
	public String getNextImage() {
		advanceSprite();
		return getCurrentImage();
	}

	/**
	 * Sets the Sprite image to a provided image
	 * @param image	The provided image
	 */
	public void setImage(String image) {
		if (!myImages.contains(image)) {
			addImage(image);
		}
		spriteIndex = myImages.indexOf(image);
	}

	/**
	 * Clears the Sprite
	 */
	public void clear() {
		myImages.clear();
	}

	/**
	 * Gets the Sprite images
	 * @return
	 */
	public List<String> getMyImages() {
		return this.myImages;
	}
}
