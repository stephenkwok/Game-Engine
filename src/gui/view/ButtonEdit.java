package gui.view;

public class ButtonEdit extends ButtonParent{

	public ButtonEdit(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected  void setButtonAction(){
		
	}

}
