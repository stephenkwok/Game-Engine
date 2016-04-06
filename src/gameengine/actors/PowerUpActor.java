package gameengine.actors;

import javafx.scene.image.Image;

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

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setImage(Image image) {
		// TODO Auto-generated method stub
		
	}
}
