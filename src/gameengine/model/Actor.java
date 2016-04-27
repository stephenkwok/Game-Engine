package gameengine.model;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import authoringenvironment.model.*;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class defines the logic for an Actor object. within the myRules map.
 * When provided with a Trigger object, all actions associated with a particular
 * Trigger are executed. The Actor also extends the ImageView class so they will
 * also be visual elements.
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
	private double myHeading;
	private double myImageViewSize;
    @XStreamOmitField
    private ImageView myImageView;
    private RuleManager myRuleManager;
    private AttributeManager myAttributeManager;
    private PhysicsEngine myPhysicsEngine;
    private Set<ActorState> myStates;
    private Sprite mySprite;
    private NextValues myNextValues;
    private boolean isMainPlayer;
    private boolean isVisible;
    private double myRotate;
    private double myOpacity;
    private double myScaleX;
    private double myScaleY;

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     */
    public Actor() {
    	myRuleManager = new RuleManager();
    	myAttributeManager = new AttributeManager();
        myStates = new HashSet<>();
        myName = DEFAULT_NAME;
        myImageViewName = DEFAULT_IMAGE_NAME;
        mySprite = new Sprite();
        myNextValues = new NextValues();
        setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(mySprite.getCurrentImage()))));
        myRotate = myImageView.getRotate();
        isMainPlayer = false;
        isVisible = true;
    }
    
    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param myTrigger The trigger to be used
     */
    @Override
    public void handleTrigger(ITrigger myTrigger) {
    	myRuleManager.handleTrigger(myTrigger);
    }

    /**
     * Adds a new Attribute to an Actors
     *
     * @param attribute The new Actor Attribute
     */
    @Override
    public void addAttribute(Attribute attribute) {
        myAttributeManager.addAttribute(attribute);
    }

    /**
     * Returns the Actor attribute based on attribute type
     *
     * @param type The new Actor Attribute Type
     */
    @Override
    public Attribute getAttribute(AttributeType type){
    	return myAttributeManager.getAttribute(type);
    }
    
    public Map<AttributeType, Attribute> getAttributeMap() {
    	return myAttributeManager.getAttributeMap();
    }

    /**
     * Modifies the current value of an Attribute
     *
     * @param type   The type of the Attribute to be changed
     * @param change The amount to change the Attribute by
     */
    public void changeAttribute(AttributeType type, int change) {
    	myAttributeManager.changeAttribute(type, change);
    }

    /**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
    @Override
    public void addRule(Rule newRule) {
        myRuleManager.addRule(newRule);
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
		myImageViewSize = myImageView.getFitHeight();
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
	 * 
	 * @return The Actor's Y coordinate
	 */
	@Override
	public double getY() {
		return y;
	}

	/**
	 * Provides the Actor's physics engine
	 * 
	 * @return The Actor's physics engine
	 */
	public PhysicsEngine getPhysicsEngine() {
		return myPhysicsEngine;
	}

	/**
	 * Provides a string representation of the Actor
	 * 
	 * @return A string representation of the Actor
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
	 * 
	 * @return The Actor's ImageView
	 */
	@Override
	public String getImageViewName() {
		return myImageViewName;
	}

	/**
	 * Sets the name of the Actor's ImageView
	 * 
	 * @param myImageViewName
	 *            The Actor's ImageView
	 */
	public void setImageViewName(String myImageViewName) {
		this.myImageViewName = myImageViewName;
		mySprite.setImage(myImageViewName);
		this.setImageView(
				new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(mySprite.getCurrentImage()))));
	}

	/**
	 * Sets the Actor as changed
	 */
	public void changed() {
		setChanged();
	}

	public ImageView getImageView() {
		return myImageView;
	}

	/**
	 * Provides the Actor's ImageView Bounds
	 * 
	 * @return The Actor's ImageView Bounds
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
	 * @param myFriction
	 *            the myFriction to set
	 */
	public void setFriction(double myFriction) {
		this.myFriction = myFriction;
	}

	/**
	 * Provides the Actor's Rules
	 * 
	 * @return The Actor's Rules
	 */
	public Map<String, List<Rule>> getRules() {
		return myRuleManager.getRules();
	}

	/**
	 * Sets a new physics engine
	 * 
	 * @param myPhysicsEngine
	 *            The new physics engine
	 */
	public void setPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}

	/**
	 * Sets the Actor's ImageView's size
	 * 
	 * @param size
	 *            The ImageView's size
	 */
	@Override
	public void setSize(double size) {
		myImageView.setFitHeight(size);
		myImageView.setPreserveRatio(true);
	}

	@Override
	public boolean checkState(ActorState state) {
		return myStates.contains(state);
	}

	@Override
	public void addState(ActorState state) {
		myStates.add(state);
	}

	@Override
	public void removeState(ActorState state) {
		myStates.remove(state);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass().equals(this.getClass())) {
			setChanged();
			notifyObservers(arg);
		}
		// if(checkState(ActorState.MAIN)){
		// setChanged();
		// notifyObservers("updateAttribute");
		// }
	}

	public double getSize() {
		return myImageViewSize;
	}

	@Override
	public void setID(int ID) {
		myID = ID;
	}

	@Override
	public void setHeading(double h) {
		myHeading = h;
	}

	@Override
	public double getHeading() {
		return myHeading;
	}

	@Override
	public int getID() {
		return myID;
	}

	@Override
	public void handleReachedAttribute(AttributeReached trigger) {
		setChanged();
		notifyObservers(Arrays.asList(new Object[] { "handleTrigger", trigger }));
	}

	@Override
	public void removeAttribute(Attribute attribute) {
		myAttributeManager.removeAttribute(attribute);

	}

	@Override
	public void removeRule(Rule rule) {
		myRuleManager.removeRule(rule);

	}

	public void setDirection() {
		if (getHeading() == 0)
			myImageView.setScaleX(1);
		else if (getHeading() == 180) {
			myImageView.setScaleX(-1);
		}
	}

	public void addSpriteImage(String newImage) {
		mySprite.addImage(newImage);
	}

    public void nextImage(){
        myImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(mySprite.getNextImage())));
    }
    
    public void setIsMainPlayer(boolean isMainPlayer) {
    	this.isMainPlayer = isMainPlayer;
    }
    
    public boolean isMainPlayer() {
    	return isMainPlayer;
    }

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void setRotate(double rotate) {
		myImageView.setRotate(rotate);
		myRotate = rotate;
	}

	@Override
	public double getRotate(){
		return myRotate;
	}

	@Override
	public void setOpacity(double opacity) {
		myImageView.setOpacity(opacity);
		myOpacity = opacity;
	}

	@Override
	public double getOpacity() {
		return myOpacity;
	}

	@Override
	public void setScaleX(double scaleX) {
		myScaleX = scaleX;
		myImageView.setScaleX(scaleX);
	}

	@Override
	public double getScaleX() {
		return myScaleX;
	}

	@Override
	public void setScaleY(double scaleY) {
		myScaleY = scaleY;
		myImageView.setScaleY(scaleY);
	}

	@Override
	public double getScaleY() {
		return myScaleY;
	}

}
