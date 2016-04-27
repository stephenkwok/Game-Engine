package authoringenvironment.model;

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
		myContainer = new VBox(10.0);
		mySliderContainers = new ArrayList<>();
		initializeSliders();
		initializeSliderContainers();
		initializeFlipButtons();
		initializeFlipHorizontallyButton();
		initializeFlipVerticalButton();
		initializeFinishButton();
	}

	private void initializeSliders() {
		initializeSizeSlider();
		initializeRotateSlider();
		initializeOpacitySliders();
		mySliders.stream().forEach(slider -> configureSlider(slider));
	}

	private void initializeSliderContainers() {
		mySliderContainers.stream().forEach(container -> configureContainer(container));
	}

	private void configureSlider(Slider slider) {
		HBox.setHgrow(slider, Priority.ALWAYS);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
	}

	private void configureContainer(HBox container) {
		container.setSpacing(CONTAINER_SPACING);
		container.prefWidthProperty().bind(myContainer.widthProperty());
		container.setPadding(new Insets(CONTAINER_PADDING));
		myContainer.getChildren().add(container);
	}
	
	private void initializeFlipButtons() {
		myFlipButtonsContainer = new HBox(CONTAINER_SPACING);
		initializeFlipHorizontallyButton();
		initializeFlipVerticalButton();
		myFlipButtonsContainer.setAlignment(Pos.CENTER);
		myFlipButtonsContainer.getChildren().addAll(myFlipHorizontalButton, myFlipVerticalButton);
		myContainer.getChildren().add(myFlipButtonsContainer);
	}

	private void initializeOpacitySliders() {
		Label opacityLabel = new Label(OPACITY_LABEL);
		myOpacitySlider = new Slider(OPACITY_MIN_VALUE, OPACITY_MAX_VALUE, myImageView.getOpacity());
		Label currentOpacityValue = new Label(Double.toString(myOpacitySlider.getValue()));
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

	private void initializeSizeSlider() {
		Label sizeLabel = new Label(SIZE_LABEL);
		mySizeSlider = new Slider(SIZE_MIN_VALUE, SIZE_MAX_VALUE, myImageView.getFitHeight());
		Label currentSizeValue = new Label(Double.toString(mySizeSlider.getValue()));
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

	private void initializeRotateSlider() {
		Label rotateLabel = new Label(ROTATE_LABEL);
		myRotateSlider = new Slider(ROTATE_MIN_VALUE, ROTATE_MAX_VALUE, myImageView.getRotate());
		Label currentRotateValue = new Label(Double.toString(myRotateSlider.getValue()));
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

	private void initializeFlipHorizontallyButton() {
		myFlipHorizontalButton = new Button(FLIP_HORIZONTAL_BUTTON_TEXT);
		myFlipHorizontalButton.setOnAction(e -> flipImageHorizontally());
	}

	private void initializeFlipVerticalButton() {
		myFlipVerticalButton = new Button(FLIP_VERTICAL_BUTTON_TEXT);
		myFlipVerticalButton.setOnAction(e -> flipImageVertically());
	}

	private void initializeFinishButton() {
		myFinishButton = new Button(FINISH_BUTTON_TEXT);
		myFinishButton.setOnAction(e -> {
			setChanged();
			notifyObservers();
		});
		myFinishButton.prefWidthProperty().bind(myContainer.widthProperty());
		myContainer.getChildren().add(myFinishButton);
	}

	private void flipImageHorizontally() {
		myImageView.setScaleX(myImageView.getScaleX() * INVERT_SIGN_MULTIPLIER);
	}

	private void flipImageVertically() {
		myImageView.setScaleY(myImageView.getScaleY() * INVERT_SIGN_MULTIPLIER);
	}

	public VBox getToolbar() {
		return myContainer;
	}

}
