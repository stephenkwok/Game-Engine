package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * 
 * This class serves as the toolbar for the Image Editing Environment and allows the author to 
 * use sliders to adjust an image's size, rotation, and opacity. The toolbar also includes buttons
 * that flip the image either horizontally or vertically on click. 
 * 
 * @author Stephen
 *
 */

public class ImageEditingToolbar extends Observable {

	private static final int OPACITY_MIN_VALUE = 0;
	private static final int OPACITY_MAX_VALUE = 1;
	private static final int SIZE_MIN_VALUE = 10;
	private static final int SIZE_MAX_VALUE = 450;
	private static final int ROTATE_MIN_VALUE = -180;
	private static final int ROTATE_MAX_VALUE = 180;
	private static final int INVERT_SIGN_MULTIPLIER = -1;
	private static final String OPACITY_LABEL = "Opacity: ";
	private static final String SIZE_LABEL = "Size: ";
	private static final String ROTATE_LABEL = "Rotation: ";
	private static final String FLIP_HORIZONTAL_BUTTON_TEXT = "Flip Image Horizontally";
	private static final String FLIP_VERTICAL_BUTTON_TEXT = "Flip Image Vertically";
	private static final String FINISH_BUTTON_TEXT = "Finish and Save Changes";
	private static final int CONTAINER_SPACING = 10;
	private static final int CONTAINER_PADDING = 10;
	private final VBox myContainer;
	private HBox myOpacityContainer, mySizeContainer, myRotateContainer, myFlipButtonsContainer;
	private List<Slider> mySliders;
	private List<HBox> mySliderContainers;
	private ImageView myImageView;
	private Slider myOpacitySlider, mySizeSlider, myRotateSlider;
	private Button myFlipHorizontalButton, myFlipVerticalButton, myFinishButton;

	public ImageEditingToolbar(ImageView imageView) {
		myImageView = imageView;
		mySliders = new ArrayList<>();
		myContainer = new VBox(CONTAINER_SPACING);
		mySliderContainers = new ArrayList<>();
		initializeSliders();
		initializeSliderContainers();
		initializeFlipButtons();
		initializeFlipHorizontallyButton();
		initializeFlipVerticalButton();
		initializeFinishButton();
	}

	/**
	 * Initializes the size, rotate, and opacity sliders that update an image's
	 * size, rotate, and opacity attributes
	 */
	private void initializeSliders() {
		initializeSizeSlider();
		initializeRotateSlider();
		initializeOpacitySliders();
		mySliders.stream().forEach(slider -> configureSlider(slider));
	}

	/**
	 * Initializes the containers that hold the sliders
	 */
	private void initializeSliderContainers() {
		mySliderContainers.stream().forEach(container -> configureContainer(container));
	}

	/**
	 * Configures a slider to show tick marks and tick labels, and sets their
	 * HGrow priority to ALWAYS.
	 * 
	 * @param slider: slider to configure
	 */
	private void configureSlider(Slider slider) {
		HBox.setHgrow(slider, Priority.ALWAYS);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
	}

	/**
	 * Configures an HBox that will contain a slider along with a Label indicating
	 * what image property the slider edits and a Label indicating the current value
	 * for that image property
	 * 
	 * @param container: the HBox containing a slider along with a Label indicating
	 * what image property the slider edits and a Label indicating the current value
	 * for that image property
	 */
	private void configureContainer(HBox container) {
		container.setSpacing(CONTAINER_SPACING);
		container.prefWidthProperty().bind(myContainer.widthProperty());
		container.setPadding(new Insets(CONTAINER_PADDING));
		myContainer.getChildren().add(container);
	}
	
	/**
	 * Initializes the buttons that allow the user to flip an image horizontally 
	 * or vertically and places these buttons in an HBox container
	 */
	private void initializeFlipButtons() {
		myFlipButtonsContainer = new HBox(CONTAINER_SPACING);
		initializeFlipHorizontallyButton();
		initializeFlipVerticalButton();
		myFlipButtonsContainer.setAlignment(Pos.CENTER);
		myFlipButtonsContainer.getChildren().addAll(myFlipHorizontalButton, myFlipVerticalButton);
		myContainer.getChildren().add(myFlipButtonsContainer);
	}

