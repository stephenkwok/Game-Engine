package authoringenvironment.model;

import java.util.List;

//The purpose of this interface is to provide an easy way for Player Data to extract level information to save into an XML file.

public interface ILevel {

	/**
	 * Gets all relevant information for each actor in a specific format to save into an XML file.
	 * @return actor properties in predetermined format.
	 */
	public String getLevelInfo();
	
	/**
	 * Gets all actors contained in the level.
	 * @return list of actors contained in the level.
	 */
	public List<IActor> getLevelActors();
	
}
