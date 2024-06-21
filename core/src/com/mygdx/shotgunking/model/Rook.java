package com.mygdx.shotgunking.model;

import java.util.Arrays;

public class Rook extends WhitePiece{
    int scoreStanding[][]={ {0,0,0,0,0,0,0,0,0},

                            {0, 10,4,4,4,4,4,4,10},
                            {0, 5,6,6,6,6,6,6,5},
                            {0, 1,3,3,3,3,3,3,1},
                            {0, 1,3,3,3,3,3,3,1},
                            {0, 1,3,3,3,3,3,3,1},
                            {0, 1,3,3,3,3,3,3,1},
                            {0, 1,3,3,3,3,3,3,1},
                            {0, 3,3,4,5,5,4,3,3},
                            };
    //nếu tốt là 1 thì xe là 4
    int valueOfRook=5;
    public Rook(Tile t, int maxTurn, int maxHP,Board onBoard) {
        super(t, maxTurn, maxHP,onBoard);
        //TODO Auto-generated constructor stub
    }
    @Override
    public boolean isMate(Tile nextCell) {
        //nếu k cùng đường thẳng sẽ return false
        return !isHasPieceBetweenStraight(nextCell, this.standing);
    }

    @Override
    int cacl(Tile c) {
        BlackKing bk=onBoard.getBlackKing();//black king
        int result=0;
        //chiếu tướng
        if(!isHasPieceBetweenStraight(bk.standing, c))
            result+=500;
        //tọa độ xung quanh quân vua
        //thay vì kiểm tra xem quân xe soi đến vua không thì 
        //mình kiểm tra xem 8 ô quanh vua có bị soi không với mỗi ô bị soi +20
        int tempx[]= Arrays.copyOf(bk.aroundX,bk.aroundX.length);
        int tempy[]=Arrays.copyOf(bk.aroundY,bk.aroundY.length);
        //duyệt trong 8 ô
        for(int i=0;i<8;i++){
            //tọa độ temp là tọa độ xung quanh quân vua
            if(!Tile.isOnBoard(bk.standing.x+tempx[i],bk.standing.y+tempy[i])) continue;
            Tile temp=new Tile(bk.standing.x+tempx[i],bk.standing.y+tempy[i]);
            //mỗi nước ở xung quanh +20
            //nếu máu bé hơn power fire vua đen thì trừ 20
            if(!isHasPieceBetweenStraight(temp, c)){
                if(temp.equals(c)&&bk.firePower>=this.hp&&bk.isCanShoot()){
                    result-=20;
                    //System.out.println("\t\t-20 "+temp.x+' '+temp.y);
                }
                result+=20;
            }
        }
        // chắn chiếu tướng
        //TODO: chắn chiếu tướng

        //vị trí quân cờ trên bàn cờ, giá trị quân cờ, hp quân cờ *2
        result+=scoreStanding[c.x][c.y]+valueOfRook+this.hp*2;
        //nếu nhiều hơn power fire của king thì +20
        
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
        int di[]={1,0,-1,0,0,1,0,-1};
        return caclBestMove(di, 4);
    }
    @Override
    char getSymbol() {
        return 'W';
    }
}
