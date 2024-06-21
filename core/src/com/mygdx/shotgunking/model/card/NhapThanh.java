package com.mygdx.shotgunking.model.card;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.shotgunking.model.Board;
import com.mygdx.shotgunking.model.Gameplay;
import com.mygdx.shotgunking.model.King;
import com.mygdx.shotgunking.model.Rook;
import com.mygdx.shotgunking.model.Tile;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.ImageManager;
/**
 * NhapThanh
 */
public class NhapThanh extends Card{
    @Override
    public Texture getTexture() {
        // TODO Auto-generated method stub
        return ImageManager.instance.pngNhapThanh;
    }
    int hpWhiteKingBef=0;
    King whiteKing=null;
    Rook rook=null;
    boolean isCanSwap=false;
    @Override
    public void actionAfterBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        Board b=gp.getBoard();
        if(b.dataBuff.isNhapThanh&&b.isHasKingOnBoard&&whiteKing.getHp()!=hpWhiteKingBef){
            int detalHp=hpWhiteKingBef-whiteKing.getHp();
            int random=(int)(Math.random()*100);
            ArrayList<Rook>listRook=new ArrayList<>();
            for(WhitePiece wp:gp.getBoard().getWhitePieces()){
                if(wp instanceof Rook){
                    listRook.add((Rook)wp);
                }
            }
            if(listRook.size()>0){
                rook=listRook.get(random%listRook.size());
                
                if(!(whiteKing.isMate(b.getBlackKing().getStanding())==false &&
                rook.isMate(b.getBlackKing().getStanding())==false)) return;

                rook.setTurn(rook.getTurn()+1);
                whiteKing.setTurn(whiteKing.getTurn()+1);
                isCanSwap=true;
                while(detalHp-->0){
                    rook.takeDamage();
                }
                if(whiteKing.getHp()<=0){
                    b.addPiece(whiteKing);
                }
                whiteKing.setHp(hpWhiteKingBef);
            }
        }
    }

    @Override
    public void actionAfterInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionAfterWhiteAction(Gameplay gp) {
        // TODO Auto-generated method stub
        Board b=gp.getBoard();
        if(b.dataBuff.isNhapThanh)
            if(gp.getNumberOfTurn()%10==0){
                Rook r=new Rook(b.getRandomTileEmpty(),b.getInitTurnRook(),b.getInitHpRook(),b);
                if(b.dataBuff.isDichBenh)
                    r.takeDamage();
                b.addPiece(r);
            }
        if(isCanSwap){
            isCanSwap=false;
            Tile tempStd=whiteKing.getStanding();
            whiteKing.setStanding(rook.getStanding());
            rook.setStanding(tempStd);
            
        }
    }

    @Override
    public void actionAfterWhiteDieAction(Gameplay gp, WhitePiece whitePiece) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionBeforeBlackAction(Gameplay gp) {
        // TODO Auto-generated method stub
        Board b=gp.getBoard();
        if(b.isHasKingOnBoard&&b.dataBuff.isNhapThanh){
            for(WhitePiece wp:b.getWhitePieces())
                if(wp instanceof King){
                    whiteKing=(King)wp;
                    break;
                }
            
        }
        if(whiteKing!=null&&b.dataBuff.isNhapThanh)
            hpWhiteKingBef=whiteKing.getHp();
    }

    @Override
    public void actionBeforeInitBoard(Gameplay gp) {
        // TODO Auto-generated method stub
        gp.getBoard().dataBuff.isNhapThanh=true;

    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Nếu vua đen bắn trúng vua trắng, tổng lượng máu mất sẽ chuyển cho một xe ngẫu nhiên, hoán đổi vị trí với xe đó và thêm 1 xe mỗi 10 lượt (áp dụng khi cả 2 không chiếu vua đen)";
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Nhập Thành";
    }

    @Override
    public boolean isBuffCard() {
        // TODO Auto-generated method stub
        return false;
    }

    
}