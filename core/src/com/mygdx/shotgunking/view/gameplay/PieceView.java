package com.mygdx.shotgunking.view.gameplay;

import com.mygdx.shotgunking.model.Piece;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.AudioManager;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.shared.TPanel;

public class PieceView extends TPanel {
    TPanel img;
    TPanel shadow;
    Piece model;
    BoardView boardView;
    public PieceView(Piece piece, BoardView boardView) {
        img = new TPanel(ImageManager.instance.getImgOfPiece(piece));
        shadow = new TPanel(ImageManager.instance.shadow);
//        this.setOpaque(true);
        img.setBounds(0,0,50,50);
        this.add(shadow);
        this.add(img);
        this.model=piece;
        this.boardView=boardView;
    }
    class MoveAnimation{
        int startX,startY,endX,endY;
        long startTime;
        boolean isStop=false;
        MoveAnimation(int startX, int startY,int endX,int endY){
            startTime = System.currentTimeMillis();
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
        void update(){
            if(isStop)return;
            long currentTime = System.currentTimeMillis()-startTime;
            double x = startX + (endX-startX)*currentTime*1.0/300;
            double y = startY + (endY-startY)*currentTime*1.0/300;
            double z = Math.sin(Math.PI/300*currentTime)*PieceView.this.getHeight()/2;
            PieceView.this.setLocation((int) x, (int) (y-z/2));
            shadow.setLocation(0, (int) (z/2));
            if(currentTime>=300) {
                PieceView.this.setLocation(endX, endY);
                shadow.setLocation(0, 0);
                isStop=true;
            }
        }
    }
    MoveAnimation currentMove;
    public void goToLocation(int endX, int endY){
        currentMove= new MoveAnimation(getX(),getY(),endX,endY);
    }

    @Override
    public void update() {
        super.update();
        if(currentMove!=null)currentMove.update();
    }

    public void beDestroyed(){
        AudioManager.instance.playSound(AudioManager.instance.broke);

        for(int i = 0;i<5;i++) {
            PieceParticle pp;
            if(this.getModel() instanceof WhitePiece)
                pp = new PieceParticle(this.getX(),this.getY(),true);
            else
                pp = new PieceParticle(this.getX(),this.getY(),false);
            pp.setSize(this.getWidth()/2,200);
            this.getParent().add(pp);
        }
        this.getParent().remove(this);
    }
    public Piece getModel() {
        return model;
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        img.setSize(this.getWidth(),this.getWidth());
        shadow.setSize(this.getWidth(),this.getWidth());
    }




}
