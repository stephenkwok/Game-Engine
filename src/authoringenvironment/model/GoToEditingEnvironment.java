package authoringenvironment.model;

import javafx.stage.Stage;

public class GoToEditingEnvironment implements IGoToEditingEnvironment {

	@Override
	public void goToEditingEnviroment(Stage stage, IEditingEnvironment environment, IEditableGameElement editable) {
		environment.setEditable(editable);
		stage.setScene(environment.getScene());
	}

}
