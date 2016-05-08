// This entire file is part of my masterpiece.
// Blake Kaplan

/*
 * This class is responsible for the animation functionality in an Actor.
 * At a foundational level, an animation is just a series of images changing at high speeds.
 * I tried to mirror that functionality with this Sprite class. This class holds a list of
 * image names through which the Sprite rotates. The sprite index is determined based on the
 * previous sprite index and the current number of Sprite images.
 * 
 * I think that this class is well designed because it provides functionality
 * to the Actor via composition. At any given moment, the Actor only knows which image it currently
 * needs to display. That image is determined by the Sprite and passed on to the Actor.
 * The logic for selecting the image is entirely encapsulated within the Actor's Sprite object.
 * Whenever it wants to switch images, it just tells the Sprite to move to the next one and
 * the Sprite handles all additional logic.
 */

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
	 * Provides the Sprite's images
	 * 
	 * @return The Sprite's images
	 */
	public List<String> getMyImages() {
		return this.myImages;
	}
}
