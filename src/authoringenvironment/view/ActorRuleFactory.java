package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.behaviors.IAuthoringBehavior;
//import gameengine.controller.Level;
/**
 * Factory to create visual representations of triggers and actions that go into an ActorRule object
 * @author AnnieTang
 *
 */
public class ActorRuleFactory {
	private static final String PACKAGE = "authoringenvironment.view.behaviors.";
	private static final String CLASS = "Class";
	private ResourceBundle myResources;
	private IAuthoringActor myActor;
//	private Map<IAuthoringActor, List<IAuthoringActor>> myActorsMap;
//	private List<Level> myLevels;
	private static final String CREATE = "create";
	private static final String ELEMENT = "Element";
	private Controller myController;
	private ActorRule myActorRule;
	
	public ActorRuleFactory(ResourceBundle myLibraryResources, IAuthoringActor myActor, Controller myController, ActorRule myActorRule){
		this.myResources = myLibraryResources;
		this.myActor = myActor;
		this.myController = myController;
		this.myActorRule = myActorRule;
//		this.myActorsMap = myActorsMap;
//		this.myLevels = myLevels;
	}
	
	/**
	 * Return Node type with parameter options for given behavior type
	 * @param behaviorType
	 * @param value
	 * @return
	 */
	public IAuthoringBehavior getAuthoringRule(String behaviorType, String value){ 
		String className = PACKAGE + myResources.getString(behaviorType+CLASS);
		String elementType = myResources.getString(behaviorType + ELEMENT);
		try{
			Method createMethod = this.getClass().getDeclaredMethod(CREATE + elementType, String.class, String.class);
			return (IAuthoringBehavior) createMethod.invoke(this, behaviorType, className);
		}catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Return ComboBox IAuthoringRule type with parameter options for Collision behavior type
	 * @param behaviorType
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IAuthoringBehavior createCollisionBehavior(String behaviorType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		try{
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(ActorRule.class, String.class, IAuthoringActor.class, ResourceBundle.class, List.class);
			List<IAuthoringActor> myActors = new ArrayList<>(myController.getActorMap().keySet());
			return (IAuthoringBehavior) constructor.newInstance(myActorRule, behaviorType,myActor,myResources,myActors);
		}catch(Exception e){
			e.printStackTrace();
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(ActorRule.class, String.class, ResourceBundle.class, List.class);
			return (IAuthoringBehavior) constructor.newInstance(myActorRule, behaviorType,myResources,myController.getLevels());
		}
	}
	/**
	 * Return Label IAuthoringRule type with parameter options for behavior type
	 * @param behaviorType
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IAuthoringBehavior createLabelBehavior(String behaviorType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(ActorRule.class, IAuthoringActor.class, String.class, ResourceBundle.class);
		return (IAuthoringBehavior) constructor.newInstance(myActorRule, myActor,behaviorType,myResources);
	}

	private IAuthoringBehavior createComboBoxBehavior(String behaviorType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(ActorRule.class, String.class, ResourceBundle.class);
		return (IAuthoringBehavior) constructor.newInstance(myActorRule,behaviorType,myResources);
	}
	
	private IAuthoringBehavior createChangeAttributeBehavior(String behaviorType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return createLabelBehavior(behaviorType, className);
	}
	
	private IAuthoringBehavior createTickBehavior(String behaviorType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return createComboBoxBehavior(behaviorType, className);
	}
}
