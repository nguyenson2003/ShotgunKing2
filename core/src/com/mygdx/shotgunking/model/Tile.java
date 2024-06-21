package com.mygdx.shotgunking.model;

/**
 * Ô của bàn cờ
 * giá trị x và y tính từ 1 đến 8
 */
public class Tile {
    public int x, y;
    /**
     * Tạo ra 1 ô bàn cờ trong khoảng từ 1 đến 8 tinh từ góc trên bên trái
     * @param x cột của ô cờ
     * @param y hàng của ô cờ
     */
    public Tile(int x,int y){
        if(!isOnBoard(x, y))throw new IllegalArgumentException(x+" "+y+"ko fai o ban co");
        this.x=x;this.y=y;
    }

    /**
     * Kiểm tra ô x, y có hợp lệ không
     * @param x tọa độ x
     * @param y tọa độ y
     * @return true nếu vị trí hợp lệ, false nếu vị trí ko hợp lệ
     */
    public static boolean isOnBoard(int x, int y){
        return x>0 && y>0 && x<9 && y<9;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Tile))return false;
        else {
            Tile that = (Tile) obj;
            return this.x == that.x && this.y == that.y;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
