package gui.view;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import authoringenvironment.model.IEditableGameElement;
import gui.controller.IScreenController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private Stage myStage;
	private IEditableGameElement myElement;
	/**
	 * Creates an image setting button.
	 * @return a button whose action sets the image.
	 */
	public ButtonFileChooser(IScreenController myController, String buttonText, String imageName, int buttonWidth, int buttonHeight, Stage stage, IEditableGameElement element){
		super(myController, buttonText, imageName);
		myStage = stage;
		myElement = element;
	}
	
	protected void setButtonAction() {
		getButton().setOnAction(event->{
			try {
				loadSelectedImage(myElement);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	/**
	 * Loads the selected image from the file selected by the user.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void loadSelectedImage(IEditableGameElement element) {
		File imageFile = promptForFileName();
		if(imageFile!=null){
			Image image = new Image(imageFile.toURI().toString());
			updateImage(element, image, imageFile);
		}
	}
	
	protected abstract void updateImage(IEditableGameElement element, Image image, File imageFile);
	
	/**
     * Creates a file picker to get a file name
     * @return returns the file
     */
    private File promptForFileName(){
        FileChooser myFileChooser = new FileChooser();
        List<String> extensions = Arrays.asList(EXTENSIONS.split(" "));
        FileChooser.ExtensionFilter myFilter = new FileChooser.ExtensionFilter(EXTENSION_FILTER_DESCRIPTION, extensions);
        myFileChooser.getExtensionFilters().add(myFilter);
        return myFileChooser.showOpenDialog(myStage);
    }
}
