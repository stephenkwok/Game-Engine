package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * 
 * This class generates a pop up helping authors use the Authoring Environment
 * 
 * @author Stephen
 *
 */

public class PopUpAuthoringHelpPage extends PopUpParent {

	private static final String RESOURCE_BUNDLE_KEY = "helpPage";
	private static final String ACTOR_ENVIRONMENT_QUESTIONS_KEY = "ActorQuestions";
	private static final String LEVEL_ENVIRONMENT_QUESTIONS_KEY = "LevelQuestions";
	private static final String TEXT = "Text";
	private static final String WELCOME_MESSAGE = "Frequently Asked Questions";
	private static final String BOLD_FONT = "-fx-font-weight: bold;";
	private static final String DELIMITER = ",";
	private static final String ANSWER = "Answer";
	private static final int POPUP_WIDTH = 400;
	private static final int POPUP_HEIGHT = 600;
	private static final double HEADER_FONT_SIZE = 20.0;
	private final ResourceBundle myResources;
	private List<String> actorEnvironmentQuestions;
	private List<String> levelEnvironmentQuestions;

	public PopUpAuthoringHelpPage(String elementName) {
		super(POPUP_WIDTH, POPUP_HEIGHT);
		myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY);
		initializeContainerSettings();
		initializePageContents(elementName);
		setLabelsToWrapText();
		bindChildrenWidthsToContainerWidth();
	}
	
	/**
	 * Initializes the container holding all nodes in the pop up
	 */
	private void initializeContainerSettings() {
		getContainer().setAlignment(Pos.TOP_LEFT);
	}

	/**
	 * Initializes the contents of the pop up
	 */
	private void initializePageContents(String elementName) {
		initializeHeader();
		addQuestionAndAnswerToPage(elementName + TEXT);
		/*actorEnvironmentQuestions = Arrays
				.asList(myResources.getString(ACTOR_ENVIRONMENT_QUESTIONS_KEY).split(DELIMITER));
		actorEnvironmentQuestions.stream().forEach(questionKey -> addQuestionAndAnswerToPage(questionKey));*/
	}
	
	/**
	 * Initializes the pop up's header
	 */
	private void initializeHeader() {
		Label header = new Label(WELCOME_MESSAGE);
		header.setAlignment(Pos.CENTER);
		header.setStyle(BOLD_FONT);
		header.setFont(new Font(HEADER_FONT_SIZE));
		getContainer().getChildren().add(header);
	}
	
	/**
	 * Sets the wrapText property to true for all Labels in this pop up
	 */
	private void setLabelsToWrapText() {
		getContainer().getChildren().stream().forEach(node -> ((Label) node).setWrapText(true));
	}
	
	/**
	 * Retrieves questions and answers from property file and displays them 
	 * in the pop up
	 * @param questionKey: the key to retrieve a question from the property file
	 */
	private void addQuestionAndAnswerToPage(String key) {
		/*String question = myResources.getString(questionKey);
		String answer = myResources.getString(questionKey + ANSWER);
		Label questionLabel = new Label(question);
		questionLabel.setStyle(BOLD_FONT);
		Label answerLabel = new Label(answer);
		getContainer().getChildren().addAll(questionLabel, answerLabel);*/
		Label textLabel = new Label(myResources.getString(key));
		getContainer().getChildren().add(textLabel);
	}

}
