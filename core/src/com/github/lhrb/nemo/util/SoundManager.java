/**
 * 
 */
package com.github.lhrb.nemo.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
/**
 * @author exa
 *  
 *  This class should handle all sounds to reduce memory load
 *
 */
public class SoundManager {
    
    private static SoundManager soundMng;
    
    private float musicVolume;
    private float sfxVolume;
    
    private HashMap<String, Music> songs;
    private HashMap<String, Sound> sounds;
    private Music current;
    
    private SoundManager() {
        songs = new HashMap<String, Music>();
        sounds = new HashMap<String, Sound>();

        musicVolume = 0.25f;
        sfxVolume = 0.5f;

        // Add sounds
        addSound("explosion","sound/explosion1.ogg");
        addSound("laser","sound/laser.ogg");
        addSound("hit", "sound/hit.ogg");

        // Add Songs
        addSong("firstlevel", "sound/soundTrack_1.ogg", musicVolume);
        addSong("menu", "sound/menu.ogg", musicVolume);
        addSong("boss", "sound/boss.ogg", musicVolume);
    }


    private void addSong(String name, String path, float volume) {
        if (!Gdx.files.internal(path).exists()) return;

        Music track = Gdx.audio.newMusic(Gdx.files.internal(path));
        track.setLooping(true);
        track.setVolume(volume);
        songs.put(name, track);
    }

    private void  addSound (String name, String path) {
        if (!Gdx.files.internal(path).exists()) return;

        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(name, sound);
    }

    
    public static SoundManager getInstance() {
        if(soundMng == null) {
            soundMng = new SoundManager();
        }
        return soundMng;
    }
    
    public void setMusicVolume(float f) {
        musicVolume = f;
    }

    public void setMusicStreamVolume(String name, float f) {
        songs.get(name).setVolume(f);
    }
    
    public void setSfxVolume(float f) {
        sfxVolume = f;
    }
    
    /**
     * plays current track
     */
    public void playTrack() {
        if(current == null) return;
        if(!current.isPlaying()) {
            current.play();
        }
    }
    
    /**
     * plays specific track
     * @param s
     */
    public void playTrack(String s) {
        if(s == null) return;
        if(songs.containsKey(s)) {
            if(current != null) {
               current.dispose(); 
            }
            current = songs.get(s);
            current.play();
        }
    }
    
    public void pauseTrack() {
        if(current == null) return;
        current.pause();
    }
    
    public void stopTrack() {
        if(current == null) return;
        current.stop();
    }
    
    public void playSound(String key) {
        if(key == null) return;
        if(sounds.containsKey(key)) {
            sounds.get(key).play(sfxVolume);
        }
    }
}
