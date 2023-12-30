package org.example;

import org.example.game.Game;
import org.example.game.UnoGame;

public class GameDriver {
    public static void main(String[] args) {
        Game unoGame =  UnoGame.createUnoGame();
        unoGame.play();
    }
}