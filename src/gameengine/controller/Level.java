package gameengine.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.Attribute;
import gameengine.model.AttributeManager;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;
import gameengine.model.Rule;
import gameengine.model.RuleManager;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A Level is essentially a package of Actor objects. It is able to relay a
 * Trigger to Actors when it receives one.
 *
 * @author blakekaplan
 */
public class Level extends Observable implements ILevel, IEditableGameElement, Comparable<Level>, IGameElement {

	private static final String DEFAULT_NAME = "Default";
	private static final String DEFAULT_IMAGE_NAME = "default_landscape.png";
	private static final double DEFAULT_HEIGHT = 800;
	private static final double DEFAULT_WIDTH = 1024;
	private static final String DEFAULT_SCROLLING = "Horizontally";
	private static final String DEFAULT_MUSIC = "Ink.mp3";
	private List<IPlayActor> myActors;
	private String myName;
	private double myHeight;
	private double myWidth;
	private int myPlayPosition;
	private List<String> myHUDOptions;
	private String myScrollingDirection;
	private String myBackgroundImgName;
	@XStreamOmitField
	private ImageView myBackground;
	@XStreamOmitField
	private DoubleProperty myBackgroundX = new SimpleDoubleProperty();
	private RuleManager myRuleManager;
	private AttributeManager myAttributeManager;
	private List<IPlayActor> myMainCharacters;
	private String soundtrack;
	private List<IPlayActor> myGarbageCollectors;
	private IPlayGame myGame;
	
	/**
	 * Instantiates the triggerMap and Actor list
	 */
	public Level() {
		myRuleManager = new RuleManager();
		myAttributeManager = new AttributeManager();
		setMyActors(new ArrayList<>());
		setName(DEFAULT_NAME);
		myBackgroundImgName = DEFAULT_IMAGE_NAME;
		soundtrack = DEFAULT_MUSIC;
		setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myBackgroundImgName))));
		myScrollingDirection = DEFAULT_SCROLLING;
		myName = DEFAULT_NAME;
		myHeight = DEFAULT_HEIGHT;
		myWidth = DEFAULT_WIDTH;
		myRuleManager = new RuleManager();
		myMainCharacters = new ArrayList<>();
		myGarbageCollectors = new ArrayList<>();
	}

	/**
	 * Calls for the appropriate response upon receiving a particular Trigger
	 *
	 * @param myTrigger
	 *            A particular Trigger object sent from the game player
	 */
	@Override
	public void handleTrigger(ITrigger myTrigger) {
		myRuleManager.handleTrigger(myTrigger);
	}

	/**
	 * Sets the Level's name
	 *
	 * @param name
	 *            A name for the Level
	 */
	@Override
	public void setName(String name) {
		this.myName = name;
	}

	/**
	 * Adds a new Actor to the Level and updates the triggerMap accordingly
	 *
	 * @param actor
	 *            The Actor to be added to the Level
	 */
	@Override
	public void addActor(IAuthoringActor actor) {
		getActors().add((IPlayActor)actor);
	}

	/**
	 * Provides the Level's name
	 *
	 * @return The Level's name
	 */
	@Override
	public String getName() {
		return myName;
	}

	/**
	 * Provides the Level's ImageView
	 *
	 * @return The Level's ImageView
	 */
	@Override
	public ImageView getImageView() {
		return myBackground;
	}

	/**
	 * Sets the Level's ImageView
	 *
	 * @param imageView
	 *            to be set as IEditableGameElement's ImageView
	 */
	@Override
	public void setImageView(ImageView imageView) {
		myBackground = imageView;
		myBackgroundX = new SimpleDoubleProperty(myBackground.getX());
	}

	/**
	 * Provides the name of the Level's background image
	 *
	 * @return The name of the Level's background image
	 */
	public String getMyBackgroundImgName() {
		return myBackgroundImgName;
	}

	/**
	 * Sets the Level's background image
	 *
	 * @param myBackgroundImgName
	 *            The desired image filepath
	 */
	public void setMyBackgroundImgName(String myBackgroundImgName) {
		this.myBackgroundImgName = myBackgroundImgName;
	}

	/**
	 * Provides a string representation of the Level object
	 *
	 * @return A string representation of the Level object
	 */
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\nLevel [ ");
		stringBuilder.append("\nmyName: ");
		stringBuilder.append(getName());
		stringBuilder.append("\nbckImg: ");
		stringBuilder.append(myBackgroundImgName);
		stringBuilder.append("\nmyActors: ");
		stringBuilder.append(getActors().toString());
		stringBuilder.append("\nimg: ");
		stringBuilder.append(myBackground);
		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}

