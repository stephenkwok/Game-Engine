package gameplayer.view;

import gui.view.IScreen;
import voogasalad.util.hud.source.AbstractHUDScreen;

public interface IBaseScreen extends IScreen {

	void setHUDScreen(AbstractHUDScreen view);

	void setGameScreen(IGameScreen view);

	void switchAlert();

}
