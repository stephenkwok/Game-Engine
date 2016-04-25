package gui.view;

public class ButtonEdit extends ButtonParent {

	public ButtonEdit(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> {
			setChanged();
			notifyObservers("ButtonEdit");
		});
	}

}
