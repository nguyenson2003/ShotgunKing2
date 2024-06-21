package com.mygdx.shotgunking.model.card;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
public class KeDiSan extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngKeDiSan;
    }
    boolean isBuffed=false;
    double spreadBef=0;
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        checkAndBuffOrDebuff(gp);  
    }
    private void checkAndBuffOrDebuff(Gameplay gp) {
        if(gp.getBoard().dataBuff.isKeDiSan==false) return;
        BlackKing bk=gp.getBoard().getBlackKing();
        gp.getBoard();
        for(WhitePiece wp:gp.getBoard().getWhitePieces()){
            if(wp.isDied()) continue;
            int absx=Math.abs(wp.getStanding().x-bk.getStanding().x);
            int absy=Math.abs(wp.getStanding().y-bk.getStanding().y);
            if(absx<=1&&absy<=1&&!isBuffed){
                isBuffed=true;
                spreadBef=bk.getSpread();
                bk.setSpread(Math.max(0,bk.getSpread()-15*Math.PI/180));
                bk.setFirePower(bk.getFirePower()+2);
                break;
            }
            if(isBuffed){
                isBuffed=false;
                bk.setSpread(spreadBef);
                bk.setFirePower(bk.getFirePower()-2);
            }
        }
            
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        isBuffed=false;
    }

    @Override
    public void actionAfterWhiteAction(Gameplay gp) {
        // TODO Auto-generated method stub
        checkAndBuffOrDebuff(gp);
    }

    @Override
    public void actionBeforeBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionBeforeInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        gp.getBoard().dataBuff.isKeDiSan=true;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Độ lệch giảm 15 và tăng 2 sát thương nhưng chỉ có tác dụng khi áp sát kẻ địch";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Kẻ Đi Săn";
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
