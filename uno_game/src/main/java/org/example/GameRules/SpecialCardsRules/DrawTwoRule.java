package org.example.GameRules.SpecialCardsRules;

import org.example.game.UnoGame;

public class DrawTwoRule implements SpecialCardRule {

    private DrawTwoRule(){}

    /**
     * Factory method to create an instance of SpecialCardRule.
     * @return An instance of DrawTwoRule.
     */
    public static SpecialCardRule createDrawTwoRule() {
        return new DrawTwoRule();
    }
    @Override
    public void applyRule(UnoGame unoGame, int playerIndex, int topCardNumber) {
        int currentPlayerIndex = playerIndex;
        int indexPlayer = unoGame.getNextPlayerIndex(playerIndex);
        unoGame.setPlayerIndex(indexPlayer);
        System.out.println("Drawing 2 cards for " + unoGame.getPlayersList().get(unoGame.getPlayerIndex()).getName() + " ...");
        unoGame.getPlayersList().get(playerIndex).drawCard(2);
        unoGame.setPlayerIndex(currentPlayerIndex);

    }
}
