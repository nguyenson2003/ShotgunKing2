package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
public class SuyGiamNhueKhi extends Card{
    @Override
    public void actionAfterWhiteDieAction(Gameplay gp, WhitePiece whitePiece) {
        int numberOfWhitePieces=gp.getBoard().getWhitePieces().size();
        if(numberOfWhitePieces==0) return;
        gp.getBoard().getWhitePieces().get(((int) (Math.random()*100)) % numberOfWhitePieces).takeDamage();
    }
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        
    }
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngSuyGiamNhueKhi;
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
    public void actionBeforeBlackAction(Gameplay gp) {
    }

    @Override
    public void actionBeforeInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        gp.getBoard().dataBuff.isSuyGiamNhueKhi=true;
    }

    @Override
    public String getDescription() {
        return "Khi một quân trắng bị tiêu diệt, 1 quân trắng bất kì khác mất 1 máu";
    }

    @Override
    public String getName() {
        return "Suy giảm nhuệ khí";
    }

    @Override
    public boolean isBuffCard() {
       return true;
    }
    
}
