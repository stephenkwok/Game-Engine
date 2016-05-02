package gui.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.controller.Level;

/**
 * ComboBoxLevelMusic for choosing level background music.
 * @author amyzhao
 *
 */

public class ComboBoxLevelMusic extends ComboBoxTextCell {
	private static final String MUSIC_RESOURCE = "authoringmusic";
	private static final String NONE = "NONE";
	private List<String> musicNames;

	/**
	 * Constructor for ComboBoxLevelMusic.
	 * @param myResources: resource bundle to use.
	 * @param promptText: text initially displayed in combobox.
	 * @param labelText: label for combobox.
	 */
	public ComboBoxLevelMusic(ResourceBundle myResources, String promptText, String labelText) {
		super(promptText, labelText);
		fillMusicOptions();
	}

	/**
	 * Add options available in music resource folder.
	 */
	private void fillMusicOptions() {
		musicNames = new ArrayList<>();
		File soundDir = new File(MUSIC_RESOURCE);
		for (File musicFile : soundDir.listFiles()) {
			musicNames.add(musicFile.getName());
		}
		musicNames.add(NONE);
	}

	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			((Level) getEditableElement()).setSoundtrack(getComboBox().getValue());
		});
	}

	@Override
	protected List<String> getOptionsList() {
		return musicNames;
	}

	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(((Level) getEditableElement()).getSoundtrack());
	}

}
