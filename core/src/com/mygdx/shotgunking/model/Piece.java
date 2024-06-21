package com.mygdx.shotgunking.model;

abstract public class Piece {
    
    /**
     * Quân cờ đang đứng ở ô nào
     */
    Tile standing;

    public void setStanding(Tile standing) {
        this.standing = standing;
    }

    Board onBoard;

    Piece(Tile standing_,Board onBoard_){
        standing=standing_;
        onBoard=onBoard_;
    }
    
    /**
     * Trả về ký từ quân cờ, ví dụ quân xe: R
     * @return trả về ký tự quân cờ
     */
    abstract char getSymbol();
    

    @Override
    public String toString() {
        return getSymbol()+"";
    }

    public Tile getStanding() {
        return standing;
    }
}
