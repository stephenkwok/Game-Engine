package gui.view;

import java.util.Observable;
import java.util.Observer;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.*;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;
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
		CheckBox checkbox = new CheckBox(myPromptText);
		checkbox.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		checkbox.setPrefWidth(myWidth);
		checkbox.setAlignment(Pos.CENTER_LEFT);
		checkbox.setId(myPromptText);
		setEvent(checkbox);
		return checkbox;
	}
	
	private void setEvent(CheckBox checkbox){
		checkbox.setOnAction(event -> {
			if(checkbox.isSelected()){
				isSelected = true;
			}else{
				isSelected = false;
			}
			System.out.println("checked");
			System.out.println(this.countObservers());
			notifyObservers((IAuthoringActor) aEE.getEditable());
		});
	}
	
	/**
	 * Sets the element that this checkbox is editing.
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
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
	
	public boolean isSelected(){
		return this.isSelected;
	}


}