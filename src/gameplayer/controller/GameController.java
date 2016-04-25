package gameplayer.controller;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gamedata.controller.HighScoresController;
import gameengine.controller.Game;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IDisplayActor;
import gameengine.model.IPlayActor;
import gameplayer.view.GameScreen;
import gameplayer.view.HUDScreen;
import javafx.scene.ParallelCamera;

/**
 * This class serves as the private interface that any game controller must
 * implement in order to set up the IGame with the appropriate levels and actors
 * for the game engine to interact with
 * 
 * @author cmt57
 */

public class GameController implements Observer, IGameController {
	@XStreamOmitField
	private Game model;
	@XStreamOmitField
	private GameScreen view;
	@XStreamOmitField
	private HUDScreen hud;
	@XStreamOmitField
	private ResourceBundle myResources;
	@XStreamOmitField
	private static final String GAME_CONTROLLER_RESOURCE = "gameActions";

	public GameController(Game game) {
		this.setGame(game);
		this.setGameView(new GameScreen(new ParallelCamera()));
		this.initialize(game.getInfo().getMyCurrentLevelNum()); // note: main
																// actor is
																// define at
																// this line
		this.myResources = ResourceBundle.getBundle(GAME_CONTROLLER_RESOURCE);
	}

	/**
	 * Sets the current game to the given Game
	 * 
	 * @param Game
	 */
	@Override
	public void setGame(Game myGame) {
		model = myGame;
		model.addObserver(this);
	}

	/**
	 * Sets the basic game view to the given GameScreen
	 * 
	 * @param GameScreen
	 */
	public void setGameView(GameScreen myGameView) {
		view = myGameView;
		view.addObserver(this);
	}

	public void setHUD(HUDScreen hud) {
		this.hud = hud;
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
	 * Will reflect changes in actors' positions or values in a new "step" to
	 * simulate one round of animation.
	 */
	public void update() {

	}

	/**
	 * Will ask game engine to check interactions that need to be resolved.
	 */
	public void checkInteractions() {

	}

	/**
	 * Will resolve any front end outcomes determined by logic in backend
	 * checking interactions.
	 */
	public void cleanUp() {

	}

	/**
	 * Will stop the animation timeline.
	 */
	public void endGame() {
		// TODO fix resource also implement saving functionality

		togglePause();
		view.terminateGame();
	}
	// Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Game over! Do you
	// want to save your score?", ButtonType.YES, ButtonType.NO);
	// alert.show();
	// alert.showingProperty().addListener((observable, oldValue, newValue) -> {
	// if (!newValue) {
	// if (alert.getResult() == ButtonType.YES) {
	// saveScorePrompt();
	// }
	// }
	// });

	// }

	// private void saveScorePrompt() {
	// TextInputDialog dialog = new TextInputDialog("Name");
	// dialog.setContentText("Please enter your name if you want to save your
	// score");
	// dialog.show();
	// dialog.setResultConverter(new Callback<ButtonType, String>() {
	// @Override
	// public String call(ButtonType b) {
	// if (b == ButtonType.OK) {
	// saveGameScore(dialog.getEditor().getText());
	// return dialog.getEditor().getText();
	// }
	// else {
	// return null;
	// }
	// }
	// });
	// }
	//
	private void saveGameScore(String name) {
		HighScoresController c = new HighScoresController(this.getGame().getInitialGameFile());
		c.saveHighScore(getGame().getScore(), name);

	}

	/**
	 * Will stop the animation timeline.
	 */
	public void winGame() {
		System.out.println("game won");
	}

	public void nextLevel() {
		view.clearGame();
		model.nextLevel();
		begin();
	}

	public GameScreen getView() {
		return view;
	}

	public Game getGame() {
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
		// System.out.println(methodName);
		// System.out.println(methodName.equals("addActor"));
		try {
			if (methodName.equals("addActor")) { // TODO: Change to reflection,
													// pass in Actor object from
													// arg.get(1)
				this.addActor(new Actor());
			} else if (myResources.getString(methodName).equals("null")) {
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

	public void toggleSound() {
		System.out.println("toggle sound unimplemented");
	}

	public void toggleMusic() {
		System.out.println("toggle music unimplemented");
	}

	public void togglePause() {
		getGame().getAnimation().pause();
		view.getMySubscene().setDisable(true);
	}

	public void toggleUnPause() {
		getGame().getAnimation().play();
		view.getMySubscene().setDisable(false);
	}

	public void restartGame() {
		System.out.println("restart game");
		System.out.println(model.getInfo().getMyCurrentLevelNum() + " game level");
		initialize(model.getInfo().getMyCurrentLevelNum());
	}

	public void updateCamera() {
		if (model.getCurrentLevel().getMainCharacter() != null) {
			if (model.getCurrentLevel().getMyScrollingDirection().equals(myResources.getString("DirectionH"))) {
				view.changeCamera(model.getCurrentLevel().getMainCharacter().getX(), 0);
			} else {
				view.changeCamera(0, model.getCurrentLevel().getMainCharacter().getY());
			}
		}
	}

	@Override
	public void preview() {
		// TODO Auto-generated method stub

	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates attributes
	 */
	public void updateAttribute() {
		model.updateAttribute();
	}

}