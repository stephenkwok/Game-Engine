package gui.view;

public class ButtonSplash extends ButtonParent {

	public ButtonSplash(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
//		getButton().setOnMouseClicked(e -> {
//			setChanged();
//			notifyObservers("ButtonSplash");
//		});
	}

}
