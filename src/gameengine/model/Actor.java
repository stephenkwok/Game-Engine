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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private DoubleProperty veloX = new SimpleDoubleProperty();
    private DoubleProperty veloY = new SimpleDoubleProperty();
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

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     */
    public Actor() {
        myRules = new HashMap<>();
        attributeMap = new HashMap<>();
        myName = DEFAULT_NAME;
        myImageViewName = DEFAULT_IMAGE_NAME;
        isMain = DEFAULT_MAIN;
        setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myImageViewName))));
        initXYBindings();
    }

	private void initXYBindings() {
		x.addListener(new ChangeListener(){
        	@Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                myImageView.setX((Double)newVal);
            }
        });
        y.addListener(new ChangeListener(){
        	@Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                myImageView.setY((Double)newVal);
            }
        });
	}

    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param triggerString
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
        attributeMap.put(newAttribute.getType(), newAttribute);
    }
    
    /**
     * Returns the Actor attribute based on attribute type
     *
     * @param newAttribute The new Actor Attribute
     */
    
    public Attribute getAttribute(AttributeType type){
    	return attributeMap.get(type);
    }

    /**
     * Modifies the current value of an Attribute
     *
     * @param type   The type of the Attribute to be changed
     * @param change The amount to change the Attribute by
     */
    public void changeAttribute(AttributeType type, int change) {

        Attribute myAttribute = attributeMap.get(type);
        myAttribute.changeAttribute(change);
    }

    /**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
    @Override
    public void addRule(IRule newRule) {
        if (myRules.containsKey(newRule.getTrigger().getTriggerName())) {
            List<Action> myActions = myRules.get(newRule.getTrigger().getTriggerName());
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger().getTriggerName(), myActions);
        } else {
            List<Action> myActions = new ArrayList<>();
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger().getTriggerName(), myActions);
        }
    }

    /**
     * Provides a list of Triggers that the Actor responds to
     *
     * @return The list of Triggers that the Actor responds to
     */
    public Set<String> getTriggers() {
        return myRules.keySet();
    }

    /**
     * Provides the Actor's ID number
     *
     * @return The Actor's ID number
     */
    @Override
    public int getID() {
        return myID;
    }

    @Override
    public double getXVelo() {
        return veloX.get();
    }

    @Override
    public double getYVelo() {
        return veloY.get();
    }

    @Override
    public void setXPos(double updateXPosition) {
       x.set(updateXPosition);
    }

    @Override
    public void setYPos(double updateYPosition) {
    	y.set(updateYPosition);

    }

    @Override
    public void setXVelo(double updateXVelo) {
        veloX.set(updateXVelo);
    }

    @Override
    public void setYVelo(double updateYVelo) {
        veloY.set(updateYVelo);
    }

    public void setID(int ID) {
        myID = ID;
    }

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public void setName(String name) {
        myName = name;
    }

    @Override

    public ImageView getImageView() {
        return myImageView;
    }

    @Override
    public void setImageView(ImageView imageView) {
    	this.myImageView = imageView;
    	this.myImageView.setX(this.getXPos());
    	this.myImageView.setY(this.getYPos());
    }

    @Override
    public double getXPos() {
        return x.get();
    }

    @Override
    public double getYPos() {
    	return y.get();
    }

	public void setEngine(PhysicsEngine physicsEngine) {
		myPhysicsEngine = physicsEngine;
	}
	
	public PhysicsEngine getPhysicsEngine(){
		return myPhysicsEngine;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
	      
	      stringBuilder.append("Actor[ ");
	      stringBuilder.append("\nid: ");
	      stringBuilder.append(myID);
	      stringBuilder.append("\nname: ");
	      stringBuilder.append(myName);
	      stringBuilder.append("\nmyImgName: ");
	      stringBuilder.append(myImageViewName);
	      stringBuilder.append("\nmyImg: ");
	      stringBuilder.append(myImageView);
	      stringBuilder.append("\nmyRules: ");
	      stringBuilder.append(myRules.toString());
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
}
