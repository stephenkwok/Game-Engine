package gui.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import authoringenvironment.model.*;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;
import gameengine.model.Rule;
import gameengine.model.Actions.Destroy;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.SideCollision;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Checkboxes for all HUD options.
 * 
 * @author amyzhao, stephen
 *
 */
public class CheckBoxesGarbageCollection extends Observable implements IGUIElement, IEditingElement {

	private static final String DELIMITER = ",";
	private static final String RESOURCE = "garbageCollectionOptions";
	private static final String OPTIONS = "Options";
	private static final String START_PROMPT = "StartPrompt";
	private static final String END_PROMPT = "EndPrompt";
	private static final String SELECT = "Select";
	private static final Double CONTAINER_SPACING = 10.0;
	private static final Double CONTAINER_PADDING = 10.0;
	private static final String LEFT = "Left";
	private static final String RIGHT = "Right";
	private static final String TOP = "Top";
	private static final String BOTTOM = "Bottom";
	private static final String COLLISION = "Collision";
	private static final String IMAGE = "Image";
	private static final String STRETCH_VERTICAL = "StretchVertical";
	private static final String STRETCH_HORIZONTAL = "StretchHorizontal";
	private static final String ADD_TO_IMAGE_DIMENSIONS = "AddToImageDimensions";
	private static final String TRIGGER_DIRECTORY = "gameengine.model.Triggers.";
	private static final String GAMESIDE = "gameside.png";
	private static final String FLOOR = "floor.png";
	private static final String X = "X";
	private static final String Y = "Y";
	private List<String> allSides;
	private Level myLevel;
	private VBox myContainer;
	private ResourceBundle myAttributesResources;
	private List<CheckBox> mySides;
	private GUIFactory myFactory;
	private Map<String, IAuthoringActor> garbageCollectors;
	private Button checkGarbageCollectorsButton;

	/**
	 * Constructs a CheckBoxesHUDOptions object for the given GameInfo object.
	 * 
	 * @param gameInfo:
	 *            GameInfo object.
	 * @param controller:
	 *            controller for the authoring environment.
	 */
	public CheckBoxesGarbageCollection() {
		this.myAttributesResources = ResourceBundle.getBundle(RESOURCE);
		this.myContainer = new VBox(CONTAINER_SPACING);
		myContainer.setPadding(new Insets(CONTAINER_PADDING));
		myFactory = new GUIFactory(myAttributesResources);
		mySides = new ArrayList<>();
		garbageCollectors = new HashMap<>();
		init();
	}

	/**
	 * Initializes the VBox containing the HUD checkboxes.
	 * 
	 * @param key:
	 *            key in resource file for the checkboxes to add.
	 * @param vbox:
	 *            vbox to add checkboxes into.
	 */
	private void init() {
		initAllSidesList();
		myContainer.getChildren().add(new Label(myAttributesResources.getString(START_PROMPT)));
		List<Node> checkboxes = addElements(OPTIONS, myContainer);
		int i = 0;
		while (i < checkboxes.size()) {
			HBox container = new HBox();
			for (int j = 0; j < 2; j++) {
				CheckBox cb = (CheckBox) checkboxes.get(i);
				cb.prefWidthProperty().bind(myContainer.widthProperty());
				mySides.add(cb);
				container.getChildren().add(cb);
				i++;
			}
			myContainer.getChildren().add(container);
		}
		checkGarbageCollectorsButton = new Button(SELECT);
		checkGarbageCollectorsButton.prefWidthProperty().bind(myContainer.widthProperty());
		checkGarbageCollectorsButton.setOnAction(e -> updateGarbageCollectingActors(myLevel.getActors()));
		myContainer.getChildren().add(new Label(myAttributesResources.getString(END_PROMPT)));
		myContainer.getChildren().add(checkGarbageCollectorsButton);
	}

	private void initAllSidesList() {
		allSides = new ArrayList<>(Arrays.asList(LEFT, RIGHT, TOP, BOTTOM));
	}

