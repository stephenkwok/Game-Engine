package authoringenvironment.model;

import authoringenvironment.view.ActorRule;
import gameengine.model.IRule;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Set;

/**
 * This interface defines the subset of Actor functionality that will be accessible to the game authoring environment.
 * We decided to make a separate interface since the authoring environment has different functionality requirements.
 *
 * @author blakekaplan
 */
public interface IAuthoringActor extends IEditableGameElement{
    void addRule(IRule newRule);

    Set<String> getTriggers();

    void setX(double updateXPosition);

    void setY(double updateYPosition);

    String getMyName();

    void setMyName(String name);

    ImageView getImageView();

    void setImageView(ImageView imageView);

    double getX();

    double getY();
    
    String getMyImageViewName();
    
    void setMyImageViewName(String imageViewName);

    void setSize(double size);

    void addActorRule(ActorRule actorRule);

    void removeActorRule(ActorRule actorRule);

    List<ActorRule> getActorRules();

	boolean isMain();

	void setMain(boolean parseBoolean);

	void setMyFriction(double parseDouble);

    void setMyID(int id);

    int getMyID();
}
