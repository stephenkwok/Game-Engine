package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.KeyTrigger;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author amyzhao
 *
 */
public class ActorCopier {
	private Actor myReferenceActor;
	
	public ActorCopier(Actor actor) {
		myReferenceActor = actor;
	}
	
	public void setReferenceActor(Actor newReference) {
		myReferenceActor = newReference;
	}
	
	public IAuthoringActor getReferenceActor() {
		return myReferenceActor;
	}
	
	public Actor makeCopy() {
		Actor actor = new Actor();
		copyActor(actor, myReferenceActor);
		return actor;
	}

	// not sure how to make this nicer yet
	private void copyActor(Actor toUpdate, Actor toCopy) {
		toUpdate.setName(toCopy.getName());
		toUpdate.setFriction(toCopy.getFriction());
		toUpdate.setImageView(toCopy.getImageView());
		toUpdate.setSize(toCopy.getSize());
		toUpdate.setImageViewName(toCopy.getImageViewName());
		toUpdate.setID(toCopy.getMyID());
		copyRules(toUpdate, toCopy.getRules());
		toUpdate.setPhysicsEngine(toCopy.getPhysicsEngine());
		//copyAttributes(toUpdate,)
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
					className = Class.forName(triggerName);
					Constructor<?> triggerConstructor = className.getConstructor(KeyCode.class);
					KeyCode key = ((KeyTrigger) toAdd.get(i).getMyTrigger()).getMyKeyCode();
					ITrigger triggerToAdd = (KeyTrigger) triggerConstructor.newInstance(key);
					
					
					String actionName = toAdd.get(i).getMyAction().getClass().getName();
					Class<?> actionClassName = Class.forName(actionName);
					Constructor<?> actionConstructor = actionClassName.getConstructor(IPlayActor.class);
					Action actionToAdd = (Action) actionConstructor.newInstance((IPlayActor) toUpdate);
					
					
					//ITrigger triggerToAdd = toAdd.get(i).getMyTrigger();
					//Action action = toAdd.get(i).getMyAction();
					//action.setMyActor((IPlayActor) (toUpdate));
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
					// TODO Auto-generated catch block
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
}
