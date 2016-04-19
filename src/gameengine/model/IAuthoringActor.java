package gameengine.model;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.ActorRule;
import javafx.scene.image.ImageView;

import java.util.List;

/**
 * This interface defines the subset of Actor functionality that will be accessible to the game authoring environment.
 * We decided to make a separate interface since the authoring environment has different functionality requirements.
 *
 * @author blakekaplan
 */
public interface IAuthoringActor extends IEditableGameElement{
    void addRule(IRule newRule);

    void setX(double updateXPosition);

    void setY(double updateYPosition);

    String getMyName();

    void setMyName(String name);

    ImageView getImageView();

    void setImageView(ImageView imageView);
    
    String getMyImageViewName();
    
    void setMyImageViewName(String imageViewName);

    void setSize(double size);

    void addActorRule(ActorRule actorRule);

    void removeActorRule(ActorRule actorRule);

    List<ActorRule> getActorRules();

	boolean isMain();

	void setMain(boolean parseBoolean);

	void setMyFriction(double parseDouble);
}
