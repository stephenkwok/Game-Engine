package gui.view;

import java.io.File;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.IEditingElement;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author amyzhao
 *
 */
public class ButtonFileChooserBackgroundImage extends ButtonFileChooser {

	public ButtonFileChooserBackgroundImage(String buttonText, String imageName, IEditingElement editor, Stage stage) {
		super(buttonText, imageName, editor, stage);
	}

	@Override
	protected void updateImage(IEditingElement editor, Image image, File imageFile) {
		((LevelEditingEnvironment) editor).changeBackgroundImage(image, imageFile);
	}

}
