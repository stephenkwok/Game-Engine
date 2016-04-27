package gui.view;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import javafx.scene.control.Label;

/**
 * 
 * @author amyzhao
 *
 */

public class PopUpActorResize extends PopUpParent {
	private static final String LABEL = "Height:";
	private static final String PROMPT = "Enter height (preserves height vs. width ratio)";
	private static final Double WIDTH = 100.0;

	public PopUpActorResize(int popUpWidth, int popUpHeight, IAuthoringActor actor, Controller controller) {
		super(popUpWidth, popUpHeight);
		Label label = new Label("Enter a new height:");
		TextFieldActorSizeEditor sizeEditor = new TextFieldActorSizeEditor(LABEL, PROMPT, WIDTH);
		sizeEditor.setEditableElement(actor);
		sizeEditor.addObserver(controller.getLevelEditingEnvironment());
		getContainer().getChildren().addAll(label, sizeEditor.createNode());
	}

}
