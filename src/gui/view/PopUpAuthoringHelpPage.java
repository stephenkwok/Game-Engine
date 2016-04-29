package gui.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
	private static final String TOPICS = "Topics";
	private static final String HEADER = "Header";
	private static final String SUBTOPICS = "Subtopics";
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
	private static final double TOPIC_HEADER_FONT_SIZE = 15.0;
	private static final double SUBTOPIC_HEADER_FONT_SIZE = 12.0;
	
	private final ResourceBundle myResources;
//	private List<String> actorEnvironmentQuestions;
//	private List<String> levelEnvironmentQuestions;
	private List<String> topics;

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
	
	private Label createHeader(String headerText, double fontSize) {
		Label header = new Label(headerText);
		header.setStyle(BOLD_FONT);
		header.setFont(new Font(fontSize));
		return header;
	}

	/**
	 * Initializes the contents of the pop up
	 */
	private void initializePageContents(String elementName) {
		initializeHeader();
		topics = Arrays.asList(myResources.getString(TOPICS).split(DELIMITER));
		for (String topic : topics) {
			
		}
		
		addQuestionAndAnswerToPage(elementName + TEXT);
		/*actorEnvironmentQuestions = Arrays
				.asList(myResources.getString(ACTOR_ENVIRONMENT_QUESTIONS_KEY).split(DELIMITER));
		actorEnvironmentQuestions.stream().forEach(questionKey -> addQuestionAndAnswerToPage(questionKey));*/
	}
	
	private VBox createTopicContent(String topic) {
		VBox topicContainer = new VBox();
		String topicHeaderText = myResources.getString(topic + HEADER);
		Label topicHeader = createHeader(topicHeaderText, TOPIC_HEADER_FONT_SIZE);
		
		List<String> subtopics = Arrays.asList(myResources.getString(topic + SUBTOPICS).split(DELIMITER));
//		for (String subtopic : )
//		String subtopicHeader = myResources.getString(subtopic + HEADER);
		return topicContainer;
		
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
