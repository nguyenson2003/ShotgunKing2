package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class BoSungQuanLuc extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngBoSungQuanLuc;
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
        Board b=gp.getBoard();
        gp.getBoard().dataBuff.isBoSungQuanLuc=true;
        if(gp.getBoard().dataBuff.isBoSungQuanLuc){
            b.setInitPawn(b.getInitPawn()+3);
        }
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Quân trắng được bổ sung thêm 3 tốt";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Bổ Sung Quân Lực";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
