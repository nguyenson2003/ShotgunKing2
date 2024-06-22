package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.mygdx.shotgunking.model.card.Card;
import com.mygdx.shotgunking.model.Pair;
import com.mygdx.shotgunking.resource.FontManager;
import com.mygdx.shotgunking.view.shared.*;


public class ChoiceCardView extends TGridLayoutPanel {
    SingleChoice choice1,choice2;
    public ChoiceCardView(Pair<Pair<Card,Card>, Pair<Card,Card>> p){
        super(2,1,0,50);
        this.setOpaque(false);

//        this.setLayout(new GridLayout(2,1,0,50));
//        this.setBorder(new EmptyBorder(20,20,20,20));
        choice1=new SingleChoice(p.first);
        choice2=new SingleChoice(p.second);
        this.add(choice1);
        this.add(choice2);
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        this.setVgap(getHeight()/11);
    }

}
class SingleChoice extends TBorderLayoutPanel {
    TPanel centerPanel;
    CardView card1img;
    CardView card2img;
    TButton selectButton;
    Pair<Card,Card> p;
    SingleChoice(Pair<Card,Card> p){
        this.setOpaque(true);
        this.setBackgroundColor(new Color(0xeeeeeeff));
        this.p = p;
        card1img = new CardView(p.first);
        card2img = new CardView(p.second);
        centerPanel = new TGridLayoutPanel(1,2);
        selectButton = new TButton("Chọn"){
            @Override
            protected boolean shortTouchAction(int x, int y, int pointer, int button) {
                GameplayRoom.getIns().selectAChoice(p);
                return true;
            }
        };
        this.setBorder(new TBorder(new Color(0x0077ffff),5));
        selectButton.setBorder(new TBorder(new Color(0x0077ffff),5,0,0,0));
        centerPanel.add(card1img);
        centerPanel.add(card2img);
        this.add(selectButton,SOUTH);
        this.add(centerPanel);
    }

    @Override
    protected void resizeAction() {
        super.resizeAction();
        card1img.setSize(this.getWidth()/2,this.getHeight()/2);
        card2img.setSize(this.getWidth()/2,this.getHeight()/2);
        selectButton.setSize(this.getWidth(),this.getHeight()/5);
        selectButton.setTextScale(selectButton.getHeight()*1.0f/ FontManager.instance.default_font.getLineHeight());
        centerPanel.getBorder().setSize(this.getWidth()/30);
//        this.getBorder().setSize(this.getWidth()/100);
    }
}
