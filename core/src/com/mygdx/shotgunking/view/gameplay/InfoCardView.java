package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.mygdx.shotgunking.model.card.Card;
import com.mygdx.shotgunking.resource.FontManager;
import com.mygdx.shotgunking.view.shared.TBorder;
import com.mygdx.shotgunking.view.shared.TBorderLayoutPanel;
import com.mygdx.shotgunking.view.shared.TMixedBorder;
import com.mygdx.shotgunking.view.shared.TPanel;

public class InfoCardView extends TBorderLayoutPanel {
    TPanel nameJLabel = new TPanel();
    TPanel desJLabel = new TPanel();
    public InfoCardView(){
        this.setBorder(new TMixedBorder(
                new TBorder(Color.BLACK,5),
                new TBorder(new Color(0),5)
        ));
        this.setOpaque(true);
        nameJLabel.setTextColor(Color.BLACK);
        desJLabel.setTextColor(Color.BLACK);
        this.add(nameJLabel,TBorderLayoutPanel.NORTH);
        this.add(desJLabel);
        nameJLabel.setTextWarp(true);
        desJLabel.setTextWarp(true);
        nameJLabel.setTextAlign(Align.left);
        desJLabel.setTextAlign(Align.topLeft);
    }
    Card temp = null;
    public void showInfoCard(Card c){
        if(temp==c)return;
        temp = c;
        nameJLabel.setText(c.getName());
        desJLabel.setText(c.getDescription());
        setVisible(true);
    }
    public void hideInfoCard(){
        temp = null;
        setVisible(false);
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        float titleScale = this.getWidth()*0.2f/100;
        float descriptionScale = this.getWidth()*0.13f/100;
        nameJLabel.setTextScale(titleScale);
        desJLabel.setTextScale(descriptionScale);
        nameJLabel.setSize(this.getWidth(), (int) (FontManager.instance.default_font.getLineHeight()*titleScale*11/10));
    }
}
