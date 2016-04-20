package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.behaviors.ResourceOptionsBehavior;
import gameengine.controller.Level;
import gui.view.IGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class ActorRuleFactory {
	private static final String PACKAGE = "authoringenvironment.view.behaviors.";
	private static final String CLASS = "Class";
	private ResourceBundle myResources;
	private IAuthoringActor myActor;
	private List<IAuthoringActor> myActors;
	private List<Level> myLevels;
	
	public ActorRuleFactory(ResourceBundle myLibraryResources, IAuthoringActor myActor, List<IAuthoringActor> myActors, List<Level> myLevels){
		this.myResources = myLibraryResources;
		this.myActor = myActor;
		this.myActors = myActors;
		this.myLevels = myLevels;
	}
	
	/**
	 * Return Node type with parameter options for given behavior type
	 * @param behaviorType
	 * @param value
	 * @return
	 */
	public Node getBehaviorHBox(String behaviorType, String value){
		try{
			String className = PACKAGE + myResources.getString(behaviorType+CLASS);
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(String.class, ResourceBundle.class);
			IGUIElement element = ((IGUIElement) constructor.newInstance(behaviorType,myResources));
			Node toReturn = element.createNode();
			if(value!=null){
				((ResourceOptionsBehavior) element).getComboBox().setValue(value);
			}
			return toReturn;
		}catch(Exception e){
			return getComboBoxHBox(behaviorType, value);
		}
	}
	/**
	 * Return Node type with parameter options for Collision behavior type
	 * @param behaviorType
	 * @param value
	 * @return
	 */
	private Node getComboBoxHBox(String behaviorType, String value){
		String className = PACKAGE + myResources.getString(behaviorType+CLASS);
		try{
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(String.class, IAuthoringActor.class, ResourceBundle.class, List.class);
			return ((IGUIElement) constructor.newInstance(behaviorType,myActor,myResources,myActors)).createNode();
		}catch(Exception e1){
			try{
				Class<?> clazz = Class.forName(className);
				Constructor<?> constructor = clazz.getConstructor(String.class, ResourceBundle.class, List.class);
				return ((IGUIElement) constructor.newInstance(behaviorType,myResources,myLevels)).createNode();
			} catch(Exception e2){
				e2.printStackTrace();
				return new Label(behaviorType);
			}
		}
	}

}
