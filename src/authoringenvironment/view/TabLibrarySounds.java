package authoringenvironment.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRuleCreator;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.media.AudioClip;

/**
 * Tab contains ListView of sounds
 * 
 * @author AnnieTang
 *
 */
public class TabLibrarySounds extends TabLibrary {
	private static final String SOUND_IMAGE_NAME = "sound.png";
	private static final int STANDARD_IMAGE_HEIGHT = 20;
	private static final String MUSIC = "Music";

	public TabLibrarySounds(ResourceBundle myResources, String tabText, ActorRuleCreator myRuleMaker) {
		super(myResources, tabText, myRuleMaker);
		setContent();
	}

	/**
	 * Set content of tab to list of sounds
	 */
	@Override
	public void setContent() {
		fillFileNames();
		fillMusicNames(); // also add music files
		setLabels(FXCollections.observableArrayList());
		for (String soundName : getFileNames()) {
			Label soundLabel = new Label(soundName, createPlaySoundButton(soundName));
			if (getActorRuleCreator() != null) {
				setDragEvent(soundLabel, TransferMode.COPY);
			}
			getLabels().add(soundLabel);
		}
		setListView(new ListView<>(getLabels()));
	}

	/**
	 * Get sound files from resources
	 */
	private void fillMusicNames() {
		File directory = new File(getResources().getString(MUSIC));
		for (File file : directory.listFiles()) {
			getFileNames().add(file.getName());
		}
	}

	/**
	 * Return sound content of this tab
	 */
	@Override
	Node getContent() {
		return getListView();
	}

	/**
	 * Return button that allows player to preview sound
	 * 
	 * @param soundName
	 * @return
	 */
	private Button createPlaySoundButton(String soundName) {
		ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(SOUND_IMAGE_NAME)));
		iv.setFitHeight(STANDARD_IMAGE_HEIGHT);
		iv.setPreserveRatio(true);
		Button button = new Button("", iv);
		URL resource = getClass().getClassLoader().getResource(soundName);
		AudioClip sound = new AudioClip(resource.toString());
		button.setOnAction(event -> {
			if (sound.isPlaying()) {
				sound.stop();
			} else
				sound.play();
		});
		return button;
	}
}
