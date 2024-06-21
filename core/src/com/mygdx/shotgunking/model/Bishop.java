package com.mygdx.shotgunking.model;

import java.util.Arrays;

public class Bishop extends WhitePiece{
    int scoreStanding[][]={ {0,0,0,0,0,0,0,0,0},

                            {0, 1,2,2,2,2,2,2,1},
                            {0, 2,4,4,4,4,4,4,2},
                            {0, 2,4,4,6,6,4,4,2},
                            {0, 2,4,4,6,6,4,4,2},
                            {0, 2,4,6,6,6,6,4,2},
                            {0, 2,5,5,5,5,5,5,2},
                            {0, 2,5,3,3,3,3,5,2},
                            {0, 3,2,2,2,2,2,2,3},
                            };
    //nếu tốt là 1 thì tịnh là 3
    int valueOfBishop=3;
    Bishop(Tile t, int maxTurn, int maxHP,Board onBoard) {
        super(t, maxTurn, maxHP,onBoard);
        //TODO Auto-generated constructor stub
    }
    @Override
    boolean isMate(Tile nextCell) {
        //nếu k cùng đường chéo sẽ return false
        return !isHasPieceBetweenDiagonal(nextCell, this.standing);
    }

    @Override
    int cacl(Tile c) {
        BlackKing bk=onBoard.getBlackKing();//black king
        int result=0;
        //chiếu tướng
        if(!isHasPieceBetweenDiagonal(bk.standing, c)){
            result+=500;
            // System.out.println("\t+500 "+c.x+' '+c.y);
        }
        //tọa độ xung quanh quân vua
        //thay vì kiểm tra xem quân tịnh soi đến vua không thì 
        //mình kiểm tra xem 8 ô quanh vua có bị soi không với mỗi ô bị soi +20
        int tempx[]= Arrays.copyOf(bk.aroundX,bk.aroundX.length);
        int tempy[]=Arrays.copyOf(bk.aroundY,bk.aroundY.length);
        //duyệt trong 8 ô
        for(int i=0;i<8;i++){
            //tọa độ temp là tọa độ xung quanh quân vua
            if(!Tile.isOnBoard(bk.standing.x+tempx[i],bk.standing.y+tempy[i])) continue;
            Tile temp=new Tile(bk.standing.x+tempx[i],bk.standing.y+tempy[i]);
            //mỗi nước ở xung quanh +20
            if(!isHasPieceBetweenDiagonal(temp, c)){
                if(temp.equals(c)&&bk.firePower>=this.hp&&bk.isCanShoot()){
                    result-=20;
                    // System.out.println("\t\t-20 "+temp.x+' '+temp.y);
                }
                result+=20;
                // System.out.println("\t+20 "+temp.x+' '+temp.y);
            }
        }
        // chắn chiếu tướng
        //TODO: chắn chiếu tướng

        //vị trí quân cờ trên bàn cờ, giá trị quân cờ, hp quân cờ *2
        result+=scoreStanding[c.x][c.y]+valueOfBishop+this.hp*2;
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
        int direction[]={1,1,-1,1,1,-1,-1,-1};
        if(onBoard.dataBuff.isQuanSu){
            int directionQueen[]={1,1,-1,1,1,-1,-1,-1,1,0,-1,0,0,1,0,-1};
            return caclBestMove(directionQueen, 8);
        }
        return caclBestMove(direction, 4);
    }
    @Override
    char getSymbol() {
        return 'B';
    }
}
