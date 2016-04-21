package gui.view;


public class ButtonRestart extends ButtonParent {

	
	public ButtonRestart(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> {
			setChanged();
			notifyObservers();
		});
		
	}

}
