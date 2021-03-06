package authoringenvironment.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.view.AlertGenerator;
import gameengine.model.IRule;

/**
 * Factory to create visual representations of triggers and actions that go into
 * an ActorRule object
 * @author AnnieTang
 *
 */
public class ActorRuleFactory {
	private static final String PACKAGE = "authoringenvironment.view.behaviors.";
	private static final String CLASS = "Class";
	private ResourceBundle myResources;
	private IAuthoringActor myActor;
	private static final String CREATE = "create";
	private static final String ELEMENT = "Element";
	private Controller myController;
	private ActorRule myActorRule;
	private final AlertGenerator myAlertGenerator;

	public ActorRuleFactory(ResourceBundle myLibraryResources, IAuthoringActor myActor, Controller myController,
			ActorRule myActorRule) {
		this.myResources = myLibraryResources;
		this.myActor = myActor;
		this.myController = myController;
		this.myActorRule = myActorRule;
		this.myAlertGenerator = new AlertGenerator();
	}

	/**
	 * Return IAuthoringBehavior with parameter options for given behavior type
	 */
	public IAuthoringBehavior getAuthoringRule(String behaviorType, IRule rule) {
		String className = PACKAGE + myResources.getString(behaviorType + CLASS);
		String elementType = myResources.getString(behaviorType + ELEMENT);
		try {
			Method createMethod = this.getClass().getDeclaredMethod(CREATE + elementType, String.class, String.class, IRule.class);
			return (IAuthoringBehavior) createMethod.invoke(this, behaviorType, className, rule);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			myAlertGenerator.generateAlert(e.getClass().toString());
		} 
		return null;
	}
	/**
	 * Return IAuthoringBehavior that is a SoundAction for given soundName
	 */
	public IAuthoringBehavior getSoundRule(String behaviorType, String soundName){
		try{
			String className = PACKAGE + myResources.getString(behaviorType + CLASS);
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(ActorRule.class, String.class, ResourceBundle.class, String.class, IAuthoringActor.class);
			return (IAuthoringBehavior) constructor.newInstance(myActorRule, behaviorType, myResources,soundName, myActor);
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * Creates a select actor behavior.
	 * @param behaviorType: behavior type.
	 * @param className: name of bheavior's class.
	 * @param rule: rule to add it to.
	 * @return behavior.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IAuthoringBehavior createSelectActorBehavior(String behaviorType, String className, IRule rule)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(IRule.class, ActorRule.class, String.class, ResourceBundle.class, 
					IAuthoringActor.class, List.class);
			List<IAuthoringActor> myActors = new ArrayList<>(myController.getActorMap().keySet());
			return (IAuthoringBehavior) constructor.newInstance(rule, myActorRule, behaviorType, myResources,myActor, 
					myActors);
	}

	/**
	 * Creates a standard actor behavior.
	 * @param behaviorType: behavior type.
	 * @param className: name of bheavior's class.
	 * @param rule: rule to add it to.
	 * @return behavior.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IAuthoringBehavior createStandardActorBehavior(String behaviorType, String className, IRule rule)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try{
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(ActorRule.class, IAuthoringActor.class, String.class,
					ResourceBundle.class);
			return (IAuthoringBehavior) constructor.newInstance(myActorRule, myActor, behaviorType, myResources);
		}catch(Exception e){
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(IRule.class, ActorRule.class, IAuthoringActor.class, String.class, ResourceBundle.class);
			return (IAuthoringBehavior) constructor.newInstance(rule, myActorRule, myActor, behaviorType, myResources);
		}
	}

	/**
	 * Creates a standard behavior.
	 * @param behaviorType: behavior type.
	 * @param className: name of bheavior's class.
	 * @param rule: rule to add it to.
	 * @return behavior.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IAuthoringBehavior createStandardBehavior(String behaviorType, String className, IRule rule)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		try{
			Constructor<?> constructor = clazz.getConstructor(ActorRule.class, String.class, ResourceBundle.class);
			return (IAuthoringBehavior) constructor.newInstance(myActorRule, behaviorType, myResources);
		}catch(Exception e1){
			try{
				Constructor<?> constructor = clazz.getConstructor(IRule.class, ActorRule.class, String.class, ResourceBundle.class);
				return (IAuthoringBehavior) constructor.newInstance(rule, myActorRule, behaviorType, myResources);
			}catch(Exception e2){
				Constructor<?> constructor = clazz.getConstructor(IAuthoringActor.class, IRule.class, ActorRule.class, String.class, ResourceBundle.class);
				return (IAuthoringBehavior) constructor.newInstance(myActor, rule, myActorRule, behaviorType, myResources);
			}
		}
	}
	
}