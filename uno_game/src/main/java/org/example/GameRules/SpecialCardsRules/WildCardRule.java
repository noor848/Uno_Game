package org.example.GameRules.SpecialCardsRules;

import org.example.GameRules.CardDesignRules.CardDesign;
import org.example.GameRules.CardDesignRules.CardDesignRule;
import org.example.GameRules.SpecialCardsRules.SpecialCardRule;
import org.example.game.UnoGame;
import org.example.utils.SpecialCard;
import org.example.utils.UnoColor;

import java.util.Scanner;

public class WildCardRule implements SpecialCardRule {
    private WildCardRule(){}

    /**
     * Factory method to create an instance of SpecialCardRule.
     * @return An instance of WildCardRule.
     */
    public static SpecialCardRule createWildCardRule() {
        return new WildCardRule();
    }
    @Override
    public void applyRule(UnoGame unoGame, int playerIndex, int topCardNumber) {
        int currentPlayerIndex = playerIndex;
        int indexPlayer = unoGame.getNextPlayerIndex(playerIndex);
        unoGame.setPlayerIndex(indexPlayer);
        switch (topCardNumber) {
            case SpecialCard.WILD:
            case SpecialCard.WILD_DRAW_FOUR:
                UnoColor topCardColor = getChosenWildCardColor();
                unoGame.setTopCardColor(topCardColor);
                System.out.println("You chose " + unoGame.getTopCardColor());
                if (topCardNumber == SpecialCard.WILD_DRAW_FOUR) {
                    System.out.println("Drawing 4 cards " + unoGame.getPlayersList().get(unoGame.getPlayerIndex()).getName() + " ...");
                    unoGame.getPlayersList().get(unoGame.getPlayerIndex()).drawCard(4);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + topCardNumber);
        }
        unoGame.setPlayerIndex(currentPlayerIndex);
    }
    private UnoColor getChosenWildCardColor() {
        Scanner input = new Scanner(System.in);
        String inputColor;
        do {
            System.out.print("Enter the color you want (Red/Green/Blue/Yellow): ");
            inputColor = input.next().toLowerCase();
        } while (!isValidColor(inputColor));

        switch (inputColor) {
            case "red":
                return UnoColor.RED;
            case "green":
                return UnoColor.GREEN;
            case "blue":
                return UnoColor.BLUE;
            case "yellow":
                return UnoColor.YELLOW;
            default:
                throw new IllegalArgumentException("Invalid color string: " + inputColor);
        }
    }
    private boolean isValidColor(String color) {
        switch (color) {
            case "red":
                return true;
            case "green":
                return true;
            case "blue":
                return true;
            case "yellow":
                return true;
            default:
                System.out.println("Invalid color. Please enter Red, Green, Blue, or Yellow.");
                return false;
        }}

}
