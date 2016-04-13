package gameengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.model.IEditableGameElement;
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

public class Actor extends Observable implements IActor, Observer, IAuthoringActor {

    private static final String DEFAULT_NAME = "Default Name";
    private static final String DEFAULT_IMAGE_NAME = "hellokitty.gif";
    private static final boolean DEFAULT_MAIN = false;

    private double x;
    private double y;
    private double veloX;
    private double veloY;
    private int myID;
    private double myFriction;
    private boolean inAir;
    private String myName;
    private String myImageViewName;
    @XStreamOmitField
    private ImageView myImageView;
    private Map<String, List<Action>> myRules;
    private Map<AttributeType, Attribute> attributeMap;
    private PhysicsEngine myPhysicsEngine;
    private boolean isMain;
    private double myHealth;
    private List<ActorRule> myActorRules;
    private boolean isDead;

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     */
    public Actor() {
        setMyRules(new HashMap<>());
        setAttributeMap(new HashMap<>());
        myName = DEFAULT_NAME;
        myImageViewName = DEFAULT_IMAGE_NAME;
        isMain = DEFAULT_MAIN;
        setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myImageViewName))));
        myActorRules = new ArrayList<>();
    }

    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param triggerString The string representation of the trigger to be executed
     */
    @Override
    public void performActionsFor(String triggerString) {
    	if(getMyRules().containsKey(triggerString)){
            List<Action> myActions = getMyRules().get(triggerString);
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
    @Override
    public void addAttribute(Attribute newAttribute) {
    	newAttribute.addObserver(this);
        getAttributeMap().put(newAttribute.getMyType(), newAttribute);
    }

    /**
     * Returns the Actor attribute based on attribute type
     *
     * @param type The new Actor Attribute Type
     */

    @Override
    public Attribute getAttribute(AttributeType type){
    	return getAttributeMap().get(type);
    }

    /**
     * Modifies the current value of an Attribute
     *
     * @param type   The type of the Attribute to be changed
     * @param change The amount to change the Attribute by
     */
    @Override
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
        if (getMyRules().containsKey(newRule.getMyTrigger().getMyKey())) {
            List<Action> myActions = getMyRules().get(newRule.getMyTrigger().getMyKey());
            myActions.add(newRule.getMyAction());
            getMyRules().put(newRule.getMyTrigger().getMyKey(), myActions);
        } else {
            List<Action> myActions = new ArrayList<>();
            myActions.add(newRule.getMyAction());
            getMyRules().put(newRule.getMyTrigger().getMyKey(), myActions);
        }
    }

    /**
     * Provides a list of Triggers that the Actor responds to
     *
     * @return The list of Triggers that the Actor responds to
     */
    @Override
    public Set<String> getTriggers() {
        return getMyRules().keySet();
    }

    /**
     * Provides the Actor's ID number
     *
     * @return The Actor's ID number
     */
    @Override
    public int getMyID() {
        return this.myID;
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
    @Override
    public void setVeloX(double updateXVelo) {
        veloX = updateXVelo;
    }

    /**
     * Sets a new Y velocity
     * @param updateYVelo   The new Y velocity
     */
    @Override
    public void setVeloY(double updateYVelo) {
        veloY = updateYVelo;
    }

    /**
     * Sets a new Actor ID
     * @param ID    The new ID
     */
    @Override
    public void setMyID(int ID) {
        myID = ID;
    }

    /**
     * Provides the Actor's name
     * @return  The Actor's name
     */
    @Override
    public String getMyName() {
        return myName;
    }

    /**
     * Sets a new Actor name
     * @param name  The new Actor name
     */
    @Override
    public void setMyName(String name) {
        myName = name;
    }

    /**
     * Provides the Actor's Imageview
     * @return  The Actor's Imageview
     */
    @Override
    public ImageView getImageView() {
        return myImageView;
    }

    /**
     * Sets a new Actor ImageView
     * @param imageView The new ImageView
     */
    @Override
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
     * Assigns a phyiscs engine to an Actor
     * @param physicsEngine The assigned physics engine
     */
	@Override
    public void setEngine(PhysicsEngine physicsEngine) {
		setMyPhysicsEngine(physicsEngine);
	}

    /**
     * Provides the Actor's physics engine
     * @return  The Actor's physics engine
     */
	@Override
    public PhysicsEngine getPhysicsEngine(){
		return getMyPhysicsEngine();
	}

    /**
     * Provides a string representation of the Actor
     * @return  A string representation of the Actor
     */
	@Override
    public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

	      stringBuilder.append("Actor[ ");
	      stringBuilder.append("\nid: ");
	      stringBuilder.append(getMyID());
	      stringBuilder.append("\nname: ");
	      stringBuilder.append(myName);
	      stringBuilder.append("\nmyImgName: ");
	      stringBuilder.append(myImageViewName);
	      stringBuilder.append("\nmyImg: ");
	      stringBuilder.append(myImageView);
	      stringBuilder.append("\nmyRules: ");
	      stringBuilder.append(getMyRules().toString());
	      stringBuilder.append(" ]");

	      return stringBuilder.toString();
	}

    /**
     * Provides the Actor's ImageView
     * @return  The Actor's ImageView
     */
	@Override
    public String getMyImageViewName() {
		return myImageViewName;
	}

    /**
     * Sets the name of the Actor's ImageView
     * @param myImageViewName   The Actor's ImageView
     */
	public void setMyImageViewName(String myImageViewName) {
		this.myImageViewName = myImageViewName;
		this.setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myImageViewName))));
	}

    /**
     * Sets the Actor as changed
     */
    public void changed(){
        setChanged();
    }

    /**
     * Provides the Actor's ImageView Bounds
     * @return  The Actor's ImageView Bounds
     */
	public Bounds getBounds() {
		return this.getImageView().getLayoutBounds();
	}

	/**
	 * @return the myFriction
	 */
	public double getMyFriction() {
		return myFriction;
	}

	/**
	 * @param myFriction the myFriction to set
	 */
	public void setMyFriction(double myFriction) {
		this.myFriction = myFriction;
	}

	/**
	 * Set whether this actor is a playable, main character.
	 */
	public void setMain(boolean bool){
		isMain = bool;
	}
	/**
	 * Return whether the actor is a playable, main character.
	 * @return
	 */
	public boolean isMain(){
		return isMain;
	}
	/**
	 * @return the inAir
	 */
	public boolean isInAir() {
		return inAir;
	}

	/**
	 * @param inAir the inAir to set
	 */
	@Override
    public void setInAir(boolean inAir) {
		inAir = inAir;
	}

    /**
     * Provides the Actor's Rules
     * @return  The Actor's Rules
     */
	@Override
    public Map<String, List<Action>> getMyRules() {
		return myRules;
	}

    /**
     * Sets the Actor's Rules
     * @param myRules   A new set of Actor rules
     */
	@Override
    public void setMyRules(Map<String, List<Action>> myRules) {
		this.myRules = myRules;
	}

    /**
     * Provides the Attribute map
     * @return  The Actor's Attribute Map
     */
	@Override
    public Map<AttributeType, Attribute> getAttributeMap() {
		return attributeMap;
	}

    /**
     * Sets a new Attribute Map
     * @param attributeMap  The new Attribute Map
     */
	@Override
    public void setAttributeMap(Map<AttributeType, Attribute> attributeMap) {
		this.attributeMap = attributeMap;
	}

    /**
     * Provides the Actor's physics engine
     * @return  The Actor's phyiscs engine
     */
	@Override
    public PhysicsEngine getMyPhysicsEngine() {
		return myPhysicsEngine;
	}

    /**
     * Sets a new physics engine
     * @param myPhysicsEngine   The new physics engine
     */
	@Override
    public void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine) {
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
     * Sets the Actor's health value
     * @param myHealth  The Actor's health value
     */
	@Override
    public void setMyHealth(double myHealth){
		this.myHealth = myHealth;
	}

    /**
     * Provides the Actor's amount of health
     * @return  The Actor's amount of health
     */
	@Override
    public double getMyHealth() {
		return myHealth;
	}

    /**
     * Adds a new ActorRule
     * @param actorRule The new ActorRule
     */
	@Override
    public void addActorRule(ActorRule actorRule){
		myActorRules.add(actorRule);
	}

    /**
     * Removes an Actor Rule
     * @param actorRule The Rule to be removed
     */
	@Override
    public void removeActorRule(ActorRule actorRule){
		myActorRules.remove(actorRule);
	}

    /**
     * Provides the List of the Actor's Rules
     * @return  The Actor's ActorRules
     */
	@Override
    public List<ActorRule> getActorRules(){
		return myActorRules;
	}

    /**
     * Marks the Actor as dead
     * @return  A boolean representing whether or not the Actor is dead
     */
	@Override
    public boolean isDead() {
		return isDead;
	}

    /**
     * Sets the Actor to alive or dead
     * @param isDead    The desired Actor state
     */
	@Override
    public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(isMain){
			setChanged();
			notifyObservers("updateAttribute");
		}
		
	}
}
