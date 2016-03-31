package gameplayer.game;

import java.util.List;

import authoringenvironment.model.IActor;
import authoringenvironment.model.ILevel;

public interface CreatedGame {
	
	public void setLevels (List<ILevel> levels);
	
	public void addLevel (ILevel level);
	
	public void initializeCurrentActors (int level);
	
	public void createActor (IActor actor);

}
