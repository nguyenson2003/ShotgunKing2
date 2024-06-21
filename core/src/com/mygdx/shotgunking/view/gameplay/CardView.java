package com.mygdx.shotgunking.view.gameplay;

import com.mygdx.shotgunking.model.card.Card;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.shared.TPanel;

public class CardView extends TPanel {
    Card card = null;
    public CardView(boolean isBuff){
        if(isBuff) setImg(ImageManager.instance.flipBlack);
        else setImg(ImageManager.instance.flipWhite);
    }
    public CardView(Card c) {
        super(c.getTexture());
        this.card = c;
    }

    public void setCard(Card card) {
        if(this.card==card)return;
        this.card = card;
        setImg(card.getTexture());
    }

//    @Override
//    protected boolean touchDownAction(int x, int y, int pointer, int button) {
//        super.touchDownAction(x, y, pointer, button);
//        if(card==null)return false;
//        GameplayRoom.getIns().showInfoCard(card);
//        return true;
//    }

    @Override
    protected boolean mouseMoveAction(int x, int y) {
        if(card==null)return false;
        GameplayRoom.getIns().showInfoCard(card);
        GameplayRoom.getIns().reloadPositionInfoCard();
        return true;
    }

    @Override
    protected boolean mouseExitAction() {
        GameplayRoom.getIns().hideInfoCard();
        return true;
    }
}
