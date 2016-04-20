package gameplayer.view;

import java.util.List;
import java.util.Observable;

import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.ITrigger;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.KeyTrigger;
import gui.view.Screen;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/** 
 * This class serves as the private interface that a Game screen must implement in order to be able to add visual elements of the game to the screen.
 * It is the container for all the game contents that will be displayed on the screen.
 * @author cmt57
 */

public class GameScreen extends Observable implements IGameScreen {
	
	private SubScene mySubscene;
	private Group mySubgroup;
	private Camera myCamera;
	private double myEndHorizontal;
	private double myEndVertical;
	
	public GameScreen(Camera camera){
		setMySubgroup(new Group());
		setMySubscene(new SubScene(getMySubgroup(),Screen.SCREEN_WIDTH, 500));
		getMySubscene().setFill(Color.ALICEBLUE);
		getMySubscene().setFocusTraversable(true);
		getMySubscene().setOnKeyPressed(e -> handleScreenEvent(e));
		getMySubscene().setOnMouseClicked(e -> handleScreenEvent(e));
		this.myCamera = camera; ///
		mySubscene.setCamera(camera);
	}
	
	
	public SubScene getScene(){
		return getMySubscene();
	}
	
	/**
	 * Will add a node to the screen's scene representing the given actor's view.
	 * @param actor an instance of IActor
	 */
	public void addActor (Actor actor){
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(actor.getMyImageViewName())));
		actor.setMyImageView(imageView);
		getMySubgroup().getChildren().add(actor.getMyImageView());//
	}
	
	public void removeActors(List<Actor> actors){
		for(Actor a: actors){
			mySubgroup.getChildren().remove(a.getMyImageView());
		}
	}
	

	public void addBackground(Level level) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(level.getMyBackgroundImgName()));
		this.myEndHorizontal = image.getWidth();
		this.myEndVertical = image.getHeight();
		ImageView imageView = new ImageView(image);
		level.setMyImageView(imageView);
		
		
		ImageView imageView2 = new ImageView(image);
		imageView2.setX(imageView.getImage().getWidth());
		
		level.getMyBackgroundX().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue o, Object oldVal, Object newVal) {
				//TODO Watch that magic constant!
				imageView2.setX((Double) newVal + imageView.getImage().getWidth() - 10);
			}
		});
		
		getMySubgroup().getChildren().addAll(imageView, imageView2); 
		//getMySubgroup().getChildren().add(imageView);
		
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
			//myCamera.setTranslateX(changeCamera(93.4, 0));
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
		myCamera.setTranslateX(0);
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


	@Override
	public void addActor(IActor actor) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void changeCamera(int x, int y) {
		/*
		if (myCamera.getTranslateX()+x < myEndHorizontal - getScene().getWidth() &&
				myCamera.getTranslateX()+x > 0) {
			myCamera.setTranslateX(myCamera.getTranslateX()+x);
		}
		
		if (myCamera.getTranslateY()+y > 0 && myCamera.getTranslateY()+y < myEndVertical - getScene().getHeight()) {		
			myCamera.setTranslateY(myCamera.getTranslateY()+y);
		}*/
	}


	@Override
	public void disableMusic(boolean disable) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void disableSoundFX(boolean disable) {
		// TODO Auto-generated method stub
		
	}
	

}
