package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class CanXung extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngCanXung;
    }
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        if(gp.getBoard().dataBuff.isCanXung){
            BlackKing bk=gp.getBoard().getBlackKing();
            bk.setFirePower(bk.getFirePower()+1);
            bk.setSpread(bk.getSpread()+15*Math.PI/180);
        }
    }

    @Override
    public void actionAfterWhiteAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterWhiteDieAction(Gameplay gp, WhitePiece whitePiece) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionBeforeBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionBeforeInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        Board b=gp.getBoard();
        gp.getBoard().dataBuff.isCanXung=true;
        if(gp.getBoard().dataBuff.isCanXung){
            b.setInitHpKing(b.getInitHpKing()+2);
        }
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Vua Trắng thêm 2 máu trong khi Vua Đen sẽ thêm 1 sát thương và tăng 15 độ lệch";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Cân Xứng";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
