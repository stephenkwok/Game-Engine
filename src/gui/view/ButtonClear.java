package gui.view;

public class ButtonClear extends ButtonParent {

	public ButtonClear(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
		
	}

}