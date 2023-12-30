package org.example.GameRules.SpecialCardsRules;

import org.example.game.UnoGame;

public interface SpecialCardRule {
    void applyRule(UnoGame unoGame, int playerIndex, int topCardNumber);
}
