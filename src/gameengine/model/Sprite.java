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

	public void addImage(String newImage) {

		if (myImages.contains(DEFAULT_IMAGE_NAME))
			myImages.remove(DEFAULT_IMAGE_NAME);
		myImages.add(newImage);
	}

	public String getCurrentImage() {
		return myImages.get(spriteIndex);
	}

	private void advanceSprite() {
		spriteIndex = (spriteIndex + 1) % myImages.size();
	}

	public String getNextImage() {
		advanceSprite();
		return getCurrentImage();
	}

	public void setImage(String image) {
		if (!myImages.contains(image)) {
			addImage(image);
		}
		spriteIndex = myImages.indexOf(image);
	}

	public void clear() {
		myImages.clear();
	}
}
