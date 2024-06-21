package com.mygdx.shotgunking.model;

import java.util.Arrays;

public class King extends WhitePiece{
    int scoreStanding[][]={ {0,0,0,0,0,0,0,0,0},

                            {0, 3,2,2,1,1,2,2,3},
                            {0, 3,2,2,1,1,2,2,3},
                            {0, 4,2,2,1,1,2,2,4},
                            {0, 4,2,2,1,1,2,2,4},
                            {0, 5,4,4,3,3,4,4,5},
                            {0, 5,4,4,4,4,4,4,5},
                            {0, 6,5,4,4,4,4,5,6},
                            {0, 6,6,5,5,5,5,6,6},
                            };
    //nếu tốt là 1 thì king là 3
    int valueOfKing=3;
    public King(Tile t, int maxTurn, int maxHP, Board onBoard_) {
        super(t, maxTurn, maxHP,onBoard_);
        //TODO Auto-generated constructor stub
    }
    @Override
    public boolean isMate(Tile nextCell) {
        if((nextCell.y==standing.y+1 && (nextCell.x==standing.x-1 || nextCell.x==standing.x+1 ||nextCell.x==standing.x ))||
        (nextCell.y==standing.y-1 && (nextCell.x==standing.x-1 || nextCell.x==standing.x+1||nextCell.x==standing.x ))||
        (nextCell.x==standing.x+1 && nextCell.y==standing.y )||
        (nextCell.x==standing.x-1 && nextCell.y==standing.y ))
            return true;
        return false;
    }

    @Override
    int cacl(Tile c) {
        //la chan thep co bao ve khong
        boolean isProtectedLaChanThep = onBoard.isHasBishopOnBoard && onBoard.dataBuff.isLaChanThep;
        int res=0;
        BlackKing bk=onBoard.getBlackKing();
//      500: chiếu tướng trực tiếp
        int absx=Math.abs(c.x-bk.standing.x),absy=Math.abs(c.y-bk.standing.y);
        if(absx<=1 && absy<=1){
                // System.out.println("\t\t+500 "+c.x+" "+c.y);
                res+=500;
        }
        // 20: mỗi 1 ô xung quanh ô tướng
        if(absx+absy>=2 && absx*absy!=1){
            res+=Math.max((5-absx-absy),0)*20;
            // System.out.println("\t\t+20 "+res+" "+c.x+" "+c.y);
        }
        //ưu tiên lại gần tấn công
        res-=(absx+absy)*20;
// 250: chiếu tướng gián tiếp //k có
// -250: chắn chiếu tướng (chưa cần làm vội)
        // duy trì khoảng cách với vua đen khi yếu máu và không được bảo vệ bở lá chắn thép
        // if(!isProtectedLaChanThep&&bk.isCanShoot()&&this.hp<=bk.firePower){
        //     if(absx+absy<=4){
        //         // System.out.println("\t\t-500 "+c.x+" "+c.y);
        //         res-=500;
        //     }

        //     //vua chạy khi yếu máu
        //     res+=(absx+absy)*1000;
        //     // System.out.println("\t\t+1000 "+c.x+" "+c.y);
        // }else{
        //     res-=(absx+absy)*1000;
        //     // System.out.println("\t\t-1000 "+c.x+" "+c.y);
        // }
            
// a (1 -> 10): vị trí quân cờ trên bàn cờ (tùy thuộc vào loại quân cờ và vị trí tương đối với quân vua sẽ có 1 cách tính khác nhau)
// b (1 -> 10): giá trị của quân cờ (ví dụ tốt 1đ, hậu 9đ)
// 2: với mỗi 1 hp của quân cờ

        res+=scoreStanding[c.x][c.y]+valueOfKing+2*this.hp;
        //ủy quyền quân vương
        if(onBoard.dataBuff.isUyQuyenQuanVuong){
            if(Math.abs(bk.standing.x-c.x)<=1&&Math.abs(bk.standing.y-c.y)<=1){
                res-=Integer.MAX_VALUE;
            }
        }
        return res;
    }

    @Override
    Tile bestMove() {
        BlackKing bk=onBoard.getBlackKing();
        int bestScore=Integer.MIN_VALUE;
        Tile resTile=new Tile(this.standing.x, this.standing.y);
        int tempx[]=Arrays.copyOf(bk.aroundX,bk.aroundX.length);
        int tempy[]=Arrays.copyOf(bk.aroundY,bk.aroundY.length);
        for(int i=0;i<8;i++){
            //nếu nó k trên bàn cờ thì continue
            if(!Tile.isOnBoard(this.standing.x+tempx[i], this.standing.y+tempy[i])) continue;
            Tile tempTile=new Tile(this.standing.x+tempx[i], this.standing.y+tempy[i]);
            //nếu vị trí này có quân cờ rồi thì continue
            if(onBoard.getPiece(tempTile)!=null) continue;
            int tempScore=cacl(tempTile);
            // System.out.println("diem vua trang: "+tempScore+" "+tempTile.x+" "+tempTile.y);
            if(bestScore<tempScore){
                bestScore=tempScore;
                resTile=tempTile;
            }
        }
        return resTile;
    }
    @Override
    char getSymbol() {
        return 'W';
    }
}
