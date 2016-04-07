package xmlTest;

public class GameInfo {
	private String myName;
	private String myImageName;
	private String myDescription;
	private int myCurrentLevelNum;
	
	public GameInfo(String name, String imName, String d) {
		this(name, imName, d, 0);
	}
	
	public GameInfo (String name, String imName, String d, int num) {
		myName = name;
		myImageName = imName;
		myDescription = d;
		myCurrentLevelNum = num;
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
}

