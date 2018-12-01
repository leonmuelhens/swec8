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
    
    private SoundManager() {
        songs = new HashMap<String, Music>();
        sounds = new HashMap<String, Sound>();
        
        musicVolume = 1.0f;
        sfxVolume = 0.8f;
        
        //soundtrack_firstlevel
        Music st_fl = Gdx.audio.newMusic(Gdx.files.internal("sound/soundTrack_1.ogg"));
        st_fl.setLooping(true);
        st_fl.setVolume(musicVolume);
        songs.put("firstlevel", st_fl);
        
        sounds.put("explosion", Gdx.audio.newSound(Gdx.files.internal("sound/explosion1.ogg")));
        sounds.put("laser", Gdx.audio.newSound(Gdx.files.internal("sound/laser.ogg")));
               
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
    
    public void setSfxVolume(float f) {
        sfxVolume = f;
    }
    
    public void playTrack(String s) {
        if(s == null) return;
        if(songs.containsKey(s)) {
            songs.get(s).play();
        }
    }
    
    public void playSound(String key) {
        if(key == null) return;
        if(sounds.containsKey(key)) {
            sounds.get(key).play(sfxVolume);
        }
        
    }
        
    

}
