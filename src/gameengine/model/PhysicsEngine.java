package gameengine.model;


/**
 * Physics Engine Class
 * Handles calculating/assigning new positions based on positional attributes and movement vectors
 * Receives Actor object via movement specific methods (ie moveRight, jump)
 * These methods then apply a set force that affects and updates the Actor's 
 * position and velocity variables
 * 
 * @author justinbergkamp
 */


public class PhysicsEngine {
	
	
	//These Variable values are arbitrary, chosen by trial/error 
	private int    timeStep         =  1;    //Arbitrary timeStep, will be set to the time provided by step()
	private double friction         = -.05;  //Horizontal acceleration dampening (friction) coefficient
	private double gravity          = .11 ;  //Falling acceleration coefficient
	private double maxHorizVelocity = 50;    //maximum horizontal velocity
	private double maxVertVelocity  = -50;   //maximum vertical velocity 
	private double horizontalForce  = 5;     //Force applied to Actors on horizontal movements
	private double jumpForce        = -5;    //Vertical Force applied to Actors on jump movements
	private double floorHeight      = 500;
	
	public PhysicsEngine(){
		
	}
	
	/**
	 * This method updates a given velocity by applying a given force
	 * @param velo
	 * @param force
	 * @return updated velo
	 */
	private double applyForce(double velo, double force){  //Applies a force(acceleration because mass =1) to a velocity
		double nextVelo = velo + force*getTimeStep();
		return nextVelo;
	}
		
	/**
	 * Primary method within the Physics Engine class, called on Actors for which gravity and friction forces apply
	 * Calculates new values for X and Y positions as well as X and Y velocities
	 * @param a
	 * @param forceX
	 * @param forceYupward
	 * @param forceYdownward
	 * @param friction
	 */
	private void update(IPlayActor a,double forceX, double forceYupward, double forceYdownward, double friction){
		double xVelo     = a.getVeloX();
		double yVelo     = a.getVeloY();
		double xPos      =  a.getX();      
		double yPos      =  a.getY();
		double nextHorzVelo;
		double nextVertVelo;
		double nextXPos;
		double nextYPos;
		forceYdownward = getGravity();				
		nextHorzVelo = xVelo;      		
		nextVertVelo = applyForce(yVelo, forceYupward);                      // Apply  y force from movement action to y velocity
		nextVertVelo = applyForce(nextVertVelo, forceYdownward);             //Applies gravitational force onto Actor's velocity 
		nextYPos     = applyForce(yPos, nextVertVelo); 
		nextVertVelo = maxLimit(nextVertVelo, getMaxVertVelocity());
			
		if(nextYPos+a.getBounds().getHeight() > getFloorHeight()){                    //Collision detection for the actor and the ground
			nextYPos = getFloorHeight()-a.getBounds().getHeight();				      //TODO: delete this if statement after the floor is implemented as an actor
			nextVertVelo = 0;
		}
		
		nextHorzVelo = applyForce(xVelo, forceX); 							// Apply  y force from movement action to y velocity
		nextHorzVelo = applyForce(nextHorzVelo, (friction*(nextHorzVelo))); //Apply frictional force
		nextXPos  = applyForce(xPos,nextHorzVelo);
		nextHorzVelo = maxLimit(nextHorzVelo, getMaxHorizVelocity());
		setValues(a,  nextHorzVelo,  nextVertVelo,  nextXPos,  nextYPos );	
	}
	
	/**
	 * Sets position and velocity variables for an Actor
	 * 
	 * @param a
	 * @param nextHorzVelo
	 * @param nextVertVelo
	 * @param nextXPos
	 * @param nextYPos
	 */
	private void setValues(IPlayActor a, double nextHorzVelo, double nextVertVelo, double nextXPos, double nextYPos){
		a.setVeloX(nextHorzVelo);
		a.setVeloY(nextVertVelo);
		a.setX(bound(nextXPos));
		a.setY(bound(nextYPos));	
	}
	
