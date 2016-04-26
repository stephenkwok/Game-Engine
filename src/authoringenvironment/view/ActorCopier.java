package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
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
import javafx.scene.input.KeyCode;

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
	private ResourceBundle myResources;
	
	public ActorCopier() {
		myReferenceActor = null;
		myResources = ResourceBundle.getBundle(RESOURCE);
	}

	public ActorCopier(Actor actor) {
		myReferenceActor = actor;
		myResources = ResourceBundle.getBundle(RESOURCE);
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
		copyRules(toUpdate, toCopy.getRules());
	}

	// work in progress.. currently only works for KeyTriggers and Move actions
	private void copyRules(Actor toUpdate, Map<String, List<Rule>> rulesToCopy) {
		toUpdate.getRules().clear();
		for (String trigger : rulesToCopy.keySet()) {
			List<Rule> toAdd = rulesToCopy.get(trigger);
			for (int i = 0; i < toAdd.size(); i++) {
				String triggerName = toAdd.get(i).getMyTrigger().getClass().getName();
				Class<?> className;
				try {
					ITrigger triggerToAdd = createTrigger(toAdd.get(i), (IPlayActor) toUpdate);
					Action actionToAdd = createAction(toAdd.get(i), (IPlayActor) toUpdate);
					//for colettes test with tick
					/*if (toAdd.get(i).getMyTrigger().getClass().equals(TickTrigger.class)){
						ITrigger trigger1 = toAdd.get(i).getMyTrigger();

						String actionName = toAdd.get(i).getMyAction().getClass().getName();
						Class<?> actionClassName = Class.forName(actionName);
						Constructor<?> actionConstructor = actionClassName.getConstructor(IPlayActor.class);
						Action actionToAdd = (Action) actionConstructor.newInstance((IPlayActor) toUpdate);

						toUpdate.addRule(new Rule(trigger1,actionToAdd));

					}else{
					className = Class.forName(triggerName);
					Constructor<?> triggerConstructor = className.getConstructor(KeyCode.class);
					KeyCode key = ((KeyTrigger) toAdd.get(i).getMyTrigger()).getMyKeyCode();
					ITrigger triggerToAdd = (KeyTrigger) triggerConstructor.newInstance(key);


					String actionName = toAdd.get(i).getMyAction().getClass().getName();
					Class<?> actionClassName = Class.forName(actionName);
					Constructor<?> actionConstructor = actionClassName.getConstructor(IPlayActor.class);
					Action actionToAdd = (Action) actionConstructor.newInstance((IPlayActor) toUpdate);
					 */
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

	private ITrigger createTrigger(Rule rule, IPlayActor toUpdate) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ITrigger triggerToAdd = null;
		String fullTriggerName = rule.getMyTrigger().getClass().getName();
		Class<?> triggerClass = Class.forName(fullTriggerName);
		String triggerClassName = getClassName(fullTriggerName);
		if (checkType(triggerClassName, KEY)) {
			Constructor<?> triggerConstructor = triggerClass.getConstructor(KeyCode.class);
			KeyCode key = ((KeyTrigger) rule.getMyTrigger()).getMyKeyCode();
			triggerToAdd = (KeyTrigger) triggerConstructor.newInstance(key);
		} else if (checkType(triggerClassName, TICK)) {
			Constructor<?> triggerConstructor = triggerClass.getConstructor(Integer.class);
			Integer interval = ((TickTrigger) rule.getMyTrigger()).getMyInterval();
			triggerToAdd = (TickTrigger) triggerConstructor.newInstance(interval);
		} else if (checkType(triggerClassName, COLLISION)) {
			Constructor<?> triggerConstructor = triggerClass.getConstructor(IPlayActor.class, IPlayActor.class);
			IPlayActor collisionActor = ((CollisionTrigger) rule.getMyTrigger()).getMyCollisionActor();
			triggerToAdd = (CollisionTrigger) triggerConstructor.newInstance(toUpdate, collisionActor);
		} else if (checkType(triggerClassName, ATTRIBUTE)) {
			Constructor<?> triggerConstructor = triggerClass.getConstructor(IGameElement.class, AttributeType.class, Integer.class);
			AttributeReached trigger = (AttributeReached) rule.getMyTrigger();
			triggerToAdd = (AttributeReached) triggerConstructor.newInstance(trigger.getMyTarget(), trigger.getMyType(), trigger.getMyTriggerValue());
		}		
		return triggerToAdd;
	}

	private Action createAction(Rule rule, IPlayActor toUpdate) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String actionName = rule.getMyAction().getClass().getName();
		Class<?> actionClassName = Class.forName(actionName);
		Constructor<?> actionConstructor = actionClassName.getConstructor(IPlayActor.class);
		Action actionToAdd = (Action) actionConstructor.newInstance(toUpdate);
		return actionToAdd;
	}
	
	private boolean checkType(String name, String key) {
		String[] fullName = name.split(".");
		String className = fullName[fullName.length - 1];
		if (Arrays.asList(myResources.getString(key).split(",")).contains(className)) {
			return true;
		}
		return false;
	}
	
	private String getClassName(String name) {
		String[] fullName = name.split(".");
		return fullName[fullName.length - 1];
	}
}
