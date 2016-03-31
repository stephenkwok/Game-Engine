package authoringenvironment.view;

import java.io.File;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
//import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
/**
 * Pair of save/load buttons so the user can load an XML to define the workspace, or save the current workspace. 
 * @author AnnieTang
 *
 */
public class SaveLoad implements IGUIElement {
	private ResourceBundle myResources;
	private Controller mainScreen;
	
	private static final int PADDING = 10;

	public SaveLoad(ResourceBundle myResources, Controller mainScreen) {
		this.myResources = myResources;
		this.mainScreen = mainScreen;
	}
	/**
	 * Returns two buttons for saving and loading XML.
	 */
	@Override
	public Node createNode() {
		VBox myBox = new VBox(PADDING);
		myBox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		
		Button saveButton = new Button(myResources.getString("Save"));
		saveButton.setOnAction(e -> mainScreen.saveGame(promptForFileName(true)));
		
		Button loadButton = new Button(myResources.getString("Load"));
		loadButton.setOnAction(e -> loadProperties());
		
		myBox.getChildren().addAll(saveButton, loadButton);
		
		return myBox;
	}

    /**
     * Creates a file picker to get a file name
     * @return returns the file
     */
    private File promptForFileName(boolean isSaving){
        FileChooser myFileChooser = new FileChooser();
        FileChooser.ExtensionFilter myFilter = new FileChooser.ExtensionFilter("XML Files (.xml)", "*.xml");
        myFileChooser.getExtensionFilters().add(myFilter);
        File fileName;
        if (isSaving){
            fileName = myFileChooser.showSaveDialog(mainScreen.getStage());
        }
        else{
            fileName = myFileChooser.showOpenDialog(mainScreen.getStage());
        }
        return fileName;
    }
    
    /**
     * Sets workspace preferences to those specified by the given XML. 
     */
    private void loadProperties() {
    	//TODO:
    }
    
    /**
     * Converts image name to Image. 
     * @param imageString
     * @return
     */
//    private Image stringToImage(String imageString){
//    	return new Image(getClass().getClassLoader().getResourceAsStream(imageString));
//    }
//    /**
//     * Converts color String to a JavaFX Color. 
//     * @param colorString
//     * @return
//     */
//    private Color stringToColor(String colorString) {
//		String[] rgb = colorString.split(" ");
//		return Color.rgb((int) Double.parseDouble(rgb[0]),
//				(int) Double.parseDouble(rgb[1]), (int) Double.parseDouble(rgb[2]));
//	}

	@Override
	public void updateNode() {
	}

}
