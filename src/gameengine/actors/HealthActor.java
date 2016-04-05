package gameengine.actors;

public class HealthActor extends PowerUpActor{

	private int myPowerUpAmount = 1;

	public HealthActor() {
		// TODO Auto-generated constructor stub
	}
	
	public void perform (Actor actor) {
		actor.setHealth(actor.getHealth()+myPowerUpAmount);
	}

}
