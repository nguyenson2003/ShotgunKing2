package com.mygdx.shotgunking.model;

import java.util.*;

public class Board {

    @Deprecated
    public static Board ins;
    private ArrayList<WhitePiece> whitePieces = new ArrayList<>();
    private BlackKing blackKing;
    public DataBuff dataBuff = new DataBuff();
    public Board() {
        ins = this;
        //        init();
    }
    public boolean isHasBishopOnBoard=false;
    public boolean isHasKnightOnBoard=false;
    public boolean isHasPawnOnBoard=false;
    public boolean isHasKingOnBoard=false;

    private int initKnight = 1,initTurnKnight=3,initHpKnight = 3;
    private int initBishop = 1,initTurnBishop=5,initHpBishop=4;
    private int initKing = 1,initTurnKing=4,initHpKing=10;
    private int initQueen = 0,initTurnQueen=4,initHpQueen=5;
    private int initPawn = 4,initTurnPawn=5,initHpPawn=3;
    private int initRook = 0,initTurnRook=4,initHpRook=5;

    
    public int getInitTurnBishop() {
        return initTurnBishop;
    }
    public void setInitTurnBishop(int initTurnBishop) {
        this.initTurnBishop = initTurnBishop;
    }
    public int getInitTurnRook() {
        return initTurnRook;
    }
    public void setInitTurnRook(int initTurnRook) {
        this.initTurnRook = initTurnRook;
    }
    public int getInitTurnQueen() {
        return initTurnQueen;
    }
    public void setInitTurnQueen(int initTurnQueen) {
        this.initTurnQueen = initTurnQueen;
    }
    /**
     * Kiểm tra xem còn những quân nào trên bàn cờ
     * 
     */
    public void checkIsOnBoardOfPiece(){
        this.isHasBishopOnBoard=false;
        this.isHasKnightOnBoard=false;
        this.isHasPawnOnBoard=false;
        this.isHasKingOnBoard=false;
        for(WhitePiece wp:getWhitePieces()){
            if(!wp.isDied()){
                this.isHasBishopOnBoard|=(wp instanceof Bishop);
                this.isHasKnightOnBoard|=(wp instanceof Knight);
                this.isHasPawnOnBoard|=(wp instanceof Pawn);
                this.isHasKingOnBoard|=(wp instanceof King);
            }
        }
        // Board.isHasKingOnBoard=isHasPawnOnBoard=true;
        // if(isHasKingOnBoard)
        //     System.out.println("k");
    }
    /**
     * Chuyển quân cờ a thành quân cờ b
     */
    public void convertAToB(Piece a,Piece b){
        this.removePiece(a);
        b.standing=a.standing;
    }
    public int getInitKing() {
        return initKing;
    }
    public void setInitKing(int initKing) {
        this.initKing = initKing;
    }

    public int getInitTurnPawn() {
        return initTurnPawn;
    }
    public void setInitTurnPawn(int initTurnPawn) {
        this.initTurnPawn = initTurnPawn;
    }

    public int getInitTurnKing() {
        return initTurnKing;
    }
    public void setInitTurnKing(int initTurnKing) {
        this.initTurnKing = initTurnKing;
    }

    public int getInitBishop() {
        return initBishop;
    }
    public int getInitTurnKnight() {
        return initTurnKnight;
    }
    public void setInitTurnKnight(int initTurnKnight) {
        this.initTurnKnight = initTurnKnight;
    }
    public void setInitBishop(int initBishop) {
        this.initBishop = initBishop;
    }

    public int getInitHpKing() {
        return initHpKing;
    }
    public void setInitHpKing(int initHpKing) {
        this.initHpKing = initHpKing;
    }
    public int getInitHpPawn() {
        return initHpPawn;
    }
    public void setInitHpPawn(int initHpPawn) {
        this.initHpPawn = initHpPawn;
    }

    public int getInitHpKnight() {
        return initHpKnight;
    }
    public void setInitHpKnight(int initHpKnight) {
        this.initHpKnight = initHpKnight;
    }

    public int getInitHpBishop() {
        return initHpBishop;
    }
    public void setInitHpBishop(int initHpBishop) {
        this.initHpBishop = initHpBishop;
    }

