package gameengine.model;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActorRule;
import gameengine.model.Actions.Action;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This interface defines the subset of Actor functionality that will be accessible to the game authoring environment.
 * We decided to make a separate interface since the authoring environment has different functionality requirements.
 *
 * @author blakekaplan
 */
public interface IAuthoringActor extends IEditableGameElement{
    void performActionsFor(String triggerString);

    void addAttribute(Attribute newAttribute);

    Attribute getAttribute(AttributeType type);

    void changeAttribute(AttributeType type, int change);

    void addRule(IRule newRule);

    Set<String> getTriggers();

    int getMyID();

    double getVeloX();

    double getVeloY();

    void setX(double updateXPosition);

    void setY(double updateYPosition);

    void setVeloX(double updateXVelo);

    void setVeloY(double updateYVelo);

    void setMyID(int ID);

    String getMyName();

    void setMyName(String name);

    ImageView getImageView();

    void setImageView(ImageView imageView);

    double getX();

    double getY();

    void setEngine(PhysicsEngine physicsEngine);

    PhysicsEngine getPhysicsEngine();

    String toString();

    String getMyImageViewName();
    
    void setMyImageViewName(String imageViewName);

    void setInAir(boolean inAir);

    Map<String, List<Action>> getMyRules();

    void setMyRules(Map<String, List<Action>> myRules);

    Map<AttributeType, Attribute> getAttributeMap();

    void setAttributeMap(Map<AttributeType, Attribute> attributeMap);

    PhysicsEngine getMyPhysicsEngine();

    void setMyPhysicsEngine(PhysicsEngine myPhysicsEngine);

    void setSize(double size);

    void setMyHealth(double myHealth);

    double getMyHealth();

    void addActorRule(ActorRule actorRule);

    void removeActorRule(ActorRule actorRule);

    List<ActorRule> getActorRules();

    boolean isDead();

    void setDead(boolean isDead);

	boolean isMain();

	void setMain(boolean parseBoolean);

	void setMyFriction(double parseDouble);
}
