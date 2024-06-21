package com.mygdx.shotgunking.view.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TProgressBar extends TPanel{
    public TProgressBar(){
        super();
        this.setOpaque(true);
    }
    private Color foregroundColor = Color.RED;
    private int minimumValue=0;
    private int maximumValue=100;
    private int value = 0;

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(!isVisible())return;
        if(getWidth()<=0 || getHeight()<=0) return;
        drawBackground(batch);
        drawForeground(batch);
        drawImage(batch);
        drawText(batch);
        childDraw(batch);
    }
    public void drawForeground(SpriteBatch batch){
        batch.setColor(foregroundColor);
        batch.draw(
                background,
                this.getScreenX(),
                Gdx.graphics.getHeight() - this.getScreenY() - this.getHeight(),
                this.getWidth()*(value-minimumValue)/(maximumValue-minimumValue),
                this.getHeight()
        );
        batch.setColor(Color.WHITE);
    }
}
