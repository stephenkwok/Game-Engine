package xmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {
	
	private static final String DEFAULT_NAME = "Untitled";
	private static final String DEFAULT_IMAGE_NAME = "default_background.png";
	
	private String myName;
	private String myBackgroundImgName;
	private List<Actor> myActors;
	private Map<String, List<Actor>> myTriggerMap;
	
	public Level () {
		this.myName = DEFAULT_NAME;
		this.myBackgroundImgName = DEFAULT_IMAGE_NAME;
		this.myActors = new ArrayList<>();
		this.myTriggerMap = new HashMap<>();
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
	

}
