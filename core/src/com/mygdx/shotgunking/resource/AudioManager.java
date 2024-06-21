package com.mygdx.shotgunking.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    public static final AudioManager instance = new AudioManager();
    public final Music backgroundMusic;
    public final Sound shoot,particle,button,broke;
    private Music playingMusic;
    private Sound playingSound;
    private AudioManager(){
        backgroundMusic = createMusic("audio/background_music.mp3");
        shoot = createSound("audio/shoot.wav");
        particle = createSound("audio/particle.wav");
        button = createSound("audio/button.wav");
        broke = createSound("audio/broke.wav");
    }
    public Music createMusic(String path){
        return Gdx.audio.newMusic(Gdx.files.internal(path));
    }
    public Sound createSound(String path){
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }


    public void playSound(Sound sound) {
        if (!Settings.instance.canPlaySound ||sound==null) return;
        sound.play(1);
        playingSound=sound;
    }
    public void playMusic (Music music) {
        if(music.isPlaying())return;
        stopMusic();
        playingMusic = music;
        if (Settings.instance.canPlayMusic) {
            music.setLooping(true);
            music.setVolume(1);
            music.play();
        }
    }
    public void playMusic(){
        if (Settings.instance.canPlayMusic && getPlayingMusic()!=null)
            getPlayingMusic().play();
    }
    public void pauseMusic(){
        if(getPlayingMusic()!=null)
            playingMusic.pause();
    }
    public void stopMusic () {
        if (playingMusic != null)
            playingMusic.stop();
    }
    public Music getPlayingMusic () {
        return playingMusic;
    }
    public void onSettingsUpdated () {
        if (playingMusic == null) return;
        if (Settings.instance.canPlayMusic) {
            playMusic();
        } else {
            pauseMusic();
        }
    }


}
