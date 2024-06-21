package com.mygdx.shotgunking.view.shared;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TBorderLayoutPanel extends TPanel{
    TPanel northChild,southChild,eastChild,westChild,centerChild;
    private int hgap;
    private int vgap;
    public static final int CENTER=0;
    public static final int NORTH=1;
    public static final int WEST=2;
    public static final int SOUTH=3;
    public static final int EAST=4;
    public TBorderLayoutPanel(){}

    public TBorderLayoutPanel(int hgap, int vgap) {
        this(null,hgap,vgap);
    }

    public TBorderLayoutPanel(Texture img, int hgap, int vgap) {
        super(img);
        this.vgap = vgap;
        this.hgap = hgap;
    }

    @Override
    public void add(TPanel child) {
        this.add(child,CENTER);
    }
    @Override
    public void add(TPanel child,int direction){
        super.add(child,0);
        if(direction==NORTH)northChild=child;
        else if(direction==WEST)westChild=child;
        else if(direction==SOUTH)southChild=child;
        else if(direction==EAST)eastChild=child;
        else centerChild=child;
    }
    @Override
    public void childDraw(SpriteBatch batch) {
//        int oldWidth = getWidth(),oldHeight = getHeight();
        int centerWidth=getWidth()-(getBorder().getLeft()+getBorder().getRight());
        int centerHeight=getHeight()-(getBorder().getTop()+getBorder().getBottom());
        if(northChild!=null){
            northChild.setSize(centerWidth,northChild.getHeight());
            northChild.setLocation(getBorder().getLeft(),getBorder().getTop());
            northChild.draw(batch);
            centerHeight -= northChild.getHeight() + vgap;
        }
        if(southChild!=null){
            southChild.setSize(centerWidth,southChild.getHeight());
            southChild.setLocation(getBorder().getLeft(),this.getHeight()-southChild.getHeight()-getBorder().getBottom());
            southChild.draw(batch);
            centerHeight -= southChild.getHeight() + vgap;
        }
        if(westChild!=null){
            westChild.setSize(westChild.getWidth(),centerHeight);
            westChild.setLocation(getBorder().getLeft(),(northChild==null?0:(northChild.getHeight() + vgap)+getBorder().getTop()));
            westChild.draw(batch);
            centerWidth -= westChild.getWidth() + hgap;
        }
        if(eastChild!=null){
            eastChild.setSize(eastChild.getWidth(),centerHeight);
            eastChild.setLocation(
                    (this.getWidth()-eastChild.getWidth())-getBorder().getRight(),
                    (northChild==null?0: (northChild.getHeight() + vgap))+getBorder().getTop()
            );
            eastChild.draw(batch);
            centerWidth -= eastChild.getWidth() + hgap;
        }
        if(centerChild!=null){
            centerChild.setSize(centerWidth,centerHeight);
            centerChild.setLocation(
                    (westChild==null?0:(westChild.getWidth() + hgap))+getBorder().getLeft(),
                    (northChild==null?0:(northChild.getHeight() + vgap))+getBorder().getTop()
            );
            centerChild.draw(batch);
        }
//        setSize(oldWidth,oldHeight);
    }

    public int getVgap() {
        return vgap;
    }

    public void setVgap(int vgap) {
        this.vgap = vgap;
    }

    public int getHgap() {
        return hgap;
    }

    public void setHgap(int hgap) {
        this.hgap = hgap;
    }
}
