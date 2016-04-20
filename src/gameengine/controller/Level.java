package gameengine.controller;

import gameengine.model.Actor;
import gameengine.model.ITrigger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.model.IEditableGameElement;

/**
 * A Level is essentially a package of Actor objects. It is able to relay a Trigger to Actors when it receives one.
 *
 * @author blakekaplan
 */
public class Level implements Observer, ILevel, IEditableGameElement {

	// TODO: should probably set these default things via properties file but idk sry guyz
	private static final String DEFAULT_NAME = "Default";
	private static final String DEFAULT_IMAGE_NAME = "default_landscape.png";
	private static final double DEFAULT_HEIGHT = 800;
	private static final double DEFAULT_WIDTH = 1024;
	private static final String DEFAULT_SCROLLING = "Vertically";
	private static final String DEFAULT_TERMINATION = "Infinite";
	private static final String DEFAULT_WINNING_CONDITION = "Survival time";
	private static final String DEFAULT_LOSING_CONDITION = "Player dies";
    private List<Actor> myActors;
    private Map<String, List<Actor>> myTriggerMap;
    private String myName;
    private double myHeight;
    private double myWidth;
    private List<String> myHUDOptions;
    private String myScrollingDirection;
    private String myTermination;
    private String myWinningCondition;
    private String myLosingCondition;
    private String myBackgroundImgName;
	@XStreamOmitField
    private ImageView myBackground;


