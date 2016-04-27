package gamedata.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * The purpose of this interface is to provide the saving capabilities for the
 * authoring environment and game engine to save a game being played or edit to
 * an XML file.
 * 
 * @author cmt57
 */

public interface ICreatorController {

	/**
	 * Uses the EditXMLCreator to write to an initial file the details for
	 * levels that can be edited again by the authoring environment
	 * 
	 * @return a list of editable levels
	 * @param the
	 *            file to save to
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws TransformerException 
	 */
	public void saveForEditing(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException;

	/**
	 * Uses the GameXMLCreator to write to an initial file the details for a
	 * current game being played so it can be reloaded again for the game engine
	 * 
	 * @return a list of playable levels
	 * @param the
	 *            file to save to
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws TransformerException 
	 */
	public void saveForPlaying(File file) throws ParserConfigurationException, SAXException, IOException, TransformerException;

}
