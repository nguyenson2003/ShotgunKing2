package com.mygdx.shotgunking.view.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TMixedBorder extends TBorder {
    TBorder outsideBorder,insideBorder;
    public TMixedBorder(TBorder outsideBorder, TBorder insideBorder){
        super(new Color(0,0,0,0),
                outsideBorder.getTop()+insideBorder.getTop(),
                outsideBorder.getLeft()+insideBorder.getLeft(),
                outsideBorder.getBottom()+insideBorder.getBottom(),
                outsideBorder.getRight()+insideBorder.getRight()
        );
        this.outsideBorder=outsideBorder;
        this.insideBorder=insideBorder;
    }
    @Override
    public void setTop(int top) {}
    @Override
    public void setLeft(int left) {}
    @Override
    public void setBottom(int bottom) {}
    @Override
    public void setRight(int right) {}

    @Override
    public int getTop() {
        return outsideBorder.getTop()+insideBorder.getTop();
    }

    @Override
    public int getLeft() {
        return outsideBorder.getLeft()+insideBorder.getLeft();
    }

    @Override
    public int getBottom() {
        return outsideBorder.getBottom()+insideBorder.getBottom();
    }

    @Override
    public int getRight() {
        return outsideBorder.getRight()+insideBorder.getRight();
    }

    public TBorder getOutsideBorder() {
        return outsideBorder;
    }

    public void setOutsideBorder(TBorder outsideBorder) {
        this.outsideBorder = outsideBorder;
    }

    public TBorder getInsideBorder() {
        return insideBorder;
    }

    public void setInsideBorder(TBorder insideBorder) {
        this.insideBorder = insideBorder;
    }

    @Override
    public void draw(SpriteBatch batch, int screenX, int screenY, int width, int height) {
        outsideBorder.draw(batch,screenX,screenY,width,height);
        insideBorder.draw(batch,
                screenX+outsideBorder.getLeft(),
                screenY+outsideBorder.getTop(),
                width-(outsideBorder.getLeft()+outsideBorder.getRight()),
                height-(outsideBorder.getTop()+outsideBorder.getBottom())
        );
    }
}
