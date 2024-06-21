package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.shotgunking.model.BlackKing;
import com.mygdx.shotgunking.resource.FontManager;
import com.mygdx.shotgunking.view.shared.TGridLayoutPanel;
import com.mygdx.shotgunking.view.shared.TPanel;


public class InfoBlackKingView extends TPanel {
    TPanel firepowerLabel = new TPanel("Hỏa lực");
    TPanel numFirepowerLabel = new TPanel();
    TPanel rangeLabel = new TPanel("Tầm bắn");
    TPanel numRangeLabel = new TPanel();
    TPanel spreadLabel = new TPanel("Độ lệch");
    TPanel numSpreadLabel = new TPanel();
    TPanel centerPanel = new TGridLayoutPanel(6,1);
    public InfoBlackKingView(){
//        this.setOpaque(true);
        setBackgroundColor(Color.GRAY);
        this.add(centerPanel);
        centerPanel.add(firepowerLabel);
        centerPanel.add(numFirepowerLabel);
        centerPanel.add(rangeLabel);
        centerPanel.add(numRangeLabel);
        centerPanel.add(spreadLabel);
        centerPanel.add(numSpreadLabel);

    }
    BlackKing model;
    public void showInfo(BlackKing p) {
        model = p;
        numFirepowerLabel.setText(p.getFirePower()+"");
        if(p.getFireRange()<100) numRangeLabel.setText((p.getFireRange()-1)+" - "+(p.getFireRange()+1));
        else  numRangeLabel.setText(999+"");
        numSpreadLabel.setText(Math.round(Math.toDegrees(p.getSpread()))+"°");
    }
    public void reload(){
        showInfo(model);
    }
    @Override
    protected void resizeAction() {
        super.resizeAction();
//        Font font = new Font(firepowerLabel.getFont().getName(), Font.PLAIN,this.getWidth()/6);
        float fontScale = getWidth()*0.5f/100f;
        firepowerLabel.setTextScale(fontScale);
        numFirepowerLabel.setTextScale(fontScale);
        rangeLabel.setTextScale(fontScale);
        numRangeLabel.setTextScale(fontScale);
        spreadLabel.setTextScale(fontScale);
        numSpreadLabel.setTextScale(fontScale);
        int heightLabel = (int) (FontManager.instance.default_font.getLineHeight()*fontScale);
//        firepowerLabel.setSize(getWidth(), heightLabel);
//        numFirepowerLabel.setSize(getWidth(), heightLabel);
//        rangeLabel.setSize(getWidth(), heightLabel);
//        numRangeLabel.setSize(getWidth(), heightLabel);
//        spreadLabel.setSize(getWidth(), heightLabel);
//        numSpreadLabel.setSize(getWidth(), heightLabel);
        centerPanel.setSize(this.getWidth(),heightLabel*6);
        centerPanel.setLocation(0,(this.getHeight()-centerPanel.getHeight())/2);
    }

}
