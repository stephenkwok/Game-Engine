package gui.view;

public class ButtonSaveGame extends ButtonParent {

	public ButtonSaveGame(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();

	}

	@Override
	protected void setButtonAction() {
	}

}
