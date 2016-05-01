package gameengine.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Created by aamir on 4/24/2016.
 */
public class SoundPlayer {
	
    private MediaPlayer soundtrackPlayer;
    private HashMap<String, Media> mediaMap;
    private List<MediaPlayer> mediaPlayers;

    /**
     * Creates the stage to be used and a sound file location (in src/soundfiles)
     */
    public SoundPlayer(){
        mediaMap = new HashMap<>();
        mediaPlayers = new ArrayList<>();
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
     * Loads a sound file from the given file, and puts it into our Media
     * @param key
     * @param mediaFile
     */

    public void loadSingleSoundFileFromFile(String key, File mediaFile){
        Media sound = new Media(mediaFile.toURI().toString());
        mediaMap.put(key, sound);
    }

    /**
     * Plays a soundtrack given the sound file name
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
     * Plays a sound given a sound file name. This is different than a soundtrack in that it doesn't repeat
     * @param soundFileName
     */
    public void playSound(String soundFileName){
//        Media sound = mediaMap.get(soundFileName);
//        MediaPlayer curMediaPlayer = new MediaPlayer(sound);
//        mediaPlayers.put(sound, curMediaPlayer);
//        curMediaPlayer.play();

    	if (!isValid(soundFileName)) {
    		return;
    	}
    	MediaPlayer mp = new MediaPlayer(mediaMap.get(soundFileName));
    	mp.play();
    	mediaPlayers.add(mp);
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
        for (MediaPlayer mediaPlayer: mediaPlayers){
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
    
    public boolean isValid(String key) {
    	return key != null && mediaMap.get(key) != null;
    }

}