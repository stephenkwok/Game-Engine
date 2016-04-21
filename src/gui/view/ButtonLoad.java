package gui.view;

/**
 * Button to load game.
 * @author AnnieTang
 *
 */
public class ButtonLoad extends ButtonParent {
	
	public ButtonLoad(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(event -> {
			notifyController("ButtonLoad");
		});
	}

	/**
     * Sets workspace preferences to those specified by the given XML. 
     */
    private void loadProperties() {
    	notifyController(null);
    }

}
