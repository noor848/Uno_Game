package org.example.card;

import org.example.utils.UnoColor;

public abstract class  UnoCardGame {
    private UnoColor color;
    private int number;
    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public UnoColor getColor() {
        return color;
    }
    public void setColor(UnoColor colorName) {
        this.color = colorName;
    }
    public abstract String getCardDesign();
    public abstract boolean isValidCard(UnoCard card, UnoColor color);
}
