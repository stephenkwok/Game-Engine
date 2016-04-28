package gameengine.model;

/**
 * Physics Engine Class Handles calculating/assigning new positions based on
 * positional attributes and movement vectors Receives Actor object via movement
 * specific methods (ie moveRight, jump) These methods then apply a set force
 * that affects and updates the Actor's position and velocity variables
 * 
 * @author justinbergkamp
 */

public class PhysicsEngine {

	double nextXVelo;
	double nextXPos;
	double nextYVelo;
	double nextYPos;
	private double horizontalForce = 5;
	private double verticalForce = -5;
	private double jumpForce = -1;
	private double gravity = .11;
	private double friction = -.05;
	private double maxVelo = 7;
	private double bounce = 2.5; // purely aesthetic
	
	public double applyForce(double vector, double force) {
		return (vector + force);
	}

	public void tick(IPlayActor a1) {
		nextXVelo = applyForce(a1.getVeloX(), friction * a1.getVeloX());
		nextXPos = applyForce(a1.getX(), nextXVelo);
		nextYVelo = applyForce(a1.getVeloY(), gravity);
		nextYPos = applyForce(a1.getY(), nextYVelo);
		a1.setVeloX(nextXVelo);
		a1.setX(bound(nextXPos));
//		if (Math.abs(nextYVelo) > a1.getBounds().getHeight()/10) {
//			nextYVelo = -a1.getBounds().getHeight()/10;
//		}
		a1.setVeloY(nextYVelo);
		if (bound(nextYPos) == 0) {
			a1.setVeloY(0);
		}

		a1.setY(bound(nextYPos));
	}

	public void moveRight(IPlayActor a1) {
		nextXVelo = (applyForce(a1.getVeloX(), horizontalForce));
		a1.setVeloX(nextXVelo);
		a1.setX((applyForce(a1.getX(), nextXVelo)));
	}

	public void moveLeft(IPlayActor a1) {
		nextXVelo = applyForce(a1.getVeloX(), -horizontalForce);
		a1.setVeloX(nextXVelo);
		a1.setX(applyForce(a1.getX(), nextXVelo));
	}

	public void moveUp(IPlayActor a1) {
		nextYVelo = applyForce(a1.getVeloY(), verticalForce);
		a1.setVeloY(nextYVelo);
		a1.setY((applyForce(a1.getY(), nextYVelo)));
	}

	public void moveDown(IPlayActor a1) {
		nextYVelo = applyForce(a1.getVeloY(), -verticalForce);
		a1.setVeloY(nextYVelo);
		a1.setY((applyForce(a1.getY(), nextYVelo)));
	}

	public void moveForward(IPlayActor a1) {
		nextXVelo = applyForce(a1.getVeloX(), horizontalForce * (Math.cos(Math.toRadians(a1.getHeading()))));
		a1.setVeloX(nextXVelo);
		a1.setX((applyForce(a1.getX(), nextXVelo)));
		nextYVelo = applyForce(a1.getVeloY(), verticalForce * (Math.sin(Math.toRadians(a1.getHeading()))));
		a1.setVeloY(nextYVelo);
		a1.setY((applyForce(a1.getY(), nextYVelo)) - gravity);
	}

	public void moveBackward(IPlayActor a1) {
		nextXVelo = applyForce(a1.getVeloX(), -horizontalForce * (Math.cos(Math.toRadians(a1.getHeading()))));
		a1.setVeloX(nextXVelo);
		a1.setX((applyForce(a1.getX(), nextXVelo)));
		nextYVelo = applyForce(a1.getVeloY(), -verticalForce * (Math.sin(Math.toRadians(a1.getHeading()))));
		a1.setVeloY(nextYVelo);
		a1.setY((applyForce(a1.getY(), nextYVelo)) - gravity);
	}
		
	public void glideRight(IPlayActor a1, double offset){
		a1.setX(applyForce(a1.getX(),offset));
	}
	public void glideLeft(IPlayActor a1, double offset){
		a1.setX(applyForce(a1.getX(),-offset));
	}
	public void glideUp(IPlayActor a1, double offset){
		a1.setY(applyForce(a1.getY(),offset));
	}
	public void glideDown(IPlayActor a1, double offset){
		a1.setY(applyForce(a1.getY(),-offset));
	}
	
	public void glideForward(IPlayActor a1, double offset) {
		a1.setX(applyForce(a1.getX(),offset*(Math.cos(Math.toRadians(a1.getHeading())))));
		a1.setY(applyForce(a1.getY(),  offset*(Math.sin(Math.toRadians(a1.getHeading()))))-gravity);
	}

	public void glideBackward(IPlayActor a1, double offset) {
		a1.setX(applyForce(a1.getX(),-offset*(Math.cos(Math.toRadians(a1.getHeading())))));
		a1.setY(applyForce(a1.getY(),-offset*(Math.sin(Math.toRadians(a1.getHeading()))))-gravity);
	}
	private double bound(double pos){
		if(pos<0){ return 0; } return pos; }
	
	private double limitVelo(double velo){
		if(Math.abs(velo)>maxVelo){return maxVelo;} return velo;}
	
	public void staticVerticalCollision(IPlayActor a1){
//		a1.setY(a1.getY()-a1.getVeloY());
//		a1.setVeloY(0);
		setNextVals(a1,a1.getX(),a1.getY()-a1.getVeloY(),a1.getVeloX(), 0 );
	}

	public void staticHorizontalCollision(IPlayActor a1) {
//		a1.setX(a1.getX() - (a1.getVeloX() * bounce));
//		a1.setVeloX(0);
		setNextVals(a1, a1.getX()-(a1.getVeloX() * bounce) , a1.getY() , 0 , a1.getVeloY()-gravity );
	}

	public void elasticHorizontalCollision(IPlayActor a1) {
//		a1.setX(a1.getX() - (a1.getVeloX() * bounce));
//		a1.setVeloX(-horizontalForce * (a1.getVeloX() / Math.abs(a1.getVeloX())));
		
		setNextVals(a1, a1.getX()-(a1.getVeloX() * bounce) , a1.getY() , -horizontalForce * (a1.getVeloX() / Math.abs(a1.getVeloX())) , a1.getVeloY() );

	}

	public void elasticVerticalCollision(IPlayActor a1) {
//		a1.setY(a1.getY() - (a1.getVeloY() * bounce));
//		a1.setVeloY(-horizontalForce * (a1.getVeloY() / Math.abs(a1.getVeloY())));
		
		setNextVals(a1,a1.getX(),a1.getY() - (a1.getVeloY() * bounce),a1.getVeloX(), -horizontalForce * (a1.getVeloY() / Math.abs(a1.getVeloY())) );

	}

	public void setHorizontalForce(double horizontalForce) {
		this.horizontalForce = horizontalForce;
	}

	public void setVerticalForce(double verticalForce) {
		this.verticalForce = verticalForce;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public void setFriction(double friction) {
		this.friction = friction;
	}

	public void setMaxVelo(double maxVelo) {
		this.maxVelo = maxVelo;
	}

	public void setBounce(double bounce) {
		this.bounce = bounce;
	}
	
	public void setNextVals(IPlayActor a1, double x, double y, double xVelo, double yVelo){
		if(!a1.getNextValues().hadCollision()){
			a1.getNextValues().setNextXPos(x);
			a1.getNextValues().setNextYPos(y);
			a1.getNextValues().setNextXVelo(xVelo);
			a1.getNextValues().setNextYVelo(yVelo);
			a1.getNextValues().setCollisoin(true);
		}
		
	}

}