package gui.view;

public class ButtonSwitch extends ButtonParent {


	public ButtonSwitch(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
		
	}

}