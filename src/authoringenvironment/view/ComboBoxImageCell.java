package authoringenvironment.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import authoringenvironment.controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
/**
 * ComboBox with list of images that the user can choose from to modify the given Object.
 * @author AnnieTang
 *
 */
public abstract class ComboBoxImageCell extends GUIComboBox {
	private Map<String, ImageView> imageMap;
	private List<String> imageNames;
	private static final int STANDARD_IMAGE_HEIGHT = 20;
	private static final String IMAGE_RESOURCE = "Images";
	
	public ComboBoxImageCell(ResourceBundle myResources, String promptText, Controller mainScreen) {
		super(myResources, promptText, mainScreen);
		imageMap = new HashMap<>();
		imageNames = new ArrayList<>();
		fillImageNames();
		fillImageMap();
	}
	/**
	 * Sets default image palette based on which images are in /Images file directory.
	 */
	private void fillImageNames(){
		File imageDir = new File(IMAGE_RESOURCE);
		for(File imageFile: imageDir.listFiles()){
			imageNames.add(imageFile.getName());
		}
	}
	/**
	 * Maps image name String to its ImageView. 
	 */
	private void fillImageMap(){
		for(String imageName: imageNames){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
			ImageView imageView = new ImageView(image);
	        imageView.setPreserveRatio(true);
	        imageView.setFitHeight(STANDARD_IMAGE_HEIGHT);
			imageMap.put(imageName, imageView);
		}
	}
	/**
	 * Returns an HBox containing the ImageView and a Label indicating index to be set as ComboBox icon.
	 */
	@Override
	protected Node getNodeForBox(String item){
        HBox hbox = new HBox();
        hbox.getChildren().addAll(new Label(options.indexOf(item) + " "), imageMap.get(item));
        return hbox;
	}
	
	/**
	 * On comboButton click, turtle ImageViews will be updated with new Image. 
	 */
	@Override
	abstract void setButtonAction();
	/**
	 * Return current list of image names. 
	 */
	@Override
	protected List<String> getOptionsList() {
		return imageNames;
	}
}
