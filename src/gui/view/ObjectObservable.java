package gui.view;

import java.util.Observable;

/**
 * This abstract class overrides Observable's notifyObservers() method so
 * that setChanged() is implicitly called when subclasses call notifyObservers()
 * 
 * @author Stephen
 *
 */

public abstract class ObjectObservable extends Observable {

	/**
	 * Sets the Observable's hasChanged value to true and notifies all Observers
	 */
	@Override
	public void notifyObservers(Object objectToPassToObserver) {
		setChanged();
		super.notifyObservers(objectToPassToObserver);
	}
	
}
