package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class DichBenh extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngDichBenh;
    }
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        if(gp.getBoard().dataBuff.isDichBenh){
            for(WhitePiece wp : gp.getBoard().getWhitePieces())
                wp.takeDamage();
        }
    }

    @Override
    public void actionAfterWhiteAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterWhiteDieAction(Gameplay gp, WhitePiece whitePiece) {

    }

    @Override
    public void actionBeforeBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionBeforeInitBoard(Gameplay gp) {
        gp.getBoard().dataBuff.isDichBenh=true;
        
    }

    @Override
    public String getDescription() {
        return "Tất cả quân trắng sẽ trừ 1 máu nếu có nhiều hơn 1 máu";
    }

    @Override
    public String getName() {
        return "Dịch Bệnh";
    }

    @Override
    public boolean isBuffCard() {
        return true;
    }
    
}