//	public Map<String, List<IPlayActor>> getMyTriggerMap() {
//		return myTriggerMap;
//	}
//
//	public void setMyTriggerMap(Map<String, List<IPlayActor>> myTriggerMap) {
//		this.myTriggerMap = myTriggerMap;
//	}

	public List<IPlayActor> getActors() {
		return myActors;
	}

	public void setMyActors(List<IPlayActor> myActors) {
		this.myActors = myActors;
	}

	/**
	 * Provides the Level's Height
	 * 
	 * @return The Level's Height
	 */
	public double getMyHeight() {
		return myHeight;
	}

	/**
	 * Sets the Level's Height
	 *
	 * @param myHeight
	 *            The desired Level height
	 */
	public void setMyHeight(double myHeight) {
		this.myHeight = myHeight;
	}

	/**
	 * Provides the Level's width
	 *
	 * @return The Level's width
	 */
	public double getMyWidth() {
		return myWidth;
	}

	/**
	 * Sets the Level's width
	 *
	 * @param myWidth
	 *            The desired Level width
	 */
	public void setMyWidth(double myWidth) {
		this.myWidth = myWidth;
	}

	/**
	 * Provides the HUD options
	 *
	 * @return The Level's HUD options
	 */
	public List<String> getMyHUDOptions() {
		return myHUDOptions;
	}

	/**
	 * Sets the Level's HUD options
	 *
	 * @param myHUDOptions
	 */
	public void setMyHUDOptions(List<String> myHUDOptions) {
		this.myHUDOptions = myHUDOptions;
	}

	/**
	 * Provides the Level's scrolling direction
	 *
	 * @return The Level's scrolling direction
	 */
	public String getMyScrollingDirection() {
		return myScrollingDirection;
	}

	/**
	 * Sets the Level's scrolling direction
	 *
	 * @param myScrollingDirection
	 *            The desired scrolling direction
	 */
	public void setMyScrollingDirection(String myScrollingDirection) {
		this.myScrollingDirection = myScrollingDirection;
	}

	public void removeActors(List<IPlayActor> deadActors) {
		myActors.removeAll(deadActors);
	}

	/**
	 * 
	 * @return: the level's play position
	 */
	public int getPlayPosition() {
		return myPlayPosition;
	}

	public void setPlayPosition(int playPosition) {
		myPlayPosition = playPosition;
	}

	/**
	 * Returns a negative number if this Level's play position is lower than the
	 * other Level's play position or a positive number if this Level's play
	 * position is higher than the other Level's play position
	 */
	@Override
	public int compareTo(Level otherLevel) {
		return this.myPlayPosition - otherLevel.getPlayPosition();
	}

	public void removeActor(Actor actor) {
		myActors.remove(actor);
	}

	public DoubleProperty getMyBackgroundX() {
		return myBackgroundX;
	}

	public void setMyBackgroundX(DoubleProperty myBackgroundX) {
		this.myBackgroundX = myBackgroundX;
	}

	public void scrollBackground(int change) {
		this.myBackground.setX((this.myBackground.getX() + change) % this.myBackground.getImage().getWidth());
		this.myBackgroundX.set(myBackground.getX());
	}

	public void setMyImageView(ImageView imageView) {
		myBackground = imageView;
		myBackgroundX = new SimpleDoubleProperty(myBackground.getX());
	}
	
	public IPlayActor getMainCharacter() {
		for (IPlayActor a : myActors) {
			if (a.checkState(ActorState.MAIN)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return: a List of the Level's main characters
	 */
	public List<IPlayActor> getMainCharacters() {
		myMainCharacters.clear();
		for (IPlayActor a : myActors) {
			if (a.checkState(ActorState.MAIN) && !myMainCharacters.contains(a)) {
				myMainCharacters.add(a);
			}
		}
		return myMainCharacters;
	}

	@Override
	public void addAttribute(Attribute attribute) {
		myAttributeManager.addAttribute(attribute);

	}

	@Override
	public void removeAttribute(Attribute attribute) {
		myAttributeManager.removeAttribute(attribute);

	}

	@Override
	public void handleReachedAttribute(AttributeReached trigger) {
		setChanged();
		notifyObservers(Arrays.asList(new Object[] { "handleTrigger", trigger }));
	}

	@Override
	public Attribute getAttribute(AttributeType type) {
		return myAttributeManager.getAttribute(type);
	}

	@Override
	public void changeAttribute(AttributeType type, int change) {
		myAttributeManager.changeAttribute(type, change);

	}

	@Override
	public void addRule(Rule rule) {
		myRuleManager.addRule(rule);

	}

	@Override
	public void removeRule(Rule rule) {
		myRuleManager.removeRule(rule);

	}

	@Override
	public Map<String, List<Rule>> getRules() {
		return myRuleManager.getRules();
	}


    public void changed(){
        setChanged();
    }

    public Bounds getBounds(){
        return myBackground.getBoundsInLocal();
    }
	
    public void setSoundtrack(String soundtrack) {
    	this.soundtrack = soundtrack;
    }
    
    public String getSoundtrack() {
    	return soundtrack;
    }
    
    
    public void shiftScene(String direction, double amount){
    	for(IPlayActor a: myActors){
    		try {
    			Class[] paramTypes = {IPlayActor.class, double.class};
    			Object[] params = {a, amount};
				PhysicsEngine.class.getDeclaredMethod("glide"+direction,paramTypes).invoke(a.getPhysicsEngine(),params);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
    	}
    } 
    
    public void addGarbageCollector(IPlayActor actor) {
    	myActors.add(actor);
    	myGarbageCollectors.add(actor);
    }
    
    public List<IPlayActor> getGarbageCollectors() {
    	return myGarbageCollectors;
    }

	@Override
	public void setGame(IPlayGame game) {
		myGame = game;
	}
	
	@Override
	public IPlayGame getGame(){
		return myGame;
	}
}
