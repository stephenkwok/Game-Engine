package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import gameengine.controller.Level;
import gui.view.PopUpRuleAdder;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * 
 * @author amyzhao
 *
 */

public class LevelRuleEditor implements IEditingElement {
	private static final String TRIGGERS = "Triggers";
	private static final String PARAMETERS = "Parameters";
	private static final String ACTIONS = "Actions";
	private static final int POP_UP_WIDTH = 100;
	private static final int POP_UP_HEIGHT = 200;
	private TableView myContainer;
	private TableColumn myTriggers;
	private TableColumn myParams;
	private TableColumn myActions;
	private PopUpRuleAdder myRuleAdder;
	private Level myLevel;
	//private ResourceBundle myResources;
	
	public LevelRuleEditor(Level level) {
		myLevel = level;
		init();
	}
	
	private void init() {
		myContainer = new TableView();
		myTriggers = new TableColumn(TRIGGERS);
		myParams = new TableColumn(PARAMETERS);
		myActions = new TableColumn(ACTIONS);
		myRuleAdder = new PopUpRuleAdder(POP_UP_WIDTH, POP_UP_HEIGHT);
	}
	
	public void addRule() {
		
	}

	@Override
	public void setEditableElement(IEditableGameElement element) {
		myLevel = (Level) element;
	}
	
}
