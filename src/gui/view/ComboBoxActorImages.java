package gui.view;

import java.io.File;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.AlertGenerator;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ComboBoxActorImages extends ComboBoxImageCell {
	ActorEditingEnvironment aEE;
	private static final int STANDARD_IMAGE_HEIGHT = 20;
	private AlertGenerator myAlertGenerator;

	public ComboBoxActorImages(String promptText, String imageResource, ActorEditingEnvironment aEE) {
		super(promptText, imageResource, STANDARD_IMAGE_HEIGHT);
		this.aEE = aEE;
		this.myAlertGenerator = new AlertGenerator();
		fillImageNames();
		fillImageMap();
	}

	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(getComboBox().getValue()));
			try {
				aEE.setActorImage(new ImageView(image), getComboBox().getValue());
				notifyObservers((IAuthoringActor) aEE.getEditable());
			} catch (Exception e) {
				myAlertGenerator.generateAlert(e.getClass().toString());
			}
		});
	}

	public void fillImageNames() {
		File imageDir = new File(selectionResource);
		for (File imageFile : imageDir.listFiles()) {
			imageNames.add(imageFile.getName());
		}
	}

	/**
	 * Returns an HBox containing the ImageView and a Label indicating index to
	 * be set as ComboBox icon.
	 */
	@Override
	protected Node getNodeForBox(String item) {
		HBox hbox = new HBox();
		hbox.getChildren().addAll(imageMap.get(item), new Label(" " + item));
		return hbox;
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}
	
	/**
	 * 
	 * @return the Actor Editing Enviroment
	 */
	public ActorEditingEnvironment getActorEditingEnvironment() {
		return aEE;
	}

}
