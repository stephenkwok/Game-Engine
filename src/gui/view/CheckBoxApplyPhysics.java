package gui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.*;
import gameengine.model.Actor;
import gameengine.model.Rule;
import gameengine.model.Actions.ApplyPhysics;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

/**
 * Checkbox object for ApplyPhysics option selection.
 * 
 * @author AnnieTang
 *
 */
public class CheckBoxApplyPhysics extends Observable implements IGUIElement, IEditingElement {
	private static final int PADDING = 10;
	private String myPromptText;
	private int myWidth;
	private IEditableGameElement myEditableElement;
	private ActorEditingEnvironment aEE;
	private boolean isSelected;
	private CheckBox myCheckBox;

	/**
	 * Constructs a CheckBoxObject to edit a given element.
	 * @param promptText: option to check or un-check.
	 * @param width: width of the checkbox.
	 */
	public CheckBoxApplyPhysics(String promptText, int width, ActorEditingEnvironment aEE) {
		myPromptText = promptText;
		myWidth = width;
		this.aEE = aEE;
		this.isSelected = false;
	}

	/**
	 * Creates the checkbox.
	 */
	@Override
	public Node createNode() {
		myCheckBox = new CheckBox(myPromptText);
		myCheckBox.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		myCheckBox.setPrefWidth(myWidth);
		myCheckBox.setAlignment(Pos.CENTER_LEFT);
		myCheckBox.setId(myPromptText);
		setEvent(myCheckBox);
		return myCheckBox;
	}
	
	private void setEvent(CheckBox checkbox){
		checkbox.setOnAction(event -> {
			if(checkbox.isSelected()){
				isSelected = true;
			}else{
				isSelected = false;
			}
			setChanged();
			notifyObservers((IAuthoringActor) aEE.getEditable());
		});
	}
	
	/**
	 * Sets the element that this checkbox is editing.
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
		Actor thisActor = (Actor) myEditableElement;
		if (thisActor.getRules().containsKey("Tick")) {
			List<Rule> tickRules = thisActor.getRules().get("Tick");
			List<Class<?>> tickActionsClasses = new ArrayList<>();
			tickRules.forEach(rule -> tickActionsClasses.add(rule.getMyAction().getClass()));
			if (tickActionsClasses.contains(ApplyPhysics.class)) {
				myCheckBox.setSelected(true);
			}
		}
	}

	/**
	 * Gets the element that this checkbox is editing.
	 * 
	 * @return game/level/actor that this checkbox is editing.
	 */
	protected IEditableGameElement getEditableElement() {
		return myEditableElement;
	}

	@Override
	public void addNodeObserver(Observer observer) {
		this.addObserver(observer);
	}
	
	/**
	 * Checks if the checkbox has been checked.
	 * @return true if checkbox checked; false o.w.
	 */
	public boolean isSelected(){
		return myCheckBox.isSelected();
	}

}