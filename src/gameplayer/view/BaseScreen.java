package gameplayer.view;

import java.lang.reflect.InvocationTargetException;

import java.util.ResourceBundle;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class provides for a private interface to create a base screen view that
 * will hold the other view components of the gaming program.
 * 
 * @author Carine
 *
 */
public class BaseScreen extends Screen {
	/**
	 * Adds the auxiliary views, like the HUD display, MenuBar, and GameScreen,
	 * to the BaseScreen
	 */

	private ResourceBundle myResources;
	private static final String GUI_RESOURCE = "gameGUI";
	private BaseScreenController myController;
	private GUIFactory factory;
	private static final String MENU_ITEMS = "MenuBarMenus";
	private static final String SIDE_BUTTONS = "SideButtons";
	public BaseScreen(Stage stage) {
		super(stage);
		init();
		addComponents();
	}

	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new BaseScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}
	

	public void addComponents() {
		VBox myBox = new VBox(20);
		try {
			addMenu(myBox);
			addGame(myBox);
			addHUD(myBox);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			e1.printStackTrace();
		}
		getRoot().getChildren().add(myBox);
	}

	public void addMenu(VBox myV)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		IGUIElement myMenu = factory.createNewGUIObject("Menu");
		MenuBar myMB = (MenuBar) myMenu.createNode();
		myV.getChildren().add(myMB);
		myMB.setMinWidth(Screen.SCREEN_WIDTH);
		String[] menuItems = myResources.getString(MENU_ITEMS).split(",");
		for (int i = 0; i < menuItems.length; i++) {
			myMB.getMenus().add(new Menu(menuItems[i]));
			
		}
	}
	
	public void addHUD(VBox myV) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		IGUIElement hudPane = factory.createNewGUIObject("hudPane");
		Pane myP = (Pane) hudPane.createNode();
		Rectangle myRect = new Rectangle(myP.getMaxWidth(), myP.getMaxHeight());
		myP.setBorder(new Border(new BorderStroke(Color.BLACK, 
				            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		myP.getChildren().add(myRect);
		myV.getChildren().add(myP);
	}

	public void addGame(VBox myV) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String[] sideButtons = myResources.getString(SIDE_BUTTONS).split(",");
		VBox smallV = new VBox();
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = factory.createNewGUIObject(sideButtons[i]);
			smallV.getChildren().add(newElement.createNode());
		}
		myV.getChildren().add(smallV);
	}

	@Override
	public Scene getScene()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}
}