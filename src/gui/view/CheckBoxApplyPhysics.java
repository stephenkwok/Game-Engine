package gui.view;

import java.util.Observable;
import java.util.Observer;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import gameengine.model.Actor;
import gameengine.model.Rule;
import gameengine.model.Actions.ApplyPhysics;
import gameengine.model.Triggers.TickTrigger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private static final String APPLY_PHYSICS = "ApplyPhysics";
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
//				System.out.println(((IAuthoringActor) aEE.getEditable()).getRules());
			}else{
				isSelected = false;
//				System.out.println(((IAuthoringActor) aEE.getEditable()).getRules());
			}
			notifyObservers((IAuthoringActor) aEE.getEditable());
		});
	}
	
	private void addApplyPhysics(IAuthoringActor myActor){
		Rule toAdd = new Rule(new TickTrigger(), new ApplyPhysics((Actor) myActor));
		myActor.addRule(toAdd);
		aEE.setEditableElement(myActor);
	}
	
	private void removeApplyPhysics(IAuthoringActor myActor){
		TickTrigger tick = new TickTrigger();
		myActor.getRules().remove(tick.getMyKey());
//		Iterator<Rule> rulesIter = myActor.getRules().get(tick.getMyKey()).iterator();
//		while (rulesIter.hasNext()){
//			Rule myRule = rulesIter.next();
//			if(myRule.getMyAction() instanceof ApplyPhysics){
//				System.out.println(myRule);
//				rulesIter.remove();
//			}
//		}
		aEE.setEditableElement(myActor);
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