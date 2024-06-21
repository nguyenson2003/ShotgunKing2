package com.mygdx.shotgunking.model.card;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
public class NghiBinh extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngNghiBinh;
    }
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {

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
        gp.getBoard().dataBuff.isNghiBinh=true;
        if(gp.getBoard().dataBuff.isNghiBinh){
            gp.getBoard().setInitPawn(gp.getBoard().getInitPawn()-2);
            gp.getBoard().setInitBishop(gp.getBoard().getInitBishop()-1);
        }
        
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Triệt tiêu ngẫu nhiên 2 tốt trắng và 1 tịnh trắng";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Nghi Binh";
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
