package com.mygdx.shotgunking.view.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.shotgunking.model.Point;
import com.mygdx.shotgunking.resource.FontManager;
import com.mygdx.shotgunking.resource.ImageManager;

import java.util.ArrayList;


public class TPanel extends InputAdapter {
    private TPanel parent;
    private ArrayList<TPanel> childList = new ArrayList<>();
    private int x,y,width,height;
    static final Texture background=ImageManager.instance.backgroundImg;
    private Color backgroundColor = Color.WHITE;
    private boolean opaque=false;
    private Texture img;
    private String text;
    private int textAlign = Align.center;
    private Color textColor = Color.WHITE;
    private float textScale = 1;
    private boolean textWarp = false;
    private boolean visible = true;
    private int imgWidth, imgHeight;
    private BitmapFont font = FontManager.instance.default_font;
    private TBorder border = new TBorder(new Color(0),0);
    public TPanel(){}
    public TPanel(String text){
        this.text=text;
    }
    public TPanel(Texture img) {
        this.img = img;
    }

    public TBorder getBorder() {
        return border;
    }

    public void setBorder(TBorder border) {
        this.border = border;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
        if(img!=null) {
            int wight1 = Math.max(1, getWidth());
            int height1 = Math.max(1, (int) (getWidth() / 1.0 / img.getWidth() * img.getHeight()));
            int width2 = Math.max(1, (int) (getHeight() / 1.0 / img.getHeight() * img.getWidth()));
            int height2 = Math.max(1, getHeight());
            imgWidth = Math.min(wight1, width2);
            imgHeight = Math.min(height1, height2);
        }
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public void setOpaque(boolean opaque) {
        this.opaque = opaque;
    }

    public void setLocation(Point p){
        setLocation(p.x,p.y);
    }
    public void setLocation(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Point getLocation(){
        return new Point(x,y);
    }
    public final void setSize(int width,int height){
        if(width==this.width && height==this.height)return;
        this.width = width;
        this.height=height;
        if(img!=null) {
            int wight1 = Math.max(1, getWidth());
            int height1 = Math.max(1, (int) (getWidth() / 1.0 / img.getWidth() * img.getHeight()));
            int width2 = Math.max(1, (int) (getHeight() / 1.0 / img.getHeight() * img.getWidth()));
            int height2 = Math.max(1, getHeight());
            imgWidth = Math.min(wight1, width2);
            imgHeight = Math.min(height1, height2);
        }
        resizeAction();
    }
    public void setBounds(int x, int y, int width,int height){
        this.setLocation(x,y);
        this.setSize(width,height);
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getScreenX(){
        if(parent==null)return this.getX();
        return parent.getScreenX()+this.getX();
    }
    public int getScreenY(){
        if(parent==null) return this.getY();
        return parent.getScreenY()+this.getY();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(int textAlign) {
        this.textAlign = textAlign;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public float getTextScale() {
        return textScale;
    }

    public void setTextScale(float textScale) {
        this.textScale = textScale;
    }

    public boolean isTextWarp() {
        return textWarp;
    }

    public void setTextWarp(boolean textWarp) {
        this.textWarp = textWarp;
    }

    public void draw(SpriteBatch batch){
        if(!isVisible())return;
        if(width<=0 || height<=0) return;
        drawBackground(batch);
        drawImage(batch);
        drawText(batch);
        childDraw(batch);
        drawBorder(batch);
    }
    public void drawBackground(SpriteBatch batch){
        if(isOpaque()){
            batch.setColor(backgroundColor);
            batch.draw(
                    background,
                    this.getScreenX(),
                    Gdx.graphics.getHeight() - this.getScreenY() - this.getHeight(),
                    this.getWidth(),
                    this.getHeight()
            );
            batch.setColor(Color.WHITE);
        }
    }
    public void drawImage(SpriteBatch batch){
        if(img != null){
            batch.draw(
                    img,
                    this.getScreenX() + ((this.getWidth() - imgWidth) >> 1),
                    Gdx.graphics.getHeight() - this.getScreenY() - this.getHeight() + ((this.getHeight() - imgHeight) >> 1),
                    imgWidth,
                    imgHeight
            );
        }
    }
    public void drawText(SpriteBatch batch){
        if(text!=null && !text.isEmpty()){
            getFont().getData().setScale(textScale);
            getFont().setColor(textColor);
            FontManager.instance.default_font.draw(
                    batch,text,
                    this.getScreenX(),
                    Gdx.graphics.getHeight()-this.getScreenY()-(Align.isTop(textAlign)?0:this.getHeight()/2-this.getFont().getCapHeight()/2),
                    0,
                    text.length(),
                    this.getWidth(),
                    textAlign,
                    textWarp
            );
            FontManager.instance.default_font.getData().setScale(1);
            FontManager.instance.default_font.setColor(Color.WHITE);
        }
    }
    public void childDraw(SpriteBatch batch){
        for(TPanel child : childList){
            child.draw(batch);
        }
    }
    public void drawBorder(SpriteBatch batch){
        if(border!=null)
            border.draw(
                    batch,getScreenX(),
                    Gdx.graphics.getHeight() - this.getScreenY() - this.getHeight(),
                    width,
                    height
            );
    }

    public TPanel getParent() {
        return parent;
    }

    public ArrayList<TPanel> getChildList() {
        return childList;
    }


    public void add(TPanel child){
        if(child.parent!=null)child.parent.remove(child);
        for(TPanel temp = this;temp!=null;temp = temp.parent)
            if(temp == child)
                throw new RuntimeException("adding container's parent to itself");

        this.childList.add(child);
        child.parent=this;
    }
    public void add(TPanel child,int index){
        if(child.parent!=null)child.parent.remove(child);
        for(TPanel temp = this;temp!=null;temp = temp.parent)
            if(temp == child)
                throw new RuntimeException("adding container's parent to itself");

        this.childList.add(index,child);
        child.parent=this;
    }
    public void remove(TPanel child){
        if(child.parent!=this)return;
        this.childList.remove(child);
        child.parent=null;
    }

    public void update(){
        ArrayList<TPanel> cloneChild = new ArrayList<>(childList);
        for (TPanel child:cloneChild)child.update();
    }
    long startTimeClick=0;
    @Override
    public final boolean touchDown(int mouseX, int mouseY, int pointer, int button) {
        mouseMoved(mouseX,mouseY);
        mouseX-=x;
        mouseY-=y;
        if(mouseX<0||mouseX>width)return false;
        if(mouseY<0||mouseY>height)return false;
        for(TPanel child:childList){
            if(child.touchDown(mouseX,mouseY,pointer,button))return true;
        }
        startTimeClick=System.currentTimeMillis();
        return touchDownAction(mouseX,mouseY,pointer,button);
    }

    @Override
    public final boolean touchUp(int mouseX, int mouseY, int pointer, int button) {
        mouseMoved(mouseX,mouseY);
        mouseX-=x;
        mouseY-=y;
        if(mouseX<0||mouseX>width)return false;
        if(mouseY<0||mouseY>height)return false;
        for(TPanel child:childList){
            if(child.touchUp(mouseX,mouseY,pointer,button))return true;
        }
        if(System.currentTimeMillis()-startTimeClick<500){
            if(shortTouchAction(mouseX,mouseY,pointer,button)) {
                touchUpAction(mouseX,mouseY,pointer,button);
                startTimeClick = 0;
                return true;
            }
        }
        return touchUpAction(mouseX,mouseY,pointer,button);
    }
    private boolean mouseIn = false;
    @Override
    public boolean mouseMoved(int mouseX, int mouseY) {
        mouseX-=x;
        mouseY-=y;
        for(TPanel child:childList){
            child.mouseMoved(mouseX,mouseY);
        }
        if(!mouseIn && isMouseOnTPanel(mouseX,mouseY)){
            mouseIn=true;
            if(mouseEnterAction()) return true;
        }else if(mouseIn && !isMouseOnTPanel(mouseX,mouseY)){
            mouseIn=false;
            if(mouseExitAction())return true;
        }
        if(isMouseOnTPanel(mouseX,mouseY)){
            if(mouseMoveAction(mouseX,mouseY))return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return mouseMoved(screenX,screenY);
    }

    private boolean isMouseOnTPanel(int mouseX, int mouseY){
        return
                0<=mouseX
                && 0<=mouseY
                && mouseX<getWidth()
                && mouseY<getHeight();
    }
    protected boolean touchDownAction(int x, int y, int pointer, int button){
        return false;
    }
    protected boolean touchUpAction(int x, int y, int pointer, int button){
        return false;
    }
    protected boolean shortTouchAction(int x,int y,int pointer,int button){
        return false;
    }
    protected boolean mouseEnterAction(){
        return false;
    }
    protected boolean mouseExitAction(){
        return false;
    }
    protected boolean mouseMoveAction(int x,int y){
        return false;
    }

    protected void resizeAction(){}
}
