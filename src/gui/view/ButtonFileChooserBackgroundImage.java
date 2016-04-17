package gui.view;

import java.io.File;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.LevelEditingEnvironment;
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

	public ButtonFileChooserBackgroundImage(IScreenController myController, String buttonText, String imageName, int buttonWidth, int buttonHeight, Stage stage, IEditingEnvironment editor) {
		super(myController, buttonText, imageName, buttonWidth, buttonHeight, stage, editor);
	}

	@Override
	protected void updateImage(IEditingEnvironment editor, Image image, File imageFile) {
		((LevelEditingEnvironment) editor).changeBackgroundImage(image, imageFile);
	}

}
