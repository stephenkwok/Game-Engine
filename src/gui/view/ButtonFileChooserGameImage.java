package gui.view;

import java.io.File;

import authoringenvironment.model.IEditingElement;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ButtonFileChooserGameImage extends ButtonFileChooser {

	public ButtonFileChooserGameImage(String buttonText, String imageName, IEditingElement editor, Stage stage) {
		super(buttonText, imageName, editor, stage);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateImage(IEditingElement editor, Image image, File imageFile) {
		// TODO Auto-generated method stub
		
	}

}
