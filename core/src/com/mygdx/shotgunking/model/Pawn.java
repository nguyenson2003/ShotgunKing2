package com.mygdx.shotgunking.model;

import java.util.Arrays;

public class Pawn extends WhitePiece{
    int scoreStanding[][]={ {0,0,0,0,0,0,0,0,0},

                            {0, 2,2,2,2,2,2,2,2},
                            {0, 6,6,6,6,6,6,6,6},
                            {0, 2,2,3,4,4,3,2,2},
                            {0, 2,2,3,4,4,3,2,2},
                            {0, 2,2,3,4,4,3,2,2},
                            {0, 2,1,1,2,2,1,1,2},
                            {0, 3,3,3,1,1,3,3,3},
                            {0, 2,2,2,2,2,2,2,2},
                            };
    //tốt là 1
    int valueOfPawn=1;
    boolean isMovedTwoTile=false;
    public Pawn(Tile t,int maxTurn,int maxHP,Board onBoard) {
        super(t,maxTurn,maxHP,onBoard);
        isMovedTwoTile=false;
    }

    @Override
    boolean isMate(Tile nextCell) {
        if(onBoard.dataBuff.isTuDo){
            return Math.abs(nextCell.x-standing.x)<=1&&Math.abs(nextCell.y-standing.y)<=1;
        }
        if(nextCell.y==standing.y+1 && (nextCell.x==standing.x-1 || nextCell.x==standing.x+1))
            return true;
        return false;
        
    }
    /**
     * tính toán nước đi như vua trắng
     */
    int caclAsWhiteKing(Tile c){
        int res=0;
        BlackKing bk=onBoard.getBlackKing();
        //ưu tiên tiếp cận vua trắng
        int sumAbs=Math.abs(c.x-bk.standing.x)+Math.abs(c.y-bk.standing.y);
        res+=1000-sumAbs;
        //khi có thể bị tiêu diệt thì sẽ giữ khoảng cách
        if(bk.isCanShoot()&&this.hp<=bk.firePower)
            res+=sumAbs;
        //ưu tiên phong quân
        res+=1000-(8-c.y);
        return res;
    }

    @Override
    Tile bestMove() {
        BlackKing bk=onBoard.getBlackKing();
        if(standing.y == 8){
            return standing;
        }
        //áp dụng thẻ tự do
        if(onBoard.dataBuff.isTuDo){
            int bestScore=caclAsWhiteKing(standing);
            Tile resTile=new Tile(this.standing.x, this.standing.y);
            int tempx[]= Arrays.copyOf(bk.aroundX,bk.aroundX.length);
            int tempy[]=Arrays.copyOf(bk.aroundY,bk.aroundY.length);
            for(int i=0;i<8;i++){
                //nếu nó k trên bàn cờ thì continue
                int x=this.standing.x+tempx[i], y=this.standing.y+tempy[i];
                if(!Tile.isOnBoard(x,y )) continue;
                Tile tempTile=new Tile(x, y);
                //nếu vị trí này có quân cờ trắng rồi thì continue
                if(onBoard.getPiece(tempTile)!=null && onBoard.getPiece(tempTile)!=bk) continue;
                int tempScore=caclAsWhiteKing(tempTile);
                //ủy quyền quân vương
                if(onBoard.dataBuff.isUyQuyenQuanVuong &&
                    bk.checkUyQuyenQuanVuong(tempTile))
                    tempScore=Integer.MIN_VALUE;
                if(bestScore<tempScore){
                    bestScore=tempScore;
                    resTile=tempTile;
                }
            }
            //áp dụng xung phong trong thẻ tự do
            if(onBoard.dataBuff.isXungPhong&&!isMovedTwoTile){
                // //chưa đến lượt thì không áp dụng
                if(turn>1) return standing;
                isMovedTwoTile=true;
                Tile tempTile2=new Tile(standing.x,standing.y+2);
                int tempScore=caclAsWhiteKing(tempTile2);
                //nếu vị trí +1 hoặc +2 không có quân nào thì có thể áp dụng xung phong
                if(checkCanApplyXungPhong()==false) 
                        tempScore=Integer.MIN_VALUE;
                //ủy quyền quân vương
                if(onBoard.dataBuff.isUyQuyenQuanVuong &&
                    bk.checkUyQuyenQuanVuong(tempTile2))
                    tempScore=Integer.MIN_VALUE;
                if(bestScore<tempScore){
                    bestScore=tempScore;
                    resTile=tempTile2;
                }
            }
            return resTile;
        }
        
        Tile temp = new Tile(standing.x, standing.y+1);
        //áp dụng xung phong -> đương nhiêu ưu tiên đi 2 nước
        if(onBoard.dataBuff.isXungPhong&&!isMovedTwoTile){
            // //chưa đến lượt thì không áp dụng
            if(turn>1) return standing;
            //nếu vị trí +1 hoặc +2 không có quân nào thì có thể áp dụng xung phong
            if(checkCanApplyXungPhong()) 
                temp.y++;
            isMovedTwoTile=true;
            
            
        }
        if(onBoard.getPiece(temp)==null && standing.y<8){
            //ủy quyền quân vương
            if(onBoard.dataBuff.isUyQuyenQuanVuong &&
                bk.checkUyQuyenQuanVuong(temp))
                return standing;
            // System.out.println("a"+turn);
            return temp;
        }else return standing;
        
    }
    /**
     * true nếu cả 2 ô trước mặt đều không có quân nào
     */
    boolean checkCanApplyXungPhong(){
        return (onBoard.getPiece(new Tile(standing.x,standing.y+1))==null && 
            onBoard.getPiece(new Tile(standing.x,standing.y+2))==null );
    }
    @Override
    public void move(Tile nextMove) {
        super.move(nextMove);
    }

    public boolean promote(){

        if(standing.y==8){
            onBoard.removePiece(this);
            WhitePiece newPiece;
            // Ngai vàng bỏ trống -> phong vua
            if(onBoard.dataBuff.isNgaiVangBoTrong && onBoard.dataBuff.isBecomeKing==false){
                onBoard.dataBuff.isBecomeKing=true;
                newPiece = new King(standing, onBoard.getInitTurnKing(), onBoard.getInitHpKing(), onBoard);
                onBoard.addPiece(newPiece);
                onBoard.checkIsOnBoardOfPiece();
            }else {
                //phong ngẫu nhiên tịnh mã xe hậu
                int choice = (int) (Math.random() * 4);

                // choice=0;
                if (choice == 0)
                    newPiece = new Queen(standing, onBoard.getInitTurnQueen(), onBoard.getInitHpQueen(), onBoard);
                else if (choice == 1)
                    newPiece=new Rook(standing, onBoard.getInitTurnRook(), onBoard.getInitHpRook(), onBoard);
                else if (choice == 2)
                    newPiece = new Knight(standing, onBoard.getInitTurnKnight(), onBoard.getInitHpKnight(), onBoard);
                else
                    newPiece = new Bishop(standing, onBoard.getInitTurnBishop(), onBoard.getInitHpBishop(), onBoard);
                onBoard.addPiece(newPiece);
            }
            newPiece.turn++;
            return true;
        }
        return false;
    }
    @Override
    char getSymbol() {
        return 'P';
    }

    @Override
    int cacl(Tile c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cacl'");
    }
    
}
