package gameengine.actors;

public class WealthActor extends PowerUpActor{

	private int myWealthAmount = 1;
	
	public WealthActor() {
		// TODO Auto-generated constructor stub
	}
	
	public void perform (Actor actor) {
		actor.setHealth(actor.getHealth()+myWealthAmount);
	}

}
