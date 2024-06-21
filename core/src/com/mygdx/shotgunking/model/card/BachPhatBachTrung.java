package com.mygdx.shotgunking.model.card;



import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

/**
 * BachPhatBachTrung
 */
public class BachPhatBachTrung extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngBachPhatBachTrung;
    }

    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        Board b=gp.getBoard();
        BlackKing bk=b.getBlackKing();
        if(b.dataBuff.isBachPhatBachTrung){
            bk.setFirePower(bk.getFirePower()-2);
            bk.setSpread(Integer.MIN_VALUE);
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
        // TODO Auto-generated method stub
        gp.getBoard().dataBuff.isBachPhatBachTrung=true;
    }

    @Override
    public String getDescription() {
        return "Độ lệch về 0 nhưng giảm 2 sát thương";
    }
    
    @Override
    public String getName() {
        return "Bách Phát Bách Trúng";
    }

    @Override
    public boolean isBuffCard() {
        return true;
    }

    
}