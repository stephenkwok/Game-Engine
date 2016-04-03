package usecases;



public class PhysicsEngine{
	
	double fallingAcceleration; //Rate at which actor loses Y velocity when falling
	double jumpStrength;        //Initial y velocity upon a jump action
	
	PhysicsEngine(double gravity, double jumpHeight){
		fallingAcceleration = gravity;
		jumpStrength = jumpHeight;
	}
	/**
	 * Sets the initial y velocity on a jump action
	 * 
	 * @param  Actor  - Method receives the Actor performing the jump action
	 * @return Nothing - Methods updates Actor position/velocity attributes
	 */
	public void jump(Actor a){
		a.setYVelo(jumpStrength);
		updatePosition(a);
		a.setYVelo(updateVelocity(a.getYVelo()));
	}
	
	/**
	 * Updates a x_position based on an x_velocity
	 * 
	 * @param x_pos
	 * @param x_velocity
	 * @return Updated x position
	 */
	public double updateXPosition(double x_pos, double x_velocity){
		x_pos  = x_pos + x_velocity;
		return x_pos;
	}
	
	/**
	 * Updates a y_position based on an y_velocity
	 * 
	 * @param y_pos
	 * @param y_velocity
	 * @return Updated y position
	 */
	public double updateYPosition(double y_pos, double y_velocity){
		y_pos = y_pos - y_velocity;
		return y_pos; 
	}
	
	/**
	 * Updates an Actor's position with calculated values
	 * @param Actor whose position is being updated
	 * @return Nothing
	 */
	public void updatePosition(Actor a){
		a.setXPos( updateXPosition(a.getX(), a.getXVelo()) );
		a.setYPos( updateYPosition(a.getY(), a.getYVelo()) );
	}

	/**
	 * Calculates an Actor's new Y velocity based on it's current 
	 * y_velocity and the given fallingAcceleration
	 * 
	 * @param y_velo
	 * @return Updated y velocity
	 */
	private double updateVelocity(double y_velo) {
		y_velo = y_velo + fallingAcceleration;
		return y_velo;
	}
	
	
}
