package com.mygdx.shotgunking.view.gameover;


import com.mygdx.shotgunking.ShotgunKing;
import com.mygdx.shotgunking.view.gameplay.GameplayRoom;
import com.mygdx.shotgunking.view.home.HomeRoom;
import com.mygdx.shotgunking.view.shared.*;

public class GameOverRoom extends TRoom {
    TPanel titleLabel = new TPanel("Shotgun King");
    TButton playButton = new TButton("Chơi lại"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            super.shortTouchAction(x, y, pointer, button);
            ShotgunKing.instance.setRoom(new GameplayRoom());
            return true;
        }
    };
    TButton homeButton = new TButton("Màn hình chính"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            super.shortTouchAction(x, y, pointer, button);
            ShotgunKing.instance.setRoom(new HomeRoom());
            return true;
        }
    };
    TPanel northPanel = new TBorderLayoutPanel();
    TPanel centerPanel = new TGridLayoutPanel(1,2,20,20);
    public GameOverRoom(boolean isWin){
        if(isWin)titleLabel.setText("Chiến thắng!");
        else titleLabel.setText("Thua cuộc!");
        this.add(northPanel, NORTH);
        this.add(centerPanel, CENTER);
        northPanel.setOpaque(false);
        northPanel.add(titleLabel);
        centerPanel.setOpaque(false);
        centerPanel.add(playButton);
        centerPanel.add(homeButton);
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        northPanel.setSize(this.getWidth(),this.getHeight()/2);
        titleLabel.setTextScale(this.getHeight()*0.3f/100);
        float fontBtnScale = this.getHeight()*0.15f/100;
        playButton.setTextScale(fontBtnScale);
        homeButton.setTextScale(fontBtnScale);
        centerPanel.getBorder().setSize(getHeight()/6,getWidth()/20,getHeight()/6,getWidth()/20);
    }
}
