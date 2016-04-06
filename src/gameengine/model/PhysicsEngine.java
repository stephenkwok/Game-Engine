package gameengine.model;

/**
 * Physics Engine Class
 * Handles calculating/assigning new positions based on positional attributes and movement vectors
 * Needs to be refactored
 * @author justinbergkamp
 */


public class PhysicsEngine {
	
	private int timeStep    = 1;    //Arbitrary timeStep, will be set to the time provided by step()
	private double friction = .05;  //Horizontal acceleration dampening (friction) coefficient
	private double gravity  = .1 ;  //Falling acceleration coefficient
	private double maxXVelo = 50;   //maximum horizontal velocity
	private double maxYVelo =  -50; //maximum vertical velocity
	
	public PhysicsEngine(){
		
	}
	
	
	/**
	 * This method updates a given velocity by applying a given force
	 * @param velo
	 * @param force
	 * @return updated
	 */
	private double applyForce(double velo, double force){  //Applies a force(acceleration because mass =1) to a velocity
		double new_velo = velo + force*timeStep;
		return new_velo;
	}
	
	private double changePos(double pos, double velo){
		double new_pos = pos + velo*timeStep;
		return new_pos;
	}
	
	private void update(Actor a,double forceX, double forceY ){
		double xVelocity = a.getXVelo();
		double yVelocity = a.getYVelo();
		double xPos      = a.getX();
		double yPos      = a.getY();
		
		double new_x_velo = xVelocity;
		double new_y_velo = applyForce(yVelocity, forceY); // y force from movement action
		double new_y_pos  = changePos(yPos, new_y_velo);
		if(new_y_velo == 0){
			new_x_velo = applyForce(xVelocity, forceX); // x force from movement action
			new_x_velo = applyForce(new_x_velo, (friction*(-new_x_velo))); //friction
		}
		
		double new_x_pos  = changePos(xPos,new_x_velo );
		new_y_velo = applyForce(new_y_velo, gravity); //gravity
		new_x_velo = maxLimit(new_x_velo, maxXVelo);
		if(new_y_velo < -50){
			new_y_velo = -50;
		}
		a.setXVelo(new_x_velo);
		a.setYVelo(new_y_velo);
		
		if(new_y_pos > 0){
			a.setYPos(0);			
			a.setYVelo(0);
		}
		a.setXPos(new_x_pos);
		a.setYPos(new_y_pos);			
		
	}
	
	private double maxLimit(double vector, double limit){
		if(vector > limit){
			return limit;
		}
		return vector;
	}
	
	
	public void moveRight(Actor a1) {
		update(a1,5, 0);
	}

	public void moveLeft(Actor a1) {
		update(a1,-5, 0);
	}
	
	public void jump(Actor a1){
		update(a1,0, -5);
	}



	public void tick(Actor a1) {
		update(a1,0.0,0.0);
	}
	
	
}
