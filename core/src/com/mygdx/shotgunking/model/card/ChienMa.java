package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.Knight;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class ChienMa extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngChienMa;
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
        Board b=gp.getBoard();
        if(gp.getNumberOfTurn()%7==0&&gp.getBoard().dataBuff.isChienMa==true){
            Knight k=new Knight(b.getRandomTileEmpty(), b.getInitTurnKnight(), b.getInitHpKnight(), b);
            if(b.dataBuff.isDichBenh)
                k.takeDamage();
            b.addPiece(k);
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
        Board b=gp.getBoard();
        gp.getBoard().dataBuff.isChienMa=true;
        if(gp.getBoard().dataBuff.isChienMa){
            if(b.getInitPawn()>=1)
                b.setInitPawn(b.getInitPawn()-1);
            else
                gp.getBoard().dataBuff.isChienMa=false;
        }
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Quân trắng bị giảm 1 tốt nhưng được thêm 1 mã mỗi 7 lượt điều kiện phải có ít nhất 1 tốt";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Chiến Mã";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
