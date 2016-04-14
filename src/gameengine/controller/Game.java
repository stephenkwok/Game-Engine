package gameengine.controller;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gameengine.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.collections.MapChangeListener.Change;
import javafx.util.Duration;

/**
 * This class is intended to represent a game containing levels with actors.
 * @author colettetorres
 *
 */


public class Game extends Observable implements Observer {
	public static final int SIZE = 400;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

	private String initialGameFile;
	private List<Level> levels;
	private GameInfo info;
	private PhysicsEngine myPhysicsEngine;
	private CollisionDetection myCollisionDetector;
	@XStreamOmitField
	private Timeline animation;
	private List<Actor> currentActors;
	private List<Actor> deadActors;
	private ObservableMap<String, Object> HUDData;

	private Actor mainCharacter;
	

    /**
     * A game is instantiated with a list of all levels in the game and a level to start on.
     * Upon instantiation, the actors from all levels are collected into a list and added to a map containing references from ID to actor.
     * In addition, a map is created mapping all the actors contained in a level to the level ID
     *
     * @param gameFilePath  The game's filepath
     * @param gameInfo      The game info associated with the game
     * @param gameLevels    All the levels in the game
     */
	public Game(String gameFilePath, GameInfo gameInfo, List<Level> gameLevels) {
		initialGameFile = gameFilePath;
		levels = gameLevels;
		info = gameInfo;
		setCurrentActors(new ArrayList<Actor>());
		setDeadActors(new ArrayList<Actor>());
        myPhysicsEngine = new PhysicsEngine();
        myCollisionDetector = new CollisionDetection(myPhysicsEngine);
        
		initTimeline();
		
		
	}

