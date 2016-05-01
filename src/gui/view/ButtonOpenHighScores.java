package gui.view;

public class ButtonOpenHighScores extends ButtonParent{

	public ButtonOpenHighScores(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {

	}
	

}