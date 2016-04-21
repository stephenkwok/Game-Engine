package gui.view;


public class ButtonSaveGame extends ButtonParent {


	public ButtonSaveGame(String buttonText, String imageName) {
		super(buttonText, imageName);

	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> {
			setChanged();
			notifyObservers();
		});
		
	}

}
