package gameengine.model;

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
