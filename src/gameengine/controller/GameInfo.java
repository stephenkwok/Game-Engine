package gameengine.controller;

public class GameInfo {
	private static final String DEFAULT_GAME_NAME = "My Game";
	private static final String DEFAULT_IMAGE_NAME = "default_game.jpg";
	private static final String DEFAULT_DESCRIPTION = "This is a scrolling game.";
	private static final int DEFAULT_LEVEL_NUM = 0;
	
	private String myName;
	private String myImageName;
	private String myDescription;
	private int myCurrentLevelNum;
	
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


}
