package authoringenvironment.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public abstract class LabelClickable extends Label {
	
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
		this.setGraphic(new ImageView(myEditable.getImage()));
	}

}
