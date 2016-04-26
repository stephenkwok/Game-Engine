package gui.view;

public class ButtonMusic extends ButtonParent {

	public ButtonMusic(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
	}

}
