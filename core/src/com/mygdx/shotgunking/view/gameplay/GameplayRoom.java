package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.mygdx.shotgunking.ShotgunKing;
import com.mygdx.shotgunking.model.*;
import com.mygdx.shotgunking.model.card.Card;
import com.mygdx.shotgunking.resource.FontManager;
import com.mygdx.shotgunking.view.gameover.GameOverRoom;
import com.mygdx.shotgunking.view.setting.SettingRoom;
import com.mygdx.shotgunking.view.shared.TBorderLayoutPanel;
import com.mygdx.shotgunking.view.shared.TButton;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TRoom;


public class GameplayRoom extends TRoom {
    private static GameplayRoom ins;

    public static GameplayRoom getIns() {
        return ins;
    }
    Gameplay gameplay;
    private BoardView boardView;
    private TPanel msgLabel =new TPanel();
    private ListCardView buffPanel;
    private ListCardView debuffPanel;
    private TPanel containLayer = new TPanel();
    private TBorderLayoutPanel boardLayer = new TBorderLayoutPanel();
    private TBorderLayoutPanel centerPanel = new TBorderLayoutPanel();
    private TBorderLayoutPanel centerCenterPanel = new TBorderLayoutPanel();
    private InfoAmmoBlackKing infoAmmoBlackKing = new InfoAmmoBlackKing();
    private InfoWhitePieceView infoWhitePieceView = new InfoWhitePieceView();
    private InfoBlackKingView infoBlackKingView=new InfoBlackKingView();
    private ChoiceCardView choiceCardView ;
    private InfoCardView infoCard = new InfoCardView();
    private TButton pauseButton = new TButton("|  |"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            super.shortTouchAction(x, y, pointer, button);
            ShotgunKing.instance.setRoom(new SettingRoom(GameplayRoom.this));
            return true;
        }
    };
    public int floor = 1;
    public int maxFloor = 11;
    public GameplayRoom(){
        ins = this;
        gameplay = new Gameplay();
        boardView = new BoardView(gameplay);
        buffPanel = new ListCardView(gameplay.getBuffCards(),true);
        debuffPanel = new ListCardView(gameplay.getDebuffCards(),false);
//        choiceCardView = new ChoiceCardView(gameplay.makeTwoChoiceOfCard());
        infoCard.setVisible(false);

        msgLabel.setSize(50,50);
        msgLabel.setTextAlign(Align.center);

        centerCenterPanel.add(infoAmmoBlackKing, TBorderLayoutPanel.NORTH);
        centerCenterPanel.add(boardView);
//        centerCenterPanel.add(choiceCardView);
        centerCenterPanel.add(msgLabel,TBorderLayoutPanel.SOUTH);
        centerPanel.add(infoBlackKingView,TBorderLayoutPanel.WEST);
        centerPanel.add(infoWhitePieceView,TBorderLayoutPanel.EAST);
        centerPanel.add(centerCenterPanel,TBorderLayoutPanel.CENTER);
        this.add(containLayer);
        containLayer.add(boardLayer);
        containLayer.add(pauseButton);
        containLayer.add(infoCard);
        boardLayer.add(centerPanel,TBorderLayoutPanel.CENTER);
        boardLayer.add(buffPanel,TBorderLayoutPanel.WEST);
        boardLayer.add(debuffPanel,TBorderLayoutPanel.EAST);

        buffPanel.reload();
        debuffPanel.reload();
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void showMsg(String msg) {
        msgLabel.setText(msg);
    }
    public void showInfoWhitePiece(WhitePiece p){
        infoWhitePieceView.showInfo(p);
    }
    public void reloadInfoWhitePiece(){
        infoWhitePieceView.reload();
    }
    public void hideInfoWhitePiece(){
        infoWhitePieceView.hideInfo();
    }
    public void showInfoBlackPiece(BlackKing p){
        infoBlackKingView.showInfo(p);
        infoAmmoBlackKing.showInfo(p);
    }
    public void showInfoCard(Card c){
        infoCard.showInfoCard(c);
        infoCard.setLocation(Gdx.input.getX()-this.getX(),Gdx.input.getY()-this.getY());
    }
    public void hideInfoCard(){
        infoCard.hideInfoCard();
    }
    public void reloadPositionInfoCard(){
        Point p = new Point(Gdx.input.getX()-this.getX(),Gdx.input.getY()-this.getY());
        int x=p.x + 10,y=p.y+10;
        x = Math.min(x,this.getWidth()- infoCard.getWidth());
        y = Math.min(y,this.getHeight()- infoCard.getHeight());
        if(y<=p.y){
            y = p.y - infoCard.getHeight();
        }
        infoCard.setLocation(x,y);
    }
    public void reloadInfoBlackPiece(){
        infoBlackKingView.reload();
        infoAmmoBlackKing.reload();
    }
    public void runOutOfAmmo(){
        infoAmmoBlackKing.runOutOfAmmo();
//        showMsg("Hết đạn, bạn cần di chuyển để nạp đạn");
//        TPanelAnimation.twink(info,200,1000/60*5);
    }
    public void endOneFloor(){
        System.out.println("test");
        hideInfoWhitePiece();
        currentEOFAnimation = new EndOneFloorAnimation();
    }
    public void makeTwoChoice(){
        Pair<Pair<Card, Card>, Pair<Card, Card>> p = gameplay.makeTwoChoiceOfCard();
        choiceCardView = new ChoiceCardView(p);
        centerCenterPanel.remove(boardView);
        centerCenterPanel.add(choiceCardView);
    }
    public void selectAChoice(Pair<Card,Card> p){
        gameplay.addAChoice(p);
        buffPanel.reload();
        debuffPanel.reload();
        gameplay = gameplay.clone();
        boardView = new BoardView(gameplay);
        centerCenterPanel.remove(choiceCardView);
        centerCenterPanel.add(boardView);
        hideInfoCard();
    }
    @Override
    protected void resizeAction() {
        super.resizeAction();
        buffPanel.setSize(getWidth()*18/100,0);
        debuffPanel.setSize(getWidth()*18/100,0);
        infoBlackKingView.setSize(getWidth()/10,0);
        infoWhitePieceView.setSize(getWidth()/10,0);
        infoAmmoBlackKing.setSize(10,getWidth()/10);
        msgLabel.setSize(0,getHeight()/13);
        msgLabel.setTextScale(msgLabel.getHeight()*1.0f/FontManager.instance.default_font.getLineHeight());
        boardLayer.setBounds(0,0,this.getWidth(),this.getHeight());
        infoCard.setSize(this.getWidth()/3,this.getWidth()/3*2/3);
        pauseButton.setSize(msgLabel.getHeight(), msgLabel.getHeight());
        pauseButton.setLocation(this.getWidth()/2-pauseButton.getWidth()/2,this.getHeight()/100);
        pauseButton.setTextScale(pauseButton.getHeight()*0.9f/100);
//        centerPanel.setBorder(new EmptyBorder(0,getWidth()/11,0,getWidth()/11));
    }

    EndOneFloorAnimation currentEOFAnimation;
    @Override
    public void update() {
        super.update();
        if(currentEOFAnimation!=null)currentEOFAnimation.update();
    }

    class EndOneFloorAnimation{
        boolean isStop;
        long startTime;
        EndOneFloorAnimation(){
            startTime = System.currentTimeMillis();
        }

        void update(){
            if(isStop)return;
            long currentTime = System.currentTimeMillis()-startTime;
            if(gameplay.checkBlackWinGame()){
                if(!boardView.whitePieceViewList.isEmpty() && currentTime>500){
                    PieceView pv = boardView.whitePieceViewList.remove(0);
                    pv.beDestroyed();
                    startTime+=currentTime;
                    return;
                }else if(currentTime>2000){
                    if(floor==maxFloor) {
                        ShotgunKing.instance.setRoom(new GameOverRoom(true));
                    }else
                        makeTwoChoice();
                    floor++;
                    isStop=true;
                }
            }else {
                if(boardView.blackPieceView!=null){
                    boardView.blackPieceView.beDestroyed();
                    boardView.blackPieceView=null;
                }
                if(currentTime>2000){
                    ShotgunKing.instance.setRoom(new GameOverRoom(false));
                    isStop=true;
                }
            }
        }
    }


}
