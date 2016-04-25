package gui.view;


public class ButtonRestart extends ButtonParent {

	
	public ButtonRestart(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
		
	}

}
