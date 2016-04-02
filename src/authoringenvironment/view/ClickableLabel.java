package authoringenvironment.view;

import authoringenvironment.model.IEditableGameElement;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public abstract class ClickableLabel extends Label {
	
	IEditableGameElement myEditable;
	
	public ClickableLabel(IEditableGameElement editable) {
		myEditable = editable;
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
