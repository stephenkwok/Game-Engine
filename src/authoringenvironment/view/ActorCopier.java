package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.CollisionTrigger;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.KeyTrigger;
import gameengine.model.Triggers.TickTrigger;

/**
 * 
 * @author amyzhao
 *
 */
public class ActorCopier {
	private static final String RESOURCE = "ruleCreator";
	private static final String KEY = "Key";
	private static final String TICK = "Tick";
	private static final String COLLISION = "Collision";
	private static final String CLICK = "Click";
	private static final String ATTRIBUTE = "Attribute";
	private static final String CREATE_ACTOR = "CreateActor";
	private static final String WIN_LOSE = "WinLose";
	private Actor myReferenceActor;
	@XStreamOmitField
	private ResourceBundle myResources;
	private ActionFactory myActionFactory;
	private TriggerFactory myTriggerFactory;

	public ActorCopier() {
		myReferenceActor = null;
		init();
	}

	public ActorCopier(Actor actor) {
		myReferenceActor = actor;
		init();
	}

	public void init() {
		myResources = ResourceBundle.getBundle(RESOURCE);
		myActionFactory = new ActionFactory();
		myTriggerFactory = new TriggerFactory();
	}
	public void setReferenceActor(Actor newReference) {
		myReferenceActor = newReference;
	}

	public IAuthoringActor getReferenceActor() {
		return myReferenceActor;
	}

	public Actor makeCopy() {
		Actor actor = new Actor();
		if (myReferenceActor != null) {
			copyActor(actor, myReferenceActor);
			return actor;
		} else {
			return null;
		}
	}

	// not sure how to make this nicer yet
	public void copyActor(Actor toUpdate, Actor toCopy) {
		toUpdate.setName(toCopy.getName());
		toUpdate.setFriction(toCopy.getFriction());
		toUpdate.setImageViewName(toCopy.getImageViewName());
		toUpdate.setSize(toCopy.getSize());
		toUpdate.setID(toCopy.getID());
		toUpdate.setRotate(toCopy.getRotate());
		toUpdate.setOpacity(toCopy.getOpacity());
		toUpdate.setScaleX(toCopy.getScaleX());
		toUpdate.setScaleY(toCopy.getScaleY());
		copyRules(toUpdate, toCopy.getRules());
		copyAttributes((IGameElement) toUpdate, toCopy.getAttributeMap());
	}

	// work in progress.. currently only works for KeyTriggers and Move actions
	private void copyRules(Actor toUpdate, Map<String, List<Rule>> rulesToCopy) {
		toUpdate.getRules().clear();
		for (String trigger : rulesToCopy.keySet()) {
			List<Rule> toAdd = rulesToCopy.get(trigger);
			for (int i = 0; i < toAdd.size(); i++) {
				try {
					ITrigger triggerToAdd = createTrigger(toAdd.get(i), (IPlayActor) toUpdate);
					Object[] params = toAdd.get(i).getMyAction().getParameters();
					params[0] = toUpdate;
					Action actionToAdd = createAction(toAdd.get(i),params);
					Rule rule = new Rule(triggerToAdd, actionToAdd);
					rule.setID(toAdd.get(i).getID() + 1);
					toUpdate.addRule(rule);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private ITrigger createTrigger(Rule rule, IPlayActor toUpdate) {
		ITrigger triggerToAdd = null;
 		String triggerClassName = rule.getMyTrigger().getClass().getSimpleName();
		List<Object> arguments = new ArrayList<>();
		if (checkType(triggerClassName, KEY)) {
			arguments.add(((KeyTrigger) rule.getMyTrigger()).getMyKeyCode());
			//triggerClassName = KEY; // ideally won't need this...
		} else if (checkType(triggerClassName, TICK)) {
			arguments.add(((TickTrigger) rule.getMyTrigger()).getMyInterval());
			//triggerClassName = TICK;
		} else if (checkType(triggerClassName, COLLISION)) {
			arguments.add(toUpdate);
			arguments.add(((CollisionTrigger) rule.getMyTrigger()).getMyCollisionActor());
		} else if (checkType(triggerClassName, ATTRIBUTE)) {
			AttributeReached trigger = (AttributeReached) rule.getMyTrigger();
			arguments.add(trigger.getMyType());
			arguments.add((IGameElement) toUpdate);
			arguments.add(trigger.getMyValue());
		} else if (checkType(triggerClassName, CLICK)) {
			arguments.add((IGameElement) toUpdate);
			//triggerClassName = CLICK;
		}
		triggerToAdd = myTriggerFactory.createNewTrigger(triggerClassName, arguments);
		return triggerToAdd;
	}
	
	private Action createAction(Rule rule, Object[] arguments) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Action actionToAdd = null;
		Class myclass = rule.getMyAction().getClass();
		Class[] argumentTypes = new Class[arguments.length];
		for(int i=0; i<argumentTypes.length;i++){
			argumentTypes[i] = arguments[i].getClass();
		}
		Constructor constructor = myclass.getConstructor(argumentTypes);
		actionToAdd = (Action) constructor.newInstance(arguments);
		return actionToAdd;
	}

	private boolean checkType(String name, String key) {
		if (Arrays.asList(myResources.getString(key).split(",")).contains(name)) {
			return true;
		}
		return false;
	}

	private void copyAttributes(IGameElement toUpdate, Map<AttributeType, Attribute> attributeMap) {
		for (AttributeType type: attributeMap.keySet()) {
			Attribute toCopy = new Attribute(type, attributeMap.get(type).getMyValue(), toUpdate);
			toUpdate.addAttribute(toCopy);
		}
	}
}
