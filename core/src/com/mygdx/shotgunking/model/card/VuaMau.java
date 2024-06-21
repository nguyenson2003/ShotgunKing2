package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
public class VuaMau extends Card{

    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        // return ImageResource.instance.png;
        return ImageManager.instance.pngVuaMau;
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
        gp.getBoard().dataBuff.isVuaMau=true;
        if(gp.getBoard().dataBuff.isVuaMau){
            Board b=gp.getBoard();
            b.setInitHpKing(b.getInitHpKing()+3);
        }
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Vua trắng thêm 3 máu";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Vua Máu";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
