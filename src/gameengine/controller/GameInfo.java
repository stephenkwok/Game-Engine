package gameengine.controller;

import java.util.*;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import javafx.scene.image.ImageView;

/**
 * This class is purposed to consolidate particular details about a game
 * designated in the game authoring environment
 * 
 * @author colettetorres
 *
 */
public class GameInfo implements IEditableGameElement {
	private static final String DEFAULT_GAME_NAME = "My Game";
	private static final String DEFAULT_IMAGE_NAME = "default_game.jpg";
	private static final String DEFAULT_DESCRIPTION = "This is a scrolling game.";
	private static final int DEFAULT_LEVEL_NUM = 0;

	private String myName;
	private String myImageName;
	private String myDescription;
	private int myCurrentLevelNum;
	@XStreamOmitField
	private ImageView myPreviewImage;
	private List<String> myHUDElementsToDisplay;
	private String myFile;
	private boolean isDestinationGame;
	private Map<IAuthoringActor, List<IAuthoringActor>> myActorMap;
	private String myHUDFileLocation;

	public GameInfo(String name, String imageName, String description, int currentLevelNum, String file) {
		this.setName(name);
		this.setMyImageName(imageName);
		this.setMyDescription(description);
		this.setMyCurrentLevelNum(currentLevelNum);
		this.setImageView(new ImageView(DEFAULT_IMAGE_NAME));
		this.setIsDestinationGame(true);
		this.myHUDElementsToDisplay = new ArrayList<String>();
		this.myFile = file;
	}

	public GameInfo(String name, String imageName, String description) {
		this(name, imageName, description, DEFAULT_LEVEL_NUM, null);
	}

	public GameInfo() {
		this(DEFAULT_GAME_NAME, DEFAULT_IMAGE_NAME, DEFAULT_DESCRIPTION);

	}

	/**
	 * Returns the name of the game
	 */
	@Override
	public String getName() {
		return this.myName;
	}

	/**
	 * Sets the name of the game
	 */
	@Override
	public void setName(String myName) {
		this.myName = myName;
	}

	/**
	 * Specifies the types of game information to be displayed in the HUD
	 * 
	 * @param options
	 */
	public void setMyHUDOptions(List<String> options) {
		myHUDElementsToDisplay = options;
	}

	/**
	 * Retrieves the types of game information displayed in the HUD
	 * 
	 * @return a list of strings representing the types of information displayed
	 *         in the HUD
	 */
	public List<String> getMyHUDOptions() {
		return myHUDElementsToDisplay;
	}

	/**
	 * Gets the filepath of the game's image
	 * 
	 * @return a filepath of the game's image
	 */
	public String getMyImageName() {
		return this.myImageName;
	}

	/**
	 * Specifies the filepath of the image to be used to represent the game
	 * 
	 * @param myImageName
	 */
	public void setMyImageName(String myImageName) {
		this.myImageName = myImageName;
	}

	/**
	 * Gets the game's description
	 * 
	 * @return the game's description
	 */
	public String getMyDescription() {
		return this.myDescription;
	}

	/**
	 * Sets the description of the game
	 * 
	 * @param myDescription
	 */
	public void setMyDescription(String myDescription) {
		this.myDescription = myDescription;
	}

	/**
	 * Gets the number of the level the game was at when saved
	 * 
	 * @return the number of the level the game was last at
	 */
	public int getMyCurrentLevelNum() {
		return this.myCurrentLevelNum;
	}

	/**
	 * Sets the number for the current level the game should be at when loaded
	 * 
	 * @param myCurrentLevelNum
	 */
	public void setMyCurrentLevelNum(int myCurrentLevelNum) {
		this.myCurrentLevelNum = myCurrentLevelNum;
	}

	/**
	 * Formats a string representation of the game when printed out
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("GameInfo [ ");
		stringBuilder.append("\nname: ");
		stringBuilder.append(getName());
		stringBuilder.append("\nimgName: ");
		stringBuilder.append(getMyImageName());
		stringBuilder.append("\nmyDescription: ");
		stringBuilder.append(getMyDescription());
		stringBuilder.append("\ncurrentLevelNum: ");
		stringBuilder.append(getMyCurrentLevelNum());
		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}

	/**
	 * Gets the image representation of the game
	 */
	@Override
	public ImageView getImageView() {
		return myPreviewImage;
	}

	/**
	 * Sets the image representation of the game
	 */
	@Override
	public void setImageView(ImageView imageView) {
		myPreviewImage = imageView;
	}

	/**
	 * Gets the filepath of the game
	 * 
	 * @return the filepath of the gane
	 */
	public String getMyFile() {
		return this.myFile;
	}

	/**
	 * Sets the filepath of the game when saved
	 * 
	 * @param name
	 */
	public void setMyFile(String name) {
		this.myFile = name;
	}
	
	public void setHUDFileLocation(String name) {
		this.myHUDFileLocation = name;
	}
	
	public String getHUDFileLocation() {
		return myHUDFileLocation;
	}
	
	

	/** {
	 * 
>>>>>>> 6a9fd24da98957a3cee5514258d62e5045e10e8d
	 * 
	 * @return isDestinationGame
	 */
	public boolean isDestinationGame() {
		return isDestinationGame;
	}

	/**
	 * 
	 * @param isDestinationGame:
	 *            true if game is destination game; false if infinitely
	 *            scrolling
	 */
	public void setIsDestinationGame(boolean isDestinationGame) {
		this.isDestinationGame = isDestinationGame;
	}

	
	public void setActorMap(Map<IAuthoringActor, List<IAuthoringActor>> actorMap) {
		this.myActorMap = actorMap;
	}
	
	public Map<IAuthoringActor, List<IAuthoringActor>> getActorMap() {
		return myActorMap;
	}
	
}