package gameengine.controller;

import java.util.HashMap;
import java.util.*;
import authoringenvironment.model.IEditableGameElement;
import javafx.scene.image.ImageView;

public class GameInfo implements IEditableGameElement {
	private static final String DEFAULT_GAME_NAME = "My Game";
	private static final String DEFAULT_IMAGE_NAME = "default_game.jpg";
	private static final String DEFAULT_DESCRIPTION = "This is a scrolling game.";
	private static final int DEFAULT_LEVEL_NUM = 0;
	
	private String myName;
	private String myImageName;
	private String myDescription;
	private int myCurrentLevelNum;
	private ImageView myPreviewImage;
	private int myID;
	private Map<String,Integer> myHUDElementsToDisplay;
	
	public GameInfo(String name, String imageName, String description, int currentLevelNum ) {
		this.setMyName(name);
		this.setMyImageName(imageName);
		this.setMyDescription(description);
		this.setMyCurrentLevelNum(currentLevelNum);
		this.myHUDElementsToDisplay = new HashMap<String, Integer>();
	}
	
	public GameInfo(String name, String imageName, String description) {
		this(name, imageName, description, DEFAULT_LEVEL_NUM);
	}
	
	public GameInfo () {
		this(DEFAULT_GAME_NAME, DEFAULT_IMAGE_NAME, DEFAULT_DESCRIPTION);
		
	}
	
	@Override
	public String getMyName() {
		return this.myName;
	}

	@Override
	public void setMyName(String myName) {
		this.myName = myName;
	}

	public void setMyHUDOptions(Map<String, Integer> options) {
		myHUDElementsToDisplay = options;
	}
	
	public List<String> getMyHUDOptions() {
		return new ArrayList<>(myHUDElementsToDisplay.keySet());
	}

	public String getMyImageName() {
		return this.myImageName;
	}


	public void setMyImageName(String myImageName) {
		this.myImageName = myImageName;
	}


	public String getMyDescription() {
		return this.myDescription;
	}


	public void setMyDescription(String myDescription) {
		this.myDescription = myDescription;
	}


	public int getMyCurrentLevelNum() {
		return this.myCurrentLevelNum;
	}


	public void setMyCurrentLevelNum(int myCurrentLevelNum) {
		this.myCurrentLevelNum = myCurrentLevelNum;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
	      
	      stringBuilder.append("GameInfo [ ");
	      stringBuilder.append("\nname: ");
	      stringBuilder.append(getMyName());
	      stringBuilder.append("\nimgName: ");
	      stringBuilder.append(getMyImageName());
	      stringBuilder.append("\nmyDescription: ");
	      stringBuilder.append(getMyDescription());
	      stringBuilder.append("\ncurrentLevelNum: ");
	      stringBuilder.append(getMyCurrentLevelNum());
	      stringBuilder.append(" ]");
	      
	      return stringBuilder.toString();
	}

	@Override
	public ImageView getImageView() {
		return myPreviewImage;
	}

	@Override
	public void setImageView(ImageView imageView) {
		myPreviewImage = imageView;
	}

	@Override
	public void setMyID(int ID) {
		myID = ID;
	}

	@Override
	public int getMyID() {
		return myID;
	}

}

