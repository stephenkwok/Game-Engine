package authoringenvironment.view;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Tab contains ListView of images
 * @author AnnieTang
 *
 */
public class TabLibraryImages extends TabLibrary{
	private static final int STANDARD_IMAGE_HEIGHT = 20;
	private ObservableList<Label> imageLabels; 
	
	public TabLibraryImages(ResourceBundle myResources, String tabText, GUIActorRuleMaker myRuleMaker) {
		super(myResources,tabText,myRuleMaker);
	}

	@Override
	Node getContent() {
		fillFileNames();
		imageLabels = FXCollections.observableArrayList();
		for(String imageName: fileNames){
			ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName)));
			iv.setFitHeight(STANDARD_IMAGE_HEIGHT);
			iv.setPreserveRatio(true);
			Label imageLabel = new Label(imageName, iv); 
			if(myRuleMaker!=null){
				setDragEvent(imageLabel);
			}
			imageLabels.add(imageLabel);
		}
		ListView<Label> listView = new ListView<>(imageLabels);
		return listView;
	}

}
