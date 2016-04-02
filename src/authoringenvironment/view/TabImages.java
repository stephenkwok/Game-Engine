package authoringenvironment.view;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabImages extends TabParent {
	private static final int STANDARD_IMAGE_HEIGHT = 20;
	private ObservableList<Label> imageLabels; 
	public TabImages(ResourceBundle myResources, String tabText) {
		super(myResources,tabText);
	}

	@Override
	void setContent() {
		fillFileNames();
		imageLabels = FXCollections.observableArrayList();
		for(String imageName: fileNames){
			ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName)));
			iv.setFitHeight(STANDARD_IMAGE_HEIGHT);
			iv.setPreserveRatio(true);
			imageLabels.add(new Label(imageName, iv));
		}
		ListView<Label> listView = new ListView<>(imageLabels);
		tab.setContent(listView);
	}

}
