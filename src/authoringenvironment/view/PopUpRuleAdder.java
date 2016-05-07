package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.LevelEditingEnvironment;
import javafx.scene.control.TabPane;

/**
 * Pop up to add rules to a level.
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
	private TabLevelRuleEditor myRuleEditor;
	private static final String EDIT_RULES = "Remove Rules";

	/**
	 * Constructs a PopUpRuleAdder.
	 * @param popUpWidth: width of pop up.
	 * @param popUpHeight: height of pop up.
	 * @param environment: current level editing environment.
	 */
	public PopUpRuleAdder(int popUpWidth, int popUpHeight, LevelEditingEnvironment environment) {
		super(popUpWidth, popUpHeight);
		myLevelEditor = environment;
		myResources = ResourceBundle.getBundle(RESOURCE);
		init();
	}

	/**
	 * Initialize the tab pane.
	 */
	private void init() {
		myPane = new TabPane();
		myRuleEditor = new TabLevelRuleEditor(myResources, EDIT_RULES, myLevelEditor.getLevel());
		myRuleAdder = new TabRuleAdder(myResources, ADD_RULES, myLevelEditor, myRuleEditor);
		myPane.getTabs().addAll(myRuleAdder.getTab(), myRuleEditor.getTab());
		getContainer().getChildren().add(myPane);
		myPane.prefWidthProperty().bind(getContainer().widthProperty());
		myPane.prefHeightProperty().bind(getContainer().heightProperty());
	}

}