package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.IAuthoringActor;
import gui.view.ButtonFileChooserActorImage;
import gui.view.ComboBoxActorImages;
import gui.view.ComboBoxSpriteImages;
import gui.view.IGUI;
import gui.view.IGUIElement;
import gui.view.ObjectObservable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ActorImageViewer implements IGUI {
	private static final String AVAILABLE_ACTOR_IMAGES = "Available Images";
	private static final String AVAILABLE_SPRITE_IMAGES = "Available Sprites";
	private static final String IMAGE_RESOURCE = "images";
	private static final double ACTOR_DISPLAY_FIT_SIZE = 80.0;
	private static final double DEFAULT_HEIGHT = 20;
	private static final int PADDING = 10;
	private static final String BUTTON_LABEL = "Load Image...";
	private ImageView myActorIV;
	private StackPane myPane;
	private ActorEditingEnvironment aEE;
	private List<IGUIElement> elementList;
	

	/**
	 * Module that contains actor image display and options for loading an image
	 * from available images or from computer directory.
	 * 
	 * @param aEE
	 * @param myController
	 * @param myActorIV
	 */
	public ActorImageViewer(ActorEditingEnvironment aEE, ImageView myActorIV) {
		this.aEE = aEE;
		this.myActorIV = myActorIV;
		initializeEnvironment();
	}

	/**
	 * Initialize resources and create image viewer component, displaying Actor
	 * image and allows user to set the image
	 */
	private void initializeEnvironment() {
		this.elementList = new ArrayList<>();
		myPane = new StackPane();
		VBox vbox = new VBox(PADDING);
		HBox hbox = new HBox(PADDING);
		hbox.setAlignment(Pos.CENTER);
		ButtonFileChooserActorImage imageChooser = new ButtonFileChooserActorImage(BUTTON_LABEL, null, aEE,
				aEE.getStage());
		imageChooser.addObserver(aEE);

		ImageView myActorIVCopy = new ImageView(myActorIV.getImage());
		myActorIVCopy.setFitHeight(ACTOR_DISPLAY_FIT_SIZE);
		myActorIVCopy.setFitWidth(ACTOR_DISPLAY_FIT_SIZE);
		myActorIVCopy.setPreserveRatio(true);
		hbox.getChildren().addAll(myActorIVCopy, imageChooser.createNode());

		vbox.getChildren().addAll(hbox, getImagesComboBox(), getSpritesComboBox());
		myPane.getChildren().add(vbox);
		myPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		addObserversToElements();
	}

	/**
	 * Returns combobox of available actor images (in authoringimages resource
	 * folder)
	 * 
	 * @return
	 */
	private HBox getImagesComboBox() {
		ComboBoxActorImages availableImages = new ComboBoxActorImages(AVAILABLE_ACTOR_IMAGES, IMAGE_RESOURCE, aEE);
		elementList.add((IGUIElement) availableImages);
		return (HBox) availableImages.createNode();
	}
	
	private VBox getSpritesComboBox(){
		HBox sprites = new HBox(PADDING);
		populateSprites(sprites);
		ComboBoxSpriteImages spriteImages = new ComboBoxSpriteImages(AVAILABLE_SPRITE_IMAGES, IMAGE_RESOURCE, aEE, sprites);
		elementList.add((IGUIElement) spriteImages);
		
		HBox spriteCombo = (HBox) spriteImages.createNode();
		VBox toReturn = new VBox(PADDING);
		toReturn.getChildren().addAll(spriteCombo, sprites);
		return toReturn;
	}
	
	private void addObserversToElements() {
		for (int i = 0; i < elementList.size(); i++) {
			((ObjectObservable) elementList.get(i)).addObserver(aEE);
		}
	}

	private void populateSprites(HBox sprites){
		List<String> spriteNames = ((IAuthoringActor) aEE.getEditable()).getSprite().getMyImages();
		for(String imageName: spriteNames){
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(DEFAULT_HEIGHT);
			imageView.setPreserveRatio(true);
			sprites.getChildren().add(imageView);
		}
	}
			
	/**
	 * Return Pane representation of actor image viewer
	 */
	@Override
	public Pane getPane() {
		return myPane;
	}
}