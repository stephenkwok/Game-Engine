package gui.view;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * Abstract class to implement JavaFX Buttons
 * @author AnnieTang
 *
 */
public abstract class ButtonParent extends ObjectObservable implements IGUIElement {
	private static final int ICON_SIZE = 30;
	private static final int PADDING = 10;
	private String buttonText;
	private Button button;
	private String imageName;
	
	public ButtonParent(String buttonText, String imageName) {
		this.buttonText = buttonText;
		this.imageName = imageName;
	}
	/**
	 * Creates and returns button
	 */
	@Override
	public Node createNode()  {
		button = new Button(buttonText);
		button.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		setButtonIcon();
		setButtonAction();
		return button; 
	}
	
	public void addNodeObserver(Observer observer) {
		addObserver(observer);
	}
	/**
	 * Sets action when button is pressed. 
	 */
	protected abstract void setButtonAction();
	
	/**
	 * Optional, sets image for button.
	 */
	private void setButtonIcon(){
		if (imageName != null) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
			ImageView iv = new ImageView(image);
			iv.setFitHeight(ICON_SIZE);
			iv.setPreserveRatio(true);
			button.setGraphic(iv);
		}
	}
	
    
    /**
     * Gets the button.
     * @return button.
     */
    protected Button getButton() {
    	return button;
    }
    
	protected void notifyController(Object objToPassToObserver) {
		setChanged();
		notifyObservers(objToPassToObserver);
	}
}