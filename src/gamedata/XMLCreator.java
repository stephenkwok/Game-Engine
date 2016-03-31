package gamedata;

import authoringenvironment.model.IActor;

public interface XMLCreator {

	public void writeLevelInfo (String levelInfoTag, String levelInfoValue);
	
	public void writeActorInfo (IActor actor);
}
