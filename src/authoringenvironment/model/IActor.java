package authoringenvironment.model;

import java.util.Map;

/** 
 * The purpose of this interface is to provide an easy way for Player Data to extract actor information to save into an XML file.
 * @author amyzhao
 *
 */

public interface IActor {

	/**
	 * Gets all relevant information for each actor in a specific format to save into an XML file.
	 * @return actor properties in predetermined format.
	 */
	public Map<String, String> getActorInfo();
}
