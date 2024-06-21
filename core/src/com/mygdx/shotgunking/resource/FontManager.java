package com.mygdx.shotgunking.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontManager {
    public static final FontManager instance = new FontManager();

    public final BitmapFont default_font;
    private FontManager(){
        default_font = new BitmapFont(Gdx.files.internal("font/FVF.fnt"),false);
    }
}
