package com.mygdx.shotgunking.model;

public class Knight extends WhitePiece{
    //đánh giá vị trí đứng trên bàn cờ dựa vào video sơn gửi
    //theo như quan sát có 6 mức độ màu do đó số điểm sẽ từ 1-6
    //do tile theo tọa độ từ 1-8 nên mảng này cũng vậy
    int scoreStanding[][]={ {0,0,0,0,0,0,0,0,0},

                            {0, 1,2,3,3,3,3,2,1},
                            {0, 2,3,4,4,4,4,3,2},
                            {0, 3,4,5,5,5,5,4,3},
                            {0, 3,4,5,6,6,5,4,3},
                            {0, 3,4,5,6,6,5,4,3},
                            {0, 3,4,5,5,5,5,4,3},
                            {0, 2,3,4,4,4,4,3,2},
                            {0, 1,2,3,3,3,3,2,1},
                            };
    //nếu tốt là 1 thì mã tầm 3
    int valueOfKnight=3;
    public Knight(Tile t, int maxTurn, int maxHP,Board onBoard) {
        super(t, maxTurn, maxHP,onBoard);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean isMate(Tile nextCell) {
        if((nextCell.y==standing.y+2 && (nextCell.x==standing.x-1 || nextCell.x==standing.x+1))||
        (nextCell.y==standing.y-2 && (nextCell.x==standing.x-1 || nextCell.x==standing.x+1))||
        (nextCell.x==standing.x+2 && (nextCell.y==standing.y-1 || nextCell.y==standing.y+1))||
        (nextCell.x==standing.x-2 && (nextCell.y==standing.y-1 || nextCell.y==standing.y+1)))
            return true;
        return false;
    }

    @Override
    int cacl(Tile c) {
        BlackKing bk=onBoard.getBlackKing();//black king
        //chiếu tướng
        //8 vị trí của quân mã
        int result=0;
        int tempx[]={-2,-1,+1,+2,+2,+1,-1,-2};
        int tempy[]={-1,-2,-2,-1,+1,+2,+2,+1};
        //duyệt trong 8 ô
        for(int i=0;i<8;i++){
            //tọa độ x,y trong nước tiếp theo
            int x=c.x+tempx[i];
            int y=c.y+tempy[i];
            //mỗi nước chiếu + 500
            if(Tile.isOnBoard(x,y)&&x==bk.standing.x&&y==bk.standing.y){
                result+=500;
            }
            //mỗi nước ở xung quanh +20
            else if(Tile.isOnBoard(x,y)&&Math.abs(x-bk.standing.x)<=1&&Math.abs(y-bk.standing.y)<=1){
                result+=20;
            }
        }

        if(Math.abs(c.x-bk.standing.x)<=1&&Math.abs(c.y-bk.standing.y)<=1&&this.hp<=bk.firePower&&bk.isCanShoot())
            result-=20;
        // chắn chiếu tướng
        //TODO: chắn chiếu tướng
        


        //vị trí quân cờ trên bàn cờ, giá trị quân cờ, hp quân cờ *2
        result+=scoreStanding[c.x][c.y]+valueOfKnight+this.hp*2;

        //ủy quyền quân vương
        if(onBoard.dataBuff.isUyQuyenQuanVuong){
            if(Math.abs(bk.standing.x-c.x)<=1&&Math.abs(bk.standing.y-c.y)<=1){
                result-=Integer.MAX_VALUE;
            }
        }
        return result;
    }

    @Override
    Tile bestMove() {
        int bestScore=0;
        Tile resTile=new Tile(this.standing.x, this.standing.y);
        int tempx[]={-2,-1,+1,+2,+2,+1,-1,-2};
        int tempy[]={-1,-2,-2,-1,+1,+2,+2,+1};
        for(int i=0;i<8;i++){
            if(!Tile.isOnBoard(this.standing.x+tempx[i], this.standing.y+tempy[i])) continue;
            Tile tempTile=new Tile(this.standing.x+tempx[i], this.standing.y+tempy[i]);
            if(onBoard.getPiece(tempTile)!=null) continue;
            int tempScore=cacl(tempTile);
            if(bestScore<tempScore){
                bestScore=tempScore;
                resTile=tempTile;
            }
        }
        return resTile;
    }
    

    @Override
    char getSymbol() {
        return 'K';
    }

}
