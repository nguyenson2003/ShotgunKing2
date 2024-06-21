package com.mygdx.shotgunking.view.gameplay;

import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TPanelAnimation;

public class deltaHpView extends TPanel {
    long startTime;
    public deltaHpView(int deltaHp,int _x,int _y){
        this.setBounds(_x,_y,50,50);
        this.setOpaque(false);
        String str = deltaHp+"";
        for(int i=0;i<str.length();i++){
            TPanel num = new TPanel(ImageManager.instance.number[str.charAt(i)-'0']);
            num.setSize(20,50);
            this.add(num);
        }
        TPanelAnimation.setLocation(this,_x,_y-50,300);
        startTime= System.currentTimeMillis();
    }

    @Override
    public void update() {
        super.update();
        long currentTime = System.currentTimeMillis();
        if(currentTime-startTime>=400)
            this.getParent().remove(this);
    }
}
