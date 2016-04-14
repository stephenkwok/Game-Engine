package gui.view;

import java.io.File;
import gui.controller.IScreenController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * Abstract class to implement JavaFX Buttons
 * @author AnnieTang
 *
 */
public abstract class ButtonParent implements IGUIElement {
	private static final int ICON_SIZE = 30;
	private static final int PADDING = 10;
	private IScreenController myController;
	private String buttonText;
	private Button button;
	private String imageName;
	
	public ButtonParent(IScreenController myController, String buttonText, String imageName) {
		this.myController = myController;
		this.buttonText = buttonText;
		this.imageName = imageName;
	}
	/**
	 * Creates and returns button
	 */
	@Override
	public Node createNode()  {
		button = new Button(buttonText);
		button.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		setButtonIcon();
		setButtonAction();
		return button; 
	}

	/**
	 * Sets action when button is pressed. 
	 */
	protected abstract void setButtonAction();
	
	/**
	 * Optional, sets image for button.
	 */
	private void setButtonIcon(){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
		ImageView iv = new ImageView(image);
        iv.setFitHeight(ICON_SIZE);
        iv.setPreserveRatio(true);
		button.setGraphic(iv);
	}
	
	/**
     * Creates a file picker to get a file name
     * @return returns the file
     */
    protected File promptForFileName(boolean isSaving){
        FileChooser myFileChooser = new FileChooser();
        FileChooser.ExtensionFilter myFilter = new FileChooser.ExtensionFilter("XML Files (.xml)", "*.xml");
        myFileChooser.getExtensionFilters().add(myFilter);
        File fileName;
        if (isSaving){
            fileName = myFileChooser.showSaveDialog(myController.getStage());
        }
        else{
            fileName = myFileChooser.showOpenDialog(myController.getStage());
        }
        return fileName;
    }
    
    protected Button getButton() {
    	return button;
    }
    
    protected IScreenController getController() {
    	return myController;
    }
}