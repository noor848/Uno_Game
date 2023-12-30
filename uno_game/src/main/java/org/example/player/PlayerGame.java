package org.example.player;

import org.example.card.UnoCard;

import java.util.ArrayList;

public abstract class PlayerGame {
   private String name;
   private int numberOfPlayer;
   protected ArrayList<UnoCard> pile;
   public PlayerGame(String name) {
      this.name = name;
      this.pile = new ArrayList<>();
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
    public abstract void drawCard(int card);
    public abstract ArrayList<UnoCard> getCardPile();
    public abstract UnoCard shuffleCards(UnoCard card);
}