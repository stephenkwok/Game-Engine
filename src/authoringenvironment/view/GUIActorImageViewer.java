package authoringenvironment.view;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import authoringenvironment.controller.Controller;
import gui.view.ComboBoxActorImages;
import gui.view.IGUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class GUIActorImageViewer implements IGUI {
	private static final String AVAILABLE_ACTORS = "Available Actors";
	private static final String IMAGE_RESOURCE = "authoringimages";
	private static final int PADDING = 10;
	private static final String EXTENSION_FILTER_DESCRIPTION = "Image Files (.jpg, .png .gif)";
	private static final String EXTENSIONS = "*.jpg *.png *.gif";
	private static final int STANDARD_IMAGE_HEIGHT = 100;
	private static final String BUTTON_LABEL = "Load Image...";
	private ImageView myActorIV;
	private StackPane myPane;
	private Controller myController;
	private GUIActorEditingEnvironment aEE;
	
	/**
	 * Module that contains actor image display and options for loading an image from available images or from computer directory.
	 * @param aEE
	 * @param myController
	 * @param myActorIV
	 */
	public GUIActorImageViewer(GUIActorEditingEnvironment aEE, Controller myController, ImageView myActorIV) {
		this.aEE = aEE;
		this.myController = myController;
		this.myActorIV = myActorIV;
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		myPane = new StackPane();
		VBox vbox = new VBox(PADDING);
		HBox hbox = new HBox(PADDING);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(myActorIV, getImageSettingButton());
		vbox.getChildren().addAll(hbox, getImagesComboBox());
		myPane.getChildren().add(vbox);
		myPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private HBox getImagesComboBox(){
		ComboBoxActorImages availableImages = new ComboBoxActorImages(AVAILABLE_ACTORS, IMAGE_RESOURCE,aEE);
		return (HBox) availableImages.createNode();
	}
	
	private Button getImageSettingButton(){
		Button imageSetter = new Button(BUTTON_LABEL);
		imageSetter.setOnAction(event->{
			loadSelectedImage();
		});
		return imageSetter;
	}
	
	private void loadSelectedImage(){
		File imageFile = promptForFileName();
		if(imageFile!=null){
			Image image = new Image(imageFile.toURI().toString());
			ImageView imageView = new ImageView(image);
			imageView.setPreserveRatio(true);
	        imageView.setFitHeight(STANDARD_IMAGE_HEIGHT);
	        aEE.setActorImage(imageView);
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
        return myFileChooser.showOpenDialog(myController.getStage());
    }

	@Override
	public Pane getPane() {
		return myPane;
	}
}
