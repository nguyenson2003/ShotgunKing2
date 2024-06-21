package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.Pawn;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class AnBinhBatDong extends Card{

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
        if(gp.getBoard().dataBuff.isAnBinhBatDong&&gp.getNumberOfTurn()<=3){
            for(WhitePiece wp:gp.getBoard().getWhitePieces()){
                if(wp instanceof Pawn) wp.setTurn(wp.getMaxTurn());
            }
        }

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
        gp.getBoard().dataBuff.isAnBinhBatDong=true;
        Board b=gp.getBoard();
        b.setInitHpPawn(b.getInitHpPawn()+3);
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Tốt trắng không thể di chuyển trong 3 nước đầu nhưng được tăng 3 máu";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Án Binh Bất Động";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngAnBinhBatDong;
    }
    
}
