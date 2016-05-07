// This entire file is part of my masterpiece.
// Carine Torres (cmt57)
// The purpose of this code is to represent the Game visually.  It features image views for the actors, background, etc.  It handles user input (i.e. mouse clicks and key presses) that affect the Game.
// I think this code features key design features in that it facilitates a clean MVC design model.  By extending Observable (where the GameController is its Observer), the GameScreen remains connected to a larger entity that can execute actions on behalf of the screen, but it remains isolated from that entity in that it doesn't have control over it.  The GameScreen, by not storing the GameController within it, thus allows the model to remain encapsulated because it does not have access to the actions that the GameController can perform on the Game by not accessing the GameController directly.  At the same time, it can notify the GameController to do something to allow the Game to still be connected to a user interface. Thus, the Observable nature of the GameScreen facilitates encapsulation while maintaining functionality.  Additionally, the commendable separation of the View from the Model is evident in GameScreen in that any functionality only deals with front-end objects (such as image views or cameras).  If anything more needs to happen to the model, that's when the GameController is notified to "restart" the Game object itself (the GameScreen doesn't reinitialize any back-end itself).  
// Additionally, I think this code features good design as far as striving for DRY code.  I refactored the means of notifying an observer by creating a method that passed over the argument in the expected format for each time an observer was notified so that, for example, setChanged() wouldn't have to be implemented each time, and neither would the process of hardcoding an array and then converting it to an array list.  The notify method implements that process once to avoid its repetition.  Additionally, the functionality of an alert was refactored  so as to avoid duplicate code for how to both display an alert and respond to its result.  Now, any kind of a confirmation alert can function using the createAlert method.  
// The createAlert method also helps improve extensibility because it incorporates the use of a resource file to dictate how to respond to user input on the alert without hardcoding those responses.  Now, any alert created with the createAlert method has the flexibility of modifying its response behaviors without modifying code itself.  Merely, one has to adjust the associated properties file (gameGui.properties) to change the methods that are mapped to that alert's key, and the responses will change accordingly despite no code changing.  Thus, the createAlert method strives to achieve the open-close principle by being closed to modification even while open to extension.
// Lastly, the GameScreen class uses reflection to adhere to the open-close principle and allow for modification of behaviors without code behavior.  The generic reflection facilitates a case where an end game prompt, say, decides to not save scores but instead, view high scores because saving high scores is not hardcoded as the next method to invoke in the end game prompt.  The reflection just abides by whatever is dictated in the properties file, regardless of what is in the properties file.  
// Note: though this code master piece is a bit over 200 lines, it is because of the copious amounts of import statements and instance variables.

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
import javafx.util.Callback;

/**
 * This class serves as the private interface that a Game screen must implement
 * in order to be able to add visual elements of the game to the screen. It is
 * the container for all the game contents that will be displayed on the screen.
 * 
 * @author cmt57
 */

public class GameScreen extends Observable implements IGameScreen {
	
	private static int BACKGROUND_OFFSET = 10;
	private static String MESSAGE = "Message";
	private static String AFFIRM = "Affirm";
	private static final int SUB_HEIGHT = 500;
	private static String DENY = "Deny";
	private SubScene mySubscene;
	private Group mySubgroup;
	private Camera myCamera;
	private double myEndHorizontal;
	private double myEndVertical;
	private ResourceBundle myResources;
	private static final String GAME_RESOURCE = "gameGUI";

	public GameScreen(Camera camera) {
		mySubgroup = new Group();
		mySubscene = new SubScene(mySubgroup, Screen.SCREEN_WIDTH, SUB_HEIGHT);
		mySubscene.setFocusTraversable(true);
		mySubscene.setOnKeyPressed(e -> handleScreenEvent(e));
		mySubscene.setOnMouseClicked(e -> {
			handleScreenEvent(e);
			mySubscene.requestFocus();
		});
		this.myCamera = camera; 
		mySubscene.setCamera(camera);
		this.myResources = ResourceBundle.getBundle(GAME_RESOURCE);
	}

	public SubScene getScene() {
		return mySubscene;
	}

	/**
	 * Will add a node to the screen's scene representing the given actor's view.
	 * @param actor an instance of IActor
	 */
	public void addActor(IDisplayActor actor) {
		((Actor) actor).restoreImageView();
		mySubgroup.getChildren().add(actor.getImageView());
	}

	public void removeActor(IDisplayActor a) {
		mySubgroup.getChildren().remove(a.getImageView());
	}