	// if they add an already existing one --> Just update the actors
	// if they add a new one --> update the actors
	// if they remove one --> remove from level
	public void updateGarbageCollectingActors(List<IPlayActor> actors) {
		List<String> sides = getSides();
		for (int i = 0; i < allSides.size(); i++) {
			IAuthoringActor garbageCollector;
			if (garbageCollectors.containsKey(allSides.get(i))) {
				garbageCollector = garbageCollectors.get(allSides.get(i));
			} else {
				garbageCollector = new Actor();
			}
			String sideToCheck = allSides.get(i);
			if (sides.contains(sideToCheck)) {
				garbageCollector.setImageView(new ImageView(new Image(myAttributesResources.getString(sides.get(i) + IMAGE))));
				garbageCollector.setImageViewName(myAttributesResources.getString(sides.get(i) + IMAGE));
				if (Arrays.asList(myAttributesResources.getString(STRETCH_VERTICAL).split(DELIMITER)).contains(sides.get(i))) {
					//garbageCollector.getImageView().setPreserveRatio(false);
					//garbageCollector.getImageView().setFitHeight((myLevel.getMyHeight()));
					if (Arrays.asList(myAttributesResources.getString(ADD_TO_IMAGE_DIMENSIONS).split(DELIMITER)).contains(sides.get(i))) {
						garbageCollector.setX(myLevel.getImageView().getFitWidth() + Double.parseDouble(myAttributesResources.getString(sides.get(i) + X)));
					} else {
						garbageCollector.setX(Double.parseDouble(myAttributesResources.getString(sides.get(i) + X)));
					}
					garbageCollector.setY(Double.parseDouble(myAttributesResources.getString(sides.get(i) + Y)));
				} else {
					//garbageCollector.setWidth(myLevel.getImageView().getFitWidth());
					if (Arrays.asList(myAttributesResources.getString(ADD_TO_IMAGE_DIMENSIONS).split(DELIMITER)).contains(sides.get(i))) {
						garbageCollector.setY(myLevel.getImageView().getFitHeight() + Double.parseDouble(myAttributesResources.getString(sides.get(i) + Y)));
					} else {
						garbageCollector.setY(Double.parseDouble(myAttributesResources.getString(sides.get(i) + Y)));
					}
					garbageCollector.setX(Double.parseDouble(myAttributesResources.getString(sides.get(i) + X)));
				}

				garbageCollector.getRules().clear();
				for (int j = 0; j < actors.size(); j++) {
					if (!garbageCollectors.keySet().contains(actors.get(j))) {
						ITrigger trigger = createCollisionTrigger(sides.get(i), (Actor) garbageCollector, (Actor) actors.get(j)); // use reflection from properties file
						garbageCollector.addRule(new Rule(trigger, new Destroy((Actor) actors.get(j))));
					}
				}
				garbageCollectors.put(allSides.get(i), garbageCollector);
				myLevel.addActor(garbageCollector);
			} else {
				if (myLevel.getActors().contains(garbageCollector)) {
					myLevel.removeActor((Actor) garbageCollector);
				}
			}
		}
		System.out.println("Cur actors:");
		
		for (int k = 0; k < myLevel.getActors().size(); k++) {
			System.out.println(myLevel.getActors().toString());
		}
	}

	private ITrigger createCollisionTrigger(String side, Actor garbageCollector, Actor actor) {
		Class<?> collision;
		try {
			String name = myAttributesResources.getString(side + COLLISION);
			collision = Class.forName(TRIGGER_DIRECTORY + name);
			Constructor<?> constructor = collision.getConstructor(Actor.class, Actor.class);
			return (ITrigger) constructor.newInstance(garbageCollector, actor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add checkbox elements to the vbox.
	 * 
	 * @param key:
	 *            key in resource file for elements to add.
	 * @param vbox:
	 *            container to add checkboxes to.
	 * @return list of elements added.
	 */
	private List<Node> addElements(String key, VBox vbox) {
		String[] elements = myAttributesResources.getString(key).split(DELIMITER);
		List<Node> createdElements = new ArrayList<>();
		for (int i = 0; i < elements.length; i++) {
			createdElements.add((myFactory.createNewGUIObject(elements[i]).createNode()));
		}
		return createdElements;
	}

	/**
	 * Get the HUD elements that the user has selected.
	 * 
	 * @return list of HUD elements that the user selected.
	 */
	public List<String> getSides() {
		List<String> sides = new ArrayList<String>();
		mySides.stream().filter(checkbox -> checkbox.isSelected())
		.forEach(checkbox -> sides.add(checkbox.getId()));
		return sides;
	}

	/**
	 * Sets the editable element for the CheckBoxes.
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myLevel = (Level) element;
	}

	/**
	 * Returns the node for this object.
	 */
	@Override
	public Node createNode() {
		return myContainer;
	}

	@Override
	public void addNodeObserver(Observer observer) {
		this.addObserver(observer);
	}

}
