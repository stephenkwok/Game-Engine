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
		a1.setX(bound(applyForce(a1.getX(),nextXVelo)));
	}
	
	public void moveUp(IPlayActor a1){
		 nextYVelo = applyForce(a1.getVeloY(), verticalForce);
		a1.setVeloY(nextYVelo);
		a1.setX((applyForce(a1.getY(),nextYVelo)));
	}
	
	public void moveDown(IPlayActor a1){
		nextYVelo = applyForce(a1.getVeloY(), -verticalForce);
		a1.setVeloY(nextYVelo);
		a1.setX((applyForce(a1.getY(),nextYVelo)));
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
	
	private double bound(double pos){
		if(pos<0){ return 0; } return pos; }
	
	public void staticVerticalCollision(IPlayActor a1){
		a1.setY(a1.getY()-a1.getVeloY());
		a1.setVeloY(0);
	}
	
	public void staticHorizontalCollision(IPlayActor a1){
		a1.setX(a1.getX()-(a1.getVeloX()));
		a1.setVeloX(0);
	}
	
	public void elasticHorizontalCollision(IPlayActor a1){
		a1.setX(a1.getX()-(a1.getVeloX()*2.5));
		a1.setVeloX( -horizontalForce*(a1.getVeloX()/Math.abs(a1.getVeloX())));
	}
	
	public void elasticVerticalCollision(IPlayActor a1){
		a1.setY(a1.getY()-(a1.getVeloY()*2.5));
		a1.setVeloY( -horizontalForce*(a1.getVeloY()/Math.abs(a1.getVeloY())));
	}

	
}
