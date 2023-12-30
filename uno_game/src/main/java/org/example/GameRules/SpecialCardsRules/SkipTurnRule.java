package org.example.GameRules.SpecialCardsRules;

import org.example.game.UnoGame;

public class SkipTurnRule implements SpecialCardRule {
    private SkipTurnRule(){}

    /**
     * Factory method to create an instance of SpecialCardRule.
     * @return An instance of SkipTurnRule.
     */
    public static SpecialCardRule createSkipTurnRule() {
        return new SkipTurnRule();
    }
    @Override
    public void applyRule(UnoGame unoGame, int playerIndex, int topCardNumber) {
        int indexPlayer = unoGame.getNextPlayerIndex(playerIndex);
        unoGame.setPlayerIndex(indexPlayer);
    }
}
