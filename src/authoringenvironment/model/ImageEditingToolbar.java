package authoringenvironment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.sun.media.jfxmediaimpl.platform.Platform;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ImageEditingToolbar extends Observable {

	private static final int OPACITY_MIN_VALUE = 0;
	private static final int OPACITY_MAX_VALUE = 1;
	private static final int SIZE_MIN_VALUE = 10;
	private static final int SIZE_MAX_VALUE = 450;
	private static final int ROTATE_MIN_VALUE = -180;
	private static final int ROTATE_MAX_VALUE = 180;
	private static final int INVERT_SIGN_MULTIPLIER = -1;
	private final VBox myContainer;
	private List<Slider> mySliders;
	private ImageView myImageView;
	private Slider myOpacitySlider;
	private Slider mySizeSlider;
	private Slider myRotateSlider;
	private Button myFlipHorizontalButton;
	private Button myFlipVerticalButton;
	private Button myFinishButton;

	public ImageEditingToolbar(ImageView imageView) {
		myImageView = imageView;
		myContainer = new VBox();
		initializeSliders();
		initializeFlipHorizontallyButton();
		initializeFlipVerticalButton();
		initializeFinishButton();
		myContainer.getChildren().addAll(myOpacitySlider, mySizeSlider, myRotateSlider, myFlipHorizontalButton,
				myFlipVerticalButton, myFinishButton);
	}

	private void initializeSliders() {
		mySliders = new ArrayList<>();
		initializeOpacitySliders();
		initializeSizeSlider();
		initializeRotateSlider();
		mySliders.stream().forEach(slider -> configureSlider(slider));
	}

	private void configureSlider(Slider slider) {
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);

	}

	private void initializeOpacitySliders() {
		myOpacitySlider = new Slider(OPACITY_MIN_VALUE, OPACITY_MAX_VALUE, myImageView.getOpacity());
		Label opacityValue = new Label(Double.toString(myOpacitySlider.getValue()));
		myOpacitySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myImageView.setOpacity(newValue.doubleValue());
				opacityValue.setText(String.format("%.2f", newValue));
			}
		});
		mySliders.add(myOpacitySlider);
	}

	private void initializeSizeSlider() {
		mySizeSlider = new Slider(SIZE_MIN_VALUE, SIZE_MAX_VALUE, myImageView.getImage().getHeight());
		Label sizeDisplay = new Label(Double.toString(mySizeSlider.getValue()));
		mySizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myImageView.setFitHeight(newValue.doubleValue());
				myImageView.setPreserveRatio(true);
				sizeDisplay.setText(String.format("%.2f", newValue));
			}
		});
		mySliders.add(mySizeSlider);
	}

	private void initializeRotateSlider() {
		myRotateSlider = new Slider(ROTATE_MIN_VALUE, ROTATE_MAX_VALUE, myImageView.getRotate());
		Label rotateDisplay = new Label(Double.toString(myRotateSlider.getValue()));
		myRotateSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myImageView.setRotate(newValue.doubleValue());
				rotateDisplay.setText(String.format("%.2f", newValue));
			}
		});
		mySliders.add(myRotateSlider);
	}

	private void initializeFlipHorizontallyButton() {
		myFlipHorizontalButton = new Button("Flip Horizontally");
		myFlipHorizontalButton.setOnAction(e -> flipImageHorizontally());
	}

	private void initializeFlipVerticalButton() {
		myFlipVerticalButton = new Button("Flip Vertically");
		myFlipVerticalButton.setOnAction(e -> flipImageVertically());
	}

	private void initializeFinishButton() {
		myFinishButton = new Button("Finish and Save Changes");
		myFinishButton.setOnAction(e -> {
			setChanged();
			notifyObservers();
		});
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
