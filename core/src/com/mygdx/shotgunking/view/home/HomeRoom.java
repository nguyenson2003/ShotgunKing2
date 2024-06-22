package com.mygdx.shotgunking.view.home;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.shotgunking.ShotgunKing;
import com.mygdx.shotgunking.resource.AudioManager;
import com.mygdx.shotgunking.view.gameplay.GameplayRoom;
import com.mygdx.shotgunking.view.setting.SettingRoom;
import com.mygdx.shotgunking.view.shared.*;

public class HomeRoom extends TRoom {
    TPanel titleLabel = new TPanel("Shotgun King");
    TButton playButton = new TButton("Chơi ngay"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            ShotgunKing.instance.setRoom(new GameplayRoom());
            return true;
        }
    };
    TButton settingButton = new TButton("Cài đặt"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            ShotgunKing.instance.setRoom(new SettingRoom(HomeRoom.this));
            return true;
        }
    };
    TButton exitButton = new TButton("Thoát"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            System.exit(0);
            return true;
        }
    };
    TBorderLayoutPanel northPanel = new TBorderLayoutPanel();
    TPanel centerPanel = new TGridLayoutPanel(1,3,20,20);
    public HomeRoom(){
        this.add(northPanel, NORTH);
        this.add(centerPanel, CENTER);
        northPanel.setOpaque(false);
        northPanel.add(titleLabel);
        centerPanel.setOpaque(false);
        centerPanel.add(playButton);
        centerPanel.add(settingButton);
        centerPanel.add(exitButton);
        AudioManager.instance.playMusic(AudioManager.instance.backgroundMusic);
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        northPanel.setSize(this.getWidth(),this.getHeight()/2);
        titleLabel.setTextScale(this.getHeight()*0.4f/100);
        float fontBtnScale = this.getHeight()*0.15f/100;
        playButton.setTextScale(fontBtnScale);
        settingButton.setTextScale(fontBtnScale);
        exitButton.setTextScale(fontBtnScale);
        centerPanel.setBorder(new TBorder(new Color(0),getHeight()/6,getWidth()/20,getHeight()/6,getWidth()/20));
    }

}