	/**
	 * Method returns 0 if given position parameter is less than 0
	 * Used to prevent actor from leaving screen to left or top
	 * @param pos
	 * @return
	 */
	private double bound(double pos){
		if(pos<0){
			return 0;
		}
		return pos;
	}
	
	private double maxLimit(double vector, double limit){
		double v = Math.abs(vector);
		if( v > Math.abs(limit)){
			return limit;
		}
		return vector;
	}
	
	//These methods correspond to Actions that call them
	//They differ in the force applied to the Actor
	
	public void moveRight(IPlayActor a1) {
		update(a1,getHorizontalForce(), 0, 0, a1.getFriction());
	}

	public void moveLeft(IPlayActor a1) {
		update(a1,-getHorizontalForce(), 0, 0, a1.getFriction());
	}
	
	public void jump(IPlayActor a1){
		update(a1,0,getJumpForce(), getGravity(), a1.getFriction());
	}
	
	//gliding methods for when force and gravity aren't applied
	
	public void glideRight(IPlayActor a1) {
		a1.setX(a1.getX()+5);
	}

	public void glideLeft(IPlayActor a1) {
		a1.setX(a1.getX()-5);
	}
	
	public void glideUp(IPlayActor a1 ){
		a1.setY(a1.getY()+5);
	}
	
	public void tick(IPlayActor a1) {
		update(a1,0.0,0.0, getGravity(), friction);
	}
	
	public void staticVerticalCollision(IPlayActor a1){
		a1.setY(a1.getY()-a1.getVeloY());
		a1.setVeloY(0);
	}
	
	public void staticHorizontalCollision(IPlayActor a1){
		a1.setX(a1.getX()-(a1.getVeloX()*2.5));
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


	public int getTimeStep() {
		return timeStep;
	}


	public void setTimeStep(int timeStep) {
		this.timeStep = timeStep;
	}


	public double getFriction() {
		return friction;
	}


	public void setFriction(double friction) {
		this.friction = friction;
	}


	public double getGravity() {
		return gravity;
	}


	public void setGravity(double gravity) {
		this.gravity = gravity;
	}


	public double getMaxHorizVelocity() {
		return maxHorizVelocity;
	}


	public void setMaxHorizVelocity(double maxHorizVelocity) {
		this.maxHorizVelocity = maxHorizVelocity;
	}


	public double getMaxVertVelocity() {
		return maxVertVelocity;
	}


	public void setMaxVertVelocity(double maxVertVelocity) {
		this.maxVertVelocity = maxVertVelocity;
	}


	public double getHorizontalForce() {
		return horizontalForce;
	}


	public void setHorizontalForce(double horizontalForce) {
		this.horizontalForce = horizontalForce;
	}


	public double getJumpForce() {
		return jumpForce;
	}


	public void setJumpForce(double jumpForce) {
		this.jumpForce = jumpForce;
	}
	
	public double getFloorHeight() {
		return floorHeight;
	}


	public void setFloorHeight(double floorHeight) {
		this.floorHeight = floorHeight;
	}

	public void moveForward(IPlayActor a1) {
		update(a1, getHorizontalForce()*Math.cos(Math.toRadians(a1.getHeading())), 0, 0, a1.getFriction());
		update(a1,-getHorizontalForce()*Math.sin(Math.toRadians(a1.getHeading())), 0, 0, a1.getFriction());
	}

	public void moveBackward(IPlayActor iPlayActor) {
		update(iPlayActor, -getHorizontalForce()*Math.cos(Math.toRadians(iPlayActor.getHeading())), 0, 0, iPlayActor.getFriction());
		update(iPlayActor,  getHorizontalForce()*Math.sin(Math.toRadians(iPlayActor.getHeading())), 0, 0, iPlayActor.getFriction());
	}

	public void glideBackward(IPlayActor myActor) {
		// TODO Auto-generated method stub
		
	}

	public void glideForward(IPlayActor myActor) {
		// TODO Auto-generated method stub
		
	}
	
}