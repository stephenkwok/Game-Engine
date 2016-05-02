package gameplayer.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gamedata.controller.HighScoresController;
import gamedata.controller.ParserController;
import gameengine.controller.Game;
import gameengine.controller.IGame;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IDisplayActor;
import gameengine.model.IPlayActor;
import gameplayer.view.GameScreen;
import gameplayer.view.IGameScreen;
import javafx.scene.ParallelCamera;

/**
 * This class serves as the private interface that any game controller must
 * implement in order to set up the IGame with the appropriate levels and actors
 * for the game engine to interact with
 * 
 * @author cmt57
 */

public class GameController extends Observable implements Observer, IGameController {
	private IGame model;
	@XStreamOmitField
	private IGameScreen view;
	@XStreamOmitField
	private ResourceBundle myResources;
	@XStreamOmitField
	private static final String GAME_CONTROLLER_RESOURCE = "gameActions";
	private PlayType myMode;

	public GameController(Game game) {
		this(game, PlayType.PLAY);
	}
	public GameController(Game game, PlayType mode) {
		this.setGame(game);
		this.setGameView(new GameScreen(new ParallelCamera()));
		this.initialize(game.getInfo().getMyCurrentLevelNum()); // note: main
																// actor is
																// define at
																// this line
		this.myResources = ResourceBundle.getBundle(GAME_CONTROLLER_RESOURCE);
		this.myMode = mode;
	}

	/**
	 * Sets the current game to the given Game
	 * 
	 * @param Game
	 */
	@Override
	public void setGame(Game myGame) {
		model = myGame;
		((Observable) model).addObserver(this);
	}

	/**
	 * Sets the basic game view to the given GameScreen
	 * 
	 * @param GameScreen
	 */
	public void setGameView(GameScreen myGameView) {
		view = myGameView;
		((Observable) view).addObserver(this);
	}


	/**
	 * Will initialize the backend (game engine) with the current level's
	 * information and actor information to set up the game for playing. Will
	 * visualize that backend too.
	 * 
	 * @param level
	 *            an int representing the level to be played
	 */
	public void initialize(int level) {
		model.setCurrentLevel(level);
		// ObservableMap<String, Object> a = FXCollections.observableHashMap();
		// a.addListener(new MapChangeListener<String, Object>() {
		// @Override
		// public void onChanged(Change<? extends String, ? extends Object>
		// change) {
		// if(change!=null && hud != null)
		// hud.handleChange(change);
		// }
		// });
		// a.put("Points", 0);
		begin();
		
	}

	/**
	 * Will play the animation timeline.
	 */
	public void begin() {
		Level current = model.getCurrentLevel();
		view.clearGame();
		view.addBackground(current);
		for (IPlayActor actor : model.getActors()) {
			view.addActor((IDisplayActor) actor);
		}
		this.toggleUnPause();
		model.startGame();
	}

	/**
	 * Will stop the animation timeline.
	 */
	public void endGame(boolean win) {
		model.stopGame();
		view.togglePause();
		if (myMode == PlayType.PLAY) {
			view.terminateGame(win);
		}
	}
	
	public void winGame() {
		endGame(true);
	}

	public void loseGame() {
		endGame(false);
	}
	

	private void saveGameScore(String name) throws SAXException, IOException, TransformerException, ParserConfigurationException {
		HighScoresController c = new HighScoresController(this.getGame().getInfo().getMyFile());
		c.saveHighScore(getGame().getScores(), Arrays.asList(name.split(",")));
	}

	
	public void nextLevel() {
		if (model.nextLevel()) {
			view.clearGame();
			model.nextLevel();
			model.resetLevelTime();
			begin();
		}
		else {
			winGame();
		}
	}

	@Override
	public IGameScreen getView() {
		return view;
	}

	public IGame getGame() {
		return model;
	}

	private void updateActors() {
		for (IPlayActor a : model.getDeadActors()) {
			view.removeActor((IDisplayActor) a);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		List<Object> myList = (List<Object>) arg;
		String methodName = (String) myList.get(0);
		try {
			if(methodName.equals("addActor")){ 
				this.addActor((Actor)myList.get(1));
			}else
			if (myResources.getString(methodName).equals("null")) {
				this.getClass().getDeclaredMethod(methodName).invoke(this);
			} else if (myResources.getString(methodName).equals("String")) {
				this.getClass().getDeclaredMethod(methodName, String.class).invoke(this, (String) myList.get(1));
			} else {
				Class<?> myClass = Class.forName(myResources.getString(methodName));
				Object arg2 = myClass.cast(myList.get(1));
				model.getClass().getDeclaredMethod(methodName, myClass).invoke(model, arg2);
			}
		} catch (IllegalArgumentException | SecurityException | ClassNotFoundException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void addActor(Actor a) {
		model.addActor(a);
		view.addActor(a);
	}

	@Override
	public void toggleSound() {
		getGame().toggleSound();
	}
	
	@Override
	public void toggleMusic() {
		getGame().toggleMusic();
	}
	
	@Override
	public void togglePause() {
		model.stopGame();
		view.togglePause();
	}

	@Override
	public void toggleUnPause() {
		model.toggleUnPause();
		view.toggleUnPause();
	}

	@Override
	public void restartGame() {
		togglePause();
		view.restartGame();
		ParserController parserController = new ParserController();
		Game initialGame = parserController.loadforPlaying(new File(getGame().getInitialGameFile()));
		setGame(initialGame);
		initialize(0);
		Object[] args = {"setUpHUDScreen", null};
		setChanged();
		notifyObservers(Arrays.asList(args));
	}

	public void updateCamera() {
		if (model.getCurrentLevel().getMainCharacter() != null) {
			if (model.getCurrentLevel().getMyScrollingDirection().equals(myResources.getString("DirectionH"))) {
				try {
					view.changeCamera(model.getCurrentLevel().getMainCharacters().get(0).getX(), 0);
				} catch (Exception e) {
					model.stopGame();
					e.printStackTrace();
				}
			} else {
				view.changeCamera(0, model.getCurrentLevel().getMainCharacters().get(0).getY());
			}
		}
	}
	
	public void leave() {
		Object[] args = {"goToSplash", null};
		setChanged();
		notifyObservers(Arrays.asList(args));
		
	}
	
	private void playSound(String key) {
		model.playSound(key);
	}

	
}