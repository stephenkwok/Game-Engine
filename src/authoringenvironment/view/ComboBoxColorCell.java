package authoringenvironment.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * ComboBox with palette of images that the user can choose from to modify the given Object.
 * @author AnnieTang
 *
 */
abstract class ComboBoxColorCell extends ComboBoxParent{
	private static final int RECTANGLE_SIDE_SIZE = 20;
	private static final String DEFAULT_COLORS_KEY = "DefaultColors";
	protected List<String> palette;
	
	public ComboBoxColorCell(ResourceBundle myResources, String promptText) {
		super(myResources, promptText);
		fillDefaultPalette();
	}
	/**
	 * Sets default color palette based on paletteSource, which is a list of colors from resource file.
	 */
	protected void fillDefaultPalette() {
		palette = new ArrayList<>(Arrays.asList(myResources.getString(DEFAULT_COLORS_KEY).split(",")));
	}
	/**
	 * On comboButton click, specific features will have their color set to the ComboBox value.
	 */
	@Override
	abstract void setButtonAction();
	
	/**
	 * Create icon for each entry in the ComboBox that will show up next to its String value. 
	 */
	@Override
	protected Node getNodeForBox(String item){
		HBox hbox = new HBox();
		String[] rgb = item.split(" ");
	   	Color col = Color.rgb(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
	   	Rectangle rectangle = new Rectangle(RECTANGLE_SIDE_SIZE, RECTANGLE_SIDE_SIZE);
	   	rectangle.setFill(col);
	   	hbox.getChildren().addAll(new Label(options.indexOf(item) + " "), rectangle); 
	   	return hbox;
	}
	
	/**
	 *Returns current palette of colors. 
	 */
	@Override
	protected List<String> getOptionsList() {
		return palette;
	}
}
