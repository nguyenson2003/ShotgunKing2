package com.mygdx.shotgunking.model;

import com.mygdx.shotgunking.model.card.Card;
import com.mygdx.shotgunking.model.card.HoangHauSatThu;
import com.mygdx.shotgunking.model.card.*;

import java.util.ArrayList;

/**
 * Gameplay
 */
public class Gameplay {
    Board b;
    boolean isPlaying=true;
    /**
     * biến ktra buff: phải giết tất cả tốt để chiến thắng
     */
    private boolean killAllPawnToWin = false;
    private int numberOfTurn = 1;
    ArrayList<Card> buffCards;
    ArrayList<Card> debuffCards;

    public ArrayList<Card> getBuffCards() {
        return buffCards;
    }

    public ArrayList<Card> getDebuffCards() {
        return debuffCards;
    }

    public Gameplay(){
        buffCards = new ArrayList<>();
        debuffCards = new ArrayList<>();
        debugAddCards();
        gameplayInit();
    }
    public Gameplay(ArrayList<Card> buffCards, ArrayList<Card> debuffCards){
        this.buffCards = buffCards;
        this.debuffCards = debuffCards;
        gameplayInit();
    }
    private void gameplayInit(){
        this.b=new Board(){
            @Override
            public void removePiece(Piece p) {
                super.removePiece(p);
                if(!(p instanceof WhitePiece))return;
                WhitePiece wp = (WhitePiece) p;
                for(Card c:buffCards){
                    if(c.isFlip())continue;
                    c.actionAfterWhiteDieAction(Gameplay.this,wp);
                }
                for(Card c:debuffCards){
                    if(c.isFlip())continue;
                    c.actionAfterWhiteDieAction(Gameplay.this,wp);
                }
            }
        };
        for(Card c:buffCards){
            if(c.isFlip())continue;
            c.actionBeforeInitBoard(this);
        }
        for(Card c:debuffCards){
            if(c.isFlip())continue;
            c.actionBeforeInitBoard(this);
        }
        b.init();
        for(Card c:buffCards){
            if(c.isFlip())continue;
            c.actionAfterInitBoard(this);
        }
        for(Card c:debuffCards){
            if(c.isFlip())continue;
            c.actionAfterInitBoard(this);
        }
        //update after init 
        updateAfterInit();
        //vua Súng độ lệch giảm 1 nửa
        BlackKing bk=this.getBoard().getBlackKing();
        if(this.getBoard().dataBuff.isVuaSung)
            bk.setSpread(bk.getSpread()/2);
    }
    /**
     * gán giá trị lại cho độ lệch và sát thương
     * 
     */
    void updateAfterInit(){
        BlackKing bk=this.getBoard().getBlackKing();
        bk.setFirePower(Math.max(1,bk.getFirePower()));
        bk.setSpread(Math.max(0,bk.getSpread()));
        bk.setMaxSpareAmmo(Math.max(1,bk.getMaxSpareAmmo()));
        bk.setMaxShellAmmo(Math.max(1,bk.getMaxShellAmmo()));
        bk.setSpareAmmo(bk.getMaxSpareAmmo());
        bk.setShellAmmo(bk.getMaxShellAmmo());
    }

