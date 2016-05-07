package authoringenvironment.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.view.AlertGenerator;
import gameengine.model.*;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.Trigger;
import javafx.scene.image.*;

/**
 * Actor copying class
 * @author amyzhao, colette
 *
 */
public class ActorCopier {
	private static final String RESOURCE = "ruleCreator";
	private static final String DELIMITER = ",";
	private Actor myReferenceActor;
	@XStreamOmitField
	private ResourceBundle myResources;
	private ActionFactory myActionFactory;
	private TriggerFactory myTriggerFactory;

	/**
	 * Creates an ActorCopier with no reference actor to copy yet.
	 */
	public ActorCopier() {
		myReferenceActor = null;
		init();
	}

	/**
	 * Creates an ActorCopier with a reference actor to copy.
	 * @param actor
	 */
	public ActorCopier(Actor actor) {
		myReferenceActor = actor;
		init();
	}

	/**
	 * Initializes the ActorCopier.
	 */
	public void init() {
		myResources = ResourceBundle.getBundle(RESOURCE);
		myActionFactory = new ActionFactory();
		myTriggerFactory = new TriggerFactory();
	}
	
	/**
	 * Sets the reference actor to copy.
	 * @param newReference: reference actor to copy.
	 */
	public void setReferenceActor(Actor newReference) {
		myReferenceActor = newReference;
	}

	/**
	 * Gets the reference actor.
	 * @return reference actor.
	 */
	public IAuthoringActor getReferenceActor() {
		return myReferenceActor;
	}

	/**
	 * Makes a copy if the reference actor has already been set.
	 * @return copy of actor.
	 */
	public Actor makeCopy() {
		Actor actor = new Actor();
		if (myReferenceActor != null) {
			copyActor(actor, myReferenceActor);
			return actor;
		} else {
			return null;
		}
	}

	/**
	 * Copies the actor.
	 * @param toUpdate: new actor.
	 * @param toCopy: actor to copy.
	 */
	public void copyActor(Actor toUpdate, Actor toCopy) {
		toUpdate.setName(toCopy.getName());
		toUpdate.setFriction(toCopy.getFriction());
		toUpdate.setImageViewName(toCopy.getImageViewName());
		toUpdate.setImageView(new ImageView(new Image(toCopy.getImageViewName())));
		toUpdate.setSize(toCopy.getSize());
		toUpdate.setID(toCopy.getID());
		toUpdate.setRotate(toCopy.getRotate());
		toUpdate.setOpacity(toCopy.getOpacity());
		toUpdate.setScaleX(toCopy.getScaleX());
		toUpdate.setScaleY(toCopy.getScaleY());
		copyStates(toUpdate, toCopy);
		toUpdate.setSprite(toCopy.getSprite());
		copyRules(toUpdate, toCopy.getRules());
		copyAttributes(toUpdate, toCopy.getAttributeMap());
	}

	/**
	 * Copies the reference actor's rules.
	 * @param toUpdate: actor to update.
	 * @param rulesToCopy: rules from reference actor.
	 */
	private void copyRules(Actor toUpdate, Map<String, List<IRule>> rulesToCopy) {
		toUpdate.getRules().clear();
		for (String trigger : rulesToCopy.keySet()) {
			List<IRule> toAdd = rulesToCopy.get(trigger);
			for (int i = 0; i < toAdd.size(); i++) {
					try {
						IRule oldRule = toAdd.get(i);
						Trigger oldTrigger = oldRule.getMyTrigger();
						Action oldAction = oldRule.getMyAction();
						
						Trigger triggerToAdd = createTrigger(oldTrigger, toUpdate);
						Action actionToAdd = createAction(oldAction,toUpdate);
						IRule rule = new Rule(triggerToAdd, actionToAdd);
						toUpdate.addRule(rule);
						
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
							| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						AlertGenerator alert = new AlertGenerator();
						alert.generateAlert(e.getClass().toString());
					}
			}
		}
	}
	
	/**
	 * Creates either a trigger or an action.
	 * @param object: trigger or action to create.
	 * @param arguments: arguments to add into the trigger or action.
	 * @return trigger or action.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Object createObject(Object object, Object[] arguments) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class myclass = object.getClass();
		Class[] argumentTypes = new Class[arguments.length];
		for(int i=0; i<argumentTypes.length;i++){
			if((myclass.getSuperclass() == Trigger.class || (myclass.getSuperclass() == Action.class && i==0)) && arguments[i].getClass() == Actor.class){
				argumentTypes[i] = IGameElement.class;	
			}else{
				argumentTypes[i] = arguments[i].getClass();
			}
		}
		Constructor constructor = myclass.getConstructor(argumentTypes);
		return constructor.newInstance(arguments);
	}
	
	/**
	 * Create a copy of a trigger.
	 * @param trigger: trigger to copy.
	 * @param toUpdate: actor to update.
	 * @return copy of trigger specific to the actor to update.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Trigger createTrigger(Trigger trigger, Actor toUpdate) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] params = trigger.getParameters();
		if(params[0].getClass().equals(Actor.class)&&((Actor)params[0]).getID()==toUpdate.getID()){
			params[0] = toUpdate;
		}
		return (Trigger) createObject(trigger,params);
	}
	
	/**
	 * Create a copy of action.
	 * @param action: action to copy.
	 * @param toUpdate: actor to update.
	 * @return copy of action specific to the actor to update.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Action createAction(Action action, Actor toUpdate) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] params = action.getParameters();
		params[0] = toUpdate;
		return (Action) createObject(action,params);
	}

	/**
	 * Copies an actor's attributes.
	 * @param toUpdate: actor to update.
	 * @param attributeMap: attribute map to copy.
	 */
	private void copyAttributes(IGameElement toUpdate, Map<AttributeType, IAttribute> attributeMap) {
		for (AttributeType type: attributeMap.keySet()) {
			Attribute toCopy = new Attribute(type, attributeMap.get(type).getMyValue(), toUpdate);
			toCopy.setTriggerValues(attributeMap.get(type).getTriggerValues());
			toUpdate.addAttribute(toCopy);
		}
	}
	
	/**
	 * Copies an actor's states.
	 * @param toUpdate: actor to update.
	 * @param toCopy: actor to copy.
	 */
	private void copyStates(Actor toUpdate, Actor toCopy) {
		Set<ActorState> updatedStates = new HashSet<ActorState>();
		for (ActorState state: toCopy.getStates()) {
			updatedStates.add(state);
		}
		toUpdate.setStates(updatedStates);
	}
}
