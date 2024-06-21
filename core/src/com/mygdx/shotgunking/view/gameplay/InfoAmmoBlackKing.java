package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.shared.TBorderLayoutPanel;
import com.mygdx.shotgunking.view.shared.TGridLayoutPanel;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TPanelAnimation;


public class InfoAmmoBlackKing extends TBorderLayoutPanel {
    TPanel shellAmmoLabel = new TPanel("Băng đạn:");
    TGridLayoutPanel numShellAmmo;
    TPanel spareAmmoLabel = new TPanel("Đạn dược:");
    TPanel numSpareAmmo = new TPanel();
    TPanel shieldLabel = new TPanel("Khiên");
    TPanel numShield = new TPanel();
    Texture ammo1Img = ImageManager.instance.ammo1;
    Texture ammo2Img =ImageManager.instance.ammo2;
    Texture blank =ImageManager.instance.blank;
    Texture shield1 =ImageManager.instance.shield1;
    Texture shield2 =ImageManager.instance.shield2;
    TPanel westPanel = new TGridLayoutPanel(2,1);
    TPanel centerPanel = new TGridLayoutPanel(2,1);
    TPanel eastPanel = new TGridLayoutPanel(2,1);
    public InfoAmmoBlackKing(){
//        this.setOpaque(true);
        setBackgroundColor(Color.CYAN);
        shellAmmoLabel.setTextAlign(Align.left);
        spareAmmoLabel.setTextAlign(Align.left);

        numShellAmmo = new TGridLayoutPanel(1,15);
        for(int i=0;i<15;i++){
            TPanel img=new TPanel();
            numShellAmmo.add(img);
        }
        numShellAmmo.setOpaque(false);

        numSpareAmmo = new TGridLayoutPanel(1,15);
        for(int i=0;i<15;i++){
            TPanel img=new TPanel();
            numSpareAmmo.add(img);
        }
        numSpareAmmo.setOpaque(false);

        numShield = new TGridLayoutPanel(1,3);
        for(int i=0;i<3;i++){
            TPanel img=new TPanel();
            numShield.add(img);
        }
        numShield.setOpaque(false);
        this.add(westPanel,TBorderLayoutPanel.WEST);
        this.add(centerPanel);
        this.add(eastPanel,TBorderLayoutPanel.EAST);
        westPanel.add(shellAmmoLabel);
        westPanel.add(spareAmmoLabel);
        centerPanel.add(numShellAmmo);
        centerPanel.add(numSpareAmmo);
        eastPanel.add(shieldLabel);
        eastPanel.add(numShield);

    }
    BlackKing model;
    public void showInfo(BlackKing p) {
        model = p;
        for(int i=0;i<p.getShellAmmo();i++)
            numShellAmmo.getChildList().get(i).setImg(ammo1Img);
        for(int i=p.getShellAmmo();i<p.getMaxShellAmmo();i++)
            numShellAmmo.getChildList().get(i).setImg(ammo2Img);
        for(int i=p.getMaxShellAmmo();i<15;i++)
            numShellAmmo.getChildList().get(i).setImg(blank);

        for(int i=0;i<p.getSpareAmmo();i++)
            numSpareAmmo.getChildList().get(i).setImg(ammo1Img);
        for(int i=p.getSpareAmmo();i<p.getMaxSpareAmmo();i++)
            numSpareAmmo.getChildList().get(i).setImg(ammo2Img);
        for(int i=p.getMaxSpareAmmo();i<15;i++)
            numSpareAmmo.getChildList().get(i).setImg(blank);

        for(int i=0;i<p.getShield();i++)
            numShield.getChildList().get(3-i-1).setImg(shield1);
        for(int i=p.getShield();i<p.getMaxShield();i++)
            numShield.getChildList().get(3-i-1).setImg(shield2);
        for(int i=p.getMaxShield();i<3;i++)
            numShield.getChildList().get(3-i-1).setImg(blank);
    }
    public void reload(){
        showInfo(model);
    }
    public void runOutOfAmmo(){
        TPanelAnimation.twink(shellAmmoLabel,200,1000/60*5);
        TPanelAnimation.twink(numShellAmmo,200,1000/60*5);
    }
    @Override
    protected void resizeAction() {
        super.resizeAction();
        float fontScale = this.getHeight()*0.5f/100;
        shellAmmoLabel.setTextScale(fontScale);
//        numShellAmmoLabel.setFont(font);
        spareAmmoLabel.setTextScale(fontScale);
        numSpareAmmo.setTextScale(fontScale);
        shieldLabel.setTextScale(fontScale);
        westPanel.setSize(this.getWidth()*25/100,this.getHeight());
        eastPanel.setSize(this.getWidth()*15/100,this.getHeight());
    }
}
