package authoringenvironment.view;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
	
	public TabLibraryImages(ResourceBundle myResources, String tabText, ActorRuleCreator myRuleMaker) {
		super(myResources,tabText,myRuleMaker);
		setContent();
	}
	
	@Override
	void setContent() {
		fillFileNames();
		labels = FXCollections.observableArrayList();
		for(String imageName: fileNames){
			ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName)));
			iv.setFitHeight(STANDARD_IMAGE_HEIGHT);
			iv.setPreserveRatio(true);
			Label imageLabel = new Label(imageName, iv); 
			if(myActorRuleCreator!=null){
				setDragEvent(imageLabel);
			}
			labels.add(imageLabel);
		}
		listView = new ListView<>(labels);
	}
	@Override
	Node getContent() {
		return listView;
	}
}
