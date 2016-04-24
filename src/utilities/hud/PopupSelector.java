package Utilities.HUD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author bobby_mac
 *
 */
/**
 * @author bobby_mac
 *
 */
public class PopupSelector {
	
	private Group root;
	private Scene scene;
	private Stage stage;
	private TextArea input;
	private IAuthoringHUDController controller;
	
	private double width;
	private double height;
	
	private static final double DEFAULT_WIDTH = 200;
	private static final double DEFAULT_HEIGHT = 200;
	private static final String HELP_TEXT = "Enter your desired HUD fields in order below. \nWhen finished, click 'save'.";
	
	
	public PopupSelector(double width, double height, IAuthoringHUDController controller) {
		this.controller = controller;
		this.width = width;
		this.height = height;
		this.root = new Group();
		this.scene = new Scene(root);
		this.stage = new Stage();
		stage.setScene(scene);
		input = new TextArea();
		input.setPrefSize(width, height);
		init();
		stage.show();
	}
	
	public PopupSelector(IAuthoringHUDController controller) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, controller);
	}
	
	public void init() {
		BorderPane top = new BorderPane();
		Button save = new Button("Save");
		top.setLeft(new Text(HELP_TEXT));
		top.setRight(save);
		BorderPane all = new BorderPane();
		all.setTop(top);
		all.setBottom(input);
		this.root.getChildren().add(all);
		System.out.println(scene.getWidth());
		save.setLayoutX(this.width-50);
		save.setOnAction(e->saveInputs(input.getText()));	
	}
	
	public void saveInputs(String s) {
		String[] inputs = s.split("\n++");
		File file = new FileChooser().showSaveDialog(stage);
		if (file != null) {
			try {
				String path = file.getPath() + ".hud";
				file = new File(path);
				BufferedWriter b = new BufferedWriter(new FileWriter(file));
				b.write(s);
				b.close();
				controller.setHUDInfoFile(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(Arrays.toString(inputs));
	}
	
	public Scene getScene() {
		return scene;
	}
}