package gameplayer.view;

import java.util.Arrays;
import java.util.Observable;
import java.util.ResourceBundle;

import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IDisplayActor;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.KeyTrigger;
import gui.view.Screen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * This class serves as the private interface that a Game screen must implement
 * in order to be able to add visual elements of the game to the screen. It is
 * the container for all the game contents that will be displayed on the screen.
 * 
 * @author cmt57
 */

public class GameScreen extends Observable implements IGameScreen {

	private SubScene mySubscene;
	private Group mySubgroup;
	private Camera myCamera;
	private double myEndHorizontal;
	private double myEndVertical;

	private ResourceBundle myResources;
	private static final String GAME_RESOURCE = "gameGUI";

	public GameScreen(Camera camera) {
		setMySubgroup(new Group());
		mySubscene = new SubScene(getMySubgroup(), Screen.SCREEN_WIDTH, 500);
		mySubscene.setFill(Color.ALICEBLUE);
		mySubscene.setFocusTraversable(true);
		mySubscene.setOnKeyPressed(e -> handleScreenEvent(e));
		mySubscene.setOnMouseClicked(e -> {
			handleScreenEvent(e);
			mySubscene.requestFocus();
		});
		this.myCamera = camera; ///
		mySubscene.setCamera(camera);
		this.myResources = ResourceBundle.getBundle(GAME_RESOURCE);
	}

	public SubScene getScene() {
		return mySubscene;
	}

	/**
	 * Will add a node to the screen's scene representing the given actor's
	 * view.
	 * 
	 * @param actor
	 *            an instance of IActor
	 */
	public void addActor(IDisplayActor actor) {
		((Actor) actor).restoreImageView();
		getMySubgroup().getChildren().add(actor.getImageView());
	}

	public void removeActor(IDisplayActor a) {
		mySubgroup.getChildren().remove(a.getImageView());
	}

	public void addBackground(Level level) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(level.getMyBackgroundImgName()));
		
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true); // amy added this to resize background to fit height
		imageView.setFitHeight(level.getMyBackgroundHeight()); // amy also added this
		level.setMyImageView(imageView);
		double ratio = image.getHeight()/imageView.getFitHeight();
		double width = image.getWidth() / ratio;
		this.myEndHorizontal = width;
		this.myEndVertical = imageView.getFitHeight();
		
		
		ImageView imageView2 = new ImageView(image);
		imageView2.setPreserveRatio(true); // amy also added
		imageView2.setFitHeight(level.getMyBackgroundHeight()); //amy also added SORRY IF THIS RUINS THINGS - CAN BE TAKEN OUT
		imageView2.setX(imageView.getImage().getWidth());

		level.getMyBackgroundX().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue o, Object oldVal, Object newVal) {
				// TODO Watch that magic constant!
				imageView2.setX((Double) newVal + imageView.getImage().getWidth() - 10);
			}
		});

		getMySubgroup().getChildren().addAll(imageView, imageView2);

	}

	/**
	 * Will receive events on screen and then pass to the game engine's handler
	 * to determine what action to take
	 * 
	 * @param e
	 *            event
	 */
	public void handleScreenEvent(Event e) {
		ITrigger trigger = null;
		if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
			trigger = handleClick(((MouseEvent) e).getSceneX() + myCamera.getTranslateX(), ((MouseEvent) e).getSceneY() + myCamera.getTranslateY());
		} else if (e.getEventType() == KeyEvent.KEY_PRESSED) {
			trigger = handleKeyPress(((KeyEvent) e).getCode());
		}
		setChanged();
		Object[] methodArg = { "handleTrigger", trigger };
		notifyObservers(Arrays.asList(methodArg));
	}

	private ClickTrigger handleClick(double x, double y) {
		ClickTrigger clickTrigger = new ClickTrigger(x, y);
		return clickTrigger;
	}

	private KeyTrigger handleKeyPress(KeyCode key) {
		return new KeyTrigger(key);
	}

	public void clearGame() {
		myCamera.setTranslateX(0.0);
		getMySubgroup().getChildren().clear();
	}

	public Group getMySubgroup() {
		return mySubgroup;
	}

	public void setMySubgroup(Group mySubgroup) {
		this.mySubgroup = mySubgroup;
	}

	@Override
	public void changeCamera(double x, double y) {
		if (x < myEndHorizontal - getScene().getWidth() && x > 0) {
			myCamera.setTranslateX(x);
		}
		if (y > 0 && y < myEndVertical - getScene().getHeight()) {
			myCamera.setTranslateY(y);
		}
	}

	@Override
	public void disableMusic(boolean disable) {
		// Depracated method: sound is handled through gameController now

	}

	@Override
	public void disableSoundFX(boolean disable) {
		// Depracated method: sound is handled through gameController now

	}

	public void terminateGame(boolean win) {
		String gameStatus = "";
		if (win) {
			gameStatus = "win";
		}
		else {
			gameStatus = "lose";
		}
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, myResources.getString(gameStatus + "EndMessage"), ButtonType.YES,
				ButtonType.NO);
		alert.setOnCloseRequest( e -> {
			if (alert.getResult() == ButtonType.YES) {
				saveScorePrompt();
			}
			else {
				restartGamePrompt();
			}
		});
		alert.show();

	}
	

	private void restartGamePrompt() {
		Alert endAlert = new Alert(Alert.AlertType.CONFIRMATION, myResources.getString("RestartMessage"), ButtonType.YES,
				ButtonType.NO);
		endAlert.show();
		endAlert.showingProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				Object[] args = {"leave", null};
				if (endAlert.getResult() == ButtonType.YES) {
					Object[] yesArgs = { "restartGame", null };
					args = yesArgs;
				}
				setChanged();
				notifyObservers(Arrays.asList(args));
			}
		});
		
	}

	public void saveScorePrompt() {
		TextInputDialog dialog = new TextInputDialog(myResources.getString("Name"));
		dialog.setContentText(myResources.getString("SaveMessage"));
		dialog.show();
		dialog.setOnCloseRequest(e -> restartGamePrompt());
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			@Override
			public String call(ButtonType b) {
				String answer = null;
				if (b == ButtonType.OK) {
					setChanged();
					Object[] args = { myResources.getString("SaveScore"), dialog.getEditor().getText() };
					notifyObservers(Arrays.asList(args));
					answer = dialog.getEditor().getText();
				}
				return answer;
				
			}
		});
		
	}

	public void restartGame() {
		clearGame();	
	}

	@Override
	public void togglePause() {
		getScene().setDisable(true);
		
	}

	@Override
	public void toggleUnPause() {
		getScene().setDisable(false);
		
	}

}