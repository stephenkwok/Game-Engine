package gui.view;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import authoringenvironment.model.IEditingElement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author amyzhao, AnnieTang
 *
 */
public abstract class ButtonFileChooser extends ButtonParent {
	private static final String EXTENSION_FILTER_DESCRIPTION = "Image Files (.jpg, .png .gif)";
	private static final String EXTENSIONS = "*.jpg *.png *.gif";
	private IEditingElement myEditor;
	private Stage myStage;

	/**
	 * Creates an image setting button.
	 * 
	 * @return a button whose action sets the image.
	 */
	public ButtonFileChooser(String buttonText, String imageName, IEditingElement editor, Stage stage) {
		super(buttonText, imageName);
		myEditor = editor;
		myStage = stage;
	}

	protected void setButtonAction() {
		getButton().setOnAction(event -> {
			try {
				loadSelectedImage();
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(e.getClass().toString());
			}
		});
	}

	/**
	 * Loads the selected image from the file selected by the user.
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void loadSelectedImage() {
		File imageFile = promptForFileName();
		if (imageFile != null) {
			Image image = new Image(imageFile.toURI().toString());
			updateImage(myEditor, image, imageFile);
		}
	}

	protected abstract void updateImage(IEditingElement editor, Image image, File imageFile);

	/**
	 * Creates a file picker to get a file name
	 * 
	 * @return returns the file
	 */
	private File promptForFileName() {
		FileChooser myFileChooser = new FileChooser();
		List<String> extensions = Arrays.asList(EXTENSIONS.split(" "));
		FileChooser.ExtensionFilter myFilter = new FileChooser.ExtensionFilter(EXTENSION_FILTER_DESCRIPTION,
				extensions);
		myFileChooser.getExtensionFilters().add(myFilter);
		return myFileChooser.showOpenDialog(myStage);
	}
}
