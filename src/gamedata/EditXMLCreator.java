package gamedata;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import gameengine.controller.Level;
import gameengine.model.IActor;


/** 
 * This class serves as the private interface that any Game Data xml creator writing editable game files must implement in order to write data to an initial XML file from objects belonging to the authoring environment.
 * @author cmt57
 */

public class EditXMLCreator extends XMLCreator {
	
	private File myFile;
	private Document myDocument;
	
	public EditXMLCreator (File file) throws ParserConfigurationException {
		super(file);
		this.myFile = file;
		setUpDocument();
	}
	
	
	/**
	 * Writes to an XML file a piece of information pertinent to an level's settings.
	 * @param levelInfoTag a string representing the moniker for a specific level setting
	 * @param levelInfoValue a string representing the value of a specific level setting 
	 */
	public void writeLevelInfo (String levelInfoTag, String levelInfoValue) {};
	
	/**
	 * Writes to an XML file all the information pertinent to an actor's settings.
	 * @param actor an instance of a level's actor
	 */
	public void writeActorInfo (IActor actor) {};
	
	/**
	 * Saves all relevant information for each level in a specific format reflected at a basic level in initialGame.XML.
	 * @param levelInfo a map of tags referring to level settings matched to their values
	 * @param levelActors a list of actors belonging to a level's editing environment
	 */
	public void saveLevel (Map<String, String> levelInfo, List<IActor> levelActors){};

}
