package gameengine.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.AttributeType;
import gameengine.model.CollisionDetection;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.PhysicsEngine;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.TickTrigger;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import voogasalad.util.hud.source.Property;

/**
 * This class is intended to represent a game containing levels with actors.
 * 
 * @author colettetorres
 *
 */

public class Game extends Observable implements Observer, IGame, IPlayGame {
	public static final int SIZE = 400;
	public static final int FRAMES_PER_SECOND = 50;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final int BACKGROUND_SCROLL_SPEED = -1;

	private String initialGameFile;
	private List<Level> levels;
	private GameInfo info;
	private PhysicsEngine myPhysicsEngine;
	private CollisionDetection myCollisionDetector;
	private Map<String, Set<IGameElement>> activeTriggers;
	@XStreamOmitField
	private Timeline animation;
	private List<IPlayActor> currentActors;
	private List<IPlayActor> deadActors;
	private Property<Integer> levelTime = new Property<>(1, "Time This Level");
	private Property<Integer> globalTime = new Property<>(1, "Global Time");

    @XStreamOmitField
    private SoundPlayer soundEngine;
    private boolean sfxOff = false;
    private boolean musicOff = false;
	private List<IPlayActor> actorsToAdd;

    
    public Game(String initialGameFile, 
    		List<Level> levels, 
    		GameInfo info, 
    		PhysicsEngine myPhysicsEngine,
    		CollisionDetection myCollisionDetector, 
    		Map<String, Set<IGameElement>> activeTriggers,
    		Timeline animation, 
    		List<IPlayActor> currentActors, 
    		List<IPlayActor> deadActors,
    		int levelTime, int globalTime) {
    	this(initialGameFile, info, levels);
    	currentActors = new ArrayList<IPlayActor>();
		deadActors = new ArrayList<IPlayActor>();
		actorsToAdd = new ArrayList<IPlayActor>();
		myPhysicsEngine = new PhysicsEngine();
		myCollisionDetector = new CollisionDetection(myPhysicsEngine);
		this.levelTime.setValue(levelTime);
		this.globalTime.setValue(globalTime);
		initSoundEngine();
	}

	/**
	 * A game is instantiated with a list of all levels in the game and a level
	 * to start on. Upon instantiation, the actors from all levels are collected
	 * into a list and added to a map containing references from ID to actor. In
	 * addition, a map is created mapping all the actors contained in a level to
	 * the level ID
	 *
	 * @param gameFilePath
	 *            The game's filepath
	 * @param gameInfo
	 *            The game info associated with the game
	 * @param gameLevels
	 *            All the levels in the game
	 */
	public Game(String gameFilePath, GameInfo gameInfo, List<Level> gameLevels) {
		initialGameFile = gameFilePath;
		levels = gameLevels;
		info = gameInfo;
		currentActors = new ArrayList<IPlayActor>();
		deadActors = new ArrayList<IPlayActor>();
		actorsToAdd = new ArrayList<IPlayActor>();
		myPhysicsEngine = new PhysicsEngine();
		myCollisionDetector = new CollisionDetection(myPhysicsEngine);
		initTimeline();
		// initSoundEngine();
	}