	public void addBackground(Level level) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(level.getMyBackgroundImgName()));
		ImageView imageView = setUpImageView(image, level.getMyBackgroundHeight());
		this.myEndHorizontal = imageView.getBoundsInParent().getWidth();
		this.myEndVertical = imageView.getBoundsInParent().getHeight();
		level.setMyImageView(imageView);
		ImageView imageView2 = setUpImageView(image, level.getMyBackgroundHeight());
		imageView2.setX(imageView.getImage().getWidth());
		level.getMyBackgroundX().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue o, Object oldVal, Object newVal) {
				imageView2.setX((Double) newVal + imageView.getImage().getWidth() - BACKGROUND_OFFSET);
			}
		});
		mySubgroup.getChildren().addAll(imageView, imageView2);
	}
	
	private ImageView setUpImageView(Image image, double fitHeight) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(fitHeight);
		return imageView;
	}

	/**
	 * Will receive events on screen and then pass to the game engine's handler
	 * to determine what action to take
	 * @param e event
	 */
	public void handleScreenEvent(Event e) {
		ITrigger trigger = null;
		if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
			trigger = handleClick(((MouseEvent) e).getSceneX() + myCamera.getTranslateX(), ((MouseEvent) e).getSceneY() + myCamera.getTranslateY());
		} else if (e.getEventType() == KeyEvent.KEY_PRESSED) {
			trigger = handleKeyPress(((KeyEvent) e).getCode());
		}
		notify("handleTrigger", trigger);
	}

	/**
	 * Accounts for user's click input on environment
	 * @param x
	 * @param y
	 * @return clickTrigger
	 */
	private ClickTrigger handleClick(double x, double y) {
		ClickTrigger clickTrigger = new ClickTrigger(x, y);
		return clickTrigger;
	}

	/**
	 * Makes a user input's key into a KeyTrigger
	 * @param key
	 * @return KeyTrigger
	 */
	private KeyTrigger handleKeyPress(KeyCode key) {
		return new KeyTrigger(key);
	}

	public void clearGame() {
		myCamera.setTranslateX(0.0);
		mySubgroup.getChildren().clear();
	}
	
	/**
	 * Repositions the camera's position
	 */
	@Override
	public void changeCamera(double x, double y) {
		if (x < myEndHorizontal - getScene().getWidth() && x > 0) {
			myCamera.setTranslateX(x);
		} 
		if (y > 0 && y < myEndVertical - getScene().getHeight()) {
			myCamera.setTranslateY(y);
		} 
	}
	
	public void terminateGame(boolean win) {
		String gameStatus = "Lose";
		if (win) {
			gameStatus = "Win";
		}
		createAlert(Thread.currentThread().getStackTrace()[1].getMethodName() + gameStatus);
	}

	/**
	 * Prompts the user to decide whether to replay or not and then notifies the game controller of this decision
	 */
	private void restartGamePrompt() {
		createAlert(Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	private void restart() {
		notify("restartGame", null);	
	}
	
	private void splash() {
		notify("leave", null);
	}
	
	private void notify(String method, Object parameter) {
		Object[] args = {method, parameter};
		setChanged();
		notifyObservers(Arrays.asList(args));	
	}

	/**
	 * Prompts the user to decide whether to save the game or not and then notifies the game controller of this decision
	 */
	private void saveScorePrompt() {
		TextInputDialog dialog = new TextInputDialog(myResources.getString("Name"));
		dialog.setContentText(myResources.getString("SaveMessage"));
		dialog.show();
		dialog.setOnCloseRequest(e -> restartGamePrompt());
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			@Override
			public String call(ButtonType b) {
				String answer = null;
				if (b == ButtonType.OK) {
					GameScreen.this.notify(myResources.getString("SaveScore"), dialog.getEditor().getText());
					answer = dialog.getEditor().getText();
				}
				return answer;	
			}
		});
	}
	
	private void createAlert(String key) {
		String alertKey = myResources.getString(key);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, myResources.getString(alertKey + MESSAGE), ButtonType.YES, ButtonType.NO);
		alert.setOnCloseRequest( e -> {
			String method = myResources.getString(alertKey + DENY);
			if (alert.getResult() == ButtonType.YES) {
				method = myResources.getString(alertKey + AFFIRM);
			}
			try {
				this.getClass().getDeclaredMethod(method).invoke(this);
			} catch (Exception e1) {
				showError(e1.getMessage());
			}
		});
		alert.show();
	}
	
	public void showError(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setContentText(message);
	    alert.show();
	}

	@Override
	public void togglePause(boolean pause) {
		getScene().setDisable(pause);
	}

}