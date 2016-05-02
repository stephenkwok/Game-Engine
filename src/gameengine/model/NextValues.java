package gameengine.model;

/**
 * This class is an info class for the actor. 
 * These next values are set during collision resolution.
 * It makes it so collisions are not skipped over by actors moving during the collision detection process.
 * 
 * @author justinbergkamp
 */
public class NextValues {
	
	double nextXVelo;
	double nextXPos;
	double nextYVelo;
	double nextYPos;
	boolean collision;
	
	public double getNextXPos(){
		return nextXPos;
	}
	
	public void setNextXPos(double x){
		nextXPos = x;
	}
	
	
	public double getNextXVelo(){
		return nextXVelo;
	}
	
	public void setNextXVelo(double x){
		nextXVelo = x;
	}
	
	public double getNextYPos(){
		return nextYPos;
	}
	
	public void setNextYPos(double x){
		nextYPos = x;
	}
	
	public double getNextYVelo(){
		return nextYVelo;
	}
	
	public void setNextYVelo(double x){
		nextYVelo = x;
	}

	public boolean hadCollision() {
		return collision;
	}
	
	public void setCollisoin(boolean c){
		collision = c;
	}
	
	
}
