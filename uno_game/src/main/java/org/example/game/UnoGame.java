package org.example.game;

import org.example.GameRules.SpecialCardsRules.*;
import org.example.utils.SpecialCard;
import org.example.utils.UnoColor;
import org.example.card.UnoCard;
import org.example.player.Player;
import org.example.player.PlayerGame;

import java.util.*;
/**
 * Represents the Uno game, a subclass of the general Game class.
 * Manages game mechanics, player turns, and special card rules.
 */
public class UnoGame extends Game{

    private UnoCard topCard;
    private int userChoice;
    private UnoColor topCardColor;
    private List<PlayerGame> players;
    private int numberOfPlayers;
    private int playerIndex;
    private boolean reverseDirection = false;
    private List<SpecialCardRule> cardRules;
    private final Scanner input = new Scanner(System.in);

    private  UnoGame(){}

    /**
     * Factory method to create an instance of UnoGame.
     * @return An instance of UnoGame.
     */
    public static UnoGame createUnoGame() {
        UnoGame unoGame = new UnoGame();
        unoGame.initializeGame();
        return unoGame;
    }
    @Override
    protected void initializeGame() {
        players = new ArrayList<>();
        cardRules = new ArrayList<>();
        System.out.println("\n* you gonna win if you have a 2 cards or less");
        setNumberOfPlayer();
        createPlayers();
        PlayerGame player1 = players.get(0);
        drawTopCard(player1);
        /// adding rules to use
        cardRules.add(ReverseDirectionRule.createReverseDirectionRule());
        cardRules.add(SkipTurnRule.createSkipTurnRule());
        cardRules.add(DrawTwoRule.createDrawTwoRule());
        cardRules.add(WildCardRule.createWildCardRule());
    }
    @Override
    protected void playGame() {

        int playerNumberOfDraws = 0;
        while (getTheWinner() == null && players.size() > 1) {
            numberOfPlayers = players.size();
            StringBuilder result = new StringBuilder("\nTop Card ----> ").append(topCard.getCardDesign()).append(", Color is: ").append(topCardColor).append("\n");
            System.out.println(result.toString());
            PlayerGame currentPlayer = players.get(playerIndex % numberOfPlayers);
            result = new StringBuilder("\nTop Card ----> ").append(currentPlayer.getName()).append(", Your turn! Your choices:");
            System.out.println(result.toString());
            getPlayerCardsChoices(currentPlayer);
            userChoice = getPlayerChoiceIndex();

            if (userChoice == currentPlayer.getCardPile().size()) {
                handleDrawOption(playerNumberOfDraws, currentPlayer);
                playerNumberOfDraws++;
            } else if (userChoice == currentPlayer.getCardPile().size() + 1) {
                handleSkipOption();
                playerNumberOfDraws = 0;
            } else if (userChoice == currentPlayer.getCardPile().size() + 2)
            {
                handleQuitOption(currentPlayer);
            }
            else if (isValidCardChoice(currentPlayer)) {
                playerNumberOfDraws = 0;
                handleValidCardOption(currentPlayer);
            } else {
                System.err.println("BAD CHOICE, PLEASE TRY AGAIN!");
            }
        }
    }
    private void handleValidCardOption(PlayerGame currentPlayer) {
        topCard = currentPlayer.getCardPile().get(userChoice);
        currentPlayer.getCardPile().remove(userChoice);
        topCardColor = topCard.getColor();
        if (topCard.getNumber() == SpecialCard.REVERSE) {
            handleReverseOption();
        }
        else if (topCard.getNumber() == SpecialCard.SKIP){ // skipp
            handleSkipTurnOption();
        }
        else if(topCard.getNumber() == SpecialCard.DRAW_2) {
            handleDrawTwoOption();
        }
        else if(topCard.getNumber() == SpecialCard.WILD || topCard.getNumber() == SpecialCard.WILD_DRAW_FOUR) {
            handleWildCardOption();
        }
        else {
            playerIndex = getNextPlayerIndex(playerIndex);
        }
    }
    private boolean isValidCardChoice(PlayerGame currentPlayer) {
        return currentPlayer.getCardPile().get(userChoice).isValidCard(topCard, topCardColor);
    }
    private void handleQuitOption(PlayerGame currentPlayer) {
        players.remove(currentPlayer);
    }
    private void handleSkipOption() {
        playerIndex = getNextPlayerIndex(playerIndex);
    }
    private void handleDrawOption(int playerNumberOfDraws, PlayerGame currentPlayer) {
        if (playerNumberOfDraws < 1) currentPlayer.drawCard(1);
        else System.err.println("\nYou have one chance to draw a card in each turn");
    }
    private void handleWildCardOption() {
        cardRules.get(3).applyRule(this,playerIndex, topCard.getNumber());
    }
    private void handleDrawTwoOption() {
        cardRules.get(2).applyRule(this,playerIndex, topCard.getNumber());
    }
    private void handleReverseOption() {
        cardRules.get(0).applyRule(this,playerIndex, topCard.getNumber());
    }
    private void handleSkipTurnOption() {
        cardRules.get(1).applyRule(this,playerIndex, topCard.getNumber());
        cardRules.get(1).applyRule(this,playerIndex, topCard.getNumber());
    }
    @Override
    protected void displayResults() {
        PlayerGame winner = getTheWinner();
        StringBuilder result = new StringBuilder("\n\n").append(winner.getName()).append(" IS THE WINNER !");
        if (winner != null){
            System.out.println(result.toString());
        }
    }
    public int getNextPlayerIndex(int currentPlayerIndex) {
        if (reverseDirection) {
            return (currentPlayerIndex - 1 + numberOfPlayers) % numberOfPlayers;
        } else {
            return (currentPlayerIndex + 1) % numberOfPlayers;
        }
    }
    private PlayerGame getTheWinner() {
        int numberOfCards = 2 ;
        if (players.size() == 1)
            return  players.get(0);
        for (int i = 0; i<players.size();i++){
         if (players.get(i).getCardPile().size() == numberOfCards)
             return  players.get(i);
        }
        return null;
    }
    private void drawTopCard(PlayerGame player) {
        topCard =  UnoCard.createRandomUnoCard();
        topCard = player.shuffleCards(topCard);
        topCardColor = topCard.getColor();
        topCard.getCardDesign();
    }
    private void setNumberOfPlayer() {
        while (true) {
            System.out.print("Enter the number of players: ");

            if (input.hasNextInt()) {
                numberOfPlayers = input.nextInt();
                if (numberOfPlayers > 1) {
                    break; // Valid input, exit the loop
                } else {
                        System.out.println("Please enter number of players greater than 1");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                input.next(); // Consume invalid input to avoid an infinite loop
            }
        }
    }
    private void createPlayers() {

        for (int i = 0; i < this.numberOfPlayers ;i++){
            System.out.println("Name of Player");
            String name = input.next();
            PlayerGame player = new Player(name);
            player.drawCard(7);
            players.add(player);
        }
    }
    private int getPlayerChoiceIndex() {
        Scanner input;
        do {
            System.out.print("Enter your Choice :");
            input = new Scanner(System.in);
        }
       while (!input.hasNextInt());
        userChoice = input.nextInt() - 1;
        return  userChoice;
    }
    private void getPlayerCardsChoices(PlayerGame player) {

        for (int i = 0; i < player.getCardPile().size(); i++){
            System.out.println(i + 1 +"."+(player.getCardPile().get(i)).getCardDesign());
        }
        System.out.println(player.getCardPile().size() + 1 + "." + "Draw card");
        System.out.println(player.getCardPile().size() + 2 + "." + "I have no Card");
        System.out.println(player.getCardPile().size() + 3 + "." + "Quit");
    }
    public int getPlayerIndex() {
        return playerIndex;
    }
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
    public  List<PlayerGame> getPlayersList(){
        return players;
    }
    public UnoColor getTopCardColor() {
        return topCardColor;
    }
    public void setTopCardColor(UnoColor topCardColor) {
        this.topCardColor = topCardColor;
    }
    public void setReverseDirection(boolean reverseDirection) {
        this.reverseDirection = reverseDirection;
    }
    public boolean isReverseDirection() {
        return reverseDirection;
    }

}
