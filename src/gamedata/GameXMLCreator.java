package gamedata;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import gameengine.controller.Level;
import gameengine.model.IActor;

/** 
 * This class serves as the private interface that any Game Data xml creator writing playable game files must implement in order to write data to an initial XML file from objects belonging to the game engine.
 * @author cmt57
 */


public class GameXMLCreator extends XMLCreator {
	
	private File myFile;
	private Document myDocument;
	
	
	public GameXMLCreator (File file) throws ParserConfigurationException {
		super(file);
		this.myFile = file;
		setUpDocument();
	}

	/**
	 * Writes to an XML file a piece of information pertinent to an level's settings.
	 * @param levelInfo a map representing a level's information settings monikers and their values
	 */
	public void writeLevelInfo (Map<String, String> levelInfo) {};
	
	/**
	 * Writes to an XML file all the information pertinent to an actor's settings.
	 * @param actor an instance of a level's actor
	 */
	public void writeActorInfo (IActor actor) {};
	
	/**
	 * Writes to an XML file the filepath to the initial file that the game was loaded off of
	 */
	
	/**
	 * Saves all relevant information for each level in a specific format reflected at a basic level in saveGame.XML.
	 * @param levelInfo a map of tags referring to level settings matched to their values
	 * @param levelActors a list of actors belonging to a level's playing environment
	 */
	public void saveLevel (Map<String, String> levelInfo, List<IActor> levelActors) {};


}
