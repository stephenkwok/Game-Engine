package gameengine.controller;

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
	
	public GameInfo(String name, String imageName, String description, int currentLevelNum ) {
		this.myName = name;
		this.myImageName = imageName;
		this.myDescription = description;
		this.myCurrentLevelNum = currentLevelNum;
	}
	
	public GameInfo(String name, String imageName, String description) {
		this(name, imageName, description, DEFAULT_LEVEL_NUM);
	}
	
	public GameInfo () {
		this(DEFAULT_GAME_NAME, DEFAULT_IMAGE_NAME, DEFAULT_DESCRIPTION);
		
	}
	
	public String getName() {
		return myName;
	}


	public void setName(String myName) {
		this.myName = myName;
	}


	public String getImageName() {
		return myImageName;
	}


	public void setImageName(String myImageName) {
		this.myImageName = myImageName;
	}


	public String getDescription() {
		return myDescription;
	}


	public void setDescription(String myDescription) {
		this.myDescription = myDescription;
	}


	public int getCurrentLevelNum() {
		return myCurrentLevelNum;
	}


	public void setCurrentLevelNum(int myCurrentLevelNum) {
		this.myCurrentLevelNum = myCurrentLevelNum;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
	      
	      stringBuilder.append("GameInfo [ ");
	      stringBuilder.append("\nname: ");
	      stringBuilder.append(myName);
	      stringBuilder.append("\nimgName: ");
	      stringBuilder.append(myImageName);
	      stringBuilder.append("\nmyDescription: ");
	      stringBuilder.append(myDescription);
	      stringBuilder.append("\ncurrentLevelNum: ");
	      stringBuilder.append(myCurrentLevelNum);
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
	public void setID(int ID) {
		myID = ID;
	}

	@Override
	public int getID() {
		return myID;
	}

}
