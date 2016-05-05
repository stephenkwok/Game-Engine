package gui.view;

import java.io.File;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.IEditingElement;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Button that opens file chooser to change background image.
 * @author amyzhao
 *
 */
public class ButtonFileChooserBackgroundImage extends ButtonFileChooser {

	/**
	 * Constructs a ButtonFileChooserBackgroundImage
	 * @param buttonText: text to show on button.
	 * @param imageName: name of image.
	 * @param editor: editing environment.
	 * @param stage: stage to open file chooser on.
	 */
	public ButtonFileChooserBackgroundImage(String buttonText, String imageName, IEditingElement editor, Stage stage) {
		super(buttonText, imageName, editor, stage);
	}

	@Override
	protected void updateImage(IEditingElement editor, Image image, File imageFile) {
		((LevelEditingEnvironment) editor).changeBackgroundImage(image, imageFile);
		notifyObservers();
	}

}
