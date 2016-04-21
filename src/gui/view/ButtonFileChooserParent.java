package gui.view;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import authoringenvironment.model.IEditingElement;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author amyzhao, AnnieTang
 *
 */
public abstract class ButtonFileChooserParent extends ButtonParent {
	private static final String EXTENSION_FILTER_DESCRIPTION = "Image Files (.jpg, .png .gif)";
	private static final String EXTENSIONS = "*.jpg *.png *.gif";
	private IEditingElement myEditor;
	private File myImageFile;
	private Image myImage;
	
	/**
	 * Creates an image setting button.
	 * @return a button whose action sets the image.
	 */
	public ButtonFileChooserParent(String buttonText, String imageName, Stage stage){
		super(buttonText, imageName);
	}
	
	protected abstract void makeUpdates();
	
	protected void setButtonAction() {
		getButton().setOnAction(event->{
			try {
				loadSelectedImage();
				makeUpdates();
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
	private void loadSelectedImage() {
//		myImageFile = promptForFileName();
		if(myImageFile!=null){
			myImage = new Image(myImageFile.toURI().toString());
		}
	}
	
//	/**
//     * Creates a file picker to get a file name
//     * @return returns the file
//     */
//    private File promptForFileName(){
//        FileChooser myFileChooser = new FileChooser();
//        List<String> extensions = Arrays.asList(EXTENSIONS.split(" "));
//        FileChooser.ExtensionFilter myFilter = new FileChooser.ExtensionFilter(EXTENSION_FILTER_DESCRIPTION, extensions);
//        myFileChooser.getExtensionFilters().add(myFilter);
//        return myFileChooser.showOpenDialog(myEditor.getStage());
//    }
    
    private File getMyImageFile() {
    	return myImageFile;
    }
    
    private Image getMyImage() {
    	return myImage;
    }
    
}
