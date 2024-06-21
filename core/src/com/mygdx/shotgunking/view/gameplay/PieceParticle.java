package com.mygdx.shotgunking.view.gameplay;


import com.mygdx.shotgunking.resource.AudioManager;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TPanelAnimation;


public class PieceParticle extends TPanel {
    TPanel particleImg;
    TPanel shadowImg;
    double x,y;
    double z = Math.random()*100;
    int angle = (int) (Math.random()*360);
    double vxy = Math.random()* 5;
    double vz = z/10;
    double dvxy = 0.05;
    double dvz = 1;
    long startTime;
    public PieceParticle(int _x,int _y, boolean isWhite){
        if(isWhite)particleImg = new TPanel(ImageManager.instance.particle[(int) (Math.random()*5)]);
        else particleImg = new TPanel(ImageManager.instance.blackParticle[(int) (Math.random()*5)]);
        shadowImg= new TPanel(ImageManager.instance.shadowParticle);
        this.setOpaque(false);
        this.add(shadowImg);
        this.add(particleImg);
        this.x = _x;
        this.y = _y;
        this.setLocation((int) this.x, (int) (this.y-this.z/2));
        startTime = System.currentTimeMillis();

        TPanelAnimation.twink(this,2000,50);
    }

    @Override
    public void update() {
        super.update();
        long currentTime = System.currentTimeMillis();
        if(currentTime-startTime<1500) {
            particleImg.setSize(this.getWidth(), this.getWidth());
            shadowImg.setSize(this.getWidth(), this.getWidth());
            vxy -= dvxy;
            if (vxy < 0) {
                vxy = 0;
                dvxy = 0;

            }
            x += Math.cos(angle) * vxy;
            y += Math.sin(angle) * vxy;
            vz -= dvz;
            z += vz;
            if (z < 0) {
                z = -z;
                vz = -vz / 10 * 7;
                if (vz > 5)
                    AudioManager.instance.playSound(AudioManager.instance.particle);
            }
            this.setLocation((int) this.x, (int) (this.y-this.z/2));
            shadowImg.setLocation(0, (int) (this.z/2));
        }else{
            getParent().remove(this);
        }
    }
}
