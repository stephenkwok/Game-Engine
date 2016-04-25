package gui.view;

public class ButtonOpenHighScores extends ButtonParent {

	public ButtonOpenHighScores(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> {
			setChanged();
			notifyObservers("ButtonOpenHighScores");
		});

	}

}