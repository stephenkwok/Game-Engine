package gamedata.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gameengine.controller.Game;

/**
 * The purpose of this interface is to provide the loading capabilities from the
 * authoring environment and game engine to play or edit a game from an XML
 * file.
 * 
 * @author cmt57
 */

public interface IParserController {

	/**
	 * Uses the EditXMLParser to parse through an initial file to produce levels
	 * for editing
	 * 
	 * @return a list of editable levels
	 * @param the
	 *            name of the file to parse through
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public Game loadForEditing(File file)
			throws ParserConfigurationException, SAXException, IOException, TransformerException;

	/**
	 * Uses the GameXMLParser to parse through an game file to produce levels
	 * for playing
	 * 
	 * @return a list of playable levels
	 * @param the
	 *            name of the file to parse through
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public Game loadforPlaying(File file)
			throws ParserConfigurationException, SAXException, IOException, TransformerException;

}
