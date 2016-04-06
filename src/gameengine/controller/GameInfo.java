package gameengine.controller;

public class GameInfo {
	private String myName;
	private String myImageName;
	private String myDescription;
	private int myCurrentLevelNum;
	
	public GameInfo() {
		// TODO Auto-generated constructor stub
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
