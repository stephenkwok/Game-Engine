package gui.view;

import java.io.File;

import authoringenvironment.model.IEditableGameElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import gameengine.controller.Level;
import gui.controller.IScreenController;

/**
 * 
 * @author amyzhao
 *
 */
public class ButtonFileChooserBackgroundImage extends ButtonFileChooser {

	public ButtonFileChooserBackgroundImage(IScreenController myController, String buttonText, String imageName, int buttonWidth, int buttonHeight, Stage stage, IEditableGameElement element) {
		super(myController, buttonText, imageName, buttonWidth, buttonHeight, stage, element);
	}

	@Override
	protected void updateImage(IEditableGameElement element, Image image, File imageFile) {
		((Level) element).setMyImageView(new ImageView(image));
		((Level) element).setMyBackgroundImgName(imageFile.getPath());
	}

}
