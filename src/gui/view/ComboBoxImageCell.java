package gui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ComboBox with list of images from the folder specified by the image resource
 * given.
 * 
 * @author AnnieTang
 *
 */
public abstract class ComboBoxImageCell extends ComboBoxParent {
	protected Map<String, ImageView> imageMap;
	protected List<String> imageNames;
	protected int boxHeight;
	protected String selectionResource;

	public ComboBoxImageCell(String promptText, String imageResource, int boxHeight) {
		super(promptText);
		this.selectionResource = imageResource;
		this.boxHeight = boxHeight;
		imageMap = new HashMap<>();
		imageNames = new ArrayList<>();
	}

	/**
	 * Sets default image palette based on which images are in /Images file
	 * directory.
	 */
	public abstract void fillImageNames();

	/**
	 * Maps image name String to its ImageView.
	 */
	public void fillImageMap() {
		for (String imageName : imageNames) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
			ImageView imageView = new ImageView(image);
			imageView.setPreserveRatio(true);
			imageView.setFitHeight(boxHeight);
			imageMap.put(imageName, imageView);
		}
	}

	/**
	 * Returns an HBox containing the ImageView and a Label indicating index to
	 * be set as ComboBox icon.
	 */
	@Override
	protected abstract Node getNodeForBox(String item);

	/**
	 * On comboButton click, turtle ImageViews will be updated with new Image.
	 */
	@Override
	public abstract void setButtonAction();

	/**
	 * Return current list of image names.
	 */
	@Override
	public List<String> getOptionsList() {
		return imageNames;
	}
}
