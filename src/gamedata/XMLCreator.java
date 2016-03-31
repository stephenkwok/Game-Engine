package gamedata;

import authoringenvironment.model.ICreatedActor;

/** 
 * This class serves as the private interface that any Game Data XMLCreator must implement in order to write data to an initial XML file from objects belonging to the authoring environment.
 * @author cmt57
 */

public interface XMLCreator {
	
	
	/**
	 * Gets from an XML file all the information pertinent to an actor's settings.
	 * @param levelInfoTag a string representing the moniker for a specific level setting
	 * @param levelInfoValue a string representing the value of a specific level setting 
	 */
	public void writeLevelInfo (String levelInfoTag, String levelInfoValue);
	
	/**
	 * Gets from an XML file all the information pertinent to an actor's settings.
	 * @param actor an instance of a level's actor
	 */
	public void writeActorInfo (ICreatedActor actor);
}
