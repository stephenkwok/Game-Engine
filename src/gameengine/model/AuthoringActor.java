package gameengine.model;

import java.util.List;

import authoringenvironment.view.ActorRule;

public class AuthoringActor extends Actor {
	private String myName;
	private double mySize;
	private int myID;
    private List<ActorRule> myActorRules;
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public double getMySize() {
		return mySize;
	}
	public void setMySize(double mySize) {
		this.mySize = mySize;
	}
	public int getMyID() {
		return myID;
	}
	public void setMyID(int myID) {
		this.myID = myID;
	}
	public List<ActorRule> getMyActorRules() {
		return myActorRules;
	}
	public void setMyActorRules(List<ActorRule> myActorRules) {
		this.myActorRules = myActorRules;
	}
    
    
}
