package gui.view;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.AlertGenerator;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ComboBoxSpriteImages extends ComboBoxActorImages {
	private static final String ADD = "+";
	private static final double DEFAULT_HEIGHT = 20;
	private HBox sprites;
	private AlertGenerator myAlertGenerator;
	
	public ComboBoxSpriteImages(String promptText, String imageResource, ActorEditingEnvironment aEE, HBox sprites) {
		super(promptText, imageResource, aEE);
		this.sprites = sprites;
		this.myAlertGenerator = new AlertGenerator();
		getComboButton().setText(ADD);
		setClickToDelete();
	}

	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			try {
				Image image = new Image(getClass().getClassLoader().getResourceAsStream(getComboBox().getValue()));
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(DEFAULT_HEIGHT);
				imageView.setPreserveRatio(true);
				((IAuthoringActor) getaEE().getEditable()).addSpriteImage(getComboBox().getValue());
				sprites.getChildren().add(imageView);
				setClickToDelete();
			} catch (Exception e) {
				myAlertGenerator.generateAlert(e.getClass().toString());
			}
		});
	}
	
	/**
	 * Sets the click behavior so they're deleted when clicked.
	 */
	private void setClickToDelete(){
		for(Node child: sprites.getChildren()){
			child.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					sprites.getChildren().remove(child);
				}
			});
		}
	}

}
