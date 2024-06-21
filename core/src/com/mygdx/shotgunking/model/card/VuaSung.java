package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
public class VuaSung extends Card{

    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngVuaSung;
    }
    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub

        BlackKing bk=gp.getBoard().getBlackKing();
        if(gp.getBoard().dataBuff.isVuaSung){
            bk.setFirePower(bk.getFirePower()+1);
            bk.setFireRange(bk.getFireRange()+1);
            //giảm 1 nửa độ lệch trong gameplay
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
        gp.getBoard().dataBuff.isVuaSung=true;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Tăng 1 sát thương cùng với 1 tầm bắn và giảm độ lệch đi 1 nửa";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Vua Súng";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void actionAfterWhiteDieAction(Gameplay gp, WhitePiece whitePiece) {
        // TODO Auto-generated method stub
    }
    
}
