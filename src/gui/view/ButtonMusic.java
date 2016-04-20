package gui.view;

public class ButtonMusic extends ButtonParent{
	

	public ButtonMusic(String buttonText, String imageName) {
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
