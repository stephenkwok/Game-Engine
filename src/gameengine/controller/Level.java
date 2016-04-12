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

	private static final String DEFAULT_NAME = "Untitled";
	private static final String DEFAULT_IMAGE_NAME = "default_background.png";

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
        setImageView(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myBackgroundImgName))));
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
            if (myTrigger.evaluate(myActor)){
                myActor.performActionsFor(myTrigger.getMyKey());
            }
        }
        //myCollisionDetector.detection(myActors); //Collision Detection/Resolution for each Actor
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
    public String getName() {
        return getMyName();
    }

	@Override
	public ImageView getImageView() {
		return myBackground;
	}

	@Override
	public void setImageView(ImageView imageView) {
		myBackground = imageView;
	}

	@Override
	public void setMyID(int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMyID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
    public String getMyBackgroundImgName() {
		return myBackgroundImgName;
	}

	public void setMyBackgroundImgName(String myBackgroundImgName) {
		this.myBackgroundImgName = myBackgroundImgName;
	}
	
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


    @Override
    public void update(Observable o, Object arg) {
        Actor myActor = (Actor) o;
        if (arg.equals("DESTROY")){
            getActors().remove(myActor);
        } else if (arg.equals("WINGAME")) {
        }
    }

	public Map<String, List<Actor>> getMyTriggerMap() {
		return myTriggerMap;
	}

	public void setMyTriggerMap(Map<String, List<Actor>> myTriggerMap) {
		this.myTriggerMap = myTriggerMap;
	}

	public List<Actor> getActors() {
		return myActors;
	}

	public void setMyActors(List<Actor> myActors) {
		this.myActors = myActors;
	}

	public double getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(double myHeight) {
		this.myHeight = myHeight;
	}

	public double getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(double myWidth) {
		this.myWidth = myWidth;
	}

	public List<String> getMyHUDOptions() {
		return myHUDOptions;
	}

	public void setMyHUDOptions(List<String> myHUDOptions) {
		this.myHUDOptions = myHUDOptions;
	}

	public String getMyScrollingDirection() {
		return myScrollingDirection;
	}

	public void setMyScrollingDirection(String myScrollingDirection) {
		this.myScrollingDirection = myScrollingDirection;
	}

	public String getMyTermination() {
		return myTermination;
	}

	public void setMyTermination(String myTermination) {
		this.myTermination = myTermination;
	}

	public String getMyWinningCondition() {
		return myWinningCondition;
	}

	public void setMyWinningCondition(String myWinningCondition) {
		this.myWinningCondition = myWinningCondition;
	}

	public String getMyLosingCondition() {
		return myLosingCondition;
	}

	public void setMyLosingCondition(String myLosingCondition) {
		this.myLosingCondition = myLosingCondition;
	}

	public String getMyName() {
		return myName;
	}

	public void removeActors(List<Actor> deadActors) {
		myActors.removeAll(deadActors);
		
	}

}