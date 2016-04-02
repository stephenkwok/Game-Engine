package authoringenvironment.view;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabImages extends TabParent {
	private static final String IMAGE_RESOURCE = "actorimages";
	private static final int STANDARD_IMAGE_HEIGHT = 20;
	private ObservableList<Label> imageLabels; 
	private List<String> imageNames;
	
	public TabImages(ResourceBundle myResources, String tabText) {
		super(myResources,tabText);
		fillImageNames();
	}
	
	/**
	 * Fills list of actor images ased on which images are in /actorimages file directory.
	 */
	private void fillImageNames(){
		imageNames = new ArrayList<>();
		File imageDir = new File(IMAGE_RESOURCE);
		for(File imageFile: imageDir.listFiles()){
			imageNames.add(imageFile.getName());
		}
	}

	@Override
	void setContent() {
		imageLabels = FXCollections.observableArrayList();
		for(String imageName: imageNames){
			ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName)));
			iv.setFitHeight(STANDARD_IMAGE_HEIGHT);
			iv.setPreserveRatio(true);
			imageLabels.add(new Label(imageName, iv));
		}
		ListView<Label> listView = new ListView<>(imageLabels);
		tab.setContent(listView);
	}

}
