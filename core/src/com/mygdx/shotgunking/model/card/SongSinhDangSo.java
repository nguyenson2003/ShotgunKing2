package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;

public class SongSinhDangSo extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngSongSinhDangSo;
    }
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        BlackKing bk=gp.getBoard().getBlackKing();
        if(gp.getBoard().dataBuff.isSongSinhDangSo){
            bk.setFirePower(bk.getFirePower()+1);
        }
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
        Board b=gp.getBoard();
        gp.getBoard().dataBuff.isSongSinhDangSo=true;
        if(gp.getBoard().dataBuff.isSongSinhDangSo){
            b.setInitQueen(b.getInitQueen()+2);
            b.setInitHpQueen(b.getInitHpQueen()+1);
        }
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Trắng thêm 2 hậu và hậu tăng 1 máu nhưng vua đen được tăng 1 sát thương";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Song Sinh Đáng Sợ";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
