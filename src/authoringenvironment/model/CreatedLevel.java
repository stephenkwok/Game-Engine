package authoringenvironment.model;

import java.util.List;
import java.util.Map;

/**
 * This class represents the individual levels that the user will create. It will implement the ILevel interface and
 * holds the relevant data for importing this level into the game engine.
 * @author amyzhao
 *
 */
public interface CreatedLevel {
	
	/**
	 * Sets the level number (i.e. if it's the first level, second level, third level, etc.)
	 * @param number: level number.
	 */
	public void setLevelNumber(int number);
	
	/**
	 * Adds an actor to the level from the list of existing CreatedActors.
	 * @param actor: actor to add.
	 */
	public void addActor(ICreatedActor actor);
	
	/**
	 * Sets the level name.
	 * @param name: name of level.
	 */
	public void setLevelName(String name);
	
	/**
	 * Sets whether the level scrolls horizontally or vertically.
	 * @param direction: either horizontal or vertical.
	 */
	public void setScrollingDirection(String direction);
	
	/**
	 * Sets whether the level continues infinitely or stops at a certain point in the scene.
	 * @param terminationMode: infinite or finite.
	 */
	public void setTerminationMode(String terminationMode);
	
	/**
	 * Sets the background music for the level.
	 * @param filepath: path to the background music's sound file.
	 */
	public void setBackgroundMusic(String filepath);
	
	/**
	 * Sets the background image for the level.
	 * @param filename: name of the image file.
	 */
	public void setBackgroundImage(String filename);
	
	/**
	 * Returns a map of the level's relevant info for saving and loading into the game engine.
	 * @return: map of level's relevant info.
	 */
	public Map<String, String> getInfo();
	
	/**
	 * Returns a list of the actors in this level.
	 * @return list of actors.
	 */
	public List<ICreatedActor> getLevelActors();
	
}
