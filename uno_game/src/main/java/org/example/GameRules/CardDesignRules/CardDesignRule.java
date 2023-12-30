package org.example.GameRules.CardDesignRules;

import org.example.card.UnoCard;
import org.example.utils.UnoColor;

public interface CardDesignRule {
    void setMainColorCard(UnoCard playedCard);
    void setWildColorCard(UnoCard playedCard);
    boolean isValidCard(UnoCard card , UnoColor color, UnoCard currentCard);
    String  getCardDesign(UnoCard card);
}
