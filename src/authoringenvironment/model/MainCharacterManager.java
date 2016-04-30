package authoringenvironment.model;

import java.util.List;
import java.util.stream.Collectors;

import gameengine.controller.Level;
import gameengine.model.ActorState;
import gameengine.model.IPlayActor;

/**
 * 
 * @author Stephen
 *
 */

public class MainCharacterManager {

	private static final int INDEX_OF_FIRST_ACTOR = 0;
	private final List<Level> myLevels;

	public MainCharacterManager(List<Level> levels) {
		myLevels = levels;
	}

	public void updateMainCharacterList() {
		if (allLevelsHaveActor()) {
			filterOutNonMainCharacters();
			addMainCharacters();
			setDefaultMainCharacters();
		}
	}

	private boolean allLevelsHaveActor() {
		List<Level> levelsWithNoActors = myLevels.stream().filter(level -> level.getActors().isEmpty())
				.collect(Collectors.toList());
		System.out.println(levelsWithNoActors);
		return levelsWithNoActors.isEmpty();
	}

	private void addMainCharacters() {
		myLevels.forEach(level -> level.getActors().forEach(actor -> addActorIfNecessary(level, actor)));
	}

	private void filterOutNonMainCharacters() {
		myLevels.forEach(level -> level.getActors().forEach(actor -> removeActorIfNecessary(level, actor)));
	}

	private void removeActorIfNecessary(Level level, IPlayActor actor) {
		if (level.getMainCharacters().contains(actor) && !actor.checkState(ActorState.MAIN)) {
			level.getMainCharacters().remove(actor);
		}
	}

	private void addActorIfNecessary(Level level, IPlayActor actor) {
		if (actor.checkState(ActorState.MAIN) && !level.getMainCharacters().contains(actor)) {
			level.getMainCharacters().add(actor);
		}
	}

	private void setDefaultMainCharacters() {
		myLevels.stream().filter(level -> level.getMainCharacters().isEmpty())
				.forEach(level -> setDefaultMainCharacter(level));
	}

	private void setDefaultMainCharacter(Level level) {
		IPlayActor firstActor = level.getActors().get(INDEX_OF_FIRST_ACTOR);
		firstActor.addState(ActorState.MAIN);
		level.getMainCharacters().add(firstActor);
	}

}
