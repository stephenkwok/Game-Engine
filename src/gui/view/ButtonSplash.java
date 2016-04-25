package gui.view;

public class ButtonSplash extends ButtonParent {

	public ButtonSplash(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> {
			setChanged();
			notifyObservers("ButtonSplash");
		});
	}

}
