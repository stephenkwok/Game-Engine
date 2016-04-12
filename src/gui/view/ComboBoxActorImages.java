package gui.view;

import java.io.File;

import authoringenvironment.view.GUIActorEditingEnvironment;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ComboBoxActorImages extends ComboBoxImageCell{
	GUIActorEditingEnvironment aEE;
	private static final int STANDARD_IMAGE_HEIGHT = 20;
	
	public ComboBoxActorImages(String promptText, String imageResource, GUIActorEditingEnvironment aEE) {
		super(promptText,imageResource, STANDARD_IMAGE_HEIGHT);
		this.aEE = aEE;
		fillImageNames();
		fillImageMap();
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event->{
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(comboBox.getValue()));
			try {
				aEE.setActorImage(new ImageView(image));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public void fillImageNames(){
		File imageDir = new File(selectionResource);
		for(File imageFile: imageDir.listFiles()){
			imageNames.add(imageFile.getName());
		}
	}

	/**
	 * Returns an HBox containing the ImageView and a Label indicating index to be set as ComboBox icon.
	 */
	@Override
	protected Node getNodeForBox(String item){
		HBox hbox = new HBox();
		hbox.getChildren().addAll(imageMap.get(item), new Label(" " + item));
		return hbox;
	}

}
