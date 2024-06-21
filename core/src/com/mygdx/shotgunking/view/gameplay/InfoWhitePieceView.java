package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.mygdx.shotgunking.model.WhitePiece;
import com.mygdx.shotgunking.resource.FontManager;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.shared.TBorderLayoutPanel;
import com.mygdx.shotgunking.view.shared.TGridLayoutPanel;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TProgressBar;


public class InfoWhitePieceView extends TPanel {
    TPanel nameLabel = new TPanel();
    TPanel imgPiece = new TPanel();
    TProgressBar hpBar = new TProgressBar();
//    TPanel hpLabel = new TPanel();
    TPanel waitLabel = new TPanel("Chờ:");
    TPanel waitMove = new TPanel();
    TBorderLayoutPanel centerPanel = new TBorderLayoutPanel();
    TGridLayoutPanel southCenterPanel = new TGridLayoutPanel(4,1);

    public InfoWhitePieceView() {
//        this.setOpaque(true);
        this.setVisible(false);
        this.setBackgroundColor(Color.GRAY);
        hpBar.setForegroundColor(Color.RED);
        hpBar.setBackgroundColor(new Color(1,0,0, 152/255f));
//        hpBar.add(hpLabel);
        hpBar.setTextAlign(Align.center);
        hpBar.setTextColor(Color.WHITE);
//        imgPiece.setBackgroundColor(new Color(0x99e550ff));
//        imgPiece.setOpaque(true);
        this.add(centerPanel);
        centerPanel.add(nameLabel,TBorderLayoutPanel.NORTH);
        centerPanel.add(imgPiece);
        centerPanel.add(southCenterPanel,TBorderLayoutPanel.SOUTH);
        southCenterPanel.add(hpBar);
        southCenterPanel.add(waitLabel);
        southCenterPanel.add(waitMove);
    }

    WhitePiece model;

    public void showInfo(WhitePiece p) {
        model = p;
        reload();
        this.setVisible(true);
    }
    public void reload(){
        if(model==null) return;
        String typePiece = model.getClass().getSimpleName();
        nameLabel.setText(typePiece);
        imgPiece.setImg(ImageManager.instance.getImgOfPiece(model));
        hpBar.setMaximumValue(model.getMaxHP());
        hpBar.setValue(model.getHp());
        hpBar.setText(model.getHp()+"/"+model.getMaxHP()+" hp");
        waitMove.setText(model.getTurn() + "/" + model.getMaxTurn());
    }

    public void hideInfo() {
        this.setVisible(false);
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        float fontScale = this.getWidth()*0.5f/100;
        nameLabel.setTextScale(fontScale);
        imgPiece.setTextScale(fontScale);
        hpBar.setTextScale(fontScale*90/100);
        waitLabel.setTextScale(fontScale);
        waitMove.setTextScale(fontScale);
        int heightLabel = (int) (FontManager.instance.default_font.getLineHeight()*fontScale);
        centerPanel.setSize(this.getWidth(),heightLabel*5+this.getWidth()/10*9);
        centerPanel.setLocation((this.getWidth()-centerPanel.getWidth())/2,(this.getHeight()-centerPanel.getHeight())/2);
        southCenterPanel.setSize(this.getWidth(),heightLabel*4);
        southCenterPanel.getBorder().setSize(this.getWidth()*5/100);
        nameLabel.setSize(this.getWidth(),heightLabel);

//        hpBar.setSize(this.getWidth()-20, (int) (this.getWidth()/4.5));
//        imgPiece.setSize(this.getWidth()/3*2,this.getWidth()/3*2);

    }
}
