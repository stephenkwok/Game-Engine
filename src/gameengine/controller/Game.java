package gameengine.controller;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gameengine.model.Triggers.TickTrigger;
import gameengine.model.*;
import gameengine.model.*;
import gameengine.model.Triggers.*;
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
	private List<IPlayActor> currentActors;
	private List<IPlayActor> deadActors;
	private ObservableMap<String, Object> HUDData;
	
	//NOTE: NEED TO INITZLIWE THIS
	private IPlayActor mainCharacter;
	
	
	/**
	 * A game is instantiated with a list of all levels in the game and a level to start on.
	 * Upon instantiation, the actors from all levels are collected into a list and added to a map containing references from ID to actor.
	 * In addition, a map is created mapping all the actors contained in a level to the level ID 
	 * @param levelNum the first level to play in the game
	 * @param gameLevels all the levels in the game 
	 */
	public Game(String gameFilePath, GameInfo gameInfo, List<Level> gameLevels) {
		initialGameFile = gameFilePath;
		levels = gameLevels;
		info = gameInfo;
		setCurrentActors(new ArrayList<IPlayActor>());
		setDeadActors(new ArrayList<IPlayActor>());
        myPhysicsEngine = new PhysicsEngine();
        myCollisionDetector = new CollisionDetection(myPhysicsEngine);
        
		initTimeline();
		
		
	}
	

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
	
	public void startGame(){
		initCurrentActors();
		//This is here because it needs to know who the main actor is
		initHUDData();
		
		animation.play();
	}


	public void initCurrentActors() {
		setCurrentActors(getCurrentLevel().getActors());
		for (IPlayActor actor: currentActors) {
			if (actor.checkState(ActorState.MAIN)) {
				mainCharacter = actor;
			}
		}
		initActors();
	}
	
	private void step(){
		//physicsUpdate();
		myCollisionDetector.detection(getCurrentActors());
		signalTick();
		updateActors();
	}
	
	private void signalTick(){
		handleTrigger(new TickTrigger());
	}
	
	private void initActors(){
		for(IPlayActor a: getCurrentActors()){
			((Observable)a).addObserver(this);
			a.setEngine(myPhysicsEngine);
		}
		
	}
	
	public String getInitialGameFile() {
		return initialGameFile;
	}

	public void setInitialGameFile(String initialGameFile) {
		this.initialGameFile = initialGameFile;
	}

	public GameInfo getInfo() {
		return info;
	}

	public void setInfo(GameInfo info) {
		this.info = info;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
	
	/**
	 * Gets a list of all levels in the game 
	 * @return a list of all levels in the game 
	 */
	public List<Level> getLevels() {
		return levels;
	}
	
	public void nextLevel(){
		animation.stop();
		if(levels.size()>=info.getMyCurrentLevelNum()+1){
			setCurrentLevel(info.getMyCurrentLevelNum()+1);
		}
	}
	
	/**
	 * Lets current level handle a trigger 
	 * @param myTrigger the trigger received from the game player 
	 */
	public void handleTrigger(ITrigger myTrigger) {
		getCurrentLevel().handleTrigger(myTrigger);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	public List<IPlayActor> getActors() {
		return getLevels().get(getInfo().getMyCurrentLevelNum()).getActors();
	}
	
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
	
	public Level getCurrentLevel(){
		return levels.get(info.getMyCurrentLevelNum());
	}
	
	public void setCurrentLevel(int levelNum){
		info.setMyCurrentLevelNum(levelNum);
	}
	
	public void updateActors(){
		setDeadActors(new ArrayList<IPlayActor>());
		for(IPlayActor a: getCurrentActors()){
			if(a.checkState(ActorState.DEAD)){
				deadActors.add(a);
			}
		}
		if(deadActors.size()!=0){
			removeDeadActors();
		}
		setCurrentActors(getCurrentLevel().getActors());
	}


	private void removeDeadActors() {
		setChanged();
		notifyObservers("updateActors");
		getCurrentLevel().removeActors(deadActors);	
		deadActors.clear();
	}


	public List<IPlayActor> getDeadActors() {
		return deadActors;
	}


	public void setDeadActors(List<IPlayActor> deadActors) {
		this.deadActors = deadActors;
	}


	public List<IPlayActor> getCurrentActors() {
		return currentActors;
	}


	public void setCurrentActors(List<IPlayActor> list) {
		this.currentActors = list;
	}


	public PhysicsEngine getMyPhysicsEngine() {
		return myPhysicsEngine;
	}


	public void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}


	public CollisionDetection getMyCollisionDetector() {
		return myCollisionDetector;
	}


	public void setMyCollisionDetector(CollisionDetection myCollisionDetector) {
		this.myCollisionDetector = myCollisionDetector;
	}


	public Timeline getAnimation() {
		return animation;
	}


	public void setAnimation(Timeline animation) {
		this.animation = animation;
	}
	
	public void initHUDData() {
		HUDData = FXCollections.observableHashMap();
		updateHUDFields(info.getMyHUDOptions(), HUDData);
		HUDData.addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> change) {
				setChanged();
				notifyObservers(change);//IDK if casting to observable causes issues with equality
			}
        });
	}
	
	public Map<String, Object> getHUDData() {
		return HUDData;
	}
	
	
	public void updateHUDFields(Collection<String> keys, Map<String, Object> destinationMap) {
		for (String key : keys) {
			Object value = null;
			if (key.equals("Health")) {
				value = ((Attribute) mainCharacter.getAttribute(AttributeType.HEALTH)).getMyValue();
			} else if (key.equals("Level")) {
				value = info.getMyCurrentLevelNum();
			} else if (key.equals("Ammo")) {
				//todo
			} else if (key.equals("Coins")) {
				//todo
			} else if (key.equals("Time")) {
				//todo
			} else if (key.equals("Points")){
				value = ((Attribute) mainCharacter.getAttribute(AttributeType.POINTS)).getMyValue();
			} else {
				value = "Error";
			}
			destinationMap.put(key, value);
		}
	}
	
	
	
	
	public void updateAttribute() {
		updateHUDFields(HUDData.keySet(), HUDData);
	}
	
	

}
