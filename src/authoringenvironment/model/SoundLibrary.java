package authoringenvironment.model;

import javafx.scene.image.Image;

/**
 *  The SoundLibrary will contain all images loaded by the user. It will use the singleton design pattern and whenever the user
 *  adds a new sound, the SoundLibrary will store a map of soundName --> filePath, and whenever a sound is attached to an event,
 *  it can be accessed from the SoundLibrary.
 * @author amyzhao
 *
 */

public interface SoundLibrary {

	/**
	 * Stores the path to the sound file and adds it to the map as a new entry (soundName, filePath).
	 * @param soundName: name of the sound.
	 */
	public void addSound(String soundName);
	
	/**
	 * Gets the file path matching the soundName from the map.
	 * @param soundName: name of the sound.
	 * @return file path matching the sound name.
	 */
	public Image getSound(String soundName);
	
}
