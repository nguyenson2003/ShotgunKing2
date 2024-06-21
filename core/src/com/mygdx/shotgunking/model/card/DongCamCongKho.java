package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class DongCamCongKho extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngDongCamCongKho;
    }
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
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
        gp.getBoard().dataBuff.isDongCamCongKho=true;
        Board b=gp.getBoard();
        if(gp.getBoard().dataBuff.isDongCamCongKho){
            b.setInitHpQueen(b.getInitHpQueen()+1);
            b.setInitHpPawn(b.getInitHpPawn()+1);
            b.setInitHpBishop(b.getInitHpBishop()+1);
            b.setInitHpKnight(b.getInitHpKnight()+1);
            b.setInitHpRook(b.getInitHpRook()+1);
            b.setInitHpKing(b.getInitHpKing()+1);
        }
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Quân trắng được thêm 1 máu";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Đồng Cam Cộng Khổ";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
