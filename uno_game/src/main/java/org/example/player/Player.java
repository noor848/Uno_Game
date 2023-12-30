package org.example.player;

import org.example.card.UnoCard;
import org.example.utils.UnoColor;

import java.util.ArrayList;
/**
 * Represents the Player Uno game, a subclass of the general PlayerGame class.
 * Manages Player game cards , player drawCard, shuffle.
 */
public class Player extends PlayerGame {
    public Player(String name) {
        super(name);
        pile = new ArrayList<>();
    }
    @Override
    public void drawCard(int cardsToDraw) {
        if (cardsToDraw < 0) {
            throw new IllegalArgumentException("Number of cards to draw must be non-negative.");
        }
        for (int i = 0; i < cardsToDraw; i++)
            this.getCardPile().add(UnoCard.createRandomUnoCard());
    }
    @Override
    public ArrayList<UnoCard> getCardPile() {
        return pile;
    }
    @Override
    public UnoCard shuffleCards(UnoCard card) {
        do{
            card =  UnoCard.createRandomUnoCard();
        }
        while (card.getColor() == UnoColor.NONE);
        return card;
    }
}

