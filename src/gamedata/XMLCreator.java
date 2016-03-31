package gamedata;

import authoringenvironment.model.ICreatedActor;

public interface XMLCreator {

	public void writeLevelInfo (String levelInfoTag, String levelInfoValue);
	
	public void writeActorInfo (ICreatedActor actor);
}
