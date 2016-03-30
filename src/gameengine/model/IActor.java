package gameengine.model;

/**
 * Created by blakekaplan on 3/30/16.
 */
public interface IActor {

    public double getX();

    public double getY();

    public double getDirection();

    public double getHealth();

    public double getPoints();

    public void setX();

    public void setY();

    public void setDirection();

    public void setPoints();

    public void setHealth();

}
