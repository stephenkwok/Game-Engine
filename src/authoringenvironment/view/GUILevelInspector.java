package authoringenvironment.view;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gui.view.IGUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUILevelInspector implements IGUI {
	private static final String BUTTON_LABEL = "Choose a new background image";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 300;
	private static final String EXTENSION_FILTER_DESCRIPTION = "Image Files (.jpg, .png .gif)";
	private static final String EXTENSIONS = "*.jpg *.png *.gif";
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private static final String ACTORS = "Actors";
	private static final String LEVEL_ATTRIBUTES = "Level Attributes";
	private static final int SPACING = 5;
	private Level myLevel;
	private Controller myController;
	private Pane myPane;
	private TabActors myActorsTab;
	private TabAttributes myAttributesTab;
	private VBox myContainer;
	
	/**
	 * Constructor for Level Inspector.
	 * @param controller: authoring environment controller.
	 * @param myResources: resource bundle for authoring environment.
	 * @param availActors: list of currently available actors.
	 * @param level: level that is being edited.
	 */
	public GUILevelInspector(Controller controller, ResourceBundle myResources, List<IAuthoringActor> availActors, Level level) {
		myLevel = level;
		myController = controller;
		init(controller, myResources, availActors, level);
	}
	
	private void init(Controller controller, ResourceBundle myResources, List<IAuthoringActor> availActors, Level level) {
		myPane = new StackPane();		
		myContainer = new VBox(SPACING);
		myContainer.setAlignment(Pos.CENTER);
		myActorsTab = new TabActors(myResources, ACTORS, availActors);
		myAttributesTab = new TabAttributes(controller, myResources, LEVEL_ATTRIBUTES,LEVEL_OPTIONS_RESOURCE, level);
		addTabToContainer(myAttributesTab, false);
		myContainer.getChildren().add(getImageSettingButton());
		addTabToContainer(myActorsTab, true);
		myPane.getChildren().addAll(myContainer);
	}

	/**
	 * Adds a tab to the Level Inspector's container.
	 * @param tab: tab to add.
	 * @param bindToContainer: true if you want tab to bind to its individual container's height; false o.w.
	 */
	private void addTabToContainer(TabParent tab, boolean bindToContainer) {
		VBox container = new VBox();
		TabPane tabPane = new TabPane();
		container.getChildren().add(tabPane);
		tabPane.getTabs().add(tab.getTab());
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		if (bindToContainer) {
			VBox.setVgrow(container, Priority.ALWAYS);
			tabPane.prefHeightProperty().bind(container.heightProperty());
		}
		myContainer.getChildren().add(container);
	}
	
	/**
	 * Returns the pane that the level inspector is built on.
	 */
	@Override
	public Pane getPane() {
		return myPane;
	}
	
	/**
	 * Returns the tab of available actors.
	 * @return tab of available actors.
	 */
	public TabActors getActorsTab() {
		return myActorsTab;
	}
	
	/**
	 * Returns the tab of level attributes.
	 * @return tab of level attributes.
	 */
	public TabAttributes getAttributesTab() {
		return myAttributesTab;
	}
	
	/**
	 * Creates an image setting button.
	 * @return a button whose action sets the image.
	 */
	private Button getImageSettingButton(){
		Button imageSetter = new Button(BUTTON_LABEL);
		imageSetter.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		imageSetter.setOnAction(event->{
			try {
				loadSelectedImage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return imageSetter;
	}
	
	// TODO: need to have preview show up on level editing environment
	/**
	 * Loads the selected image from the file selected by the user.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void loadSelectedImage() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File imageFile = promptForFileName();
		if(imageFile!=null){
			Image image = new Image(imageFile.toURI().toString());
			myLevel.setImageView(new ImageView(image));
			myLevel.setMyBackgroundImgName(imageFile.getPath());
		}
	}
	
	/**
     * Creates a file picker to get a file name
     * @return returns the file
     */
    private File promptForFileName(){
        FileChooser myFileChooser = new FileChooser();
        List<String> extensions = Arrays.asList(EXTENSIONS.split(" "));
        FileChooser.ExtensionFilter myFilter = new FileChooser.ExtensionFilter(EXTENSION_FILTER_DESCRIPTION, extensions);
        myFileChooser.getExtensionFilters().add(myFilter);
        return myFileChooser.showOpenDialog(new Stage());
    }

}
