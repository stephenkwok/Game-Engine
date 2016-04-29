package gameengine.controller;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.HashMap;

/**
 * Created by aamir on 4/24/2016.
 */
public class SoundPlayer {
	
	
	
	
    private static final String folderName = "soundfiles";
    private MediaPlayer soundtrackPlayer;
    private File soundFileLocation;
    private Stage popupStage;
    private FileChooser soundFileChooser;
    private DirectoryChooser soundFolderChooser;
    private HashMap<String, Media> mediaMap;
    private HashMap<Media, MediaPlayer> mediaPlayers;

    /**
     * Creates the stage to be used and a sound file location (in src/soundfiles)
     */
    public SoundPlayer(){
        String resourceLocation = this.getClass().getResource("").getPath().toString();
        mediaMap = new HashMap<>();
        mediaPlayers = new HashMap<>();
        soundFileLocation = new File(resourceLocation + File.separator + folderName);
        soundFileLocation.mkdir();
        popupStage = new Stage();
        soundFileChooser = new FileChooser();
        soundFolderChooser = new DirectoryChooser();
//        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("*.mp3", "*.wav", "*.wma", "*.ogg", "*.aac", "*.flac");
//        soundFileChooser.getExtensionFilters().add(fileExtensions);
    }

    /**
     * Loads multiple sound files from a directory given from a DirectoryChooser
     */
    public void loadMultipleSoundFiles(){
        File folder = soundFolderChooser.showDialog(popupStage);
        loadMultipleSoundFilesFromDir(folder);
    }

    /**
     * Loads multiple sound files from a given directory with sound files
     * @param folder
     */
    public void loadMultipleSoundFilesFromDir(File folder){
        File[] files = folder.listFiles();
        for (File file: files) {
            loadSingleSoundFileFromFile(file.getName(), file);
        }
    }

    /**
     * Pops up a file chooser to upload a single sound file
     */
    public void loadSingleSoundFile(){
        File soundFile = soundFileChooser.showOpenDialog(popupStage);
        loadSingleSoundFileFromFile(soundFile.getName(), soundFile);
    }

    /**
     * Loads a sound file from the given file, and puts it into our Media
     * @param key
     * @param mediaFile
     */

    public void loadSingleSoundFileFromFile(String key, File mediaFile){
        Media sound = new Media(mediaFile.toURI().toString());
        try {
            File dest = new File(soundFileLocation.getPath().toString() + File.separator + mediaFile.getName());
            copy(mediaFile, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaMap.put(key, sound);
    }

    /**
     * Plays a soundtrack given the sound file name
     * @param soundFileName
     */
    public void setSoundtrack(String soundFileName){
        Media soundtrack = mediaMap.get(soundFileName);
        soundtrackPlayer = new MediaPlayer(soundtrack);
        soundtrackPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                soundtrackPlayer.seek(Duration.ZERO);
            }
        });
        soundtrackPlayer.play();
    }

    /**
     * Plays a sound given a sound file name. This is different than a soundtrack in that it doesn't repeat
     * @param soundFileName
     */
    public void playSound(String soundFileName){
        Media sound = mediaMap.get(soundFileName);
        MediaPlayer curMediaPlayer = new MediaPlayer(sound);
        mediaPlayers.put(sound, curMediaPlayer);
        curMediaPlayer.play();
    }

    /**
     * Utility method to copy a file to another file
     * @param source
     * @param target
     * @throws IOException
     * @throws FileNotFoundException
     */
    private void copy(File source, File target) throws IOException, FileNotFoundException {

        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(target);

        // Copy the bits from instream to outstream
        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

    /**
     * Sets all sounds, including the soundtrack, as muted or unmuted, depending on the given boolean
     * @param mute -> true = mute, false = unmute
     */
    public void allSetMute(boolean mute){
        allSoundsSetMute(mute);
        soundtrackSetMute(mute);
    }

    /**
     * Sets all sounds, minus the soundtrack, as muted or unmnuted, depending on the given boolean
     * @param mute -> true = mute, false = unmute
     */
    public void allSoundsSetMute(boolean mute){
        for (MediaPlayer mediaPlayer: mediaPlayers.values()){
            mediaPlayer.setMute(mute);
        }
    }

    /**
     * Sets the soundtrack as muted or unmuted, depending on the given boolean
     * @param mute -> true = mute, false = unmute
     */
    public void soundtrackSetMute(boolean mute){
        soundtrackPlayer.setMute(mute);
    }

    /**
     * Sets a specific sound as muted or unmuted, depending on the given boolean
     * @param soundFileName
     * @param mute -> true = mute, false = unmute
     */
    public void soundSetMute(String soundFileName, boolean mute){
        mediaPlayers.get(mediaMap.get(soundFileName)).setMute(mute);
    }

}