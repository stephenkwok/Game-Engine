package gameengine.actors;

public class PowerUpActor extends Actor {

	private String myActorType = "POWERUP"; 
	private int myPowerUpAmount = 1;
	
	public PowerUpActor() {
		this.setActorType(myActorType);
	}

	public String getMyActorType(){
		return this.getActorType();
	}
	
	public void perform (Actor actor) {
		actor.setHealth(actor.getHealth()+myPowerUpAmount);
	}
}
