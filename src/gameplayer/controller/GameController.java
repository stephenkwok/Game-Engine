package gameplayer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gameengine.controller.Game;
import gameengine.controller.Level;
import gameengine.model.IDisplayActor;
import gameengine.model.IPlayActor;
import gameengine.model.ITrigger;
import gameplayer.view.GameScreen;
import gameplayer.view.HUDScreen;
import javafx.collections.MapChangeListener.Change;
import javafx.scene.control.Alert;


/** 
 * This class serves as the private interface that any game controller must implement in order to set up the IGame with the appropriate levels and actors for the game engine to interact with
 * @author cmt57
 */

public class GameController implements Observer {
	@XStreamOmitField
	private Game model;
	@XStreamOmitField
	private GameScreen view;
	@XStreamOmitField
	private HUDScreen hud;
	
	/**
	 * Sets the current game to the given Game
	 * @param Game
	 */
	public void setGame (Game myGame){
		model = myGame;
		model.addObserver(this);
	}
	
	/**
	 * Sets the basic game view to the given GameScreen
	 * @param GameScreen
	 */
	public void setGameView (GameScreen myGameView){
		view = myGameView;
		view.addObserver(this);
	}
	
	public void setHUD(HUDScreen hud) {
		this.hud = hud;
	}
	
	
	/**
	 * Will initialize the backend (game engine) with the current level's information and actor information to set up the game for playing.  Will visualize that backend too. 
	 * @param level an int representing the level to be played
	 */
	public void initialize (int level){
		model.setCurrentLevel(level);
		begin();
	}
	
	/**
	 * Will play the animation timeline. 
	 */
	public void begin (){
		Level current = model.getCurrentLevel();
		view.clearGame();
		view.addBackground(current.getMyBackgroundImgName());
		for(IPlayActor actor: model.getActors()){
			view.addActor((IDisplayActor)actor);
		}
		this.toggleUnPause();
		model.startGame();
	}
	
	/**
	 * Will reflect changes in actors' positions or values in a new "step" to simulate one round of animation.
	 */
	public void update (){
		
	}
	
	/**
	 * Will ask game engine to check interactions that need to be resolved.
	 */
	public void checkInteractions (){
		
	}
	
	/**
	 * Will resolve any front end outcomes determined by logic in backend checking interactions.
	 */
	public void cleanUp (){
		
	}
	
	/**
	 * Will stop the animation timeline.
	 */
	public void endGame (){
		//TODO fix resource also implement saving functionality 
		togglePause();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText("Game over!");
		alert.show();
	}
	
	/**
	 * Will stop the animation timeline.
	 */
	public void winGame (){
		System.out.println("game won");
	}
	
	/**
	 * Transitions the game to the next level
	 */
	public void nextLevel (){
		view.clearGame();
		model.nextLevel();
		begin();
	}
	
	/**
	 * Gets the view component of the game
	 * @return
	 */
	public GameScreen getView() {
		return view;
	}
	
	/**
	 * Gets the model component of the game
	 * @return
	 */
	public Game getGame() {
		return model;
	}
	
	/**
	 * Updates the display of actors on the game screen
	 */
	private void updateActors(){
		List<IDisplayActor> castedActors = new ArrayList<IDisplayActor>();
		for(IPlayActor a: model.getDeadActors()){
			view.removeActor((IDisplayActor)a);
		}
	}
	
	/**
	 * Handles updates from the view and model
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o.equals(view)){
			model.handleTrigger((ITrigger)arg);
		}
		if(o.equals(model)){
			try{
				this.getClass().getDeclaredMethod(((String)arg)).invoke(this);
			}
			catch (Exception e){
				//e.printStackTrace();
				hud.handleChange((Change) arg); //keep it, this works
			}
		}
	}
	
	/**
	 * Toggles sound effects coming from the game
	 */
	public void toggleSound() {
		System.out.println("toggle sound unimplemented");
	}
	
	/**
	 * Toggles music playing in the game 
	 */
	public void toggleMusic(){
		System.out.println("toggle music unimplemented");
	}
	
	/**
	 * Pauses the game
	 */
	public void togglePause() {
		getGame().getAnimation().pause();
		view.getMySubscene().setDisable(true);
	}
	
	/**
	 * Resumes the game 
	 */
	public void toggleUnPause() {
		getGame().getAnimation().play();
		view.getMySubscene().setDisable(false);
	}

	/**
	 * Restarts the game 
	 */
	public void restartGame() {
		System.out.println("restart game");
		System.out.println(model.getInfo().getMyCurrentLevelNum() + " game level");
		initialize(model.getInfo().getMyCurrentLevelNum());
	}
	
	/**
	 * Updates attributes 
	 */
	public void updateAttribute() {
		model.updateAttribute();
	}
	
	
	
	

}