    /**
     * Instantiates the triggerMap and Actor list
     */
    public Level() {
        setMyActors(new ArrayList<>());
        setMyTriggerMap(new HashMap<>());
        setMyName(DEFAULT_NAME);
        myBackgroundImgName = DEFAULT_IMAGE_NAME;
        setMyImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myBackgroundImgName))));
        myTermination = DEFAULT_TERMINATION;
        myScrollingDirection = DEFAULT_SCROLLING;
        myWinningCondition = DEFAULT_WINNING_CONDITION;
        myLosingCondition = DEFAULT_LOSING_CONDITION;
        myName = DEFAULT_NAME;
        myHeight = DEFAULT_HEIGHT;
        myWidth = DEFAULT_WIDTH;
    }

    /**
     * Calls for the appropriate response upon receiving a particular Trigger
     *
     * @param myTrigger A particular Trigger object sent from the game player
     */
    @Override
    public void handleTrigger(ITrigger myTrigger) {
        if (!getMyTriggerMap().containsKey(myTrigger.getMyKey())) return;
        List<Actor> relevantActors = getMyTriggerMap().get(myTrigger.getMyKey());
        for (Actor myActor : relevantActors) {
            myActor.performActionsFor(myTrigger);
        }
    }

    /**
     * Sets the Level's name
     *
     * @param name A name for the Level
     */
    @Override
    public void setMyName(String name) {
        this.myName = name;
    }

    /**
     * Adds a new Actor to the Level and updates the triggerMap accordingly
     *
     * @param newActor The Actor to be added to the Level
     */
    @Override
    public void addActor(Actor newActor) {
        newActor.addObserver(this);
        getActors().add(newActor);
        Set<String> actorTriggers = newActor.getTriggers();
        for (String myTrigger : actorTriggers) {
            if (getMyTriggerMap().containsKey(myTrigger)) {
                List<Actor> levelActors = getMyTriggerMap().get(myTrigger);
                levelActors.add(newActor);
                getMyTriggerMap().put(myTrigger, levelActors);
            } else {
                List<Actor> levelActors = new ArrayList<>();
                levelActors.add(newActor);
                getMyTriggerMap().put(myTrigger, levelActors);
            }
        }

    }

    /**
     * Provides the Level's name
     *
     * @return The Level's name
     */
    @Override
    public String getMyName() {
        return myName;
    }

    /**
     * Provides the Level's ImageView
     *
     * @return  The Level's ImageView
     */
	@Override
	public ImageView getMyImageView() {
		return myBackground;
	}

    /**
     * Sets the Level's ImageView
     *
     * @param imageView to be set as IEditableGameElement's ImageView
     */
	@Override
	public void setMyImageView(ImageView imageView) {
		myBackground = imageView;
	}

    /**
     * Provides the name of the Level's background image
     *
     * @return  The name of the Level's background image
     */
    public String getMyBackgroundImgName() {
		return myBackgroundImgName;
	}

    /**
     * Sets the Level's background image
     *
     * @param myBackgroundImgName   The desired image filepath
     */
	public void setMyBackgroundImgName(String myBackgroundImgName) {
		this.myBackgroundImgName = myBackgroundImgName;
	}

    /**
     * Provides a string representation of the Level object
     *
     * @return  A string representation of the Level object
     */
	public String toString() {

	      StringBuilder stringBuilder = new StringBuilder();

	      stringBuilder.append("\nLevel [ ");
	      stringBuilder.append("\nmyName: ");
	      stringBuilder.append(getMyName());
	      stringBuilder.append("\nbckImg: ");
	      stringBuilder.append(myBackgroundImgName);
	      stringBuilder.append("\nmyActors: ");
	      stringBuilder.append(getActors().toString());
	      stringBuilder.append("\nTriggerMap: ");
	      stringBuilder.append(getMyTriggerMap().toString());
	      stringBuilder.append("\nimg: ");
	      stringBuilder.append(myBackground);
	      stringBuilder.append(" ]");

	      return stringBuilder.toString();
	}

    /**
     * Performs the Appropriate procedure when notified by an object that it observes
     * @param o The observable object that notifies Level
     * @param arg   Arguments passed by the observable object
     */
    @Override
    public void update(Observable o, Object arg) {
        Actor myActor = (Actor) o;
        if (arg.equals("DESTROY")){
            getActors().remove(myActor);
        } else if (arg.equals("WINGAME")) {
        }
    }

    /**
     * Provides the Level's Map of Trigger to List of Actors
     *
     * @return  The Level's Map of Trigger to List of Actors
     */
	public Map<String, List<Actor>> getMyTriggerMap() {
		return myTriggerMap;
	}

    /**
     * Sets a new Trigger map
     *
     * @param myTriggerMap  The desired new Trigger map
     */
	public void setMyTriggerMap(Map<String, List<Actor>> myTriggerMap) {
		this.myTriggerMap = myTriggerMap;
	}

    /**
     * Provides the Game's list of Actors
     * @return  The Game's list of Actors
     */
	public List<Actor> getActors() {
		return myActors;
	}

    /**
     * Sets the Game's list of Actors
     * @param myActors  The desired list of Actors
     */
	public void setMyActors(List<Actor> myActors) {
		this.myActors = myActors;
	}

    /**
     * Provides the Level's Height
     * @return  The Level's Height
     */
	public double getMyHeight() {
		return myHeight;
	}

    /**
     * Sets the Level's Height
     *
     * @param myHeight  The desired Level height
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
     * @param myWidth   The desired Level width
     */
	public void setMyWidth(double myWidth) {
		this.myWidth = myWidth;
	}

    /**
     * Provides the HUD options
     *
     * @return  The Level's HUD options
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
     * @return  The Level's scrolling direction
     */
	public String getMyScrollingDirection() { return myScrollingDirection; }

    /**
     * Sets the Level's scrolling direction
     *
     * @param myScrollingDirection  The desired scrolling direction
     */
	public void setMyScrollingDirection(String myScrollingDirection) {
		this.myScrollingDirection = myScrollingDirection;
	}

    /**
     * Provides the Level's termination string
     *
     * @return  The Level's termination strings
     */
	public String getMyTermination() {
		return myTermination;
	}

    /**
     * Sets the Level's termination string
     *
     * @param myTermination The Level's termination string
     */
	public void setMyTermination(String myTermination) {
		this.myTermination = myTermination;
	}

    /**
     * Provides the Level's winning condition string
     *
     * @return  The Level's winning condition string
     */
	public String getMyWinningCondition() {
		return myWinningCondition;
	}

    /**
     * Sets the Level's winning condition string
     *
     * @param myWinningCondition    The desired winning condition string
     */
	public void setMyWinningCondition(String myWinningCondition) {
		this.myWinningCondition = myWinningCondition;
	}

    /**
     * Provides the Level's losing condition string
     *
     * @return  The Level's losing condition string
     */
	public String getMyLosingCondition() {
		return myLosingCondition;
	}

    /**
     * Sets the Level's losing condition string
     *
     * @param myLosingCondition The desired losing condition string
     */
	public void setMyLosingCondition(String myLosingCondition) {
		this.myLosingCondition = myLosingCondition;
	}

    /**
     * Removes the provided dead Actors from the list of Actors
     *
     * @param deadActors    The list of dead Actors
     */
	public void removeActors(List<Actor> deadActors) {
		myActors.removeAll(deadActors);
	}

}

