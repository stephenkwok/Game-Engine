package gui.view;

public class ButtonSound extends ButtonParent{

	public ButtonSound(String buttonText, String imageName) {
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
