package authoringenvironment.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public abstract class LabelClickable extends Label {
	
	private static final String IMAGE_TEXT_PADDING = "        ";
	IEditableGameElement myEditable;
	
	public LabelClickable(IEditableGameElement editable) {
		myEditable = editable;
		this.setOnMouseClicked(e -> reactToMouseClicked());
	}
	
	protected abstract void reactToMouseClicked();
	
	protected IEditableGameElement getEditable() {
		return myEditable;
	}
	
	protected void update() {
		this.setText(myEditable.getName());
		ImageView imageView = new ImageView(myEditable.getImage());
		imageView.setFitHeight(50.0);
		imageView.setFitWidth(50);
		Insets insets = new Insets(0.0, 10.0, 10.0, 5.0);
		this.setPadding(insets);
		this.setGraphic(imageView);
	}

}
