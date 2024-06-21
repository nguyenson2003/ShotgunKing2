package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class ChiaSeQuyenLuc extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngChiaSeQuyenLuc;
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
        gp.getBoard().dataBuff.isChiaSeQuyenLuc=true;
        if(gp.getBoard().dataBuff.isChiaSeQuyenLuc){
            Board b=gp.getBoard();
            b.setInitHpKing(Math.max(1,b.getInitHpKing()-1));
            b.setInitHpQueen(b.getInitHpQueen()+2);
        }
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Vua trắng mất 1 máu bù lại hậu thêm 2 máu";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Chia Sẻ Quyền Lực";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
