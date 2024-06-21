package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.mygdx.shotgunking.model.*;
import com.mygdx.shotgunking.resource.AudioManager;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TPanelAnimation;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardView extends TPanel {
    Gameplay gp;
    ArrayList<PieceView> whitePieceViewList = new ArrayList<>();
    PieceView blackPieceView;
    TPanel borderHover;
    boolean canClickMouse = false;
    Pixmap shootCursorImage ;
    Cursor c;
    public BoardView(Gameplay gp) {
        super(ImageManager.instance.boardImg);
        borderHover = new TPanel(ImageManager.instance.border);
        this.add(borderHover);

        this.gp = gp;
        for(Piece p : this.gp.getBoard().getWhitePieces()){
            whitePieceViewList.add(new PieceView(p,this));
        }
        for(PieceView pv : whitePieceViewList){
            this.add(pv);
        }

        blackPieceView = new PieceView(this.gp.getBoard().getBlackKing(),this);
        GameplayRoom.getIns().showInfoBlackPiece((BlackKing) blackPieceView.getModel());
        this.add(blackPieceView);


        GameplayRoom.getIns().showMsg("Lượt: "+gp.getNumberOfTurn());
        canClickMouse=true;

        Pixmap shootCursorImage = new Pixmap(Gdx.files.internal("img/icon/shootCursor.png"));
        c = Gdx.graphics.newCursor(shootCursorImage, 7,7);
        shootCursorImage.dispose();
    }


    private Tile pixelToTile(int x, int y){
        int tileX = (int) Math.floor ((x - (getWidth() - getImgWidth()) / 2.0) / getImgWidth()*8);
        int tileY = (int) Math.floor ((y - (getHeight() - getImgHeight()) / 2.0) / getImgHeight()*8);
        tileX++;tileY++;
        if(tileX<1 || tileY<1 || tileX>8 || tileY>8) return null;
        else return new Tile(tileX,tileY);
    }
    public Point tileToPixel(Tile t){
        return tileToPixel(t.x,t.y);
    }
    public Point tileToPixel(int tileX, int tileY){
        int x = (int)((tileX-1)*(getImgWidth()/8.0)+(getWidth() - getImgWidth())/2.0);
        int y = (int)((tileY-1)*(getImgHeight()/8.0)+(getHeight() - getImgHeight())/2.0);
        return new Point(x,y);
    }
    public Point tileDoubleToPixel(double tileX, double tileY){
        int x = (int)((tileX-1)*(getImgWidth()/8.0)+(getWidth() - getImgWidth())/2.0);
        int y = (int)((tileY-1)*(getImgHeight()/8.0)+(getHeight() - getImgHeight())/2.0);
        return new Point(x,y);
    }
    public void drawABullet(double startBulletX, double startBulletY,double endBulletX, double endBulletY){
        AudioManager.instance.playSound(AudioManager.instance.shoot);

        TPanelAnimation.shake(GameplayRoom.getIns(),8,8,200,200/4);
        Point p1 = tileDoubleToPixel(startBulletX,startBulletY);
        Point p2 = tileDoubleToPixel(endBulletX,endBulletY);
        this.add(new BulletView(p1.x,p1.y,p2.x,p2.y));
    }


    public void updatePositionBlackPiece(){
        Tile t = blackPieceView.getModel().getStanding();
        Point temp = tileToPixel(t.x,t.y);
//        ComponentAnimation.setLocation(blackPieceView,temp.x+3,temp.y+1,200);
        if(!t.equals(pixelToTile(blackPieceView.getX(),blackPieceView.getY())))
            blackPieceView.goToLocation(temp.x+3,temp.y+1);
    }
    public void updateBeforeMoveWhitePiece(){

        for(PieceView pv : whitePieceViewList){
            WhitePiece model = (WhitePiece)pv.getModel();
            TPanelAnimation.shakeStop(pv);
            if(model.isMateFlag() && gp.isPlaying()){
                TPanelAnimation.twink(pv,500,1000/60*3);
            }
            if(model.isTakeDamageFlag()>0 && gp.isPlaying()){
                Point p = tileToPixel(model.getStanding());
                this.add(new deltaHpView(model.isTakeDamageFlag(),p.x,p.y));
                TPanelAnimation.shake(pv,4,4,200,200/4);
//                ComponentAnimation.twink(pv,200,200/4);
            }
        }
        Iterator<PieceView> it= whitePieceViewList.iterator();
        while(it.hasNext()){
            PieceView pv = it.next();
            if(((WhitePiece)pv.getModel()).isDied()){
                pv.beDestroyed();
                it.remove();
            }
        }
    }
    public void updateMoveWhitePiece(){
        boolean hasPieceMove = false;
        for (WhitePiece whitePiece: gp.getBoard().getWhitePieces()) {
            int count = 0;
            for (PieceView pieceView : whitePieceViewList) {
                if (pieceView.getModel() == whitePiece) {
                    count++;
                }
            }
            if(count==0){
                PieceView pv = new PieceView(whitePiece,this);
                whitePieceViewList.add(pv);
                this.add(pv);
                Tile t = pv.getModel().getStanding();
                Point temp = tileToPixel(t.x,t.y);
                pv.setBounds(
                        temp.x+3,
                        temp.y+1,
                        getImgWidth()/8-6,
                        getImgHeight()/8-6+100
                );
                TPanelAnimation.twink(pv,200,1000/60*5);
            }
        }
        for(PieceView pv : whitePieceViewList){
            if(((WhitePiece)pv.getModel()).isDied())continue;
            WhitePiece model = (WhitePiece)pv.getModel();
            Tile t = model.getStanding();
            Point temp = tileToPixel(t.x,t.y);
            TPanelAnimation.shakeStop(pv);
//            ComponentAnimation.setLocation(pv,temp.x+3,temp.y+1,200);
            if(!t.equals(pixelToTile(pv.getX(),pv.getY()))) {
                pv.goToLocation(temp.x + 3, temp.y + 1);
                hasPieceMove=true;
            }
        }
        Iterator<PieceView> it= whitePieceViewList.iterator();
        while(it.hasNext()){
            PieceView pv = it.next();
            if(!gp.getBoard().getWhitePieces().contains((WhitePiece) pv.getModel())){
                this.remove(pv);
                it.remove();
            }
        }
        this.hasPieceMove = hasPieceMove;
    }
    public void  updateAfterMoveWhitePiece(){
        for(PieceView pv : whitePieceViewList){
            WhitePiece model = (WhitePiece)pv.getModel();
            if(model.canMove() && gp.isPlaying()){
                TPanelAnimation.shakeInfinity(pv,1,0,1000/60*5);
            }
            model.resetFlag();
        }
    }

    @Override
    protected boolean mouseMoveAction(int x, int y) {
        super.mouseMoveAction(x, y);
        Tile t = pixelToTile(x,y);
        if(t!=null ) {
            if(blackPieceView!=null && ((BlackKing)blackPieceView.getModel()).canMoveTo(t)) {
                borderHover.setSize(
                        tileToPixel(2, 2).x - tileToPixel(1, 1).x,
                        tileToPixel(2, 2).y - tileToPixel(1, 1).y
                );
                borderHover.setLocation(tileToPixel(t));
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }else {
                borderHover.setSize(0,0);
                Gdx.graphics.setCursor(c);
            }
            Piece p = gp.getBoard().getPiece(t);
            if(p instanceof WhitePiece){
                WhitePiece wp = (WhitePiece) p;
                GameplayRoom.getIns().showInfoWhitePiece(wp);
            }else{
                GameplayRoom.getIns().hideInfoWhitePiece();
            }
        }else{
            borderHover.setSize(0,0);
        }
        return true;
    }


    @Override
    protected boolean mouseExitAction() {
        borderHover.setSize(0,0);
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        return true;
    }
    private long startTimeClick = 0;
    private boolean clickStep1 = true;
    private boolean clickStep2 = true;
    private boolean clickStep3 = true;
    private boolean hasPieceMove = true;

    @Override
    protected boolean shortTouchAction(int x, int y, int pointer, int button) {
        if(!canClickMouse)return false;
        Tile t = pixelToTile(x,y);
        if(t==null)return false;
        if(t.equals(blackPieceView.getModel().getStanding()))return false;
        if(!((BlackKing)blackPieceView.getModel()).isCanShoot() && !((BlackKing)blackPieceView.getModel()).canMoveTo(t)){
            GameplayRoom.getIns().runOutOfAmmo();
        }
        Point blackKingPoint = blackPieceView.getLocation();
        blackKingPoint.x+=blackPieceView.getWidth()/2;
        blackKingPoint.y+=blackPieceView.getWidth()/2;
        Point mousePoint = new Point(x,y);
        double distance = Math.sqrt(Math.pow(blackKingPoint.x-mousePoint.x,2)+Math.pow(blackKingPoint.y-mousePoint.y,2));
        double angleCos = Math.acos((mousePoint.x-blackKingPoint.x)/distance);
        double angleSin = Math.asin((mousePoint.y-blackKingPoint.y)/distance);
        double angle = angleCos;
        if(angleSin<0)angle=-angle;
        gp.blackMoveAction(t, angle);
        //đã xóa thread
        {
            canClickMouse = false;
            startTimeClick = System.currentTimeMillis();
            clickStep1 = clickStep2 = clickStep3 = false;
        }
        return true;
    }

    @Override
    public void update() {
        super.update();
        long currentTimeClick = System.currentTimeMillis();
        if(!clickStep1 && currentTimeClick-startTimeClick>=0) {
            clickStep1=true;
            updatePositionBlackPiece();
            GameplayRoom.getIns().reloadInfoBlackPiece();
            updateBeforeMoveWhitePiece();
            GameplayRoom.getIns().reloadInfoWhitePiece();
        }
        if(!clickStep2 && currentTimeClick-startTimeClick>=300) {
            clickStep2=true;
            updateMoveWhitePiece();
        }
        if(
                (!clickStep3 && currentTimeClick-startTimeClick>=650)
                || (!clickStep3 && currentTimeClick-startTimeClick>=300 && !hasPieceMove)
        ) {
            clickStep3 = true;
            if (!gp.isPlaying()) {
                GameplayRoom.getIns().endOneFloor();
                return;
            }
            updateAfterMoveWhitePiece();
            GameplayRoom.getIns().showMsg("Lượt: " + gp.getNumberOfTurn());
            canClickMouse = true;
        }
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        borderHover.setSize(0,0);
        for(PieceView pv : whitePieceViewList){
            TPanelAnimation.shakeStop(pv);
            Tile t = pv.getModel().getStanding();
            Point temp = tileToPixel(t.x,t.y);
            pv.setBounds(
                    temp.x+3,
                    temp.y+1,
                    getImgWidth()/8-6,
                    getImgHeight()/8-6+100
            );


            if(((WhitePiece)pv.getModel()).canMove() && gp.isPlaying())
                TPanelAnimation.shakeInfinity(pv,2,0,1000/60*5);
        }

        if(blackPieceView!=null) {
            Tile t = blackPieceView.getModel().getStanding();
            Point temp = tileToPixel(t.x, t.y);
            blackPieceView.setBounds(
                    temp.x + 3,
                    temp.y + 1,
                    getImgWidth() / 8 - 6,
                    getImgHeight() / 8 - 6 + 100
            );
        }
    }


}
