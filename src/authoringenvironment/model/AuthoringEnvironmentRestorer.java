package authoringenvironment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gameengine.controller.Level;
import gameengine.model.Actor;
import javafx.scene.image.ImageView;

/**
 * This class restores the Actors and Levels in the Authoring Environment when a saved game
 * is loaded 
 * 
 * @author Stephen
 *
 */

public class AuthoringEnvironmentRestorer {

	private final Map<IAuthoringActor, List<IAuthoringActor>> myActorMap;
	private final List<Level> myLevels;

	/**
	 * Creates a restorer that restores the Actors and Levels of a Game when a saved game is loaded back in
	 * @param actorMap: the map of created actors
	 * @param levels: the list of created levels
	 */
	public AuthoringEnvironmentRestorer(Map<IAuthoringActor, List<IAuthoringActor>> actorMap, List<Level> levels) {
		myActorMap = actorMap;
		myLevels = levels;
	}

	/**
	 * Restores the Actors and Levels in the Authoring Environment
	 */
	public void restoreActorsAndLevels() {
		restoreActors();
		restoreLevels();
	}

	/**
	 * Restores the Actors in the Authoring Environment
	 */
	private void restoreActors() {
		List<IAuthoringActor> allActors = new ArrayList<>();
		myActorMap.keySet().stream().forEach(actor -> allActors.add(actor));
		myActorMap.values().stream().forEach(list -> list.stream().forEach(actor -> allActors.add(actor)));
		allActors.stream().forEach(actor -> actor.restoreImageView());
	}

	/**
	 * Restores the Levels in the Authoring Environment
	 */
	private void restoreLevels() {
		myLevels.stream().forEach(level -> level.setImageView(new ImageView(level.getMyBackgroundImgName())));
		myLevels.stream().forEach(level -> level.getGarbageCollectors().forEach(actor -> ((Actor) actor).restoreImageView()));
	}

}
