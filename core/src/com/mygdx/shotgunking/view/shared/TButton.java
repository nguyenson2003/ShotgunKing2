package com.mygdx.shotgunking.view.shared;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.mygdx.shotgunking.resource.AudioManager;

public class TButton extends TPanel {
    private Color DEFAUT_COLOR1 = new Color(0x0099ffff);
    private Color DEFAUT_COLOR2 = new Color(0x00ddffff);
    public TButton(){
//        setBorder(BorderFactory.createLineBorder(Color.black,4));
        setBackgroundColor(DEFAUT_COLOR1);
        setTextColor(Color.WHITE);
        setOpaque(true);

    }

    public TButton(String text){
        this();
        setText(text);
        setBorder(new TBorder(Color.BLACK,5));
    }
    @Override
    protected boolean shortTouchAction(int x, int y, int pointer, int button) {
        AudioManager.instance.playSound(AudioManager.instance.button);
        return super.shortTouchAction(x, y, pointer, button);
    }

    @Override
    protected boolean mouseEnterAction() {
        TPanelAnimation.setBackground(this,DEFAUT_COLOR2,100);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);

        return true;
    }

    @Override
    protected boolean mouseExitAction() {
        TPanelAnimation.setBackground(this,DEFAUT_COLOR1,100);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);

        return true;
    }
}
