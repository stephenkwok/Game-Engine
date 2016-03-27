package authoringenvironment.model;

import java.util.List;
import java.util.Map;

/** 
 * The purpose of this interface is to provide an easy way for Player Data to extract level information to save into an XML file.
 * @author amyzhao
 *
 */

public interface ILevel {

	/**
	 * Gets all relevant information for each actor in a specific format to save into an XML file.
	 * @return actor properties in predetermined format.
	 */
	public Map<String, String> getLevelInfo();
	
	/**
	 * Gets all actors contained in the level.
	 * @return list of actors contained in the level.
	 */
	public List<IActor> getLevelActors();
	
}
