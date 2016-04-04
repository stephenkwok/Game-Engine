package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class LabelClickable extends Label {
	
	private static final String IMAGE_TEXT_PADDING = "        ";
	IEditableGameElement myEditable;
	IEditingEnvironment myEnvironment;
	Controller controller;
	
	public LabelClickable(IEditableGameElement editable, IEditingEnvironment environment, Controller controller) {
		this.myEditable = editable;
		this.controller = controller;
		this.myEnvironment = environment;
		this.setOnMouseClicked(e -> reactToMouseClicked());
	}
	
	private void reactToMouseClicked() {
		controller.goToEditingEnvironment(myEditable, myEnvironment);
	}
	
	protected IEditableGameElement getEditable() {
		return myEditable;
	}
	
	protected void update() {
		this.setText(myEditable.getName());
		ImageView imageView = new ImageView(myEditable.getImage());
		// HARD-CODED VALUES
		imageView.setFitHeight(50.0);
		imageView.setFitWidth(50);
		Insets insets = new Insets(0.0, 10.0, 10.0, 5.0);
		this.setPadding(insets);
		this.setGraphic(imageView);
	}

}
