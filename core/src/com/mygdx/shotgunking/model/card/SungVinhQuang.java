package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
public class SungVinhQuang extends Card{

    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngSungVinhQuang;
    }
    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        BlackKing bk=gp.getBoard().getBlackKing();
        if(gp.getBoard().dataBuff.isSungVinhQuang){
            bk.setSpread(bk.getSpread()+30*Math.PI/180);
            bk.setFirePower(bk.getFirePower()+1);
            bk.setFireRange(bk.getFireRange()+1);
        }
    }

    @Override
    public void actionAfterWhiteAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionBeforeBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionBeforeInitBoard(Gameplay gp) {
        
        // TODO Auto-generated method stub
        gp.getBoard().dataBuff.isSungVinhQuang=true;
        
    }

    @Override
    public String getDescription() {
        return "Độ lệch tăng thêm 30 bù lại sẽ tăng thêm 1 sát thương và 1 tầm bắn";
    }

    @Override
    public String getName() {
        return "Súng Vinh Quang";
    }

    @Override
    public boolean isBuffCard() {
        return true;
    }

    @Override
    public void actionAfterWhiteDieAction(Gameplay gp, WhitePiece whitePiece) {
        // TODO Auto-generated method stub
    }
    
}