    public int getInitHpRook() {
        return initHpRook;
    }
    public void setInitHpRook(int initHpRook) {
        this.initHpRook = initHpRook;
    }

    
    public int getInitHpQueen() {
        return initHpQueen;
    }
    public void setInitHpQueen(int initHpQueen) {
        this.initHpQueen = initHpQueen;
    }
    public int getInitQueen() {
        return initQueen;
    }
    public void setInitQueen(int initQueen) {
        this.initQueen = initQueen;
    }
    public int getInitRook() {
        return initRook;
    }
    public void setInitRook(int initRook) {
        this.initRook = initRook;
    }
    public int getInitPawn() {
        return initPawn;
    }
    public void setInitPawn(int initPawn) {
        this.initPawn = initPawn;
    }
    public void init() {
        addPiece(new BlackKing(new Tile(4, 8), this, 2, 2, 8,
            4, 5, 40));
        isHasBishopOnBoard=(initBishop>0);
        isHasKnightOnBoard=(initKnight>0);
        isHasKingOnBoard=(initKing>0);
        isHasPawnOnBoard=(initPawn>0);
        
        int numberOfPieceWithoutPawn=initBishop+initKing+initQueen+initKnight+initRook;
        int colList[]={4,5,3,6,2,7,1,8};
        int row=1,indexCol=0,col=0;
        int cntPawn=0,cntKnight=0,cntBishop=0,cntKing=0,cntRook=0,cntQueen=0;
        while(numberOfPieceWithoutPawn>0){
            col=colList[indexCol%colList.length];
            row=indexCol/colList.length+1;
            if(cntKing<initKing){
                cntKing++;
                addPiece(new King(new Tile(col, row), initTurnKing, initHpKing, this));
                numberOfPieceWithoutPawn--;
            }else if(cntQueen<initQueen){
                cntQueen++;
                addPiece(new Queen(new Tile(col, row), initTurnQueen, initHpQueen, this));
                numberOfPieceWithoutPawn--;
            }else if(cntBishop<initBishop){
                cntBishop++;
                addPiece(new Bishop(new Tile(col, row), initTurnBishop, initHpBishop, this));
                numberOfPieceWithoutPawn--;
            }else if(cntKnight<initKnight){
                cntKnight++;
                addPiece(new Knight(new Tile(col, row), initTurnKnight, initHpKnight, this));
                numberOfPieceWithoutPawn--;
            }else if(cntRook<initRook){
                cntRook++;
                addPiece(new Rook(new Tile(col, row), initTurnRook, initHpRook, this));
                numberOfPieceWithoutPawn--;
            }
            indexCol++;
        }
        indexCol=((initBishop+initKing+initQueen+initKnight+initRook)/colList.length)*colList.length;
        while(cntPawn++<initPawn){
            col=colList[indexCol%colList.length];
            row=Math.max(2,indexCol/colList.length+1);
            if(this.getPiece(new Tile(col,row))!=null)
                row+=1;
            addPiece(new Pawn(new Tile(col, row), initTurnPawn, initHpPawn, this));
            
            indexCol++;
            // System.out.println(col+" "+row);
        }
        
    }
    public Tile getRandomTileEmpty(){
        int indexTile=0,row=0,col=0;
        do{
            row=indexTile/8+1;
            col=indexTile%8+1;
            indexTile++;
        }while(Tile.isOnBoard(col, row) && this.getPiece(new Tile(col, row))!=null);
        indexTile--;
        int random=(int)(Math.random()*100/8);
        while(random>0){
            col=indexTile%8+1;
            indexTile++;
            if(Tile.isOnBoard(col, row) && this.getPiece(new Tile(col, row))==null)
                random--;
        }
        return new Tile(col, row);
    }
    public void debugInit() {
        addPiece(new BlackKing(new Tile(4, 7), this, 2, 2, 8,
                4, 5, 40));
        addPiece(new King(new Tile(5, 1), 3, 10, this));
        addPiece(new Rook(new Tile(1, 1), 3, 5, this));
        addPiece(new Knight(new Tile(2, 1), 3, 3, this));
        addPiece(new Bishop(new Tile(3, 1), 3, 3, this));
        addPiece(new Queen(new Tile(4, 1), 3, 3, this));
        addPiece(new Bishop(new Tile(6, 1), 3, 3, this));
        addPiece(new Pawn(new Tile(4, 2), 3, 3, this));
    }

    /**
     * Lấy ra quân vua đen của bàn cờ
     *
     * @return trả về quân vua đen
     */
    public BlackKing getBlackKing() {
        return blackKing;
    }

    /**
     * Thêm 1 quân cờ vào trong bàn cờ
     *
     * @param p quân cờ được thêm vào bàn cờ
     */
    public void addPiece(Piece p) {
        if (getPiece(p.standing) != null) throw new IllegalArgumentException("Ô này đã có quân cờ");
        if (p instanceof WhitePiece) {whitePieces.add((WhitePiece) p);
        }else blackKing = (BlackKing) p;
    }
    /**
     * Xóa 1 quân cờ ra khỏi bàn cờ
     *
     * @param p quân cờ sẽ xóa khỏi bàn cờ
     */
    public void removePiece(Piece p) {
        if (p instanceof BlackKing) blackKing = null;
        else whitePieces.remove((WhitePiece) p);
    }

    /**
     * Xóa quân cờ ở 1 ô ra khỏi bàn cờ
     *
     * @param c vị trí quân cờ được xóa
     */
    public void removePiece(Tile c) {
        removePiece(getPiece(c));
    }

    /**
     * Tìm quân cờ của 1 ô cờ
     *
     * @param c ô cờ
     * @return Trả về quân cờ ở vị trí c
     */
    public Piece getPiece(Tile c) {
        if (blackKing != null && blackKing.standing.equals(c)) return blackKing;
        for (WhitePiece p : whitePieces) {
            if(p.isDied())continue;
            if (p.standing.equals(c)) return p;
        }
        return null;
    }

    /**
     * @return Trả về list các quân trắng
     */
    public List<WhitePiece> getWhitePieces() {
        return whitePieces;
    }

    @Override
    public String toString() {
        char board[][] = new char[8][8];
        for (int i = 0; i < 8; i++) {
            Arrays.fill(board[i], '.');
        }
        for (Piece p : whitePieces) {
            board[p.standing.y - 1][p.standing.x - 1] = p.getSymbol();
        }
        if (blackKing != null)
            board[blackKing.standing.y - 1][blackKing.standing.x - 1] = blackKing.getSymbol();

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            res.append(String.valueOf(board[i])).append('\n');
        }
        return res.toString();
    }
}
