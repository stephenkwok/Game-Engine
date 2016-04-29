package gui.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoringenvironment.model.IActionCreator;
import authoringenvironment.model.ITriggerCreator;
import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.AttributeTriggerAndActionCreator;
import authoringenvironment.view.CreateActorActionCreator;
import authoringenvironment.view.KeyTriggerCreator;
import authoringenvironment.view.LevelEditingEnvironment;
import authoringenvironment.view.LoseGameActionCreator;
import authoringenvironment.view.NextLevelActionCreator;
import authoringenvironment.view.TabRuleAdder;
import authoringenvironment.view.TickTriggerCreator;
import authoringenvironment.view.TriggerFactory;
import authoringenvironment.view.WinGameActionCreator;
import gameengine.controller.Level;
import gameengine.model.IAction;
import gameengine.model.IGameElement;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Triggers.ITrigger;

/**
 * 
 * @author amyzhao
 *
 */
public class PopUpRuleAdder extends PopUpParent {
	private LevelEditingEnvironment myLevelEditor;
	private TabPane myPane;
	private TabRuleAdder myRuleAdder;
	private ResourceBundle myResources;
	private static final String RESOURCE = "ruleAdder";
	private static final String ADD_RULES = "Add New Rule";


	public PopUpRuleAdder(int popUpWidth, int popUpHeight, LevelEditingEnvironment environment) {
		super(popUpWidth, popUpHeight);
		myLevelEditor = environment;
		myResources = ResourceBundle.getBundle(RESOURCE);
		init();
	}

	private void init() {
		myPane = new TabPane();
		myRuleAdder = new TabRuleAdder(myResources, ADD_RULES, myLevelEditor);
		myPane.getTabs().add(myRuleAdder.getTab());
		getContainer().getChildren().add(myPane);
		myPane.prefWidthProperty().bind(getContainer().widthProperty());
		myPane.prefHeightProperty().bind(getContainer().heightProperty());
	}

}