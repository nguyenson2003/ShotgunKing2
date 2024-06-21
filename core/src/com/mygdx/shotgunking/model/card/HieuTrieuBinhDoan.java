package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.Pawn;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class HieuTrieuBinhDoan extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngHieuTrieuBinhDoan;
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
        if(gp.getNumberOfTurn()%5==0){
            Pawn p=new Pawn(b.getRandomTileEmpty(), b.getInitTurnPawn(), b.getInitHpPawn(), b);
            if(b.dataBuff.isDichBenh)
                p.takeDamage();
            b.addPiece(p);
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
        gp.getBoard().dataBuff.isHieuTrieuBinhDoan=true;
        
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Thêm 1 tốt trắng mỗi 5 lượt";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Hiệu Triệu Binh Đoàn";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
