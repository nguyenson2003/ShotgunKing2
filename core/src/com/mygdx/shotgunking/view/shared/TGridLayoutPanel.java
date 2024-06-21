package com.mygdx.shotgunking.view.shared;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class TGridLayoutPanel extends TPanel{
    private int rows,cols;
    private int vgap,hgap;
    public TGridLayoutPanel(int rows,int cols){
        this(rows,cols,0,0);
    }
    public TGridLayoutPanel(int rows, int cols, int hgap, int vgap) {
        this(null,rows,cols,hgap,vgap);
    }
    public TGridLayoutPanel(Texture img,int rows,int cols, int hgap, int vgap) {
        super(img);
        if(rows<=0 || cols<=0)
            throw new IllegalArgumentException("row and col number must be greater than 0 (zero)");
        this.rows = rows;
        this.cols = cols;
        this.vgap = vgap;
        this.hgap = hgap;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
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

    @Override
    public void childDraw(SpriteBatch batch) {
        int elementWidth = (this.getWidth()-hgap*(cols-1) -(getBorder().getLeft()+getBorder().getRight()))/cols ;
        int elementHeight = (this.getHeight()-vgap*(rows-1) -(getBorder().getTop()+getBorder().getBottom()))/rows;
        ArrayList<TPanel> childList = getChildList();
        for(int i=0;i<rows*cols && i<childList.size();i++){
            TPanel tp = childList.get(i);
            tp.setSize(elementWidth,elementHeight);
            tp.setLocation((i%cols)*(elementWidth+hgap) + getBorder().getLeft(),(i/cols)*(elementHeight+vgap) + getBorder().getTop());
            tp.draw(batch);
        }
    }
}
