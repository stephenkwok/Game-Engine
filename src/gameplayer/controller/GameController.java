package gameplayer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import gameengine.controller.Game;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.ITrigger;
import gameplayer.view.BaseScreen;
import gameplayer.view.GameScreen;
import gameplayer.view.HUDScreen;


/** 
 * This class serves as the private interface that any game controller must implement in order to set up the IGame with the appropriate levels and actors for the game engine to interact with
 * @author cmt57
 */

public class GameController implements Observer {
	private Game model;
	private GameScreen view;
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
	 * Sets the basic game view to the given BaseScreen
	 * @param BaseScreen
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
		view.addBackground(current.getMyBackgroundImgName());
		for(Actor actor: model.getActors()){
			view.addActor(actor);
		}
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
		System.out.println("game over");
	}
	
	/**
	 * Will stop the animation timeline.
	 */
	public void winGame (){
		System.out.println("game won");
	}
	
	public void nextLevel (){
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
	
	private void updateActors(){
		view.removeActors(model.getDeadActors());
	}
	
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
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	

}