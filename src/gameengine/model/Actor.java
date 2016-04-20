package gameengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import authoringenvironment.model.*;
import authoringenvironment.view.ActorRule;
import gameengine.model.Actions.Action;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This class defines the logic for an Actor object.
 * Each interactive element in the Game is an Actor. Actors contains a set of rules that are contained
 * within the myRules map. When provided with a Trigger object, all actions associated with a particular Trigger
 * are executed. The Actor also extends the ImageView class so they will also be visual elements.
 *
 * @author blakekaplan
 */

public class Actor extends Observable implements Observer, IPlayActor, IDisplayActor, IAuthoringActor {

    private static final String DEFAULT_NAME = "Default Name";
    private static final String DEFAULT_IMAGE_NAME = "hellokitty.gif";
    private double x;
    private double y;
    private double veloX;
    private double veloY;
    private double myFriction;
    private String myName;
    private int myID;
	private String myImageViewName;
    @XStreamOmitField
    private ImageView myImageView;
    private Map<String, List<Action>> myRules;
    private Map<AttributeType, Attribute> attributeMap;
    private PhysicsEngine myPhysicsEngine;
    private List<ActorRule> myActorRules;
    private Set<ActorState> myStates;

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     */
    public Actor() {
    	myRules =  new HashMap<>();
        attributeMap = new HashMap<>();
        myActorRules = new ArrayList<>();
        myStates = new HashSet<>();
        myName = DEFAULT_NAME;
        myImageViewName = DEFAULT_IMAGE_NAME;
        setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myImageViewName))));
    }

    public List<ActorRule> getMyActorRules() {
		return myActorRules;
	}
    
    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param triggerString The string representation of the trigger to be executed
     */
    @Override
    public void performActionsFor(String triggerString) {
    	if(myRules.containsKey(triggerString)){
            List<Action> myActions = myRules.get(triggerString);
            for (Action myAction : myActions) {
                myAction.perform();
            }
    	}
    }

    /**
     * Adds a new Attribute to an Actors
     *
     * @param newAttribute The new Actor Attribute
     */
    public void addAttribute(Attribute newAttribute) {
    	newAttribute.addObserver(this);
        getAttributeMap().put(newAttribute.getMyType(), newAttribute);
    }

    /**
     * Returns the Actor attribute based on attribute type
     *
     * @param type The new Actor Attribute Type
     */

    public Attribute getAttribute(AttributeType type){
    	return getAttributeMap().get(type);
    }

    /**
     * Modifies the current value of an Attribute
     *
     * @param type   The type of the Attribute to be changed
     * @param change The amount to change the Attribute by
     */
    public void changeAttribute(AttributeType type, int change) {

        Attribute myAttribute = getAttributeMap().get(type);
        if(myAttribute!=null){
            myAttribute.changeAttribute(change);
        }
    }

    /**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
    @Override
    public void addRule(IRule newRule) {
        if (myRules.containsKey(newRule.getMyTrigger().getMyKey())) {
            List<Action> myActions = myRules.get(newRule.getMyTrigger().getMyKey());
            myActions.add(newRule.getMyAction());
            myRules.put(newRule.getMyTrigger().getMyKey(), myActions);
        } else {
            List<Action> myActions = new ArrayList<>();
            myActions.add(newRule.getMyAction());
            myRules.put(newRule.getMyTrigger().getMyKey(), myActions);
        }
    }

    /**
     * Provides the Actor's X Velocity
     * @return  The Actors X Velocity
     */
    @Override
    public double getVeloX() {
        return veloX;
    }

    /**
     * Provides the Actor's Y Velocity
     * @return  The Actor's Y Velocity
     */
    @Override
    public double getVeloY() {
        return veloY;
    }

    /**
     * Sets the Actor's X coordinate
     * @param updateXPosition   The new X coordinate
     */
    @Override
    public void setX(double updateXPosition) {
       x = updateXPosition;
       myImageView.setX(x);
    }

    /**
     * Sets the Actor's Y position
     * @param updateYPosition   The new Y position
     */
    @Override
    public void setY(double updateYPosition) {
    	y = updateYPosition;
    	myImageView.setY(updateYPosition);

    }

    /**
     * Sets a new X velocity
     * @param updateXVelo   The new X velocity
     */
    public void setVeloX(double updateXVelo) {
        veloX = updateXVelo;
    }

    /**
     * Sets a new Y velocity
     * @param updateYVelo   The new Y velocity
     */
    public void setVeloY(double updateYVelo) {
        veloY = updateYVelo;
    }

    /**
     * Provides the Actor's name
     * @return  The Actor's name
     */
    @Override
    public String getName() {
        return myName;
    }

    /**
     * Sets a new Actor name
     * @param name  The new Actor name
     */
    @Override
    public void setName(String name) {
        myName = name;
    }


    /**
     * Sets a new Actor ImageView
     * @param imageView The new ImageView
     */
    public void setImageView(ImageView imageView) {
    	myImageView = imageView;
    	myImageView.setX(this.getX());
    	myImageView.setY(this.getY());
		myImageView.setFitHeight(imageView.getFitHeight());
    }

    /**
     * Provides the Actor's X coordinate
     * @return  The Actor's X coordinate
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Provides the Actor's Y coordinate
     * @return  The Actor's Y coordinate
     */
    @Override
    public double getY() {
    	return y;
    }

    /**
     * Provides the Actor's physics engine
     * @return  The Actor's physics engine
     */
    public PhysicsEngine getPhysicsEngine(){
		return myPhysicsEngine;
	}

    /**
     * Provides a string representation of the Actor
     * @return  A string representation of the Actor
     */
	@Override
    public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

	      stringBuilder.append("Actor[ ");
	      stringBuilder.append("\nname: ");
	      stringBuilder.append(myName);
	      stringBuilder.append("\nmyImgName: ");
	      stringBuilder.append(myImageViewName);
	      stringBuilder.append("\nmyImg: ");
	      stringBuilder.append(myImageView);
	      stringBuilder.append("\nmyRules: ");
	      stringBuilder.append(getRules().toString());
	      stringBuilder.append(" ]");

	      return stringBuilder.toString();
	}

    /**
     * Provides the Actor's ImageView
     * @return  The Actor's ImageView
     */
	@Override
    public String getImageViewName() {
		return myImageViewName;
	}

    /**
     * Sets the name of the Actor's ImageView
     * @param myImageViewName   The Actor's ImageView
     */
	public void setImageViewName(String myImageViewName) {
		this.myImageViewName = myImageViewName;
		this.setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myImageViewName))));
	}

    /**
     * Sets the Actor as changed
     */
    public void changed(){
        setChanged();
    }
    
    public ImageView getImageView(){
    	return myImageView;
    }
    
    /**
     * Provides the Actor's ImageView Bounds
     * @return  The Actor's ImageView Bounds
     */
	public Bounds getBounds() {
		return myImageView.getLayoutBounds();
	}

	/**
	 * @return the myFriction
	 */
	public double getFriction() {
		return myFriction;
	}

	/**
	 * @param myFriction the myFriction to set
	 */
	public void setFriction(double myFriction) {
		this.myFriction = myFriction;
	}

    /**
     * Provides the Actor's Rules
     * @return  The Actor's Rules
     */
    public Map<String, List<Action>> getRules() {
		return myRules;
	}

    /**
     * Provides the Attribute map
     * @return  The Actor's Attribute Map
     */
    public Map<AttributeType, Attribute> getAttributeMap() {
		return attributeMap;
	}

    /**
     * Sets a new physics engine
     * @param myPhysicsEngine   The new physics engine
     */
    public void setPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}

    /**
     * Sets the Actor's ImageView's size
     * @param size  The ImageView's size
     */
	@Override
    public void setSize(double size){
		myImageView.setFitHeight(size);
		myImageView.setPreserveRatio(true);
	}

    /**
     * Provides the List of the Actor's Rules
     * @return  The Actor's ActorRules
     */
	@Override
    public List<ActorRule> getActorRules(){
		return myActorRules;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(checkState(ActorState.MAIN)){
			setChanged();
			notifyObservers("updateAttribute");
		}
		
	}
	
	@Override
	public boolean checkState(ActorState state){
		return myStates.contains(state);
	}
	
	@Override
	public void addState(ActorState state){
		myStates.add(state);
	}
	
	@Override
	public void removeState(ActorState state){
		myStates.remove(state);
	}

	@Override
	public int getMyID() {
		return myID;
	}

	@Override
	public double getSize() {
		return myImageView.getFitHeight();
	}

	@Override
	public void setID(int ID) {
		myID = ID;
		
	}
}