    /**
     * Khi muốn tạo lại 1 màn chơi, nên xài hàm này
     * @return trả về 1 gameplay mới, các thẻ bài vẫn sẽ được giữ nguyên
     */
    public Gameplay clone() {
        return new Gameplay(this.buffCards, this.debuffCards);
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Board getBoard() {
        return b;
    }

    public int getNumberOfTurn() {
        return numberOfTurn;
    }

    public void blackMoveAction(Tile nextMove, double shootAngle){
        if(checkBlackWinGame())return;//check black is win game
        //calculator move or shoot (before black action)
        if(!isPlaying)return;
        boolean willShoot = false;
        if(b.getBlackKing().standing.equals(nextMove))return;
        if(!b.getBlackKing().canMoveTo(nextMove)){
            nextMove=b.getBlackKing().standing;
            willShoot=true;
            if(!b.getBlackKing().isCanShoot())return;
        }
        //check all piece checkmate (before black action)
        int countMate=0;
        for (WhitePiece piece : b.getWhitePieces()) {
            if(piece.isMate(nextMove)){
                piece.mateFlag=true;
                countMate++;
            }
        }
        if(b.getBlackKing().shield >0 && countMate>0){
            System.out.println("Vi tri nay bi "+countMate+" quan co chieu, moi ban di lai");
            b.getBlackKing().shield--;
            return;
        }

        for(Card c:buffCards){
            if(c.isFlip())continue;
            c.actionBeforeBlackAction(this);
        }
        for(Card c:debuffCards){
            if(c.isFlip())continue;
            c.actionBeforeBlackAction(this);
        }
        //black action
        if(!willShoot)b.getBlackKing().move(nextMove);
        else b.getBlackKing().shoot(shootAngle);
        //end black action
        

        for(Card c:buffCards){
            if(c.isFlip())continue;
            c.actionAfterBlackAction(this);
        }
        for(Card c:debuffCards){
            if(c.isFlip())continue;
            c.actionAfterBlackAction(this);
        }

        b.checkIsOnBoardOfPiece();
        if(checkBlackWinGame()){
            isPlaying = false;
            return;// check black is win game?
        }
        //start white action
        whiteAction();
        if(!isPlaying)return;
        numberOfTurn++;
        for(Card c:buffCards){
            if(c.isFlip())continue;
            c.actionAfterWhiteAction(this);
        }
        for(Card c:debuffCards){
            if(c.isFlip())continue;
            c.actionAfterWhiteAction(this);
        }
    }
    public void whiteAction(){
        //nếu có chiếu hết, đen thua
        for (WhitePiece piece : b.getWhitePieces()) {
            if(piece.isMate(b.getBlackKing().getStanding())){
                piece.mate(b.getBlackKing().getStanding());
                System.out.println("Thua!");
                isPlaying=false;
                b.removePiece(b.getBlackKing());
                System.out.println(b);
                return;
            }
        }

        //quân trắng di chuyển
        int whitePieceSizeTemp = b.getWhitePieces().size();
        System.out.println(whitePieceSizeTemp);
        whitePieceSizeTemp = b.getWhitePieces().size();
        for (int i = 0; i< whitePieceSizeTemp; i++) {
            WhitePiece piece = b.getWhitePieces().get(i);
            piece.move(piece.bestMove());
            if(piece instanceof Pawn){
                Pawn pawn = (Pawn) piece;
                pawn.promote();
            }
        }

        
        b.getWhitePieces().removeIf(WhitePiece::isDied);
        b.getBlackKing().shield = b.getBlackKing().maxShield;
    }

    /**
     * @return true nếu black thắng, false nếu black chưa thắng
     */
    public boolean checkBlackWinGame(){
        //Ngai vàng bỏ trống nếu k có vua phải giết hết tốt
        if(getBoard().dataBuff.isNgaiVangBoTrong && !getBoard().dataBuff.isBecomeKing){
            return !b.isHasPawnOnBoard;
        }
        //Cơ bản: 
        return !b.isHasKingOnBoard;
    }
    public void debugAddCards(){
//        buffCards.add(new TuDo());
//         buffCards.add(new NgaiVangBoTrong());
        // buffCards.add(new SucManhVoHan());
        // buffCards.add(new SuyGiamNhueKhi());
//         debuffCards.add(new NhapThanh());
        // buffCards.add(new XungPhong());
        // buffCards.add(new UyQuyenQuanVuong());
        // debuffCards.add(new TuDo());
        // debuffCards.add(new XungPhong());
        // buffCards.add(new SongSinhDangSo());
        // buffCards.add(new SungVinhQuang());
        // buffCards.add(new XaSung());
        // buffCards.add(new NghiBinh());
        // buffCards.add(new NguyenRua());
        // buffCards.add(new TapChungCaoDo());
        // buffCards.add(new LaChanThep());
        // buffCards.add(new ChienMa());
        // buffCards.add(new ChienXa());
//         buffCards.add(new CuoiNguaHanhQuan());
//        buffCards.add(new BachPhatBachTrung());
    }

    public Pair<Pair<Card,Card>,Pair<Card,Card>> makeTwoChoiceOfCard(){

        Card buff1,debuff1,buff2,debuff2;
        int count;
        do{
            buff1 = Card.randomABuffCard();
            count=0;
            for(Card c : buffCards)
                if(c.getClass() == buff1.getClass())
                    count++;
        } while (count>0);
        do{
            buff2 = Card.randomABuffCard();
            count=0;
            if(buff1.getClass()==buff2.getClass())
                count++;
            for(Card c : buffCards)
                if(c.getClass() == buff2.getClass())
                    count++;
        } while (count>0);
        do{
            debuff1 = Card.randomADebuffCard();
            count=0;
            for(Card c : debuffCards)
                if(c.getClass() == debuff1.getClass() || debuff1.getClass() == HoangHauSatThu.class)
                    count++;
        } while (count>0);
        do{
            debuff2 = Card.randomADebuffCard();
            count=0;
            if(debuff1.getClass()==debuff2.getClass())
                count++;
            for(Card c : debuffCards)
                if(c.getClass() == debuff2.getClass() || debuff2.getClass() == HoangHauSatThu.class)
                    count++;
        } while (count>0);
//        if(GameplayRoom.getIns().floor ==3){
//            debuff1=new HoangHauSatThu();
//            debuff2=new HoangHauSatThu();
//        }
        Pair<Card,Card> choice1 = new Pair<>(buff1,debuff1);
        Pair<Card,Card> choice2 = new Pair<>(buff2,debuff2);
        return new Pair<>(choice1,choice2);
    }
    public void addAChoice(Pair<Card,Card> p){
        buffCards.add(p.first);
        debuffCards.add(p.second);
    }
}