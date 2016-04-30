package gameengine.controller;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aamir on 4/24/2016.
 * Edited by Bobby on 4/29/2016.
 */
public class SoundPlayer {
	
    private MediaPlayer soundtrackPlayer;
    private HashMap<String, Media> mediaMap;
    private List<MediaPlayer> mediaPlayers;

    /**
     * Initializes the mediaMap and mediaPlayers list
     */
    public SoundPlayer(){
        mediaMap = new HashMap<>();
        mediaPlayers = new ArrayList<>();
    }


    /**
     * Loads multiple sound files from a given directory
     * @param folder
     */
    public void loadMultipleSoundFilesFromDir(File folder){
        File[] files = folder.listFiles();
        for (File file: files) {
            loadSingleSoundFileFromFile(file.getName(), file);
        }
    }


    /**
     * Loads a sound file from the given file, and puts it into our Media
     * @param key which is your future file name
     * @param mediaFile which is the file to be loaded
     */

    public void loadSingleSoundFileFromFile(String key, File mediaFile){
        Media sound = new Media(mediaFile.toURI().toString());
        mediaMap.put(key, sound);
    }

    /**
     * Plays a soundtrack given the sound file name from the mediaMap
     * @param soundFileName
     */
    public void setSoundtrack(String soundFileName){
    	
    	if (!isValid(soundFileName)) {
    		return;
    	}
    	
        if (soundtrackPlayer != null) {
        	soundtrackPlayer.dispose();
        }

        soundtrackPlayer = new MediaPlayer(mediaMap.get(soundFileName));

        soundtrackPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                soundtrackPlayer.seek(Duration.ZERO);
            }
        });
        soundtrackPlayer.play();
    }

    /**
     * Plays a sound given a sound file name.
     * @param soundFileName
     */
    public void playSound(String soundFileName){
    	
    	if (!isValid(soundFileName)) {
    		return;
    	}
    	MediaPlayer mp = new MediaPlayer(mediaMap.get(soundFileName));
    	mp.play();
    	mediaPlayers.add(mp);
    }


    /**
     * Mutes or unmutes all sound effects and soundtrack.
     * @param mute -> true = mute, false = unmute
     */
    public void allSetMute(boolean mute){
        allSoundsSetMute(mute);
        soundtrackSetMute(mute);
    }

    /**
     * Mutes or undmutes all sound effects
     * @param mute -> true = mute, false = unmute
     */
    public void allSoundsSetMute(boolean mute){
        for (MediaPlayer mediaPlayer: mediaPlayers){
            mediaPlayer.setMute(mute);
        }
    }

    /**
     * Mutes or unmutes the soundtrack
     * @param mute -> true = mute, false = unmute
     */
    public void soundtrackSetMute(boolean mute){
        soundtrackPlayer.setMute(mute);
    }
    
    /**
     * Tests whether the given string will throw a null pointer exception
     * when used as a key in the mediaMap to create a new mediaPlayer
     * @param key -> string to be tested
     * @return whether the key can be used to create a valid mediaPlayer
     */
    
    
    public boolean isValid(String key) {
    	return key != null && mediaMap.get(key) != null;
    }

}