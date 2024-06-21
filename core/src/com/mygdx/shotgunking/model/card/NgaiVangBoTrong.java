package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
public class NgaiVangBoTrong extends Card{
    
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngNgaiVangBoTrong;
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
        b.dataBuff.isNgaiVangBoTrong=true;
        if(b.dataBuff.isNgaiVangBoTrong){
            b.setInitKing(0);
            b.setInitPawn(b.getInitPawn()+5);
            b.setInitHpPawn(b.getInitHpPawn()+1);
            b.dataBuff.isBecomeKing=false;
        }

    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Không còn vua trắng, thêm 5 tốt, tốt thêm 1 máu. Quân tốt đầu tiên ở cuối bàn cờ sẽ phong thành vua. Nếu chưa có con tốt phong vua thì thắng khi giết hết tốt";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Ngai Vàng Bỏ Trống";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
