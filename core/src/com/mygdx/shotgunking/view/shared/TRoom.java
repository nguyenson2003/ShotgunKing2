package com.mygdx.shotgunking.view.shared;

import com.badlogic.gdx.graphics.Color;

public class TRoom extends TBorderLayoutPanel{
    public static final Color defaultBackgroundColor = new Color(0x00bbffff);
    public TRoom(){
        this(0,0);
    }

    public TRoom(int vgap, int hgap) {
        super(vgap, hgap);
        setOpaque(true);
        setBackgroundColor(defaultBackgroundColor);
    }

}
