package xmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//@XStreamAlias("level")
public class Level {
	
	private static final String DEFAULT_NAME = "Untitled";
	private static final String DEFAULT_IMAGE_NAME = "default_background.png";
	
	private String myName;
	private String myBackgroundImgName;
	private List<Actor> myActors;
	private Map<String, List<Actor>> myTriggerMap;
	@XStreamOmitField
	private ImageView myImage;
	
	public Level () {
		this.myName = DEFAULT_NAME;
		this.myBackgroundImgName = DEFAULT_IMAGE_NAME;
		this.myActors = new ArrayList<>();
		this.myTriggerMap = new HashMap<>();
		this.myImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myBackgroundImgName)));
	}
	
	public void setName (String name) {
		this.myName = name;
	}
	
	public String getName () {
		return this.myName;
	}
	
	public void setImage (String imgName) {
		this.myBackgroundImgName = imgName;
	}
	
	public String getImage () {
		return this.myBackgroundImgName;
	}
	
	public void setActors (List<Actor> actors) {
		this.myActors = actors;
	}
	
	public List<Actor> getActors () {
		return this.myActors;
	}
	
	public void addActor (Actor actor) {
		this.myActors.add(actor);
	}
	
	public void setTriggerMap (HashMap<String, List<Actor>> map) {
		this.myTriggerMap = map;
	}
	
	public Map<String, List<Actor>> getMap () {
		return this.myTriggerMap;
	}

	public ImageView getMyImage() {
		return myImage;
	}

	public void setMyImage(ImageView myImage) {
		this.myImage = myImage;
	}
	
	public String toString() {

	      StringBuilder stringBuilder = new StringBuilder();
	      
	      stringBuilder.append("Level [ ");
	      stringBuilder.append("\nmyName: ");
	      stringBuilder.append(myName);
	      stringBuilder.append("\nbckImg: ");
	      stringBuilder.append(myBackgroundImgName);
	      stringBuilder.append("\nmyActors: ");
	      stringBuilder.append(myActors.toString());
	      stringBuilder.append("\nTriggerMap: ");
	      stringBuilder.append(myTriggerMap.toString());
	      stringBuilder.append("\nimg: ");
	      stringBuilder.append(myImage);
	      stringBuilder.append(" ]");
	      
	      return stringBuilder.toString();
	}
	

}
