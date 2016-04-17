package authoringenvironment.view;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import authoringenvironment.controller.Controller;
import gui.view.ButtonFileChooserActorImage;
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
	private static final String AVAILABLE_ACTOR_IMAGES = "Available Images";
	private static final String IMAGE_RESOURCE = "authoringimages";
	private static final int PADDING = 10;
	private static final String BUTTON_LABEL = "Load Image...";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 150;
	private ImageView myActorIV;
	private StackPane myPane;
	private Controller myController;
	private ActorEditingEnvironment aEE;
	
	/**
	 * Module that contains actor image display and options for loading an image from available images or from computer directory.
	 * @param aEE
	 * @param myController
	 * @param myActorIV
	 */
	public GUIActorImageViewer(ActorEditingEnvironment aEE, Controller myController, ImageView myActorIV) {
		this.aEE = aEE;
		this.myController = myController;
		this.myActorIV = myActorIV;
		initializeEnvironment();
	}
	/**
	 * Initialize resources and create image viewer component, displaying Actor image and allows user to set the image
	 */
	private void initializeEnvironment(){
		myPane = new StackPane();
		VBox vbox = new VBox(PADDING);
		HBox hbox = new HBox(PADDING);
		hbox.setAlignment(Pos.CENTER);
		ButtonFileChooserActorImage imageChooser = new ButtonFileChooserActorImage(myController, BUTTON_LABEL, null, BUTTON_WIDTH, BUTTON_HEIGHT, myController.getStage(), aEE);
		hbox.getChildren().addAll(myActorIV, imageChooser.createNode());
		vbox.getChildren().addAll(hbox, getImagesComboBox());
		myPane.getChildren().add(vbox);
		myPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	/**
	 * Returns combobox of available actor images (in authoringimages resource folder)
	 * @return
	 */
	private HBox getImagesComboBox(){
		ComboBoxActorImages availableImages = new ComboBoxActorImages(AVAILABLE_ACTOR_IMAGES, IMAGE_RESOURCE,aEE);
		return (HBox) availableImages.createNode();
	}
	
    /**
     * Return Pane representation of actor image viewer
     */
	@Override
	public Pane getPane() {
		return myPane;
	}
}
