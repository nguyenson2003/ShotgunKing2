package com.mygdx.shotgunking.view.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.shotgunking.resource.ImageManager;

public class TBorder {
    Texture background = ImageManager.instance.backgroundImg;
    Color borderColor;
    private int top,left,bottom,right;

    public TBorder(Color borderColor, int top, int left, int bottom, int right) {
        this.borderColor = borderColor;
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }
    public TBorder(Color borderColor, int topBottom, int leftRight) {
        this.borderColor = borderColor;
        this.top = topBottom;
        this.left = leftRight;
        this.bottom = topBottom;
        this.right = leftRight;
    }
    public TBorder(Color borderColor, int topLeftBottomRight) {
        this.borderColor = borderColor;
        this.top = topLeftBottomRight;
        this.left = topLeftBottomRight;
        this.bottom = topLeftBottomRight;
        this.right = topLeftBottomRight;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setSize(int topLeftBottomRight){setSize(topLeftBottomRight,topLeftBottomRight);}
    public void setSize(int topBottom, int leftRight){setSize(topBottom,leftRight,topBottom,leftRight);}
    public void setSize(int top, int left, int bottom, int right){
        setTop(top);
        setLeft(left);
        setBottom(bottom);
        setRight(right);
    }

    public void draw(SpriteBatch batch,int screenX,int screenY,int width,int height){
        batch.setColor(borderColor);
        batch.draw(background,screenX,screenY+height-top,width,top);//top
        batch.draw(background,screenX,screenY,left,height);//left
        batch.draw(background,screenX,screenY,width,bottom);//
        batch.draw(background,screenX+width-right,screenY,right,height);//right
        batch.setColor(Color.WHITE);
    }
}
