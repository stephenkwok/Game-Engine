package gui.view;

import java.util.Arrays;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract class to implement JavaFX Buttons
 * 
 * @author AnnieTang
 *
 */
public abstract class ButtonParent extends ObjectObservable implements IGUIElement {
	private static final int ICON_SIZE = 30;
	private static final int PADDING = 10;
	private String buttonText;
	private Button button;
	private String imageName;
	private int iconSize;
	private String myInstanceName;
	private ResourceBundle myResources;
	
	public ButtonParent(String buttonText, String imageName) {
		this.buttonText = buttonText;
		this.imageName = imageName;
		this.iconSize = ICON_SIZE;
		initializeButton();
		myResources =  ResourceBundle.getBundle("buttonInfo");
	}

	/**
	 * Initialize the button.
	 */
	private void initializeButton() {
		button = new Button(buttonText);
		button.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		setButtonIcon();
		setButtonAction();
	}

	/**
	 *
	 * Creates and returns button
	 */
	@Override
	public Node createNode() {
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
	 * Sets the button to notify a controller when clicked
	 */
	public void setClick(){
		button.setOnMouseClicked(e -> {
			setChanged();
			Object[] methodArg = {myResources.getString(myInstanceName), null};
			notifyObservers(Arrays.asList(methodArg));
		});
	}
	
	
	/**
	 * Records the current inherited instance class's name for update purposes 
	 */
	public void setName(String myName){
		myInstanceName = myName;
	}
	
	
	/**
	 * Optional, sets image for button.
	 */
	protected void setButtonIcon() {
		if (imageName != null) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
			ImageView iv = new ImageView(image);
			iv.setFitHeight(iconSize);
			iv.setPreserveRatio(true);
			button.setGraphic(iv);
		}
	}

	/**
	 * Gets the button.
	 * 
	 * @return button.
	 */
	protected Button getButton() {
		return button;
	}

	/**
	 * Sets the button's image's name
	 * 
	 * @param newImageName:
	 *            name of button's new image
	 */
	protected void setImageName(String newImageName) {
		imageName = newImageName;
	}

	/**
	 * Sets the button's icon size
	 * 
	 * @param size:
	 *            size to set button's icon to
	 */
	protected void setIconSize(int size) {
		iconSize = size;
	}

}