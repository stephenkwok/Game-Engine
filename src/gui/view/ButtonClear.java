package gui.view;

public class ButtonClear extends ButtonParent {

	public ButtonClear(String buttonText, String imageName) {
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