	/**
	 * Initializes the slider that adjusts an image's opacity as well as a Label indicating that 
	 * this slider sets the image's opacity and a Label displaying the image's current opacity
	 */
	private void initializeOpacitySliders() {
		Label opacityLabel = new Label(OPACITY_LABEL);
		myOpacitySlider = new Slider(OPACITY_MIN_VALUE, OPACITY_MAX_VALUE, myImageView.getOpacity());
		Label currentOpacityValue = new Label(String.format("%.2f", myRotateSlider.getValue()));
		myOpacitySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myImageView.setOpacity(newValue.doubleValue());
				currentOpacityValue.setText(String.format("%.2f", newValue));
			}
		});
		myOpacityContainer = new HBox();
		myOpacityContainer.getChildren().addAll(opacityLabel, myOpacitySlider, currentOpacityValue);
		mySliders.add(myOpacitySlider);
		mySliderContainers.add(myOpacityContainer);
	}

	/**
	 * Initializes the slider that adjusts an image's size as well as a Label indicating that 
	 * this slider sets the image's size and a Label displaying the image's current size
	 */
	private void initializeSizeSlider() {
		Label sizeLabel = new Label(SIZE_LABEL);
		mySizeSlider = new Slider(SIZE_MIN_VALUE, SIZE_MAX_VALUE, myImageView.getFitHeight());
		Label currentSizeValue = new Label(String.format("%.2f", mySizeSlider.getValue()));
		mySizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myImageView.setFitHeight(newValue.doubleValue());
				myImageView.setPreserveRatio(true);
				currentSizeValue.setText(String.format("%.2f", newValue));
			}
		});
		mySizeContainer = new HBox();
		mySizeContainer.getChildren().addAll(sizeLabel, mySizeSlider, currentSizeValue);
		mySliders.add(mySizeSlider);
		mySliderContainers.add(mySizeContainer);
	}

	/**
	 * Initializes the slider that adjusts an image's rotation as well as a Label indicating that 
	 * this slider sets the image's rotation and a Label displaying the image's current rotation
	 */
	private void initializeRotateSlider() {
		Label rotateLabel = new Label(ROTATE_LABEL);
		myRotateSlider = new Slider(ROTATE_MIN_VALUE, ROTATE_MAX_VALUE, myImageView.getRotate());
		Label currentRotateValue = new Label(String.format("%.2f", myRotateSlider.getValue()));
		myRotateSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myImageView.setRotate(newValue.doubleValue());
				currentRotateValue.setText(String.format("%.2f", newValue));
			}
		});
		myRotateContainer = new HBox();
		myRotateContainer.getChildren().addAll(rotateLabel, myRotateSlider, currentRotateValue);
		mySliders.add(myRotateSlider);
		mySliderContainers.add(myRotateContainer);
	}

	/**
	 * Initializes the button that allows the author to flip an image horizontally
	 */
	private void initializeFlipHorizontallyButton() {
		myFlipHorizontalButton = new Button(FLIP_HORIZONTAL_BUTTON_TEXT);
		myFlipHorizontalButton.setOnAction(e -> flipImageHorizontally());
	}

	/**
	 * Initializes the button that allows the author to flip an image vertically
	 */
	private void initializeFlipVerticalButton() {
		myFlipVerticalButton = new Button(FLIP_VERTICAL_BUTTON_TEXT);
		myFlipVerticalButton.setOnAction(e -> flipImageVertically());
	}

	/**
	 * Initializes the button that allows the author to save changes and close the Image Editing Environment
	 */
	private void initializeFinishButton() {
		myFinishButton = new Button(FINISH_BUTTON_TEXT);
		myFinishButton.setOnAction(e -> {
			setChanged();
			notifyObservers();
		});
		myFinishButton.prefWidthProperty().bind(myContainer.widthProperty());
		myContainer.getChildren().add(myFinishButton);
	}

	/**
	 * Flips an image horizontally
	 */
	private void flipImageHorizontally() {
		myImageView.setScaleX(myImageView.getScaleX() * INVERT_SIGN_MULTIPLIER);
	}

	/**
	 * Flips an image vertically
	 */
	private void flipImageVertically() {
		myImageView.setScaleY(myImageView.getScaleY() * INVERT_SIGN_MULTIPLIER);
	}

	/**
	 * Returns the container holding all toolbar components such as buttons and sliders
	 * 
	 * @return the VBox containing holding all toolbar components such as buttons and sliders
	 */
	public VBox getToolbar() {
		return myContainer;
	}

}
