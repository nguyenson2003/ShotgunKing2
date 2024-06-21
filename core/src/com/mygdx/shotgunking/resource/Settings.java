package com.mygdx.shotgunking.resource;

import java.util.prefs.BackingStoreException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
    public static final Settings instance = new Settings();
    public boolean canPlayMusic;
    public boolean canPlaySound;
    public int shield;
    Preferences prefs;
    private Settings(){
        load();
        save();
    }

    public void load(){
        prefs = Gdx.app.getPreferences("shotgun_king_setting.prefs");
        canPlayMusic = prefs.getBoolean("canPlayMusic",true);
        canPlaySound = prefs.getBoolean("canPlaySound",true);
        shield =prefs.getInteger("shield",0);
        AudioManager.instance.onSettingsUpdated();
    }
    public void save(){
        prefs.putBoolean("canPlayMusic",canPlayMusic);
        prefs.putBoolean("canPlaySound",canPlaySound);
        prefs.putInteger("shield",shield);
        AudioManager.instance.onSettingsUpdated();
    }
    public void reset() {
        prefs.clear();
        load();
    }
}
