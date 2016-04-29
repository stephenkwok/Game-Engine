package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.model.IAuthoringActor;
import gameengine.model.Actor;
import gameengine.model.ActorState;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		copyAttributes((IGameElement) toUpdate, toCopy.getAttributeMap());
	}

	// work in progress.. currently only works for KeyTriggers and Move actions
	private void copyRules(Actor toUpdate, Map<String, List<Rule>> rulesToCopy) {
		toUpdate.getRules().clear();
		for (String trigger : rulesToCopy.keySet()) {
			List<Rule> toAdd = rulesToCopy.get(trigger);
			for (int i = 0; i < toAdd.size(); i++) {
					try {
						Rule oldRule = toAdd.get(i);
						ITrigger oldTrigger = oldRule.getMyTrigger();
						Action oldAction = oldRule.getMyAction();
						
						ITrigger triggerToAdd = createTrigger(oldTrigger, toUpdate);
						Action actionToAdd = createAction(oldAction,toUpdate);
						Rule rule = new Rule(triggerToAdd, actionToAdd);
						toUpdate.addRule(rule);
						
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
							| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	private Object createObject(Object action, Object[] arguments) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class myclass = action.getClass();
		Class[] argumentTypes = new Class[arguments.length];
		for(int i=0; i<argumentTypes.length;i++){
			argumentTypes[i] = arguments[i].getClass();
		}
		Constructor constructor = myclass.getConstructor(argumentTypes);
		return constructor.newInstance(arguments);
	}
	
	private ITrigger createTrigger(ITrigger trigger, Actor toUpdate) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("triggerkey " + trigger.getMyKey());
		Object[] params = trigger.getParameters();
		if(params[0].getClass().equals(Actor.class)&&((Actor)params[0]).getID()==toUpdate.getID()){
			params[0] = toUpdate;
		}
		return (ITrigger) createObject(trigger,params);
	}
	
	private Action createAction(Action action, Actor toUpdate) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] params = action.getParameters();
		params[0] = toUpdate;
		return (Action) createObject(action,params);
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
	
	private void copyStates(Actor toUpdate, Actor toCopy) {
		Set<ActorState> updatedStates = new HashSet<ActorState>();
		for (ActorState state: toCopy.getStates()) {
			updatedStates.add(state);
		}
		toUpdate.setStates(updatedStates);
	}
}
