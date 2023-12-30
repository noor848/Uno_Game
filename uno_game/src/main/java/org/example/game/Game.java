package org.example.game;

public abstract class Game {
    public final void play() {
        playGame();
        displayResults();
    }
    protected abstract void initializeGame();
    protected abstract void playGame();
    protected abstract void displayResults();
}
