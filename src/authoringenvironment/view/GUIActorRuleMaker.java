package authoringenvironment.view;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GUIActorRuleMaker implements IGUI {
	private StackPane myWrapperPane;
	private ScrollPane myScrollPane;
	
	public GUIActorRuleMaker() {
		myWrapperPane = new StackPane();
		myScrollPane = new ScrollPane();
		myScrollPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		myWrapperPane.getChildren().add(myScrollPane);
	}

	@Override
	public Pane getPane() {
		return myWrapperPane;
	}

	@Override
	public void updateAllNodes() {
		
	}

}
