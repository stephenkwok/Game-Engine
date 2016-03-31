package gameplayer.game;

import java.util.List;

import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;

public interface CreatedGame {
	
	public void setLevels (List<ICreatedLevel> levels);
	
	public void addLevel (ICreatedLevel level);
	
	public void initializeCurrentActors (int level);
	
	public void createActor (ICreatedActor actor);

}
