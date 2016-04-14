package gamedata.view;

import gameengine.controller.Game;
import gameplayer.view.BaseScreen;
import javafx.stage.Stage;

@Deprecated
public class FileChooserScreenLoad extends FileChooserScreen {

	public FileChooserScreenLoad(Stage myStage) {
		super(myStage);
	}

	@Override
	public void use(Game game) {
		BaseScreen myB = new BaseScreen(getStage(), game);
		getStage().setScene(myB.getMyScene());

	}

}
