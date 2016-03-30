package gameengine.controller;

import java.util.List;

/**
 * Created by blakekaplan on 3/30/16.
 */
public interface IGame {

    public void setScene(IScene myScene);

    public List<IScene> getScenes();

}
