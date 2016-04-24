package gameengine.model;

public class DopePhysicsEngine {
		
	double nextXVelo;
	double nextXPos;
	double nextYVelo;
	double nextYPos;
	private double horizontalForce = 5;
	private double verticalForce   = 5;
	private double gravity         = .11;
	private double friction        = -.05;
		
	public double applyForce(double vector, double force){
		return (vector + force);
	}
	
	public void tick(IPlayActor a1){
		nextXVelo = applyForce(a1.getVeloX(),friction);
		nextXPos  = applyForce(a1.getX()    ,nextXVelo);
		nextYVelo = applyForce(a1.getVeloY(),gravity);
		nextYPos  = applyForce(a1.getY()    ,nextYVelo);
		a1.setVeloX(nextXVelo);
		a1.setX(nextXPos);
		a1.setVeloY(nextYVelo);
		a1.setY(nextYPos);
	}
	
	public void moveRight(IPlayActor a1){
		nextXVelo = (applyForce(a1.getVeloX(), horizontalForce));
		a1.setVeloX(nextXVelo);
		a1.setX((applyForce(a1.getX(),nextXVelo)));
	}
	
	public void moveLeft(IPlayActor a1){
		nextXVelo = applyForce(a1.getX(), -horizontalForce);
		a1.setVeloX(nextXVelo);
		a1.setX((applyForce(a1.getX(),nextXVelo)));
	}
	
	public void moveUp(IPlayActor a1){
		double nextYVelo = applyForce(a1.getY(), verticalForce);
		a1.setVeloY(nextYVelo);
		double nextYPos = applyForce(a1.getY(),nextYVelo);
		a1.setX(nextYPos);
	}
	
	public void moveDown(IPlayActor a1){
		double nextYVelo = applyForce(a1.getY(), -verticalForce);
		a1.setVeloY(nextYVelo);
		double nextYPos = applyForce(a1.getY(),nextYVelo);
		a1.setX(nextYPos);
	}
		
	public void glideRight(IPlayActor a1){
		a1.setX(applyForce(a1.getX(),horizontalForce));
	}
	public void glideLeft(IPlayActor a1){
		a1.setX(applyForce(a1.getX(),-horizontalForce));
	}
	public void glideUp(IPlayActor a1){
		a1.setY(applyForce(a1.getY(),verticalForce));
	}
	public void glideDown(IPlayActor a1){
		a1.setY(applyForce(a1.getY(),-verticalForce));
	}
	

	
}
