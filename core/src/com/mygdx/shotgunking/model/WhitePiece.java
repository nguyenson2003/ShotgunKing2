package com.mygdx.shotgunking.model;

public abstract class WhitePiece extends Piece{
    

    WhitePiece(Tile t,int maxTurn, int maxHP,Board onBoard_) {
        super(t,onBoard_);
        this.maxTurn=maxTurn;
        this.turn=(int) (this.maxTurn*Math.random()+1);
        this.hp=this.maxHP=maxHP;
    }


    int maxTurn;

    public void setTurn(int turn) {
        this.turn = turn;
    }

    int turn;
    int maxHP,hp;
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }


    boolean mateFlag=false; //đánh dấu chiếu tướng ô tiếp theo, nhấp nháy view 1 lần
    int takeDamageFlag=0;
    /**
     * kiểm tra có chiếu ô x, y hay ko
     * @param nextCell nước đi tiếp theo của vua đen
     * @return nước đi tiếp theo của vua đen có bị chiếu bởi quân cờ này hay ko
     */
    abstract boolean isMate(Tile nextCell);

    /**
     * quân trắng giết quân vua
     */
    public void mate(Tile nextMove){
        if(!isMate(nextMove))throw new IllegalArgumentException("Ô này không chiếu hết");
        standing=nextMove;
    }

    /**
     * kiểm tra đến lượt di chuyển chưa
     */
    public boolean canMove(){return turn<=1;}

    /**
     * <div> Tính điểm của quân cờ khi ở ô c </div>
     * Quy tắc tính điểm:
     * <ul>
     *      <li>500: chiếu tướng trực tiếp</li>
     *      <li>250: chiếu tướng gián tiếp</li>
     *      <li>-250: chắn chiếu tướng (chưa cần làm vội)</li>
     *      <li>20: mỗi 1 ô xung quanh ô tướng</li>
     *      <li>a (1 -> 10): vị trí quân cờ trên bàn cờ (tùy thuộc vào loại quân cờ và vị trí tương đối với quân vua sẽ có 1 cách tính khác nhau)</li>
     *      <li>b (1 -> 10): giá trị của quân cờ (ví dụ tốt 1đ, hậu 9đ)
     *      <li>2: với mỗi 1 hp của quân cờ</li>
     * </ul>
     * @return Trả về giá trị của quân cờ khi ở ô c
     */
    abstract int cacl(Tile c);

    /**
     * Trả về nước đi tốt nhất
     */
    abstract Tile bestMove();

    public void move(Tile nextMove){
        turn--;
        if(turn>0){
            return;
        }
        if(onBoard.getPiece(nextMove)!=null && onBoard.getPiece(nextMove)!=this)
            throw new IllegalArgumentException(
                "Ô "+nextMove.toString()+" đã có quân cờ"
            );
        standing = nextMove;
        turn=maxTurn;
    }

    public void takeDamage(){
        hp--;
        takeDamageFlag++;
        if(isDied())onBoard.removePiece(this);
    }
    public boolean isDied(){
        return hp<=0;
    }

    public boolean isMateFlag() {
        return mateFlag;
    }

    public int isTakeDamageFlag() {
        return takeDamageFlag;
    }
    public void resetFlag(){
        mateFlag=false;
        takeDamageFlag=0;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getTurn() {
        return turn;
    }

    public int getMaxTurn() {
        return maxTurn;
    }

    /**
     * tính bestmove chung cho tịnh, hậu, xe
     * 
     */
    Tile caclBestMove(int di[],int numberOfDi){
        int bestScore=0;
        Tile resTile=new Tile(this.standing.x, this.standing.y);
        //4 hướng, 2 phần tử là 1 hướng
        int x=this.standing.x;
        int y=this.standing.y;
        //4 hướng
        for(int j=1;j<=numberOfDi;j++)
            //khoảng cách từ 1 -> 7
            for(int i=1;i<=7;i++){
                int tempx=x+i*di[j*2-2],tempy=y+i*di[j*2-1];
                if(Tile.isOnBoard(tempx,tempy)){
                    Tile tempTile=new Tile(tempx, tempy);
                    if(onBoard.getPiece(tempTile)==null||
                        //cưỡi ngựa hành quân
                        (onBoard.dataBuff.isCuoiNguaHanhQuan &&
                            onBoard.isHasKnightOnBoard )){
                        if(onBoard.getPiece(tempTile) instanceof Pawn) break;
                        if(onBoard.getPiece(tempTile)!=null) continue;
                        int tempScore=cacl(tempTile);
                        if(bestScore<tempScore){
                            bestScore=tempScore;
                            resTile=tempTile;
                        }
                        // System.out.println("."+tempScore+' '+tempTile.x+' '+tempTile.y);   
                    }else
                        break;
                }else
                    break;
                
            }

        return resTile;
    }

    /**
     * kiểm tra piece khác null và khác black King (là quân trắng) và điều kiện cưỡi ngựa hành quân
     * 
     */
    private boolean checkForStraightAndDiagonal(Piece p){
        return p!=null && p!=onBoard.getBlackKing() && (!(onBoard.isHasKnightOnBoard && onBoard.dataBuff.isCuoiNguaHanhQuan) || p instanceof Pawn);
    }
    /**
     * Kiểm tra xem có quân cờ nào nằm giữa Tile start và Tile end theo đường thẳng không. Không tính vua đen
     * @param start Tile vị trí bắt đầu 
     * @param end Tile vị trí kết thúc
     */
    boolean isHasPieceBetweenStraight (Tile start, Tile end){
        if(start.x!=end.x&&start.y!=end.y)
            return true;
        else{
            //Cưỡi ngựa hành quân có hoạt động không
            if(start.x==end.x){
                for(int i=Math.min(start.y,end.y)+1;i<Math.max(start.y,end.y);i++){
                    if(checkForStraightAndDiagonal(onBoard.getPiece(new Tile(start.x, i)))){
                        return true;
                    }
                }
                return false;
            }else{
                for(int i=Math.min(start.x,end.x)+1;i<Math.max(start.x,end.x);i++){
                    if(checkForStraightAndDiagonal(onBoard.getPiece(new Tile(i, start.y)))){
                            return true;
                    }
                }
                return false;
            }
        }
    }
    /**
     * Kiểm tra xem có quân cờ nào nằm giữa Tile start và Tile end theo đường chéo không. Không tính vua đen
     * @param start Tile vị trí bắt đầu 
     * @param end Tile vị trí kết thúc
     */
    boolean isHasPieceBetweenDiagonal (Tile start, Tile end){
        //kiểm tra xem có cùng đường chéo k
        //nếu không cùng đường chéo sẽ coi như có 1 quân ở giữa và không thể chiếu
        if(start.x-start.y!=end.x-end.y&&start.x+start.y!=end.x+end.y)
            return true;
        else{
            //Cưỡi ngựa hành quân có hoạt động không
            // boolean ck=Board.isHasKnightOnBoard && Board.dataBuff.isCuoiNguaHanhQuan;
            //nếu cùng đường chéo thì
            if(start.x-start.y==end.x-end.y){
                for(int i=Math.min(start.x,end.x)+1;i<Math.max(start.x,end.x);i++){
                    // System.out.println("cheo1 "+i+" "+start.x+" "+start.y+" "+end.x+" "+end.y);
                    if(checkForStraightAndDiagonal(onBoard.getPiece(new Tile(i,i-start.x+start.y)))){
                        return true;
                    }
                        
                }
                return false;
            }else {
                for(int i=Math.min(start.x,end.x)+1;i<Math.max(start.x,end.x);i++){
                    // System.out.println("cheo2 "+i+" "+start.x+" "+start.y+" "+end.x+" "+end.y);
                    if(checkForStraightAndDiagonal(onBoard.getPiece(new Tile(i,-i+start.x+start.y)))){
                        return true;
                    }
                }
                return false;
            }
        }
    }
    
}
