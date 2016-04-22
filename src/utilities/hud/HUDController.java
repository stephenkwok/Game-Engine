package utilities.hud;

import java.util.Observable;
import java.util.Observer;

public class HUDController implements Observer{
	
	HUDModel model;
	HUDScreen view;
	
	public void setModel(HUDModel model) {
		this.model = model;
		this.model.addObserver(this);
	}
	
	public void setView(HUDScreen view) {
		this.view = view;
		this.view.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		ValueChange change = (ValueChange) arg;
		model.getData().get(change.getFieldName()).setValue(change.getNewValue());
		view.handleChange(change);
	}

}
