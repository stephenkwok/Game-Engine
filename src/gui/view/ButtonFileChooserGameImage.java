package gui.view;

import java.io.File;

import authoringenvironment.model.IEditingElement;
import authoringenvironment.view.GameAttributesDisplay;
import gameengine.controller.GameInfo;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.stage.Stage;

/**
 * This class extends the ButtonFileChooser class and allows the author to
 * upload an image to be used as the Game's preview image. The button's icon
 * will also be updated to reflect the new image.
 * 
 * @author Stephen
 *
 */

public class ButtonFileChooserGameImage extends ButtonFileChooser {

	private static final String BUTTON_TEXT = "Current Game Preview Image\n(Click to Load New Image)";
	private static final int BUTTON_ICON_SIZE = 50;

	public ButtonFileChooserGameImage(String imageName, IEditingElement editor, Stage stage) {
		super(BUTTON_TEXT, imageName, editor, stage);
		setIconSize(BUTTON_ICON_SIZE);
		setButtonIcon();
	}

	/**
	 * 
	 * @param editor:
	 *            Editing Element
	 * @param image
	 * @param imageFile
	 */
	@Override
	protected void updateImage(IEditingElement editor, Image image, File imageFile) {
		GameInfo myGameInfo = ((GameAttributesDisplay) editor).getGameInfo();
		String imageName = imageFile.getPath();
		myGameInfo.setImageView(new ImageView(image));
		myGameInfo.setMyImageName(imageName);
		setImageName(imageName);
		setButtonIcon();
	}

}
