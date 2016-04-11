package gameplayer.view;

import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.ITrigger;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.KeyTrigger;
import javafx.event.Event;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/** 
 * This class serves as the private interface that a Game screen must implement in order to be able to add visual elements of the game to the screen.
 * It is the container for all the game contents that will be displayed on the screen.
 * @author cmt57
 */

public class GameScreen extends Observable {
	private SubScene myScene;
	private Group myGroup;

	
	private Camera camera;
	
	public GameScreen(Scene mainScene, Camera camera){
		myGroup = new Group();
		myScene = new SubScene(myGroup,600,400);
		mainScene.setOnKeyPressed(e->handleScreenEvent(e));//
		this.camera = camera; ///
	}
	
	public SubScene getScene(){
		return myScene;
	}
	
	/**
	 * Will add a node to the screen's scene representing the given actor's view.
	 * @param actor an instance of IActor
	 */
	public void addActor (Actor actor){
		myGroup.getChildren().add(actor.getImageView());//
	}
	
	public void addToRoot(Node n){
		myGroup.getChildren().add(n);
	}
	
	/**
	 * Will receive events on screen and then pass to the game engine's handler to determine what action to take
	 * @param e event 
	 */
	public void handleScreenEvent (Event e){
		if(e.getEventType()==MouseEvent.MOUSE_CLICKED){
			ITrigger trigger = handleClick(((MouseEvent)e).getSceneX(),((MouseEvent)e).getSceneY());
			setChanged();
			notifyObservers(trigger);
		}
		else if(e.getEventType()==KeyEvent.KEY_PRESSED){
			camera.setTranslateX(camera.getTranslateX()+5);
			ITrigger trigger = handleKeyPress(((KeyEvent)e).getCode());
			setChanged();
			notifyObservers(trigger);
		}
	}
	
	private ClickTrigger handleClick(double x, double y){
		ClickTrigger clickTrigger = new ClickTrigger();
		clickTrigger.setClickedAt(x, y);
		return clickTrigger;
	}
	
	private KeyTrigger handleKeyPress(KeyCode key){
		return new KeyTrigger(key);
	}
	
	public void clearGame(){
		myGroup.getChildren().clear();
	}
}
