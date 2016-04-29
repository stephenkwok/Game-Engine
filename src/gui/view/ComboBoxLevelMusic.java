package gui.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.view.ActorEditingEnvironment;
import gameengine.controller.Level;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;


public class ComboBoxLevelMusic extends ComboBoxTextCell {
	private static final String MUSIC_RESOURCE = "authoringmusic";
	private List<String> musicNames;

	public ComboBoxLevelMusic(ResourceBundle myResources, String promptText, String labelText) {
		super(promptText, labelText);
		fillMusicOptions();
	}

	private void fillMusicOptions() {
		musicNames = new ArrayList<>();
		File soundDir = new File(MUSIC_RESOURCE);
		for (File musicFile : soundDir.listFiles()) {
			musicNames.add(musicFile.getName());
		}
	}

	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			((Level) getEditableElement()).setMyBackgroundMusicName(getComboBox().getValue());
			System.out.println(((Level) getEditableElement()).getMyBackgroundMusicName());
		});
	}

	@Override
	protected List<String> getOptionsList() {
		return musicNames;
	}

	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(((Level) getEditableElement()).getMyBackgroundMusicName());
	}

}
