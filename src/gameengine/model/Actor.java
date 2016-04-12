package gameengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IActor;
import gameengine.model.IRule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.AttributeType;
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

public class Actor extends Observable implements IActor, IEditableGameElement {

    private static final String DEFAULT_NAME = "Default Name";
    private static final String DEFAULT_IMAGE_NAME = "default_actor.jpg";
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
    }

    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param triggerString
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
    public void addAttribute(Attribute newAttribute) {
        getAttributeMap().put(newAttribute.getMyType(), newAttribute);
    }
    
    /**
     * Returns the Actor attribute based on attribute type
     *
     * @param newAttribute The new Actor Attribute
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
        myAttribute.changeAttribute(change);
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

    @Override
    public double getVeloX() {
        return veloX;
    }

    @Override
    public double getVeloY() {
        return veloY;
    }

    @Override
    public void setX(double updateXPosition) {
       x = updateXPosition;
       myImageView.setX(x);
    }

    @Override
    public void setY(double updateYPosition) {
    	y = updateYPosition;
    	myImageView.setY(updateYPosition);

    }

    @Override
    public void setVeloX(double updateXVelo) {
        veloX = updateXVelo;
    }

    @Override
    public void setVeloY(double updateYVelo) {
        veloY = updateYVelo;
    }

    public void setMyID(int ID) {
        this.myID = ID;
    }

    @Override
    public String getMyName() {
        return myName;
    }

    @Override
    public void setMyName(String name) {
        myName = name;
    }

    @Override

    public ImageView getImageView() {
        return myImageView;
    }

    @Override
    public void setImageView(ImageView imageView) {
    	this.myImageView = imageView;
    	this.myImageView.setX(this.getX());
    	this.myImageView.setY(this.getY());
		this.myImageView.setFitHeight(imageView.getFitHeight());
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
    	return y;
    }

	public void setEngine(PhysicsEngine physicsEngine) {
		setMyPhysicsEngine(physicsEngine);
	}
	
	public PhysicsEngine getPhysicsEngine(){
		return getMyPhysicsEngine();
	}
	
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


	public String getMyImageViewName() {
		return myImageViewName;
	}


	public void setMyImageViewName(String myImageViewName) {
		this.myImageViewName = myImageViewName;
		this.setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myImageViewName))));
	}

    public void changed(){
        setChanged();
    }


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
		this.isMain = bool;
	}
	/**
	 * Return whether the actor is a playable, main character. 
	 * @return
	 */
	public boolean isMain(){
		return this.isMain;
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
	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

	public Map<String, List<Action>> getMyRules() {
		return myRules;
	}

	public void setMyRules(Map<String, List<Action>> myRules) {
		this.myRules = myRules;
	}

	public Map<AttributeType, Attribute> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<AttributeType, Attribute> attributeMap) {
		this.attributeMap = attributeMap;
	}

	public PhysicsEngine getMyPhysicsEngine() {
		return myPhysicsEngine;
	}

	public void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}

	
	public void setSize(double size){
		myImageView.setFitHeight(size);
		myImageView.setPreserveRatio(true);
	}
	
	public void setMyHealth(double myHealth){
		this.myHealth = myHealth;
	}

	public double getMyHealth() {
		return myHealth;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}


}
