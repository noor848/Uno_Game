package org.example.GameRules.SpecialCardsRules;

import org.example.game.UnoGame;

public class ReverseDirectionRule implements SpecialCardRule {

    private ReverseDirectionRule(){}

    /**
     * Factory method to create an instance of SpecialCardRule.
     * @return An instance of ReverseDirectionRule.
     */
    public static SpecialCardRule createReverseDirectionRule() {
        return new ReverseDirectionRule();
    }
    @Override
    public void applyRule(UnoGame unoGame, int playerIndex, int topCardNumber) {
        unoGame.setReverseDirection(!unoGame.isReverseDirection());
        int indexPlayer = unoGame.getNextPlayerIndex(playerIndex);
        unoGame.setPlayerIndex(indexPlayer);
    }
}
