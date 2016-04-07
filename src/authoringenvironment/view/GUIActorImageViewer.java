package authoringenvironment.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import authoringenvironment.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class GUIActorImageViewer implements IGUI {
	private static final String IMAGE_RESOURCE = "authoringimages";
	private static final int PADDING = 10;
	private static final String EXTENSION_FILTER_DESCRIPTION = "Image Files (.jpg, .png)";
	private static final String EXTENSIONS = "*.jpg *.png";
	private static final int STANDARD_IMAGE_HEIGHT = 100;
	private static final String BUTTON_LABEL = "Load Image...";
	private ImageView myActorIV;
	private StackPane myPane;
	private Controller myController;
	private GUIActorEditingEnvironment aEE;
	
	public GUIActorImageViewer(GUIActorEditingEnvironment aEE, Controller myController, ImageView myActorIV) {
		this.aEE = aEE;
		this.myController = myController;
		this.myActorIV = myActorIV;
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		myPane = new StackPane();
		HBox hbox = new HBox(PADDING);
		hbox.getChildren().addAll(myActorIV,getImageSetter());
		myPane.getChildren().add(hbox);
		myPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private Button getImageSetter(){
		Button imageSetter = new Button(BUTTON_LABEL);
		imageSetter.setOnAction(event->{
			loadSelectedImage();
		});
		return imageSetter;
	}
	
	private void loadSelectedImage(){
		File imageFile = promptForFileName();
//		addFileToResources(imageFile);
		ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFile.getName())));
		imageView.setPreserveRatio(true);
        imageView.setFitHeight(STANDARD_IMAGE_HEIGHT);
        aEE.setActorImage(imageView);
	}
	
//	private void addFileToResources(File file){
//		File dir=new File(IMAGE_RESOURCE);
//		File tagFile=new File(dir,file.getName());
//		if(!tagFile.exists()){
//			try {
//				tagFile.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
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
