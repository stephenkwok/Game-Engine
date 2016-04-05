package gameengine.controller;

public class PowerUpActor extends Actor {

	private String myID = "POWERUP"; 
	
	public PowerUpActor() {
		this.setActorType(myID);
	}

	public String getMyActorType(){
		return this.getActorType();
	}
}
