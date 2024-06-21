package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.shotgunking.model.card.Card;
import com.mygdx.shotgunking.view.shared.TGridLayoutPanel;
import com.mygdx.shotgunking.view.shared.TPanel;
import com.mygdx.shotgunking.view.shared.TPanelAnimation;

import java.util.List;

public class ListCardView extends TGridLayoutPanel {
    CardView[] cardViews = new CardView[10];
    List<Card> cardList;
    public ListCardView(List<Card> cardList,boolean isBuff){
        super(5,2,0,10);
//        this.setOpaque(true);
//        this.setBorder(BorderFactory.createDashedBorder(Color.WHITE,5,3,3, false));
//        this.setBackgroundColor(Color.WHITE);

//        this.setBorder(new EmptyBorder(10,10,10,10));
        this.cardList = cardList;
        for(int i = 0;i<cardViews.length;i++){
            cardViews[i] = new CardView(isBuff);
            this.add(cardViews[i]);
        }
    }
    public void reload(){
        for(int i = 0; i<cardViews.length && i<cardList.size(); i++){
            if(cardViews[i].card==cardList.get(i))continue;
            cardViews[i].setCard(cardList.get(i));
            TPanelAnimation.twink(cardViews[i],500,100);
        }
    }
}
