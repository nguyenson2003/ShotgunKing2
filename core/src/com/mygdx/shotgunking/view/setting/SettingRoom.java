package com.mygdx.shotgunking.view.setting;


import com.mygdx.shotgunking.ShotgunKing;
import com.mygdx.shotgunking.resource.AudioManager;
import com.mygdx.shotgunking.resource.Settings;
import com.mygdx.shotgunking.view.gameplay.GameplayRoom;
import com.mygdx.shotgunking.view.home.HomeRoom;
import com.mygdx.shotgunking.view.shared.*;

public class SettingRoom extends TRoom  {
    TRoom backroom;
    TPanel titleLabel = new TPanel("Cài đặt");
    TPanel musicLabel = new TPanel("Nhạc nền");
    TButton musicButton;
    TPanel soundLabel = new TPanel("Âm thanh");
    TButton soundButton;
    TButton homeButton;
    TButton playButton;
    TBorderLayoutPanel northPanel = new TBorderLayoutPanel();
    TGridLayoutPanel centerPanel = new TGridLayoutPanel(4,2);
    public SettingRoom(TRoom backroom){
        musicButton = new TButton(){
            @Override
            protected boolean shortTouchAction(int x, int y, int pointer, int button) {
                super.shortTouchAction(x, y, pointer, button);
                if(Settings.instance.canPlayMusic){
                    Settings.instance.canPlayMusic=false;
                    musicButton.setText("Off");
                }else{
                    Settings.instance.canPlayMusic=true;
                    musicButton.setText("On");
                }
                Settings.instance.save();
                return true;
            }
        };
        soundButton = new TButton(){
            @Override
            protected boolean shortTouchAction(int x, int y, int pointer, int button) {
                super.shortTouchAction(x, y, pointer, button);
                if(Settings.instance.canPlaySound){
                    Settings.instance.canPlaySound=false;
                    soundButton.setText("Off");
                }else{
                    Settings.instance.canPlaySound=true;
                    soundButton.setText("On");
                }
                Settings.instance.save();
                return true;
            }
        };
        homeButton = new TButton("Màn hình chính"){
            @Override
            protected boolean shortTouchAction(int x, int y, int pointer, int button) {
                super.shortTouchAction(x, y, pointer, button);
                ShotgunKing.instance.setRoom(new HomeRoom());
                return true;
            }
        };
        playButton = new TButton(backroom instanceof GameplayRoom?"Chơi tiếp":"Chơi"){
            @Override
            protected boolean shortTouchAction(int x, int y, int pointer, int button) {
                super.shortTouchAction(x, y, pointer, button);
                if(backroom instanceof GameplayRoom) {
                    ShotgunKing.instance.setRoom(backroom);
                }else {
                    ShotgunKing.instance.setRoom(new GameplayRoom());
                }
                return true;
            }
        };
        this.add(northPanel, NORTH);
        this.add(centerPanel, CENTER);
        northPanel.setOpaque(false);
        northPanel.add(titleLabel);
        centerPanel.setOpaque(false);
        centerPanel.add(musicLabel);
        centerPanel.add(soundLabel);
        centerPanel.add(musicButton);
        centerPanel.add(soundButton);
        centerPanel.add(new TPanel());
        centerPanel.add(new TPanel());
        centerPanel.add(homeButton);
        centerPanel.add(playButton);
        this.backroom = backroom;
        musicButton.setText(Settings.instance.canPlayMusic?"On":"Off");
        soundButton.setText(Settings.instance.canPlaySound?"On":"Off");

    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        northPanel.setSize(this.getWidth(),this.getHeight()/4);
        titleLabel.setTextScale(this.getHeight()*0.3f/100);
        float fontBtnScale = this.getHeight()*0.15f/100;
        musicLabel.setTextScale(fontBtnScale);
        musicButton.setTextScale(fontBtnScale);
        soundLabel.setTextScale(fontBtnScale);
        soundButton.setTextScale(fontBtnScale);
        homeButton.setTextScale(fontBtnScale);
        playButton.setTextScale(fontBtnScale);
        centerPanel.getBorder().setSize(getHeight()/6,getWidth()/5,getHeight()/6,getWidth()/5);
    }

}
