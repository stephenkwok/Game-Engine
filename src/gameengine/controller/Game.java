package gameengine.controller;

import java.util.*;

import gameengine.model.IActor;
import gameengine.model.ITrigger;

/**
 * This class is intended to represent a game containing levels with actors.
 * @author colettetorres
 *
 */
public class Game implements IGame {
	private String initialGameFile;
	private ILevel currentLevel;
	private List<Level> levels;
	private List<IActor> allActors = new ArrayList<IActor>();
	private Map<Integer,IActor> actorIDMap = new HashMap<Integer,IActor>();
	private Map<String,List<Integer>> levelActorsMap = new HashMap<String,List<Integer>>();

	/**
	 * A game is instantiated with a list of all levels in the game and a level to start on.
	 * Upon instantiation, the actors from all levels are collected into a list and added to a map containing references from ID to actor.
	 * In addition, a map is created mapping all the actors contained in a level to the level ID 
	 * @param levelNum the first level to play in the game
	 * @param gameLevels all the levels in the game 
	 */
	public Game(int levelNum, List<Level> gameLevels, String gameFilePath) {
		initialGameFile = gameFilePath;
		levels = gameLevels;
		currentLevel = gameLevels.get(levelNum);
		for(ILevel level: gameLevels){
			levelActorsMap.put(level.getName(), new ArrayList<Integer>());
			for(IActor actor: level.getActors()){
				allActors.add(actor);
				actorIDMap.put(actor.getID(), actor);
				List<Integer> levelActorIDs = levelActorsMap.get(level.getName());
				levelActorIDs.add(actor.getID());
				levelActorsMap.put(level.getName(),levelActorIDs);
			}
		}
	}

	/**
	 * Sets the current level in the game 
	 * @param mylevel the level to be set 
	 */
	@Override
	public void setLevel(ILevel mylevel) {
		currentLevel = mylevel;

	}
	
	/**
	 * Gets a list of all levels in the game 
	 * @return a list of all levels in the game 
	 */
	@Override
	public List<Level> getLevels() {
		return levels;
	}
	
	/**
	 * Lets current level handle a trigger 
	 * @param myTrigger the trigger received from the game player 
	 */
	@Override
	public void handleTrigger(ITrigger myTrigger) {
		currentLevel.handleTrigger(myTrigger);
	}
	
	/**
	 * Gets a list of all actors used in the game 
	 * @return a list of all actors in the game 
	 */
	@Override
	public List<IActor> getActors() {
		return allActors;
	}

}
