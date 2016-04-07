package gameengine.controller;

import java.util.*;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

/**
 * This class is intended to represent a game containing levels with actors.
 * @author colettetorres
 *
 */
public class Game extends Observable implements Observer {

	private String initialGameFile;
	private List<Level> levels;
	private GameInfo info;
	
	/**
	 * A game is instantiated with a list of all levels in the game and a level to start on.
	 * Upon instantiation, the actors from all levels are collected into a list and added to a map containing references from ID to actor.
	 * In addition, a map is created mapping all the actors contained in a level to the level ID 
	 * @param levelNum the first level to play in the game
	 * @param gameLevels all the levels in the game 
	 */
	public Game(String gameFilePath, GameInfo gameInfo, List<Level> gameLevels) {
		initialGameFile = gameFilePath;
		levels = gameLevels;
		info = gameInfo;
		observeActors();
	}
	
	private void observeActors(){
		for(Level level: levels){
			for(Actor actor: level.getActors()){
				actor.addObserver(this);
			}
		}
	}
	public String getInitialGameFile() {
		return initialGameFile;
	}

	public void setInitialGameFile(String initialGameFile) {
		this.initialGameFile = initialGameFile;
	}

	public GameInfo getInfo() {
		return info;
	}

	public void setInfo(GameInfo info) {
		this.info = info;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
	
	/**
	 * Gets a list of all levels in the game 
	 * @return a list of all levels in the game 
	 */
	public List<Level> getLevels() {
		return levels;
	}
	
	public void nextLevel(){
		info.setCurrentLevelNum(info.getCurrentLevelNum()+1);
	}
	
	/**
	 * Lets current level handle a trigger 
	 * @param myTrigger the trigger received from the game player 
	 */
	public void handleTrigger(ITrigger myTrigger) {
		levels.get(info.getCurrentLevelNum()).handleTrigger(myTrigger);
	}

	@Override
	public void update(Observable o, Object arg) {
		notifyObservers(arg);
		
	}

<<<<<<< HEAD
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
	      
	      stringBuilder.append("Game [ ");
	      stringBuilder.append("\ninitialFile: ");
	      stringBuilder.append(initialGameFile);
	      stringBuilder.append("\nbckImg: ");
	      stringBuilder.append(myBackgroundImgName);
	      stringBuilder.append("\nmyActors: ");
	      stringBuilder.append(myActors.toString());
	      stringBuilder.append("\nTriggerMap: ");
	      stringBuilder.append(myTriggerMap.toString());
	      stringBuilder.append("\nimg: ");
	      stringBuilder.append(myImage);
	      stringBuilder.append(" ]");
	      
	      return stringBuilder.toString();
	}
=======

>>>>>>> cfe83f6bf05e26a516562018795cf31511f6668d
}
