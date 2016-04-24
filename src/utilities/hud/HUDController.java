package utilities.hud;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class HUDController implements Observer{
	
	HUDModel model;
	HUDScreen view;
	
	public void grabData(Collection<String> fieldsToObserve) {
		setModel(new HUDModel());
		IValueFinder valueFinder = new ValueFinder(this);
		for (String field : fieldsToObserve) {
			model.getData().put(field, valueFinder.find(field));
		}
		setView(new HUDScreen(model.getData()));
	}
	
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
		model.handleChange(change);
		view.handleChange(change);
	}

}
