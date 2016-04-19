package gameplayer.view;

import java.util.List;
import java.util.Observable;

import gameengine.model.Actor;
import gameengine.model.IDisplayActor;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.KeyTrigger;
import gui.view.Screen;
import javafx.event.Event;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * This class serves as the private interface that a Game screen must implement in order to be able to add visual elements of the game to the screen.
 * It is the container for all the game contents that will be displayed on the screen.
 * @author cmt57
 */

public class GameScreen extends Observable {
	
	//@XStreamOmitField
	private SubScene mySubscene;
	//@XStreamOmitField
	private Group mySubgroup;
	//@XStreamOmitField
	private Camera camera;
	
	public GameScreen(Camera camera){
		setMySubgroup(new Group());
		setMySubscene(new SubScene(getMySubgroup(),Screen.SCREEN_WIDTH, 500));
		getMySubscene().setFill(Color.ALICEBLUE);
		getMySubscene().setFocusTraversable(true);
		getMySubscene().setOnKeyPressed(e -> handleScreenEvent(e));
		getMySubscene().setOnMouseClicked(e -> handleScreenEvent(e));
		this.camera = camera; ///
		mySubscene.setCamera(camera);
	}
	
	
	public SubScene getScene(){
		return getMySubscene();
	}
	
	/**
	 * Will add a node to the screen's scene representing the given actor's view.
	 * @param actor an instance of IActor
	 */
	public void addActor (IDisplayActor actor){
		actor.setMyImageViewName(actor.getMyImageViewName());
		getMySubgroup().getChildren().add(actor.getMyImageView());
	}
	
	public void removeActor(IDisplayActor a){
		mySubgroup.getChildren().remove(a.getMyImageView());
	}
	

	public void addBackground(String filepath) {
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(filepath)));
		imageView.toBack();
		getMySubgroup().getChildren().add(imageView);
		
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
			//camera.setTranslateX(camera.getTranslateX()+94.3);
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
		camera.setTranslateX(0);
		getMySubgroup().getChildren().clear();
		
//		for(Node n: getMySubgroup().getChildren()){
//			System.out.println("removing");
//			getMySubgroup().getChildren().remove(n);
//		}
	}


	public Group getMySubgroup() {
		return mySubgroup;
	}


	public void setMySubgroup(Group mySubgroup) {
		this.mySubgroup = mySubgroup;
	}


	public SubScene getMySubscene() {
		return mySubscene;
	}


	public void setMySubscene(SubScene mySubscene) {
		this.mySubscene = mySubscene;
	}
	

}