    /**
     * Initializes a timeline that will be used for the game loop
     */
	public void initTimeline() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step());
		setAnimation(new Timeline());
		getAnimation().setCycleCount(Timeline.INDEFINITE);
		getAnimation().getKeyFrames().add(frame);
	}
	
	public Game(GameInfo gameInfo, List<Level> gameLevels) {
		this(null, gameInfo, gameLevels);
	}
	
	public Game(List<Level> gameLevels) {
		this(new GameInfo(), gameLevels);
	}

    /**
     * Starts the game
     */
	public void startGame(){
		initCurrentActors();
		//This is here because it needs to know who the main actor is
		initHUDData();
		
		animation.play();
	}


    /**
     * Initializes the current actors
     */
	public void initCurrentActors() {
		setCurrentActors(getCurrentLevel().getActors());
		for (Actor actor: currentActors) {
			if (actor.isMain()) {
				mainCharacter = actor;
			}
		}
		initActors();
	}

    /**
     * The step method that states what will happen with each Timeline frame
     */
	private void step(){
		physicsUpdate();
		myCollisionDetector.detection(getCurrentActors());
		updateActors();
	}

    /**
     * Updates the physics for each Actor
     */
	private void physicsUpdate(){
		for(Actor a: getCurrentActors()){
			myPhysicsEngine.tick(a);
		}
	}

    /**
     * Initializes the Actors
     */
	private void initActors(){
		for(Actor a: getCurrentActors()){
			a.addObserver(this);
			a.setEngine(myPhysicsEngine);
		}
		
	}

    /**
     * Provides the initial game file
     *
     * @return  The initial game file
     */
	public String getInitialGameFile() {
		return initialGameFile;
	}

    /**
     * Sets the initial game file
     *
     * @param initialGameFile   The initial game file
     */
	public void setInitialGameFile(String initialGameFile) {
		this.initialGameFile = initialGameFile;
	}

    /**
     * Returns the GameInfo object for the Game
     *
     * @return  The Game's GameInfo objects
     */
	public GameInfo getInfo() {
		return info;
	}

    /**
     * Sets the Game's GameInfo
     *
     * @param info  The desired GameInfo for the Game
     */
	public void setInfo(GameInfo info) { this.info = info; }

    /**
     * Sets the Game's Levels
     *
     * @param levels    The desired game Levels
     */
	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
	
	/**
	 * Gets a list of all levels in the game
     *
	 * @return a list of all levels in the game 
	 */
	public List<Level> getLevels() {
		return levels;
	}

    /**
     * Changes the Game to the next Level
     */
	public void nextLevel(){
		animation.stop();
		setCurrentLevel(info.getMyCurrentLevelNum()+1);
	}
	
	/**
	 * Lets current level handle a trigger
     *
	 * @param myTrigger the trigger received from the game player 
	 */
	public void handleTrigger(ITrigger myTrigger) {
		getCurrentLevel().handleTrigger(myTrigger);
	}

    /**
     * Carries out the appropriate procedure when notified by an observed object
     * @param o The Observable object that is being observed
     * @param arg   Arguments that the Observable object passes
     */
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

    /**
     * Provides the Game's Actors
     *
     * @return The Game's Actors
     */
	public List<Actor> getActors() {
		return getLevels().get(getInfo().getMyCurrentLevelNum()).getActors();
	}

    /**
     * Provides a String representation of the Game object
     *
     * @return  The String representation of the Game object
     */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
	      
	      stringBuilder.append("Game [ ");
	      stringBuilder.append("\ninitialFile: ");
	      stringBuilder.append(initialGameFile);
	      stringBuilder.append("\ngameLevels: ");
	      stringBuilder.append(levels.toString());
	      stringBuilder.append("\nmyInfo: ");
	      stringBuilder.append(info.toString());
	      stringBuilder.append(" ]");
	      
	      return stringBuilder.toString();
	}

    /**
     * Provides the Level that is currently being used
     *
     * @return The Level that is currently being used
     */
	public Level getCurrentLevel(){
		return levels.get(info.getMyCurrentLevelNum());
	}

    /**
     * Sets the current Level
     *
     * @param levelNum  The index of the level that the user would like to load
     */
	public void setCurrentLevel(int levelNum){
		info.setMyCurrentLevelNum(levelNum);
	}


    /**
     * Updates the current actors based on gameplay
     */
    public void updateActors(){
		setDeadActors(new ArrayList<Actor>());
		for(Actor a: getCurrentActors()){
			if(a.isDead()){
				deadActors.add(a);
			}
		}
		if(deadActors.size()!=0){
			removeDeadActors();
		}
		setCurrentActors(getCurrentLevel().getActors());
	}

    /**
     * Calls for the removal of dead Actors
     */
	private void removeDeadActors() {
		setChanged();
		notifyObservers("updateActors");
		getCurrentLevel().removeActors(deadActors);	
		deadActors.clear();
	}

    /**
     * Provides a list of the dead Actors
     *
     * @return  A list of the dead Actors
     */
	public List<Actor> getDeadActors() {
		return deadActors;
	}

    /**
     * Sets the list of dead Actors
     *
     * @param deadActors    The desired list of dead Actors
     */
	public void setDeadActors(List<Actor> deadActors) {
		this.deadActors = deadActors;
	}

    /**
     * Provides a list of the current Actors
     *
     * @return  A list of the current Actors
     */
	public List<Actor> getCurrentActors() {
		return currentActors;
	}

    /**
     * Sets the list of current Actors
     *
     * @param currentActors The list of current Actors
     */
	public void setCurrentActors(List<Actor> currentActors) {
		this.currentActors = currentActors;
	}

    /**
     * Provides the Game's PhysicsEngine object
     *
     * @return  The Game's PhysicsEngine object
     */
	public PhysicsEngine getMyPhysicsEngine() {
		return myPhysicsEngine;
	}

    /**
     * Sets the Game's PhyicsEngine
     *
     * @param myPhysicsEngine   The desired PhysicsEngine
     */
	public void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}

    /**
     * Provides the Game's Timeline
     *
     * @return  The Game's Timeline
     */
	public Timeline getAnimation() {
		return animation;
	}

    /**
     * Sets the Game's Timeline
     *
     * @param animation The desired Timeline
     */
	public void setAnimation(Timeline animation) {
		this.animation = animation;
	}

    /**
     * Initializes the HUDData
     */
	public void initHUDData() {
		HUDData = FXCollections.observableHashMap();
		updateHUDFields(info.getMyHUDOptions(), HUDData);
		HUDData.addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> change) {
				update((Observable) HUDData, change); //IDK if casting to observable causes issues with equality
			}
        });
	}

    /**
     * Provides the Game's HUDData
     * @return  The Game's HUDData
     */
	public Map<String, Object> getHUDData() {
		return HUDData;
	}

    /**
     * Updates the HUD fields to be accounted for
     * @param keys  The names of the fields to be represented
     * @param destinationMap    The map to put the data into
     */
	public void updateHUDFields(Collection<String> keys, Map<String, Object> destinationMap) {
		for (String key : keys) {
			Object value = null;
			if (key.equals("Health")) {
				value = mainCharacter.getAttribute(AttributeType.HEALTH).getMyValue();
			} else if (key.equals("Level")) {
				value = info.getMyCurrentLevelNum();
			} else if (key.equals("Ammo")) {
				//todo
			} else if (key.equals("Coins")) {
				//todo
			} else if (key.equals("Time")) {
				//todo
			} else if (key.equals("Points")){
				value = mainCharacter.getAttribute(AttributeType.POINTS).getMyValue();
			} else {
				value = "Error";
			}
			destinationMap.put(key, value);
		}
	}

    /**
     * Updates the HUD values
     */
	public void updateAttributes() {
		updateHUDFields(HUDData.keySet(), HUDData);
	}

}
