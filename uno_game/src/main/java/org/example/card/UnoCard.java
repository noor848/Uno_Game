package org.example.card;
import org.example.GameRules.CardDesignRules.CardDesignRule;
import org.example.GameRules.CardDesignRules.CardDesign;
import org.example.utils.UnoColor;

import java.util.Objects;
import java.util.Random;
/**
 * Represents an Uno card in the game.
 * Each Uno card has a number, color, and follows specific rules for gameplay.
 */
public class UnoCard extends UnoCardGame{
    private final CardDesignRule cardRule;
    private static final int MAX_CARD_NUMBER = 15;
    private static final Random rand = new Random();

    private UnoCard(){
        int randNum = rand.nextInt(MAX_CARD_NUMBER);
        cardRule =  CardDesign.designCard();
        this.setNumber(randNum); // 108 cards in a standard Uno deck. Can be reduced to 15 (disregarding colors)
        cardRule.setMainColorCard(this);
        cardRule.setWildColorCard(this);
    }

    /**
     * Factory method to create an instance of UnoCard with a random number.
     * @return An instance of UnoCard.
     */
    public static UnoCard createRandomUnoCard() {
        return new UnoCard();
    }
    @Override
     public String getCardDesign() {
        return cardRule.getCardDesign(this);
    }

    /**
     * Checks if the card is valid to play based on the provided color.
     *
     * @param card  The Uno card to check for validity.
     * @param color The Uno color to match against.
     * @return True if the card is valid for play; otherwise, false.
     */
    @Override
     public boolean isValidCard(UnoCard card ,UnoColor color) {
        return  cardRule.isValidCard(card, color, this);
    }

}

