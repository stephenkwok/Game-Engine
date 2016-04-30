package gameengine.controller;

import gameengine.model.Actor;
import gameengine.model.IActor;
import gameengine.model.IPlayActor;
import gameengine.model.Triggers.ITrigger;

import java.util.List;

/**
 * This interface defines the API for the Game class. The Game will be the main
 * point of interaction between the Game Player and the Game Engine. It will
 * allow player to switch Levels and to get all of Actors to display on the
 * screen.
 *
 * @author blakekaplan
 */
public interface IGame {

	/**
	 * Provides a list of all the possible levels to be played
	 *
	 * @return A list of Level objects that can be played
	 */
	public List<Level> getLevels();

	/**
	 * Provided with a Trigger object from the player, sets off the appropriate
	 * response
	 *
	 * @param myTrigger
	 *            A Trigger object provided by the game player
	 */
	public void handleTrigger(ITrigger myTrigger);

	public void setCurrentLevel(int level);

	public Level getCurrentLevel();

	public List<IPlayActor> getActors();

	public void startGame();

	public void stopGame();

	public boolean nextLevel();

	public void resetLevelTime();

	public List<IPlayActor> getDeadActors();

	public void addActor(Actor a);

	public void toggleUnPause();

	public String getInitialGameFile();

	public List<Integer> getScores();

	public String getHUDInfoFile();

	public void setInitialGameFile(String path);

	public GameInfo getInfo();
	
	public void toggleSound();
	
	public void toggleMusic();

	public void toggleSoundPause();

	public void playSound(String key);

}