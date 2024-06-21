package com.mygdx.shotgunking.view.setting;


import com.mygdx.shotgunking.ShotgunKing;
import com.mygdx.shotgunking.resource.AudioManager;
import com.mygdx.shotgunking.resource.Settings;
import com.mygdx.shotgunking.view.shared.TBorderLayoutPanel;
import com.mygdx.shotgunking.view.shared.TButton;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TRoom;

public class SettingRoom extends TRoom  {
    TRoom backroom;
    TPanel titleLabel = new TPanel("Cài đặt");
    TPanel musicLabel = new TPanel("Nhạc nền");
    TButton musicButton = new TButton(){
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
    TPanel soundLabel = new TPanel("Âm thanh");
    TButton soundButton = new TButton(){
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
    TButton backButton = new TButton("Quay lại"){
        @Override
        protected boolean shortTouchAction(int x, int y, int pointer, int button) {
            super.shortTouchAction(x, y, pointer, button);
            ShotgunKing.instance.setRoom(backroom);
            return true;
        }
    };
    TBorderLayoutPanel northPanel = new TBorderLayoutPanel();
    TBorderLayoutPanel centerPanel = new TBorderLayoutPanel();
    public SettingRoom(TRoom backroom){
        this.add(northPanel, NORTH);
        this.add(centerPanel, CENTER);
        northPanel.setOpaque(false);
        northPanel.add(titleLabel);
        centerPanel.setOpaque(false);
        centerPanel.add(musicLabel);
        centerPanel.add(soundLabel);
        centerPanel.add(musicButton);
        centerPanel.add(soundButton);
        centerPanel.add(backButton);
        this.backroom = backroom;
        musicButton.setText(Settings.instance.canPlayMusic?"On":"Off");
        soundButton.setText(Settings.instance.canPlaySound?"On":"Off");

    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        northPanel.setSize(this.getWidth(),this.getHeight()/4);
        titleLabel.setTextScale(this.getHeight()*0.1f/100);
        float fontBtnScale = this.getHeight()*0.15f/100;
        musicLabel.setTextScale(fontBtnScale);
        musicButton.setTextScale(fontBtnScale);
        soundLabel.setTextScale(fontBtnScale);
        soundButton.setTextScale(fontBtnScale);
        backButton.setTextScale(fontBtnScale);
        centerPanel.getBorder().setSize(getHeight()/6,getWidth()/4,getHeight()/6,getWidth()/4);
    }

}
