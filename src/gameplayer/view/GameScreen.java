package gameplayer.view;

import java.util.Observable;

import gameengine.controller.Game;
import gameengine.model.Actor;
import gameengine.model.ITrigger;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.KeyTrigger;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/** 
 * This class serves as the private interface that a Game screen must implement in order to be able to add visual elements of the game to the screen.
 * It is the container for all the game contents that will be displayed on the screen.
 * @author cmt57
 */

public class GameScreen extends Observable {
	private Group mySubgroup;
	private SubScene mySubscene;

	
	public GameScreen() {
		mySubgroup = new Group();
		mySubscene = new SubScene(mySubgroup, 700, 500);
		mySubscene.setFocusTraversable(true);
		mySubscene.setOnKeyPressed(e -> handleKeyEvent(e));
		mySubscene.setOnMouseClicked(e -> handleMouseEvent(e));
	}

	private void handleKeyEvent(KeyEvent e) {
		ITrigger trigger = handleKeyPress(((KeyEvent)e).getCode());
		setChanged();
		notifyObservers(trigger);
	}
	
	private void handleMouseEvent(MouseEvent e) {
		ITrigger trigger = handleClick(((MouseEvent)e).getSceneX(),((MouseEvent)e).getSceneY());
		setChanged();
		notifyObservers(trigger);
	}

	public SubScene getScene(){
		return mySubscene;
	}
	
	/**
	 * Will add a node to the screen's scene representing the given actor's view.
	 * @param actor an instance of IActor
	 */
	public void addActor (Actor actor){
		mySubgroup.getChildren().add(actor.getImageView());//
	}

	
	private ClickTrigger handleClick(double x, double y){
		ClickTrigger clickTrigger = new ClickTrigger();
		clickTrigger.setClickedAt(x, y);
		return clickTrigger;
	}
	
	private KeyTrigger handleKeyPress(KeyCode key){
		return new KeyTrigger(key);
	}
	
}
