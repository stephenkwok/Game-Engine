package gameengine.model;

import java.lang.reflect.Constructor;
import java.util.List;
import gameengine.model.Actor;
import gameengine.model.Triggers.CollisionTrigger;
import javafx.geometry.Point2D;

/**
 * Collision Detection class handles checking for collisions among a list of
 * Actors It also handles resolving said collision should one be found
 * 
 * @author justinbergkamp
 *
 */
public class CollisionDetection {

	private String SideCollision = "SideCollision";
	private String TopCollision = "TopCollision";
	private String BottomCollision = "BottomCollision";
	private static final String TRIGGER_PREFIX = "gameengine.model.Triggers.";

	private PhysicsEngine myPhysicsEngine;

	public CollisionDetection(PhysicsEngine physicsEngine) {
		setMyPhysicsEngine(physicsEngine);
	}

	/**
	 * Called on list of actors in Level to detect any collisions between unique
	 * actors
	 * 
	 * @return List of actors with updated position variables
	 */
	public List<IPlayActor> detection(List<IPlayActor> list) {
		for (IPlayActor a1 : list) {
			for (IPlayActor a2 : list) {
				if (a1 != a2) { // Checks that each actor in the pair is unique
					if (isCollision(a1, a2))
						resolveCollision(a1, a2);
				}
			}
		}
		return list;
	}

	/**
	 * Determines if a collision is occurring by checking for intersecting
	 * Bounds.
	 * 
	 * @param a1
	 * @param a2
	 * @return True = Is Collision, False = No Collision
	 */
	private boolean isCollision(IPlayActor a1, IPlayActor a2) {
		return a1.getBounds().intersects(a2.getBounds());
	}

	private Point2D findCenter(IPlayActor a1) {
		double centerX = (a1.getBounds().getWidth()) * .5 + a1.getBounds().getMinX();
		double centerY = (a1.getBounds().getHeight()) * .5 + a1.getBounds().getMinY();
		Point2D center = new Point2D(centerX, centerY);
		return center;
	}

	/**
	 * Determines which type of Collision is occurring: Side/Top/Bottom Method
	 * does so by checking if a pair of actors overlaps more along the
	 * horizontal or vertical axis
	 * 
	 * @param a1
	 * @param a2
	 * @return Type of collision-String
	 */
	private String getCollisionType(IPlayActor a1, IPlayActor a2) {

		double w = (0.5 * (a1.getBounds().getWidth() + a2.getBounds().getWidth()));
		double h = (0.5 * (a1.getBounds().getHeight() + a2.getBounds().getHeight()));

		Point2D a1Center = findCenter(a1);
		Point2D a2Center = findCenter(a2);

		double dx = (a1Center.getX() - a2Center.getX());
		double dy = (a1Center.getY() - a2Center.getY());

		double wy = w * dy;
		double hx = h * dx;

		if (wy > hx) {
			if (wy > -hx) {
				return TopCollision;
			} else {
				return SideCollision;
			}
		} else {
			if (wy > -hx) {
				return SideCollision;
			} else {
				return BottomCollision;
			}
		}
	}

	private void resolveCollision(IPlayActor a1, IPlayActor a2) {
		String collisionType = getCollisionType(a1, a2);
		try {
			Class<?> className = Class.forName(TRIGGER_PREFIX + collisionType);
			Constructor[] myConstructors = className.getConstructors();
			CollisionTrigger myTrigger = (CollisionTrigger) myConstructors[0].newInstance(a1, a2);
			a1.handleTrigger(myTrigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PhysicsEngine getMyPhysicsEngine() {
		return myPhysicsEngine;
	}

	public void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine) {
		this.myPhysicsEngine = myPhysicsEngine;
	}
}