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
	private double gravity = .11;
	private double friction = -.05;
	private double maxVelo = 7;
	private double bounce = 2.5; // purely aesthetic

	/**
	 * Basic method for the physics engine
	 * Applies a force to a vector
	 * 
	 * @param vector
	 * @param force
	 * @return
	 */
	public double applyForce(double vector, double force) {
		return (vector + force);
	}

	/**
	 * This is the method called by ApplyPhysics
	 * Applies gravity and friction.
	 * Also bounds the Actor's x/y positions
	 * @param a1
	 */
	public void tick(IPlayActor a1) {
		nextXVelo = applyForce(a1.getVeloX(), friction * a1.getVeloX());
		nextXPos = applyForce(a1.getX(), nextXVelo);
		nextYVelo = applyForce(a1.getVeloY(), gravity);
		nextYPos = applyForce(a1.getY(), nextYVelo);
		a1.setVeloX(nextXVelo);
		a1.setX(bound(nextXPos));
		a1.setVeloY(nextYVelo);
		if (nextYPos <= 0) {
			a1.setVeloY(0);
		}
		a1.setY(bound(nextYPos));
	}
	
	/**
	 * This action applies a negative or positive horizontal force on an actor's velocity
	 * @param a1
	 * @param right
	 */
	private void moveLeftRight(IPlayActor a1, boolean right) {
		double localHorizForce = horizontalForce * (right ? 1 : -1);
		nextXVelo = (applyForce(a1.getVeloX(), localHorizForce));
		a1.setVeloX(nextXVelo);
		a1.setX((applyForce(a1.getX(), nextXVelo)));
	}

	public void moveRight(IPlayActor a1) {
		moveLeftRight(a1, true);
	}

	public void moveLeft(IPlayActor a1) {
		moveLeftRight(a1, false);
	}
	
	/**
	 * This action applies a negative or positive vertical force on an actor's velocity. 
	 * 
	 * @param a1
	 * @param up
	 */
	private void moveUpDown(IPlayActor a1, boolean up) {
		double localVerticalForce = verticalForce * (up ? 1 : -1);
		nextYVelo = applyForce(a1.getVeloY(), localVerticalForce);
		a1.setVeloY(nextYVelo);
		a1.setY((applyForce(a1.getY(), nextYVelo)));
	}

	public void moveUp(IPlayActor a1) {
		moveUpDown(a1, true);
	}

	public void moveDown(IPlayActor a1) {
		moveUpDown(a1, false);
	}
	
	/**
	 * Determines new x and y velocity and positions based on the actor's heading. 
	 * @param a1
	 * @param forward
	 */
	public void moveForwardBack(IPlayActor a1, boolean forward) {
		double localHorizForce = horizontalForce * (forward ? 1 : -1);
		double localVerticalForce = verticalForce * (forward ? 1 : -1);
		nextXVelo = applyForce(a1.getVeloX(), localHorizForce * (Math.cos(Math.toRadians(a1.getHeading()))));
		a1.setVeloX(nextXVelo);
		a1.setX((applyForce(a1.getX(), nextXVelo)));
		nextYVelo = applyForce(a1.getVeloY(), localVerticalForce * (Math.sin(Math.toRadians(a1.getHeading()))));
		a1.setVeloY(nextYVelo);
		a1.setY((applyForce(a1.getY(), nextYVelo)) - gravity);
	}
	
	public void moveForward(IPlayActor a1) {
		moveForwardBack(a1, true);
	}

	public void moveBackward(IPlayActor a1) {
		moveForwardBack(a1, false);
	}
		
	public void glideLeftRight(IPlayActor a1, double offset, boolean right) {
		offset *= (right ? 1 : -1);
		a1.setX(applyForce(a1.getX(),offset));
		a1.setVeloX(offset);
	}
	
	
	public void glideRight(IPlayActor a1, double offset){
		glideLeftRight(a1, offset, true);
	}
	public void glideLeft(IPlayActor a1, double offset){
		glideLeftRight(a1, offset, false);

	}
	
	
	public void glideUpDown(IPlayActor a1, double offset, boolean up) {
		offset *= (up ? -1 : 1);
		a1.setY(applyForce(a1.getY(),offset));
		a1.setVeloY(offset);
	}
	
	public void glideUp(IPlayActor a1, double offset){
		glideUpDown(a1, offset, true);
	}
	
	public void glideDown(IPlayActor a1, double offset){
		glideUpDown(a1, offset, false);
	}
	
	/**
	 * Moves actor in direction of its heading by an offset. 
	 * 
	 * @param a1
	 * @param offset
	 */
	public void glideForward(IPlayActor a1, double offset) {
		a1.setX(applyForce(a1.getX(),offset*(Math.cos(Math.toRadians(a1.getHeading())))));
		a1.setVeloX(offset*(Math.cos(Math.toRadians(a1.getHeading()))));
		a1.setY(applyForce(a1.getY(),  -offset*(Math.sin(Math.toRadians(a1.getHeading())))));
		a1.setVeloY(-offset*(Math.sin(Math.toRadians(a1.getHeading()))));

	}

	public void glideBackward(IPlayActor a1, double offset) {
		a1.setX(applyForce(a1.getX(),-offset*(Math.cos(Math.toRadians(a1.getHeading())))));
		a1.setY(applyForce(a1.getY(),-offset*(Math.sin(Math.toRadians(a1.getHeading())))));
	}
	private double bound(double pos){
		if(pos<0){ return 0; } return pos; }
	
	private double limitVelo(double velo){
		if(Math.abs(velo)>maxVelo){return maxVelo;} return velo;}
	
	public void staticVerticalCollision(IPlayActor a1){
		setNextVals(a1,a1.getX(),a1.getY()-a1.getVeloY(),a1.getVeloX(), 0 );
	}

	public void staticHorizontalCollision(IPlayActor a1) {
		setNextVals(a1, a1.getX()-(a1.getVeloX()) , a1.getY(), 0 , a1.getVeloY());
	}

	public void elasticHorizontalCollision(IPlayActor a1) {
		if(a1.getVeloX()!=0){
			setNextVals(a1, a1.getX()-(a1.getVeloX() * bounce) , a1.getY() , -horizontalForce * (a1.getVeloX() / Math.abs(a1.getVeloX())) , a1.getVeloY() );
		}else{
			setNextVals(a1, a1.getX()-(a1.getVeloX() * bounce) , a1.getY() , 0 , a1.getVeloY() );

		}
	}


	public void elasticVerticalCollision(IPlayActor a1) {
		if(a1.getVeloY()!=0){
			setNextVals(a1,a1.getX(),a1.getY() - (a1.getVeloY() * bounce),a1.getVeloX(), -horizontalForce * (a1.getVeloY() / Math.abs(a1.getVeloY())) );
		}else{
			setNextVals(a1 , a1.getX(), a1.getY() - (a1.getVeloY() * bounce), a1.getVeloX() , 0 );

		}
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
	
	/**
	 * Sets new NextVals
	 * 
	 * @param a1
	 * @param x
	 * @param y
	 * @param xVelo
	 * @param yVelo
	 */
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