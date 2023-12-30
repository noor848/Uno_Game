package org.example.GameRules.CardDesignRules;

import org.example.card.UnoCard;
import org.example.utils.Color;
import org.example.utils.SpecialCard;
import org.example.utils.UnoColor;

import java.util.Objects;
import java.util.Random;

public class CardDesign implements CardDesignRule {
    private static final int NUMBER_OF_COLORS = 4;

    private CardDesign(){}

    /**
     * Factory method to create an instance of CardDesignRule.
     * @return An instance of CardDesignRule.
     */
    public static CardDesignRule designCard() {
        return new CardDesign();
    }
    @Override
    public void setMainColorCard(UnoCard playedCard) {
        Random rand = new Random();
        int number = rand.nextInt(NUMBER_OF_COLORS);
        if (number == Color.RED){
            playedCard.setColor(UnoColor.RED);
        } else if (number == Color.GREEN){
            playedCard.setColor(UnoColor.GREEN);
        }
        else if (number == Color.BLUE){
            playedCard.setColor(UnoColor.BLUE);
        }
        else if (number == Color.YELLOW){
            playedCard.setColor(UnoColor.YELLOW);
        }
    }
    public void setWildColorCard(UnoCard playedCard) {
        if (playedCard.getNumber() == SpecialCard.WILD || playedCard.getNumber() == SpecialCard.WILD_DRAW_FOUR) {
            playedCard.setColor(UnoColor.NONE);
        }
    }
    @Override
    public boolean isValidCard(UnoCard card, UnoColor color,UnoCard currentCard) {
        if (UnoColor.NONE.equals(card.getColor())){
            if (color.equals(currentCard.getColor()))return true;
        }
        if (Objects.equals(currentCard.getColor(), card.getColor())) return true;
        else if (currentCard.getNumber() == card.getNumber()) return true;
        else return UnoColor.NONE.equals(currentCard.getColor()); // wild card
    }
    @Override
    public String getCardDesign(UnoCard card) {
        StringBuilder type = new StringBuilder();
        if (!UnoColor.NONE.equals(card.getColor())) {
            type.append(card.getColor()).append("-");
        }
        if (card.getNumber() == SpecialCard.SKIP) {
            type.append("Skip");
        } else if (card.getNumber() == SpecialCard.REVERSE) {
            type.append("Reverse");
        } else if (card.getNumber() == SpecialCard.DRAW_2) {
            type.append("Draw 2");
        } else if (card.getNumber() == SpecialCard.WILD) {
            type.append("Wild");
        } else if (card.getNumber() == SpecialCard.WILD_DRAW_FOUR) {
            type.append("Wild Draw 4");
        } else {
            type.append(card.getNumber());
        }
        return type.toString();

    }
}
