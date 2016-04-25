package gui.view;

public class ButtonSwitch extends ButtonParent {

	public ButtonSwitch(String buttonText, String imageName) {
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