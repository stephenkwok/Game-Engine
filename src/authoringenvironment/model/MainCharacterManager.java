package authoringenvironment.model;

import java.util.List;
import java.util.stream.Collectors;

import gameengine.controller.Level;
import gameengine.model.ActorState;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * This class ensures that each Level has at least one main character by adding Actors designated as
 * "Main" Actors to each Level's list of main characters. Levels that do not have any Actors designated
 * as a main character have the first Actor in their list of actors designated as a main character. 
 * If any level has no Actors at all, an error notification is generated prompting the author to add
 * at least one Actor to each Level.
 * 
 * @author Stephen
 *
 */

public class MainCharacterManager {

	private static final int INDEX_OF_FIRST_ACTOR = 0;
	private static final String ERROR_MESSAGE = "Please add at least one actor to each level";
	private final List<Level> myLevels;
	private boolean updateSuccessful;

	/**
	 * Creates a Main Character Manager that sets the main characters for each level
	 * @param levels: the list of levels for the game
	 */
	public MainCharacterManager(List<Level> levels) {
		myLevels = levels;
		updateSuccessful = false;
	}

	/**
	 * Checks if each level has at least one Actor and if so, adds all Actors designated
	 * as a main character in each Level to that Level's list of main characters (or sets
	 * a default main character if no Actors are marked as "Main". Otherwise, generates 
	 * error message prompting author to add at least one Actor to each Level.
	 */
	public void updateMainCharacterListsForEachLevel() {
		if (allLevelsHaveActor()) {
			updateMainCharacterLists();
			setDefaultMainCharacters();
			updateSuccessful = true;
		} else {
			notifyUserOfError();
			updateSuccessful = false;
		}
	}

	/**
	 * Generates error message prompting author to add at least one Actor to each Level
	 */
	private void notifyUserOfError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(ERROR_MESSAGE);
		alert.show();
	}

	/**
	 * Checks whether each Level has at least one Actor
	 * @return true if all Levels have at least one Actor; false otherwise
	 */
	private boolean allLevelsHaveActor() {
		List<Level> levelsWithNoActors = myLevels.stream().filter(level -> level.getActors().isEmpty())
				.collect(Collectors.toList());
		return levelsWithNoActors.isEmpty();
	}

	/**
	 * For each Level, updates that Level's list of main characters
	 */
	private void updateMainCharacterLists() {
		myLevels.forEach(level -> addActorsToMainCharacterList(level));
	}
	
	/**
	 * Calls a Level's getMainCharacters() method, which updates that Level's
	 * list of main characters by adding any Actors marked as "Main" to the list
	 * and removing any Actors that are no longer marked as "Main" from the list
 	 * @param level
	 */
	private void addActorsToMainCharacterList(Level level) {
		level.getMainCharacters();
	}

	/**
	 * Sets a default main character for each level with no Actors marked as a main character, 
	 */
	private void setDefaultMainCharacters() {
		myLevels.stream().filter(level -> level.getMainCharacters().isEmpty())
				.forEach(levelWithNoMainActor -> setDefaultMainCharacter(levelWithNoMainActor));
	}

	/**
	 * Retrieves the first actor in a Level's list of Actors, designates it a main character,
	 * and adds it to the Level's main character list
	 * @param level
	 */
	private void setDefaultMainCharacter(Level level) {
		level.getActors().get(INDEX_OF_FIRST_ACTOR).addState(ActorState.MAIN);
		addActorsToMainCharacterList(level);
	}

	/**
	 * 
	 * @return true if main character lists successfully updated; false if error detected
	 */
	public boolean updateSuccessful() {
		return updateSuccessful;
	}

}
