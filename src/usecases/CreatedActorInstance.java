package usecases;

import java.util.HashMap;
import java.util.Map;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class CreatedActorInstance extends ImageView {

	private Map<String, String> myInfo;
	private ImageView myImage;
	private String myName;
	private boolean isReactive;
	private double myX;
	private double myY;
	private Map<String, String> myRules;
	
	// create properties file of default settings
	public CreatedActorInstance() {
		initializeDefaultVariables();
		render();
		setDragResponse();
	}
	
	private void initializeDefaultVariables() {
		setImage(new Image("default_icon.png"));
		setName("Untitled");
		isReactive = false;
		myX = 400;
		myY = 300;
		myInfo = new HashMap<>();
		myRules = new HashMap<>();
	}
	
	private void setDragResponse() {
		setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                setX(event.getX());
                setY(event.getY());
                System.out.println("(" + event.getX() + ", " + event.getY() + ")");
                event.consume();
            }
        }); 
	}
	
	public void render() {
		setX(myX);
		setY(myY);
	}
	
	/**
	 * Sets the image of the actor by accessing the image through the ImageLibrary.
	 * @param filename: name of image file.
	 */
	public void setImage(String filename) {
		
	}

	/**
	 * Sets the name of the actor.
	 * @param name: name of actor.
	 */
	public void setName(String name) {
		// if name not already used
		setId(name);
		// else have popup alert saying name already exists?
	}

	/**
	 * Sets whether or not the actor responds to a trigger (mouse click, key press, or collision).
	 * @param isReactive: true if the actor responds to a trigger event, false o.w.
	 */
	public void setReactive(boolean reactivity) {
		isReactive = reactivity;
	}

	/**
	 * Sets the position of the actor.
	 * @param x: x-position.
	 * @param y: y-position.
	 */
	public void setPosition(double x, double y) {
		myX = x;
		myY = y;
	}

	/**
	 * Adds a rule to the actor.
	 * @param trigger: event that triggers the action (e.g. MouseClick, KeyPress, Collision).
	 * @param action: action that the actor carries out in response (e.g. MoveUp, MoveDown, Die).
	 */
	public void addRule(String trigger, String action) {
		
	}

	/**
	 * Returns the info necessary to create the actor in the game engine.
	 * @return: actor's info.
	 */
	public Map<String, String> getInfo() {
		return myInfo;
	}
}