	/**
	 * Initializes a timeline that will be used for the game loop
	 */
	public void initTimeline() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		setAnimation(new Timeline());
		getAnimation().setCycleCount(Timeline.INDEFINITE);
		getAnimation().getKeyFrames().add(frame);

	}

	public void initSoundEngine() {
		soundEngine = new SoundPlayer();
		soundEngine.loadMultipleSoundFilesFromDir(new File("./authoringsounds"));
		soundEngine.loadMultipleSoundFilesFromDir(new File("./authoringmusic"));
	}

	public void stopGame() {
		togglePause();
	}

	private void togglePause() {
		animation.pause();
		soundEngine.allSetMute(true);
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

	public void startGame() {
		initCurrentLevel();
		initCurrentActors();
		toggleUnPause();
		if (soundEngine != null) {
			soundEngine.setSoundtrack(levels.get(info.getMyCurrentLevelNum()).getSoundtrack());
		}
	}

	public void toggleUnPause() {
		animation.play();
		toggleSoundPause();
	}
	
	private void initGameElement(IGameElement gameElement){
		gameElement.setGame(this);
	}

	private void initCurrentLevel() {
		getCurrentLevel().addObserver(this);
		initGameElement(getCurrentLevel());
	}

	/**
	 * Initializes the current actors
	 */
	public void initCurrentActors() {
		currentActors = getCurrentLevel().getActors();
		for (IPlayActor actor : currentActors) {
			((Observable) actor).addObserver(this);
			initGameElement(actor);
			actor.setPhysicsEngine(myPhysicsEngine);
			actor.setVisibility();
		}
	}

	private void step() {
		refreshTriggerMap();
		myCollisionDetector.detection(getCurrentActors());
		signalTick();
		updateCamera();
		updateActors();
		levelTime.setValue(levelTime.getValue() + 1);
		globalTime.setValue(globalTime.getValue() + 1);
	}

	private void updateCamera() {
		setChanged();
		Object[] args = { "updateCamera" };
		notifyObservers(Arrays.asList(args));
	}

	private void signalTick() {
		handleTrigger(new TickTrigger(levelTime.getValue()));
	}

	private void updateBackground() {
		this.levels.get(info.getMyCurrentLevelNum()).scrollBackground(BACKGROUND_SCROLL_SPEED);
	}

	/**
	 * Provides the initial game file
	 *
	 * @return The initial game file
	 */

	public String getInitialGameFile() {
		return initialGameFile;
	}

	/**
	 * Sets the initial game file
	 *
	 * @param initialGameFile
	 *            The initial game file
	 */
	public void setInitialGameFile(String initialGameFile) {
		this.initialGameFile = initialGameFile;
	}

	/**
	 * Returns the GameInfo object for the Game
	 *
	 * @return The Game's GameInfo objects
	 */
	public GameInfo getInfo() {
		return info;
	}

	/**
	 * Sets the Game's GameInfo
	 *
	 * @param info
	 *            The desired GameInfo for the Game
	 */
	public void setInfo(GameInfo info) {
		this.info = info;
	}

	/**
	 * Sets the Game's Levels
	 *
	 * @param levels
	 *            The desired game Levels
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

	public boolean nextLevel() {
		animation.stop();
		if (info.getMyCurrentLevelNum() + 1 < levels.size()) {
			setCurrentLevel(info.getMyCurrentLevelNum() + 1);
			levels.get(info.getMyCurrentLevelNum()).getActors().forEach(actor -> ((Actor) actor).restoreImageView());
			levels.get(info.getMyCurrentLevelNum()).getMainCharacters().forEach(actor -> actor.setX(0));
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Lets current level handle a trigger
	 *
	 * @param myTrigger
	 *            the trigger received from the game player
	 */
	public void handleTrigger(ITrigger myTrigger) {
		if (activeTriggers.get(myTrigger.getMyKey()) != null) {
			for (IGameElement gameElement : activeTriggers.get(myTrigger.getMyKey())) {
				gameElement.handleTrigger(myTrigger);
			}
		}
	}

	private void refreshTriggerMap() {
		activeTriggers = new HashMap<String, Set<IGameElement>>();
		List<IGameElement> activeGameElements = new ArrayList<IGameElement>(
				Arrays.asList(new IGameElement[] { getCurrentLevel() }));
		activeGameElements.addAll(getActors());
		for (IGameElement gameElement : activeGameElements) {
			for (String trigger : gameElement.getRules().keySet()) {
				if (!activeTriggers.containsKey(trigger)) {
					activeTriggers.put(trigger, new HashSet<IGameElement>());
				}
				activeTriggers.get(trigger).add(gameElement);
			}
		}
	}

	/**
	 * Carries out the appropriate procedure when notified by an observed object
	 * 
	 * @param o
	 *            The Observable object that is being observed
	 * @param arg
	 *            Arguments that the Observable object passes
	 */
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	public List<IPlayActor> getActors() {
		return getLevels().get(getInfo().getMyCurrentLevelNum()).getActors();
	}

	/**
	 * Provides the Level that is currently being used
	 *
	 * @return The Level that is currently being used
	 */
	public Level getCurrentLevel() {
		return levels.get(info.getMyCurrentLevelNum());
	}

	/**
	 * Sets the current Level
	 *
	 * @param levelNum
	 *            The index of the level that the user would like to load
	 */

	public void setCurrentLevel(int levelNum) {
		info.setMyCurrentLevelNum(levelNum);
	}


	public void updateActors() {
		deadActors = new ArrayList<IPlayActor>();
		for (IPlayActor a : getCurrentActors()) {
			if (a.checkState(ActorState.DEAD)) {
				deadActors.add(a);
			}
		}
		if (deadActors.size() != 0) {
			removeDeadActors();
		}
		getCurrentLevel().getActors().addAll(actorsToAdd);
		actorsToAdd.clear();
		currentActors = getCurrentLevel().getActors();
	}


	/**
	 * Calls for the removal of dead Actors
	 */
	private void removeDeadActors() {
		setChanged();
		Object[] args = { "updateActors" };
		notifyObservers(Arrays.asList(args));
		getCurrentLevel().removeActors(deadActors);
		deadActors.clear();
	}

	public List<IPlayActor> getDeadActors() {
		return deadActors;
	}

	public void addActor(Actor newActor) {
		newActor.setPhysicsEngine(myPhysicsEngine);
		actorsToAdd.add(newActor);
	}

	/**
	 * Provides a list of the current Actors
	 *
	 * @return A list of the current Actors
	 */

	public List<IPlayActor> getCurrentActors() {
		return currentActors;
	}

	/**
	 * Provides the Game's PhysicsEngine object
	 *
	 * @return The Game's PhysicsEngine object
	 */
	public PhysicsEngine getMyPhysicsEngine() {
		return myPhysicsEngine;
	}

	/**
	 * Sets the Game's PhyicsEngine
	 *
	 * @param myPhysicsEngine
	 *            The desired PhysicsEngine
	 */
	public void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}

	/**
	 * Provides the Game's Timeline
	 *
	 * @return The Game's Timeline
	 */
	public Timeline getAnimation() {
		return animation;
	}

	/**
	 * Sets the Game's Timeline
	 *
	 * @param animation
	 *            The desired Timeline
	 */
	public void setAnimation(Timeline animation) {
		this.animation = animation;
	}

	public List<Integer> getScores() {
		List<Integer> scores = new ArrayList<>();
		for (IPlayActor actor: getCurrentLevel().getMainCharacters()) {
			scores.add(actor.getAttribute(AttributeType.POINTS).getMyValue());
		}
		return scores;
	}

	public void setHUDInfoFile(String location) {
		info.setHUDFileLocation(location);
	}

	public String getHUDInfoFile() {
		return info.getHUDFileLocation();
	}

	public CollisionDetection getMyCollisonDetector() {
		return myCollisionDetector;
	}

	public Map<String, Set<IGameElement>> getActiveTriggers() {
		return activeTriggers;
	}

	public void setActiveTriggers(Map<String, Set<IGameElement>> activeTriggers) {
		this.activeTriggers = activeTriggers;
	}

	public int getLevelTime() {
		return levelTime.getValue();
	}

	public void setLevelTime(int step) {
		this.levelTime.setValue(step);
	}

	public int getGlobalTime() {
		return globalTime.getValue();
	}

	public void setGlobalTime(int time) {
		this.globalTime.setValue(time);
	}

	public void resetLevelTime() {
		levelTime.setValue(1);
	}

	public void toggleSound() {
		if (!isPaused()) {
			sfxOff = !sfxOff;
		}
	}

	public void toggleMusic() {
		if (!isPaused()) {
			musicOff = !musicOff;
			soundEngine.soundtrackSetMute(musicOff);
		}
	}

	public void toggleSoundPause() {
		try {
			if (isPaused()) {
				soundEngine.allSetMute(true);
			} else {
				soundEngine.soundtrackSetMute(musicOff);
				soundEngine.allSoundsSetMute(sfxOff);
			}
		} catch (Exception e) {
			System.out.println("FAILED");
		}
	}

	public void playSound(String key) {
		if (!sfxOff) {
			soundEngine.playSound(key);
		}
	}

	public boolean isPaused() {
		return animation.getStatus() == Status.PAUSED;
	}
	
	public Property<Integer> getGlobalTimeProperty() {
		return globalTime;
	}
	
	public Property<Integer> getLevelTimeProperty() {
		return this.levelTime;
	}

}